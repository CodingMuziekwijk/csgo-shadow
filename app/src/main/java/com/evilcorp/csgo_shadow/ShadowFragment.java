package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ShadowFragment extends Fragment {

    private ImageView mapOverview;
    private ImageView shadowMapOverview;
    private Bitmap myShadowMapIdBackup;
    private Bitmap myBitMapIdBackup;
    private Bitmap bitmap;
    private Bitmap shadowMap;
    private TextView testTextView;
    private int pixel;
    private ArrayList<Integer> cords_x = new ArrayList<>();
    private ArrayList<Integer> cords_y = new ArrayList<>();
    final Timer timer = new Timer();
    private TimerTask task;
    ArrayList<Integer> cords_xx;
    ArrayList<Integer> cords_yy;
    private Boolean checker;


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
    }

    public void setShadowBitMap(int myShadowMapId){
        shadowMapOverview.setImageResource(myShadowMapId);
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

    public boolean whiteInMap(){
        if(cords_x.size() == 0){
            return true;
        }
        return false;
    }


    public void resetShadow(){
        //timer.cancel();
        task.cancel();

        cords_x.clear();
        cords_y.clear();
        cords_xx.clear();
        cords_yy.clear();
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

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {


                    cords_x.add((int) motionEvent.getX());
                    cords_y.add((int) motionEvent.getY());

                    if(bitmap == null){
                        bitmap = mapOverview.getDrawingCache();
                    }


                    if(myBitMapIdBackup == null){
                        myBitMapIdBackup = bitmap.copy(bitmap.getConfig(), true);
                    }

                    //myBitMapIdBackup = mapOverview.getDrawingCache();


                    if(shadowMap == null){
                        shadowMap = shadowMapOverview.getDrawingCache();
                    }


                    if(myShadowMapIdBackup == null){
                        myShadowMapIdBackup = shadowMap.copy(shadowMap.getConfig(), true);
                    }
                    //myShadowMapIdBackup = shadowMapOverview.getDrawingCache();


                    shadowMap.setPixel(cords_x.get(0), cords_y.get(0), Color.GREEN);

                    pixel = shadowMap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                    Log.i("System.out", "" + motionEvent.getX());
                    Log.i("System.out", "" + motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);


                    int initialDelay = 0; // start after 30 seconds
                    int period = 27;        // repeat every 5 seconds

                    createTimerTask();
                    timer.scheduleAtFixedRate(task, initialDelay, period);
                }
                return true;
            }
        });
    }

    private void createResetButton(View view) {
        Button resetButton = (Button) view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetShadow();
                updateMapOverview();
            }
        });
    }

    private void createTimerTask(){
        task = new TimerTask() {
            public void run() {
                //Irative timer code :
                checker = false;

                cords_xx = new ArrayList<>(cords_x);
                cords_yy = new ArrayList<>(cords_y);

                if (whiteInMap()) {
                    //timer.cancel();
                    task.cancel();
                    Log.i("System.out", "Finished!");
                    updateTextViewOverview();
                }

                cords_x.clear();
                cords_y.clear();


                for (int i = 0; i < cords_xx.size(); i++) {

                    // Iterate though all neighbours
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {

                            int neighbour_pixel = shadowMap.getPixel(cords_xx.get(i) + j - 1, cords_yy.get(i) + k - 1);
                            pixel = shadowMap.getPixel(cords_xx.get(i) + j, cords_yy.get(i) + k);
//                            if(pixel == Color.RED){
//                                Log.i("System.out", "pixel = Red!");
//                                if (neighbour_pixel == Color.WHITE) {
//                                    Log.i("System.out", "Neighbour = White!");
//                                    bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.RED);
//                                    shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.RED);
//                                    cords_x.add(cords_xx.get(i) - 1 + j);
//                                    cords_y.add(cords_yy.get(i) - 1 + k);
//                                }
//                                if(neighbour_pixel == Color.MAGENTA){
//                                    Log.i("System.out", "Neighbour = Magenta!");
//                                    bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//                                    shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//                                    cords_x.add(cords_xx.get(i) - 1 + j);
//                                    cords_y.add(cords_yy.get(i) - 1 + k);
//                                }
//                                if(neighbour_pixel == Color.RED){
//                                    Log.i("System.out", "Neighbour = Red!");
//                                    cords_x.add(cords_xx.get(i) - 1 + j);
//                                    cords_y.add(cords_yy.get(i) - 1 + k);
//                                }
//
//                            }

                            if(neighbour_pixel == Color.WHITE) {
                                //Log.i("System.out", "Neighbour = White!");
                                bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
                                shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
                                cords_x.add(cords_xx.get(i) - 1 + j);
                                cords_y.add(cords_yy.get(i) - 1 + k);
                            }

                            if(Color.red(neighbour_pixel) == 255){
                                //Log.i("System.out", "Neighbour = Red!");
                                bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
                                shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
                                cords_x.add(cords_xx.get(i) - 1 + j);
                                cords_y.add(cords_yy.get(i) - 1 + k);
                            }
                        }
                    }
                }
                updateMapOverview();
                checker = true;
            }
        };
    }
}
