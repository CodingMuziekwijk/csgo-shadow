package com.evilcorp.csgo_shadow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Openingscreen extends AppCompatActivity {

    //String[] maps = {"Dust 2", "Mirage", "Inferno", "Cobblestone", "Overpass", "Cache", "Aztec", "Dust", "Vertigo", "Nuke", "Office", "Italy", "Assault", "Militia"  };

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
        myMaps.add(new gameMap("Dust 2", R.drawable.dust2_icon));
        myMaps.add(new gameMap("Train", R.drawable.train_icon));
        myMaps.add(new gameMap("Mirage", R.drawable.mirage_icon));
        myMaps.add(new gameMap("Inferno", R.drawable.inferno_icon));
        myMaps.add(new gameMap("Cobblestone", R.drawable.cobblestone_icon));
        myMaps.add(new gameMap("Overpass", R.drawable.overpass_icon));
        myMaps.add(new gameMap("Cache", R.drawable.cache_icon));
        myMaps.add(new gameMap("Aztec", R.drawable.aztec_icon));
        myMaps.add(new gameMap("Dust", R.drawable.dust_icon));
        myMaps.add(new gameMap("Vertigo", R.drawable.vertigo_icon));
        myMaps.add(new gameMap("Nuke", R.drawable.nuke_icon));
        myMaps.add(new gameMap("Office", R.drawable.office_icon));
        myMaps.add(new gameMap("Italy", R.drawable.italy_icon));
        myMaps.add(new gameMap("Assault", R.drawable.assault_icon));
        myMaps.add(new gameMap("Militia", R.drawable.militia_icon));
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
                startActivity(new Intent(Openingscreen.this, Faction.class));
            }
        });
    }
}
