package com.evilcorp.csgo_shadow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class Session extends AppCompatActivity implements ShadowFragment.ShadowFragmentListener, BombTimerFragment.BombTimerFragmentListener, EcoFragment.EcoFragmentListener {

    //Global variables
    private int myMapId;
    private int myShadowMapId;
    private boolean startFactionIsTerror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removes notificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_session);

        //Creates bundle from intent
        Bundle bundle;
        bundle = getIntent().getExtras();

        //Extracts variables from bundle
        myMapId = bundle.getInt("myMapId");
        myShadowMapId = bundle.getInt("myShadowMapId");
        startFactionIsTerror = bundle.getBoolean("myFaction");

        setMapOverview(myMapId);
        setShadowBitmap(myShadowMapId);
        setFaction(startFactionIsTerror);
    }

    //Communicates from SessionsActivity to ShadowFragment
    @Override
    public void setMapOverview(int myMapId) {
        ShadowFragment shadowFragment = (ShadowFragment) getSupportFragmentManager().findFragmentById(R.id.shadow_fragment);
        shadowFragment.setMapOverview(myMapId);
    }

    //Communicates from SessionsActivity to ShadowFragment
    @Override
    public void setShadowBitmap(int myShadowMapId) {
        ShadowFragment shadowFragment = (ShadowFragment) getSupportFragmentManager().findFragmentById(R.id.shadow_fragment);
        shadowFragment.setShadowBitMap(myShadowMapId);
    }

    //Communicates from SessionsActivity to EcoFragment
    @Override
    public void setFaction(boolean startFactionIsTerror) {
        EcoFragment ecoFragment = (EcoFragment) getSupportFragmentManager().findFragmentById(R.id.eco_fragment);
        Log.i("faction", "" + startFactionIsTerror);
        ecoFragment.setFaction(startFactionIsTerror);
    }

    //Communicates from EcoFragment to BombTimerFragment
    @Override
    public void resetTimer(){
        BombTimerFragment bombTimerFragment = (BombTimerFragment) getSupportFragmentManager().findFragmentById(R.id.bombtimer_fragment);
        bombTimerFragment.resetTimer();
    }
}
