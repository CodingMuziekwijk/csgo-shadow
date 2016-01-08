package com.evilcorp.csgo_shadow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Faction extends AppCompatActivity {

    private Bundle bundle;
    private int myMapId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faction);

        bundle = getIntent().getExtras();
        myMapId = bundle.getInt("myMapId");

        onTerrorClick();
        onCounterClick();
    }



    private void onTerrorClick() {
        ImageView terrorButton = (ImageView) findViewById(R.id.terror_imageView);
        TextView terrorTextButton = (TextView) findViewById(R.id.terrorTextView);

        terrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Faction.this, Session.class);
                intent.putExtra("myMapId", myMapId );

                startActivity(intent);
            }
        });

        terrorTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Faction.this, Session.class);
                intent.putExtra("myMapId", myMapId);
                startActivity(intent);
            }
        });
    }

    private void onCounterClick() {
        ImageView counterImageButton = (ImageView) findViewById(R.id.counter_imageView);
        TextView counterTextButton = (TextView) findViewById(R.id.counterTextView);

        counterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Faction.this, Session.class);
                intent.putExtra("myMapId", myMapId);
                startActivity(intent);
            }
        });

        counterTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Faction.this, Session.class);
                intent.putExtra("myMapId", myMapId);
                startActivity(intent);
            }
        });

    }
}
