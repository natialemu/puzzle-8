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

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class PuzzleActivity extends AppCompatActivity {
    private static final int REQ_CODE_TAKE_PICTURE = 9021;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap = null;
    private PuzzleBoardView boardView;
    private PuzzlePagerAdapter adapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        Toolbar gameToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(gameToolbar);

        adapter = new PuzzlePagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setUpViewPager(viewPager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);









        //cameraFragment = new CameraFragment();



        //viewPager.setAdapter(puzzlePagerAdapter);

        //tabLayout.setupWithViewPager(viewPager);

        //tabLayout.setTabsFromPagerAdapter(puzzlePagerAdapter);
        //tabLayout.setupWithViewPager(viewPager);
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.setTabsFromPagerAdapter(puzzlePagerAdapter);

        /*TabLayout.Tab mainGame = tabLayout.newTab();
        TabLayout.Tab takePicture = tabLayout.newTab();
        TabLayout.Tab help = tabLayout.newTab();

        mainGame.setText("Play");
        takePicture.setText("Camera");
        help.setText("Help");

        tabLayout.addTab(takePicture,0);
        tabLayout.addTab(mainGame,1);
        tabLayout.addTab(help,2);*/

        //tabLayout.setTabTextColors(ContextCompat.getColorStateList(this,R.color.tab_selector));
        //tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.primaryDark));


        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));








        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0){
                    //secondButton.setVisibility(View.INVISIBLE);
                    //new FloatingActionMenu.Builder(getParent()).addSubActionView(firstButton).build();
                    if(!cameraFab.hasOnClickListeners()) {
                        cameraFab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }else if(position == 1){
                    //secondButton.setVisibility(View.VISIBLE);
                    if(!puzzleFab.hasOnClickListeners()) {
                        puzzleFab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        // getSupportActionBar().hide();

        // This code programmatically adds the PuzzleBoardView to the UI.
        /*RelativeLayout container = (RelativeLayout) findViewById(R.id.puzzle_container);

        // Some setup of the view.

        boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(boardView);*/

    }

    private void constructFab(){
            }


    private void setUpViewPager(ViewPager viewPager){
        //PuzzlePagerAdapter adapter = new PuzzlePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment(),"Camera");
        adapter.addFragment(new PuzzleFragment(),"Play");
        adapter.addFragment(new HelpFragment(), "Help");
        viewPager.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puzzle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dispatchTakePictureIntent(View view) {
    }



    public void shuffleImage(View view) {
        boardView.shuffle();
    }

    public void solve(View view) {
      boardView.solve();

    }

    //will take a picture and add it to the cardView. the camera fragment will need to
    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent intent){
        if(requestCode == REQ_CODE_TAKE_PICTURE && resultcode == RESULT_OK){
            imageBitmap = (Bitmap) intent.getExtras().get("data");

            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(imageBitmap);

            CardView cameraCardView = (CardView) findViewById(R.id.camera_cardView);
            cameraCardView.addView(imageView);

            viewPager.setCurrentItem(1,true);

            PuzzleFragment puzzleFragment = (PuzzleFragment) adapter.getItem(1);

            //ImageView imageView1 = new ImageView(this);
            //imageView1.setImageResource(R.drawable.chicago);
            //imageView1.buildDrawingCache();
            puzzleFragment.passBitMaptoBoardView(imageBitmap);


            //pass the imageBitmap to the constructor of

        }
    }

}
