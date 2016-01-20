package com.evilcorp.csgo_shadow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Openingscreen extends AppCompatActivity {

    private List<gameMap> myMaps = new ArrayList<gameMap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openingscreen);

        populateMapList();
        populateListView();
        registerClickCallBack();
    }

    private void populateMapList() {
        myMaps.add(new gameMap("Wall Jump", R.drawable.oefenlevel_icon, R.drawable.test_level_walljump, R.drawable.test_level_walljump));
        myMaps.add(new gameMap("Blanco", R.drawable.oefenlevel_icon, R.drawable.blanco_level, R.drawable.blanco_level));
        myMaps.add(new gameMap("Oefenlevel", R.drawable.oefenlevel_icon, R.drawable.oefenlevel, R.drawable.oefenlevel));
        myMaps.add(new gameMap("Dust2 Oefenmap", R.drawable.oefenlevel_icon, R.drawable.overview_dust2_testmap02, R.drawable.overview_dust2_testmap02));
        myMaps.add(new gameMap("Crossover", R.drawable.oefenlevel_icon, R.drawable.testlevel_crossover_overview05, R.drawable.testlevel_crossover_overview05));
        myMaps.add(new gameMap("Dust 2", R.drawable.dust2_icon, R.drawable.overview_dust2_black, R.drawable.overview_dust2_testmap02));
        myMaps.add(new gameMap("Train", R.drawable.train_icon, R.drawable.overview_train, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Mirage", R.drawable.mirage_icon, R.drawable.overview_mirage, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Inferno", R.drawable.inferno_icon, R.drawable.overview_inferno, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Cobblestone", R.drawable.cobblestone_icon, R.drawable.overview_cobblestone, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Overpass", R.drawable.overpass_icon, R.drawable.overview_overpass, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Cache", R.drawable.cache_icon, R.drawable.overview_cache, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Aztec", R.drawable.aztec_icon, R.drawable.overview_aztec, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Dust", R.drawable.dust_icon, R.drawable.overview_dust, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Vertigo", R.drawable.vertigo_icon, R.drawable.overview_vertigo, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Nuke", R.drawable.nuke_icon, R.drawable.overview_nuke, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Office", R.drawable.office_icon, R.drawable.overview_office, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Italy", R.drawable.italy_icon, R.drawable.overview_italy, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Assault", R.drawable.assault_icon, R.drawable.overview_assault, R.drawable.dust2_testmap));
        myMaps.add(new gameMap("Militia", R.drawable.militia_icon, R.drawable.overview_militia, R.drawable.dust2_testmap));
    }

    private void populateListView() {        ArrayAdapter<gameMap> adapter = new MyListAdapter();
        GridView list = (GridView) findViewById(R.id.mapListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<gameMap> {
        public MyListAdapter(){
            super(Openingscreen.this, R.layout.item_view, myMaps);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            // Find map to work with
            gameMap currentGameMap = myMaps.get(position);

            // Image:
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentGameMap.getIconID());

            // Name:
            TextView makeText = (TextView) itemView.findViewById(R.id.item_txt_make);
            makeText.setText(currentGameMap.getName());

            return itemView;
        }
    }

    private void registerClickCallBack() {
        GridView list = (GridView) findViewById(R.id.mapListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                gameMap clickedGameMap = myMaps.get(position);
                String message = "" + clickedGameMap.getName();
                Toast.makeText(Openingscreen.this, message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Openingscreen.this, Faction.class);
                intent.putExtra("myMapId", myMaps.get(position).getOverViewID() );
                intent.putExtra("myShadowMapId", myMaps.get(position).getShadowOverViewID() );

                startActivity(intent);
            }
        });
    }
}
