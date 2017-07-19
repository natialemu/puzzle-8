package com.google.engedu.puzzle8;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Nathnael on 7/12/2017.
 */

public class PuzzlePagerAdapter extends FragmentStatePagerAdapter {



    public PuzzlePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PuzzleFragment puzzleFragment = PuzzleFragment.getNewFragment(position);
        return puzzleFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == 0){
            return "CAMERA";
        }else if(position == 1){
            return "PLAY";
        }else if(position == 2){
            return "HELP";
        }
        return super.getPageTitle(position);

    }

}
