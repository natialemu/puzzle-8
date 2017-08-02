/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.puzzle8;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;


public class PuzzleBoard implements Comparable{

    private static final int NUM_TILES = 3;

    public int getSteps() {
        return steps;
    }

    private int steps;

    private static PuzzleBoard previousPuzzleBoard;


    public static int getNumTiles() {
        return NUM_TILES;
    }

    private static final int[][] NEIGHBOUR_COORDS = {
            { -1, 0 },

            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };

    public ArrayList<PuzzleTile> getTiles() {
        return tiles;
    }

    private ArrayList<PuzzleTile> tiles;

    PuzzleBoard(Bitmap imageBitmap, int parentWidth) {

        steps = 0;

        previousPuzzleBoard = null;
        //this takes the big bitMap
        //splits it
        //Bitmap[][] bitmapImages = new Bitmap[3][3];
        //x , y, x +width/3, y + height/3
        tiles = new ArrayList<>();

        int randX = (int) (Math.random()*PuzzleBoard.getNumTiles());
        int randY = (int) (Math.random()*PuzzleBoard.getNumTiles());

        assert (imageBitmap != null);
        int blockWidth = imageBitmap.getWidth()/PuzzleBoard.getNumTiles();
        int blockHeight = imageBitmap.getHeight()/PuzzleBoard.getNumTiles();
        for(int x = 0; x < PuzzleBoard.getNumTiles();x++){
            for(int y = 0; y < PuzzleBoard.getNumTiles();y++){
                Bitmap bitmapImages = Bitmap.createBitmap(imageBitmap,x*blockWidth,y*blockHeight,blockWidth,blockHeight);

                int newWidth = ((parentWidth)/imageBitmap.getWidth())*bitmapImages.getWidth();

                int newHeight = ((parentWidth)/imageBitmap.getHeight())*bitmapImages.getHeight();

                bitmapImages = Bitmap.createScaledBitmap(bitmapImages,newWidth,newHeight,false);

                PuzzleTile puzzleTile = new PuzzleTile(bitmapImages,y+(x*PuzzleBoard.getNumTiles()));
                tiles.add(puzzleTile);


            }
        }
        tiles.set(tiles.size()-1,null);


    }

    PuzzleBoard(PuzzleBoard otherBoard) {
        tiles = (ArrayList<PuzzleTile>) otherBoard.tiles.clone();
        previousPuzzleBoard = otherBoard;
        steps = otherBoard.getSteps() + 1;
    }

    public void reset() {
        // Nothing for now but you may have things to reset once you implement the solver.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return tiles.equals(((PuzzleBoard) o).tiles);
    }

