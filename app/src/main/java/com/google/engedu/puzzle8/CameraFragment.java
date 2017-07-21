package com.google.engedu.puzzle8;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class CameraFragment extends Fragment {
    private static final int REQ_CODE_TAKE_PICTURE = 9021;



    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        LinearLayout.LayoutParams cameraFabLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cameraFabLayoutParams.gravity = Gravity.CENTER;

        int fabMargin = (int)getResources().getDimension(R.dimen.fab_margin);

        cameraFabLayoutParams.setMargins(fabMargin,0,fabMargin,fabMargin);

        FloatingActionButton floatingActionButton = new FloatingActionButton(getActivity());//(FloatingActionButton)getActivity().findViewById(R.id.puzzle_fab);
        floatingActionButton.setLayoutParams(cameraFabLayoutParams);
        assert (floatingActionButton != null);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                takePictureWrapper();
                //Toast.makeText(getActivity(),"testing",Toast.LENGTH_SHORT).show();
            }

            private void takePictureWrapper() {
                int hasTakePicturePermission = getActivity().checkSelfPermission(Manifest.permission.CAMERA);
                if(hasTakePicturePermission != PackageManager.PERMISSION_GRANTED){
                    getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},
                            REQ_CODE_TAKE_PICTURE);
                    return;
                }
                Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(pictureIntent,REQ_CODE_TAKE_PICTURE);

            }
        });

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.camera_fab_layout);
        linearLayout.addView(floatingActionButton);




        return view;
    }
}
