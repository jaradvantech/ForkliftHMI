package com.example.administrator.ForkliftHMI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.administrator.ForkliftHMI.Commands.ALGC;


public class Algorithm extends Fragment {
    private AlertDialog.Builder dialogBuilder;
    private final int MANIPULATORS = 5;
    private boolean unsavedChanges = false;
    private OnFragmentInteractionListener mFragmentInteraction;
    private int manipulatorModes[]  = new int[MANIPULATORS];
    private ImageView imageViews[] = new ImageView[MANIPULATORS];
    private TextView textViews[] = new TextView[MANIPULATORS];
    private TextView demoBrick;
    private ListView algorithm_listView_colours;
    private ListView algorithm_listView_grades;
    private int currentColor = 1;
    private int currentGrade = 1;

    private final int INPUT = 0;
    private final int INOUT = 1;
    private final int OUTPUT = 2;
    private final int DISABLED = 3;

    public Algorithm() {
    }

    public void onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO manipulator number independent
        View view = inflater.inflate(R.layout.fragment_algorithm, container, false);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mFragmentInteraction = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentInteraction = null;
    }

    public void whenEnteringFragment() {
    }

    public void whenLeavingFragment() {

    }

    public void onLostConnection() {

    }

    public void onEstablishedConnection() {

    }

    public interface OnFragmentInteractionListener {
        void onSendCommand( String command );
    }
}
