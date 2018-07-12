package com.example.administrator.ForkliftHMI;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.animation.SpringForce.DAMPING_RATIO_NO_BOUNCY;
import static android.support.animation.SpringForce.STIFFNESS_VERY_LOW;
import static com.example.administrator.ForkliftHMI.Commands.RPRV;

public class Map extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View view;


    private ImageView backgroundImage;
    private SeekBar scaleSeekBar;
    private ImageView pallets[] = new ImageView[6];
    private ImageView forklift, inicio;

    public Map() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Set layout
        view = inflater.inflate(R.layout.fragment_map, container, false);

        scaleSeekBar = view.findViewById(R.id.map_seekBar_scale);
        backgroundImage = view.findViewById(R.id.map_imageView_background);

        scaleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) {
                    backgroundImage.setImageResource(R.mipmap.hgihdens);
                }else if(progress == 1) {
                    backgroundImage.setImageResource(R.mipmap.meddens);
                }else if(progress == 2) {
                    backgroundImage.setImageResource(R.mipmap.lowdens);
                }
            }
        });

        forklift = view.findViewById(R.id.forklift);
        inicio = view.findViewById(R.id.start);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFakeRoute();
            }
        });

        //STUFF WITH PALLETS
        pallets[0] = view.findViewById(R.id.pallet1);
        pallets[1] = view.findViewById(R.id.pallet2);
        pallets[2] = view.findViewById(R.id.pallet3);
        pallets[3] = view.findViewById(R.id.pallet4);
        pallets[4] = view.findViewById(R.id.pallet5);
        pallets[5] = view.findViewById(R.id.pallet6);

        for(int i=0; i<5; i++) {
            pallets[i].setVisibility(View.VISIBLE);
        }
        //pallets[4].setVisibility(View.INVISIBLE);



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLineFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onSendCommand(String command);
    }


    void doFakeRoute() {
        float finalpos_X, finalpos_Y;

        //move forklift to pallet
        finalpos_X = 70;
        finalpos_Y = 164;

        ObjectAnimator editorLayoutAnimation_x = ObjectAnimator.ofFloat(forklift, "x", finalpos_X);
        ObjectAnimator editorLayoutAnimation_y = ObjectAnimator.ofFloat(forklift, "y", finalpos_Y);
        AnimatorSet animSetline = new AnimatorSet();
        animSetline.playTogether(editorLayoutAnimation_x, editorLayoutAnimation_y);
        animSetline.setDuration(10000);
        animSetline.start();


        //move back
        finalpos_X = 1500;
        finalpos_Y = 164;
        editorLayoutAnimation_x = ObjectAnimator.ofFloat(forklift, "x", finalpos_X);
        editorLayoutAnimation_y = ObjectAnimator.ofFloat(forklift, "y", finalpos_Y);
        animSetline = new AnimatorSet();
        animSetline.playTogether(editorLayoutAnimation_x, editorLayoutAnimation_y);

        animSetline.setDuration(10000);
        animSetline.setStartDelay(13000);
        animSetline.start();

        //with the pallet, obv
        finalpos_X = 1500;
        finalpos_Y = 164;
        editorLayoutAnimation_x = ObjectAnimator.ofFloat(pallets[0], "x", finalpos_X);
        editorLayoutAnimation_y = ObjectAnimator.ofFloat(pallets[0], "y", finalpos_Y);
        animSetline = new AnimatorSet();
        animSetline.playTogether(editorLayoutAnimation_x, editorLayoutAnimation_y);

        animSetline.setDuration(10000);
        animSetline.setStartDelay(13000);
        animSetline.start();


    }

    void whenLeavingFragment() {

    }

    void whenEnteringFragment() {

    }
}
