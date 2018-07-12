package com.example.administrator.ForkliftHMI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ToggleButton;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



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

        createSideMenu();

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

    private void createSideMenu() {
        ArrayList tools = new ArrayList<EditorTool>();
        tools.add(new EditorTool("Zona de exclusión", R.mipmap.keepout_transp));
        tools.add(new EditorTool("Delimitador", R.mipmap.brickwall));
        tools.add(new EditorTool("Punto de control", R.mipmap.trafficlight));
        tools.add(new EditorTool("Punto de descarga", R.mipmap.palleticon));
        tools.add(new EditorTool("Punto de carga", R.mipmap.flag));

        ListView editorList = view.findViewById(R.id.editor_listView_tools);

        EditorToolsListAdapter adapter = new EditorToolsListAdapter(getActivity(), tools);
        editorList.setAdapter(adapter);
    }

    public void whenEnteringFragment() {

    }

    public void whenLeavingFragment() {

    }
}
