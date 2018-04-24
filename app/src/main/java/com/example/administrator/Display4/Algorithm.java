package com.example.administrator.Display4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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


public class Algorithm extends Fragment {
    private AlertDialog.Builder dialogBuilder;
    private final int MANIPULATORS = 5;

    private SharedPreferences algorithmSavedPreferences;
    private SharedPreferences.Editor sharedPrefEditor;
    private OnFragmentInteractionListener mFragmentInteraction;
    private int manipulatorModes[]  = new int[MANIPULATORS + 1];
    private ImageView imageViews[] = new ImageView[MANIPULATORS + 1];
    private TextView textViews[] = new TextView[MANIPULATORS + 1];
    private TextView demoBrick;
    private ListView algorithm_listView_colours;
    private ListView algorithm_listView_grades;

    public Algorithm() {
    }

    public void onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_algorithm, container, false);
        imageViews[1] = (ImageView) view.findViewById(R.id.algorithm_imageView_arm1mode);
        imageViews[2] = (ImageView) view.findViewById(R.id.algorithm_imageView_arm2mode);
        imageViews[3] = (ImageView) view.findViewById(R.id.algorithm_imageView_arm3mode);
        imageViews[4] = (ImageView) view.findViewById(R.id.algorithm_imageView_arm4mode);
        imageViews[5] = (ImageView) view.findViewById(R.id.algorithm_imageView_arm5mode);
        textViews[1] = (TextView) view.findViewById(R.id.algorithm_textView_manipulator1);
        textViews[2] = (TextView) view.findViewById(R.id.algorithm_textView_manipulator2);
        textViews[3] = (TextView) view.findViewById(R.id.algorithm_textView_manipulator3);
        textViews[4] = (TextView) view.findViewById(R.id.algorithm_textView_manipulator4);
        textViews[5] = (TextView) view.findViewById(R.id.algorithm_textView_manipulator5);
        demoBrick = (TextView) view.findViewById(R.id.algorithm_demobrick);
        Button saveButton = (Button) view.findViewById(R.id.algorithm_button_save);

        updateDisplay();

        //SAVE DIALOG
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(getString(R.string.Warning));
        dialogBuilder.setMessage(getString(R.string.Saveconfigurationtomachine));
        //menu buttons listener
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case DialogInterface.BUTTON_POSITIVE:
                        saveAlgorithmData();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        dialogBuilder.setPositiveButton(getString(R.string.Save), dialogClickListener);
        dialogBuilder.setNegativeButton(getString(R.string.Cancel), dialogClickListener);

        //Set demo brick to current packaging grade read from preferences
        algorithmSavedPreferences = getActivity().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        setDemoBrickGrade(algorithmSavedPreferences.getInt("CURRENT_GRADE", 0));
        setDemoBrickColor(algorithmSavedPreferences.getInt("CURRENT_COLOR", 0));

        //SAVE BUTTON
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = dialogBuilder.create();
                dialog.setIcon(R.mipmap.faq);
                dialog.show();
            }
        });

        /*Listview*************************************************************/
        algorithm_listView_colours=(ListView) view.findViewById(R.id.algorithm_listView_colours);
        algorithm_listView_colours.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.getFocusables(position);
                view.setSelected(true);
            }
        });

        algorithm_listView_grades=(ListView) view.findViewById(R.id.algorithm_listView_grades);
        algorithm_listView_grades.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
            }
        });

        algorithm_listView_colours.setItemChecked(0,true);
        algorithm_listView_colours.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        algorithm_listView_grades.setItemChecked(0,true);
        algorithm_listView_grades.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        //ON LIST PRESS
        algorithm_listView_colours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                setDemoBrickColor(algorithm_listView_colours.getCheckedItemPosition());
            }
        });

        algorithm_listView_grades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                setDemoBrickGrade(position);
            }
        });

        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Algorithm.this.updateModes(1);
            }
        });
        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Algorithm.this.updateModes(2);
            }
        });
        imageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Algorithm.this.updateModes(3);
            }
        });
        imageViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Algorithm.this.updateModes(4);
            }
        });
        imageViews[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Algorithm.this.updateModes(5);
            }
        });

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

    public void updateModes(int mManipulator) {
        manipulatorModes[mManipulator] += 1;
        if (manipulatorModes[mManipulator] > 3)
            manipulatorModes[mManipulator] = 0;
        updateDisplay();
    }

    public void updateDisplay() {
        for (int i = 1; i<MANIPULATORS+1; i++) {
            if (manipulatorModes[i] == 0) {
                imageViews[i].setImageResource(R.mipmap.in);
                textViews[i].setText(getString(R.string.Arm) + " " + i +getString(R.string.INPUT));

            } else if (manipulatorModes[i] == 1) {
                imageViews[i].setImageResource(R.mipmap.inout);
                textViews[i].setText(getString(R.string.Arm) + " " + i +getString(R.string.INOUT));

            } else if (manipulatorModes[i] == 2) {
                imageViews[i].setImageResource(R.mipmap.out);
                textViews[i].setText(getString(R.string.Arm) + " " + i +getString(R.string.OUTPUT));

            } else if(manipulatorModes[i] == 3){
                imageViews[i].setImageResource(R.mipmap.disabled);
                textViews[i].setText(getString(R.string.Arm) + " " + i +getString(R.string.DISABLED));

            }
        }
    }

    public void saveAlgorithmData(){
        int color = algorithm_listView_colours.getCheckedItemPosition();
        if(color==0) color=1;
        int grade = algorithm_listView_grades.getCheckedItemPosition(); //TODO RBS me da que este +1 arbitrario aqui va a traer problemas, asi que lo quito
        if(grade==0) grade=1;

        //Save to APP preferences
        sharedPrefEditor = algorithmSavedPreferences.edit();
        sharedPrefEditor.putInt("CURRENT_GRADE", algorithm_listView_grades.getCheckedItemPosition());
        sharedPrefEditor.putInt("CURRENT_COLOR", algorithm_listView_colours.getCheckedItemPosition());
        sharedPrefEditor.commit();

        //Save to machine
        String writeCommand = "ALSC_14_";
        writeCommand += String.format("%02d",  color);
        writeCommand += "_";
        writeCommand += String.format("%03d",  grade); //Mira Raul, algunas partes de nuestra infraestructura soportan 100 calidades distintas
        writeCommand += "_";

        for(int i=1; i<MANIPULATORS+1; i++)
            writeCommand += manipulatorModes[i];

        Log.d("Generated cmd", writeCommand);
        mFragmentInteraction.onSendCommand(writeCommand);
    }

    public void setDemoBrickGrade(int mGrade) {
        demoBrick.setText(getResources().getStringArray(R.array.Grades)[mGrade]);
    }

    public void setDemoBrickColor(int mColor ) {
        switch(mColor){
            case 0:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_1));
                break;
            case 1:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_2));
                break;
            case 2:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_3));
                break;
            case 3:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_4));
                break;
            case 4:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_5));
                break;
            case 5:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_6));
                break;
            case 6:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_7));
                break;
            case 7:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_8));
                break;
            case 8:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_9));
                break;
            case 9:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_10));
                break;
            case 10:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_11));
                break;
            case 11:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_12));
                break;
            case 12:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_13));
                break;
            case 13:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_14));
                break;
            case 14:
                demoBrick.setBackgroundColor(getResources().getColor(R.color.brick_color_15));
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onSendCommand( String command );
    }
}