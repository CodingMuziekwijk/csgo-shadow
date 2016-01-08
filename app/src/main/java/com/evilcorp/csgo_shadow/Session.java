package com.evilcorp.csgo_shadow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Session extends AppCompatActivity implements ShadowFragment.ShadowFragmentListener {

    private Bundle bundle;
    private int myMapId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        bundle = getIntent().getExtras();
        myMapId = bundle.getInt("myMapId");
        setMapOverview(myMapId);
    }


    @Override
    public void setMapOverview(int myMapId) {
        ShadowFragment shadowFragment = (ShadowFragment) getSupportFragmentManager().findFragmentById(R.id.shadow_fragment);
        shadowFragment.setMapOverview(myMapId);
    }
}
