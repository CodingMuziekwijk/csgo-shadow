package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.TestCase;

public class EcoFragment extends Fragment {

    private boolean startFactionIsTerror;
    private TextView roundView;
    private TextView factionView;
    private TextView ecoView;
    private Eco eco;

    EcoFragmentListener activityCommander;

    public interface EcoFragmentListener{
        void setFaction(boolean startFactionIsTerror);
        void resetTimer();
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
        factionView = (TextView) view.findViewById(R.id.factionView);
        ecoView = (TextView) view.findViewById(R.id.ecoView);
        createButtons(view);

        return view;
    }

    public void setFaction(boolean myFaction){
        startFactionIsTerror = myFaction;
        eco = new Eco(startFactionIsTerror);
        updateRoundView();
    }

    private void updateRoundView(){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                roundView.setText("Round " + eco.getRound_number());
                factionView.setText("Playing as " + eco.getFactionName());
                ecoView.setText(eco.getEcoMessage());
            }
        });
    }

    private void createButtons(View view){
        Button winButton = (Button) view.findViewById(R.id.winButton);
        winButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eco.endOfRound(true, false);
                updateRoundView();
                activityCommander.resetTimer();
            }
        });
        Button loseButton = (Button) view.findViewById(R.id.loseButton);
        loseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eco.endOfRound(false, false);
                updateRoundView();
                activityCommander.resetTimer();
            }
        });
        Button winEcoButton = (Button) view.findViewById(R.id.winEcoButton);
        winEcoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eco.endOfRound(true, true);
                updateRoundView();
                activityCommander.resetTimer();
            }
        });
        Button loseEcoButton = (Button) view.findViewById(R.id.loseEcoButton);
        loseEcoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eco.endOfRound(false, true);
                updateRoundView();
                activityCommander.resetTimer();
            }
        });
    }
}
