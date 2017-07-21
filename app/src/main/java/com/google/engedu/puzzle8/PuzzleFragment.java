package com.google.engedu.puzzle8;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 *
 */
public class PuzzleFragment extends Fragment {
    //public static final String ARG_PAGE  = "arg_page";
    private PuzzleBoardView boardView;



    public PuzzleFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_puzzle, container, false);
        //RelativeLayout frameLayout = (RelativeLayout)getActivity().findViewById(R.id.fragment_relative_layout);
        LinearLayout puzzleBoardLayout = (LinearLayout) view.findViewById(R.id.puzzle_board_view_layout);

        boardView = new PuzzleBoardView(getActivity());

        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        linearLayoutParams.gravity = Gravity.CENTER;
        int fabMargin = (int)getResources().getDimension(R.dimen.fab_margin);
        linearLayoutParams.setMargins(fabMargin,fabMargin,fabMargin,fabMargin);

        LinearLayout.LayoutParams puzzleBoardLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        puzzleBoardLayoutParams.gravity = Gravity.CENTER;
        boardView.setLayoutParams(puzzleBoardLayoutParams);



        FloatingActionButton floatingActionButton = new FloatingActionButton(getActivity());//(FloatingActionButton)getActivity().findViewById(R.id.puzzle_fab);
        floatingActionButton.setLayoutParams(linearLayoutParams);
        assert (floatingActionButton != null);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(),"testing",Toast.LENGTH_SHORT).show();
            }
        });





        puzzleBoardLayout.addView(boardView);
        //cardView.addView(boardView);

        LinearLayout fabCardView = (LinearLayout) view.findViewById(R.id.fragment_linear_layout);

        fabCardView.addView(floatingActionButton);



        return view;

    }

    public void passBitMaptoBoardView(Bitmap bitmapImage){
        assert(boardView != null);
        assert(bitmapImage != null);
        boardView.initialize(bitmapImage);
    }




}
