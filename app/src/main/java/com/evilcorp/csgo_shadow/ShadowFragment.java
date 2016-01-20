package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ShadowFragment extends Fragment {

    private ImageView mapOverview;
    private ImageView shadowMapOverview;
    private Bitmap myShadowMapIdBackup;
    private Bitmap myBitMapIdBackup;
    private Bitmap bitmap;
    private Bitmap shadowMap;
    private TextView testTextView;
    private ArrayList<Integer> cords_x = new ArrayList<>();
    private ArrayList<Integer> cords_y = new ArrayList<>();
    final Timer timer = new Timer();
    private TimerTask task;
    private Button resetButton;
    private Shadow shadow;

    private int mapId;
    private int shadowMapId;



    ShadowFragmentListener activityCommander;

    public interface ShadowFragmentListener{
        void setMapOverview(int myMapId);
        void setShadowBitmap(int myShadowMapId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (ShadowFragmentListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.shadow_fragment, container, false);

        shadowMapOverview = (ImageView) view.findViewById(R.id.shadowMapOverview);
        mapOverview = (ImageView) view.findViewById(R.id.mapOverview);
        testTextView = (TextView) view.findViewById(R.id.testTextView);

        mapOverview.setDrawingCacheEnabled(true);
        mapOverview.buildDrawingCache(true);

        shadowMapOverview.setDrawingCacheEnabled(true);
        shadowMapOverview.buildDrawingCache(true);

        createOnTouchListener();
        createResetButton(view);

        return view;
    }

    public void setMapOverview(int myMapId){
        mapOverview.setImageResource(myMapId);
        this.mapId = myMapId;
    }

    public void setShadowBitMap(int myShadowMapId){
        shadowMapOverview.setImageResource(myShadowMapId);
        this.shadowMapId = myShadowMapId;
    }

    public void updateMapOverview(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                mapOverview.setImageBitmap(bitmap);
            }
        });
    }

    public void updateTextViewOverview(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                testTextView.setText("Finished!");
            }
        });
    }

    public void clearTextViewOverview(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                testTextView.setText("");
            }
        });
    }

    public void resetShadow(){
        //timer.cancel();
        task.cancel();
        shadow.clearCordList();
        shadow.clearCordListContainer();

//        bitmap = BitmapFactory.decodeResource(getResources(), mapId).copy(Bitmap.Config.ARGB_8888, true);
//        shadowMap = BitmapFactory.decodeResource(getResources(), shadowMapId).copy(Bitmap.Config.ARGB_8888, true);


        shadowMap = myShadowMapIdBackup.copy(myShadowMapIdBackup.getConfig(), true);
        bitmap = myBitMapIdBackup.copy(myBitMapIdBackup.getConfig(), true);
        createOnTouchListener();
        clearTextViewOverview();
    }

    public void createOnTouchListener(){
        mapOverview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                // Turn on/off for only one/2/3/4 shadows
                mapOverview.setOnTouchListener(null);
                showResetButton();

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {

//                    bitmap = BitmapFactory.decodeResource(getResources(), mapId).copy(Bitmap.Config.ARGB_8888, true);
//                    shadowMap = BitmapFactory.decodeResource(getResources(), shadowMapId).copy(Bitmap.Config.ARGB_8888, true);

                    if (bitmap == null) {
                        bitmap = mapOverview.getDrawingCache();
                    }

                    if (myBitMapIdBackup == null) {
                        myBitMapIdBackup = bitmap.copy(bitmap.getConfig(), true);
                    }

                    if (shadowMap == null) {
                        shadowMap = shadowMapOverview.getDrawingCache();
                    }

                    if (myShadowMapIdBackup == null) {
                        myShadowMapIdBackup = shadowMap.copy(shadowMap.getConfig(), true);
                    }

                    shadow = new Shadow(shadowMap, bitmap);
                    shadow.addCordsToList((int) motionEvent.getX(), (int) motionEvent.getY(), true);
                    shadow.makePixelGreen((int) motionEvent.getX(), (int) motionEvent.getY());
                    //pixel = shadowMap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

//                    Log.i("System.out", "" + motionEvent.getX());
//                    Log.i("System.out", "" + motionEvent.getY());

//                    int r = Color.red(pixel);
//                    int g = Color.green(pixel);
//                    int b = Color.blue(pixel);


                    int initialDelay = 0; // start after 30 seconds
                    int period = 65;        // repeat every 5 seconds

                    createTimerTask();
                    timer.scheduleAtFixedRate(task, initialDelay, period);
                }
                return true;
            }
        });
    }

    private void createResetButton(View view) {
        resetButton = (Button) view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetShadow();
                updateMapOverview();
            }
        });
    }

    private void showResetButton() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                resetButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createTimerTask(){
        task = new TimerTask() {
            public void run() {
                //Irative timer code :
                shadow.setNewCordList();

                if (shadow.whiteInMap()) {
                    //timer.cancel();
                    task.cancel();
                    Log.i("System.out", "Finished!");
                    //updateTextViewOverview();
                    resetShadow();
                }

                shadow.clearCordList();

                bitmap = shadow.calculateNewFrame();

                updateMapOverview();
            }
        };
    }

    private Boolean maxShadows(int shadows){
        return shadows == 2;
    }
}
