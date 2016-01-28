package com.evilcorp.csgo_shadow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Faction extends AppCompatActivity {

    private Bundle bundle;
    private int myMapId;
    private int myShadowMapId;
    private boolean startFactionIsTerror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removes notificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_faction);

        bundle = getIntent().getExtras();
        myMapId = bundle.getInt("myMapId");
        myShadowMapId = bundle.getInt("myShadowMapId");

        createsTerrorButtons();
        createsCounterButtons();
    }

    private void createsTerrorButtons() {
        //Displays TerrorButtons
        ImageView terrorButton = (ImageView) findViewById(R.id.terror_imageView);
        TextView terrorTextButton = (TextView) findViewById(R.id.terrorTextView);

        terrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFactionIsTerror = true;
                //Create intent and go to sessionActivity
                createIntent();
            }
        });

        terrorTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFactionIsTerror = true;
                //Create intent and go to sessionActivity
                createIntent();
            }
        });
    }

    private void createsCounterButtons() {
        //Displays CounterButtons
        ImageView counterImageButton = (ImageView) findViewById(R.id.counter_imageView);
        TextView counterTextButton = (TextView) findViewById(R.id.counterTextView);

        counterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFactionIsTerror = false;
                //Create intent and go to sessionActivity
                createIntent();
            }
        });

        counterTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFactionIsTerror = false;
                //Create intent and go to sessionActivity
                createIntent();
            }
        });
    }

    private void createIntent(){
        //Creates intent
        Intent intent = new Intent(Faction.this, Session.class);

        //Adds variables to intent
        intent.putExtra("myMapId", myMapId);
        intent.putExtra("myShadowMapId", myShadowMapId);
        intent.putExtra("myFaction", startFactionIsTerror);

        //Goes to Sessions Activity
        startActivity(intent);
    }
}
