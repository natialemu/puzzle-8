package com.google.engedu.puzzle8;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 *
 */
public class PuzzleFragment extends Fragment {
    public static final String ARG_PAGE  = "arg_page";
    private PuzzleBoardView boardView;


    public PuzzleFragment() {
        // Required empty public constructor
    }

    public static PuzzleFragment getNewFragment(int pageNumber){
        PuzzleFragment puzzleFragment = new PuzzleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_PAGE, pageNumber);
        puzzleFragment.setArguments(arguments);
        return puzzleFragment;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle arguments = getArguments();
        int pageNumber = arguments.getInt(ARG_PAGE);

        TextView myText = new TextView(getActivity());
        if(pageNumber == 1){
            //RelativeLayout layout = (RelativeLayout) getActivity().findViewById(R.id.fragment_layout);

            LinearLayout layout = new LinearLayout(getActivity());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            boardView = new PuzzleBoardView(getActivity());
            // Some setup of the view.



            //boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            //myText.setText("I am inside of fragment " + pageNumber);
            //myText.setGravity(Gravity.CENTER);

            //layout.addView(myText);
            View view = getActivity().getLayoutInflater().inflate(R.layout.puzzle_buttons,null);






            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.tfe);
            imageView.setMaxHeight(100);
            imageView.setMaxWidth(100);

            CardView cardView= new CardView(getActivity());
            cardView.setElevation(60);
            cardView.addView(imageView);
            LinearLayout.LayoutParams linear_layout_params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(linear_layout_params1);

            Button solveButton = new Button(getActivity());
            LinearLayout.LayoutParams button_layout_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            solveButton.setLayoutParams(button_layout_params);






            layout.addView(cardView);

            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.puzzle_layout);
            layout.addView(view);
            LinearLayout.LayoutParams linear_layout_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linear_layout_params.gravity = Gravity.BOTTOM;
            linear_layout_params.topMargin = 30;
            view.setLayoutParams(linear_layout_params);
            linearLayout.setLayoutParams(linear_layout_params);

            //layout.addView(solveButton);
           // Button firstButton = (Button) getActivity().findViewById(R.id.shuffle_button);
            //Button secondButton = (Button) getActivity().findViewById(R.id.photo_button);

            //Button thirdButton = (Button) getActivity().findViewById(R.id.solve_button);




            return layout;
        }else{
            myText.setText("I am inside of fragment " + pageNumber);
            myText.setGravity(Gravity.CENTER);
            return myText;
        }
       // return inflater.inflate(R.layout.fragment_puzzle, container, false);

    }



}
