package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EcoFragment extends Fragment {

    private boolean startFactionIsTerror;
    private TextView roundView;
    private Eco eco;

    EcoFragmentListener activityCommander;

    public interface EcoFragmentListener{
        void setFaction(boolean startFactionIsTerror);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (EcoFragmentListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eco_fragment, container, false);

        roundView = (TextView) view.findViewById(R.id.roundView);

        eco = new Eco(startFactionIsTerror);


        updateRoundView();


        return view;
    }

    public void setFaction(boolean myFaction){
        //roundView.setText("" + myFaction);
    }

    private void updateRoundView(){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                roundView.setText("Round " + eco.getRound_number());
            }
        });
    }
}