    public void draw(Canvas canvas) {
        if (tiles == null) {
            return;
        }
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                tile.draw(canvas, i % NUM_TILES, i / NUM_TILES);
            }
        }
    }

    public boolean click(float x, float y) {
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                if (tile.isClicked(x, y, i % NUM_TILES, i / NUM_TILES)) {
                    return tryMoving(i % NUM_TILES, i / NUM_TILES);
                }
            }
        }
        return false;
    }

    private boolean tryMoving(int tileX, int tileY) {
        for (int[] delta : NEIGHBOUR_COORDS) {
            int nullX = tileX + delta[0];
            int nullY = tileY + delta[1];
            if (nullX >= 0 && nullX < NUM_TILES && nullY >= 0 && nullY < NUM_TILES &&
                    tiles.get(XYtoIndex(nullX, nullY)) == null) {
                swapTiles(XYtoIndex(nullX, nullY), XYtoIndex(tileX, tileY));
                return true;
            }

        }
        return false;
    }

    public boolean resolved() {
        for (int i = 0; i < NUM_TILES * NUM_TILES - 1; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile == null || tile.getNumber() != i)
                return false;
        }
        return true;
    }

    private int XYtoIndex(int x, int y) {
        return x + y * NUM_TILES;
    }

    protected void swapTiles(int i, int j) {
        PuzzleTile temp = tiles.get(i);
        tiles.set(i, tiles.get(j));
        tiles.set(j, temp);
    }

    public ArrayList<PuzzleBoard> neighbours() {

        ArrayList<PuzzleBoard> puzzleBoards = new ArrayList<>();

        int nullIndex = getNullIndex();

        int nullIndexX = nullIndex/NUM_TILES;

        int nullIndexY = nullIndex%NUM_TILES;

        if (isValid(nullIndex + 1, nullIndexY)) {
            PuzzleBoard copyBoard = new PuzzleBoard(this);
            int neighborIndex = XYtoIndex(nullIndexX+1,nullIndexY);
            int currentIndex = XYtoIndex(nullIndexX,nullIndexY);

            copyBoard.swapTiles(neighborIndex,currentIndex);
            puzzleBoards.add(copyBoard);
            //create a copy puzzleboard
            //convert nullIndex + 1, nullIndexY to Index
            //then swap the values at converted nullIndex + 1, y and just nullIndexX and Y
            //add the copy buzzleboard into the puzzleboards list
        }

        if (isValid(nullIndex, nullIndexY+1)) {
            PuzzleBoard copyBoard = new PuzzleBoard(this);
            int neighborIndex = XYtoIndex(nullIndexX,nullIndexY+1);
            int currentIndex = XYtoIndex(nullIndexX,nullIndexY);

            copyBoard.swapTiles(neighborIndex,currentIndex);
            puzzleBoards.add(copyBoard);
            //create a copy puzzleboard
            //convert nullIndex + 1, nullIndexY to Index
            //then swap the values at converted nullIndex + 1, y and just nullIndexX and Y
            //add the copy buzzleboard into the puzzleboards list
        }

        if (isValid(nullIndex - 1, nullIndexY)) {
            PuzzleBoard copyBoard = new PuzzleBoard(this);
            int neighborIndex = XYtoIndex(nullIndexX-1,nullIndexY);
            int currentIndex = XYtoIndex(nullIndexX,nullIndexY);

            copyBoard.swapTiles(neighborIndex,currentIndex);
            puzzleBoards.add(copyBoard);
            //create a copy puzzleboard
            //convert nullIndex + 1, nullIndexY to Index
            //then swap the values at converted nullIndex + 1, y and just nullIndexX and Y
            //add the copy buzzleboard into the puzzleboards list
        }

        if (isValid(nullIndex, nullIndexY-1)) {
            PuzzleBoard copyBoard = new PuzzleBoard(this);
            int neighborIndex = XYtoIndex(nullIndexX,nullIndexY-1);
            int currentIndex = XYtoIndex(nullIndexX,nullIndexY);

            copyBoard.swapTiles(neighborIndex,currentIndex);
            puzzleBoards.add(copyBoard);
            //create a copy puzzleboard
            //convert nullIndex + 1, nullIndexY to Index
            //then swap the values at converted nullIndex + 1, y and just nullIndexX and Y
            //add the copy buzzleboard into the puzzleboards list
        }




        return puzzleBoards;
    }

    private int getNullIndex() {

        for(int i = 0; i < tiles.size(); i++){
            if(tiles.get(i) == null){
                return i;
            }
        }

        return -1;
    }

    private boolean isValid(int nullIndexX, int nullIndexY) {

        return ((nullIndexX < NUM_TILES && nullIndexX >= 0) && ( nullIndexY < NUM_TILES && nullIndexY >= 0));

    }

    public int priority() {

        int cummulativePriority = 0;

        for(int i = 0; i < tiles.size(); i++)
        {

            int indexInTarget = tiles.get(i).getNumber();
            int targetX = indexInTarget/NUM_TILES;
            int targetY = indexInTarget%NUM_TILES;

            int currentX = i /NUM_TILES;
            int currentY = i%NUM_TILES;

            int tilePriority = Math.abs(targetY - currentY) + (targetX - currentX);
            cummulativePriority += tilePriority;
        }

        cummulativePriority+= steps;

        return cummulativePriority;

        //initialize a cummulativePriority
        //for each tile in the board:
        //    findIndexInTarget
        //    convert both indeces to x and y (currentX, currentY) and (targetX, targetY)
        //    to find the priorty, Math.abs(targetY - currentY) + Math.abs(targetX - currentX)
        //    add to the cummulitave priority
        // add the number of steps to the cummulativePriority and return it.



    }


    @Override
    public int compareTo(Object o) {
        PuzzleBoard board = (PuzzleBoard)o;
        if(priority() < board.priority()){
            return 1;
        } else if (priority() == board.priority()){
            return 0;
        }else{
            return -1;
        }
    }
}
