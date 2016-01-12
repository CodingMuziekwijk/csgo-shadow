package com.evilcorp.csgo_shadow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Session extends AppCompatActivity implements ShadowFragment.ShadowFragmentListener {

    private Bundle bundle;
    private int myMapId;
    private int myShadowMapId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        bundle = getIntent().getExtras();
        myMapId = bundle.getInt("myMapId");
        myShadowMapId = bundle.getInt("myShadowMapId");
        setMapOverview(myMapId);
        setShadowBitmap(myShadowMapId);
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
}
