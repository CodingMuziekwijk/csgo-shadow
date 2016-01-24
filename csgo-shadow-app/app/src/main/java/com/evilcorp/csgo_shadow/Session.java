package com.evilcorp.csgo_shadow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Session extends AppCompatActivity implements ShadowFragment.ShadowFragmentListener, BombTimerFragment.BombTimerFragmentListener, EcoFragment.EcoFragmentListener {

    private Bundle bundle;
    private int myMapId;
    private int myShadowMapId;
    private boolean startFactionIsTerror;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        bundle = getIntent().getExtras();
        myMapId = bundle.getInt("myMapId");
        myShadowMapId = bundle.getInt("myShadowMapId");
        startFactionIsTerror = bundle.getBoolean("myFaction");
        setMapOverview(myMapId);
        setShadowBitmap(myShadowMapId);
        //Toast.makeText(Session.this, "" + startFactionIsTerror, Toast.LENGTH_SHORT).show();
        setFaction(startFactionIsTerror);
    }

    @Override
    public void setMapOverview(int myMapId) {
        ShadowFragment shadowFragment = (ShadowFragment) getSupportFragmentManager().findFragmentById(R.id.shadow_fragment);
        shadowFragment.setMapOverview(myMapId);
    }

    @Override
    public void setShadowBitmap(int myShadowMapId) {
        ShadowFragment shadowFragment = (ShadowFragment) getSupportFragmentManager().findFragmentById(R.id.shadow_fragment);
        shadowFragment.setShadowBitMap(myShadowMapId);
    }

    @Override
    public void setFaction(boolean startFactionIsTerror) {
        EcoFragment ecoFragment = (EcoFragment) getSupportFragmentManager().findFragmentById(R.id.eco_fragment);
        Log.i("faction", "" + startFactionIsTerror);
        ecoFragment.setFaction(startFactionIsTerror);
    }

    @Override
    public void resetTimer(){
        BombTimerFragment bombTimerFragment = (BombTimerFragment) getSupportFragmentManager().findFragmentById(R.id.bombtimer_fragment);
        bombTimerFragment.resetTimer();
    }

}
