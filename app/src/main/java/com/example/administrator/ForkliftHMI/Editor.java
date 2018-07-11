package com.example.administrator.ForkliftHMI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.StackPageTransformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.ForkliftHMI.BrickManager.getRaw;


public class Editor extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<Fragment> mViewPagerFragments;

    final ToggleButton PalletButton[]=new ToggleButton[10+1];
    private View view;


    public Editor() {
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_editor, container, false);

        return view;
    }

    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onSendCommand( String command );
    }


    public void onTcpReply(String mMessage) {
        //This might be void under certain circumstances RBS: should be fixed, then
        if((mMessage.length() != 0) && (view != null) && (PalletButton != null)) {

            int index = 1;
            try {
                JSONObject JSONparser = new JSONObject(mMessage);
                index = JSONparser.getInt("palletNumber");
            } catch (Exception jsonExc) {
                Log.e("JSON Exception", jsonExc.getMessage());
            }
            //Set Button
            for (int j = 1; j < PalletButton.length; j++) {
                //uncheck buttons
                PalletButton[j].setChecked(false);
            }
            PalletButton[index].setChecked(true);
        }
    }



    public void whenEnteringFragment() {

    }

    public void whenLeavingFragment() {

    }
}
