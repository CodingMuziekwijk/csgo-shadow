package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.graphics.Bitmap;
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
    private int pixel;
    private ArrayList<Integer> cords_x = new ArrayList<>();
    private ArrayList<Integer> cords_y = new ArrayList<>();
    final Timer timer = new Timer();
    private TimerTask task;
    ArrayList<Integer> cords_xx;
    ArrayList<Integer> cords_yy;



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
        return(cords_x.size() == 0);
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

                    if (bitmap == null) {
                        bitmap = mapOverview.getDrawingCache();
                    }


                    if (myBitMapIdBackup == null) {
                        myBitMapIdBackup = bitmap.copy(bitmap.getConfig(), true);
                    }

                    //myBitMapIdBackup = mapOverview.getDrawingCache();


                    if (shadowMap == null) {
                        shadowMap = shadowMapOverview.getDrawingCache();
                    }


                    if (myShadowMapIdBackup == null) {
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
                    int period = 70;        // repeat every 5 seconds

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
                    //setStep(cords_xx.get(i), cords_yy.get(i));
                    createCircle(cords_xx.get(i), cords_yy.get(i));
//-------------------------------------------------------------------------------------------------------
//                    pixel = shadowMap.getPixel(cords_xx.get(i), cords_yy.get(i));
//
//                    int neighbour_pixel = shadowMap.getPixel(cords_xx.get(i) - 1, cords_yy.get(i));
//                    //checkPixel(neighbour_pixel);
//                    if(neighbour_pixel == Color.WHITE) {
//                        //Log.i("System.out", "Neighbour = White!");
//                        bitmap.setPixel(cords_xx.get(i) - 1, cords_yy.get(i), Color.GREEN);
//                        shadowMap.setPixel(cords_xx.get(i) - 1, cords_yy.get(i), Color.GREEN);
//                        cords_x.add(cords_xx.get(i) - 1);
//                        cords_y.add(cords_yy.get(i));
//                    }
//                    neighbour_pixel = shadowMap.getPixel(cords_xx.get(i)+ 1, cords_yy.get(i));
//                    //checkPixel(neighbour_pixel);
//                    if(neighbour_pixel == Color.WHITE) {
//                        //Log.i("System.out", "Neighbour = White!");
//                        bitmap.setPixel(cords_xx.get(i) + 1, cords_yy.get(i), Color.GREEN);
//                        shadowMap.setPixel(cords_xx.get(i) + 1, cords_yy.get(i), Color.GREEN);
//                        cords_x.add(cords_xx.get(i) + 1);
//                        cords_y.add(cords_yy.get(i));
//                    }
//                    neighbour_pixel = shadowMap.getPixel(cords_xx.get(i), cords_yy.get(i) - 1);
//                    //checkPixel(neighbour_pixel);
//                    if(neighbour_pixel == Color.WHITE) {
//                        //Log.i("System.out", "Neighbour = White!");
//                        bitmap.setPixel(cords_xx.get(i), cords_yy.get(i) - 1, Color.GREEN);
//                        shadowMap.setPixel(cords_xx.get(i), cords_yy.get(i) - 1, Color.GREEN);
//                        cords_x.add(cords_xx.get(i));
//                        cords_y.add(cords_yy.get(i) - 1);
//                    }
//
//                    neighbour_pixel = shadowMap.getPixel(cords_xx.get(i), cords_yy.get(i) + 1);
//                    //checkPixel(neighbour_pixel);
//                    if(neighbour_pixel == Color.WHITE) {
//                        //Log.i("System.out", "Neighbour = White!");
//                        bitmap.setPixel(cords_xx.get(i), cords_yy.get(i) + 1, Color.GREEN);
//                        shadowMap.setPixel(cords_xx.get(i), cords_yy.get(i) + 1, Color.GREEN);
//                        cords_x.add(cords_xx.get(i));
//                        cords_y.add(cords_yy.get(i) + 1);
//                    }


//------------------------------------------------------------------------------------------------------------
                    // Iterate though all neighbours
//                    for (int j = 0; j < 3; j++) {
//                        for (int k = 0; k < 3; k++) {
//
//                            int neighbour_pixel = shadowMap.getPixel(cords_xx.get(i) + j - 1, cords_yy.get(i) + k - 1);
//                            pixel = shadowMap.getPixel(cords_xx.get(i) + j, cords_yy.get(i) + k);
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
//
//                            if(neighbour_pixel == Color.WHITE) {
//                                //Log.i("System.out", "Neighbour = White!");
//                                bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//                                shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//                                cords_x.add(cords_xx.get(i) - 1 + j);
//                                cords_y.add(cords_yy.get(i) - 1 + k);
//                            }
//
//                            if(Color.red(neighbour_pixel) == 255){
//                                //Log.i("System.out", "Neighbour = Red!");
//                                bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//                                shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//                                cords_x.add(cords_xx.get(i) - 1 + j);
//                                cords_y.add(cords_yy.get(i) - 1 + k);
//                            }
//                        }
//                    }
                }
                updateMapOverview();
            }
        };
    }

//    private void checkPixel(int pixel, int i){
//        if(pixel == Color.WHITE) {
//            //Log.i("System.out", "Neighbour = White!");
//            bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//            shadowMap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
//            cords_x.add(cords_xx.get(i) - 1 + j);
//            cords_y.add(cords_yy.get(i) - 1 + k);
//        }
//    }

    private void setStep( int x, int y) {

        // First Row Pixels
        for(int i = 1; i < 5; i++ ){
            if(isWhite(x + i, y )){
                bitmap.setPixel( x + i, y, Color.GREEN);
                shadowMap.setPixel( x + i, y, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y);
            }
            if(isWhite(x - i, y )){
                bitmap.setPixel( x - i, y, Color.GREEN);
                shadowMap.setPixel( x - i, y, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y);
            }
        }
        // Second Row Pixels
        // Upper
        for(int i = 1; i < 5; i++ ){
            if(isWhite(x + i, y + 1 )){
                bitmap.setPixel( x + i, y + 1, Color.GREEN);
                shadowMap.setPixel( x + i, y + 1, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y + 1);
            }
            if(isWhite(x - i, y + 1 )){
                bitmap.setPixel( x - i, y + 1, Color.GREEN);
                shadowMap.setPixel( x - i, y + 1, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y + 1);
            }

        }
        // Lower
        for(int i = 1; i < 5; i++ ){
            if(isWhite(x + i, y - 1 )){
                bitmap.setPixel( x + i, y - 1, Color.GREEN);
                shadowMap.setPixel( x + i, y - 1, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y - 1);
            }
            if(isWhite(x - i, y - 1 )){
                bitmap.setPixel( x - i, y - 1, Color.GREEN);
                shadowMap.setPixel( x - i, y - 1, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y - 1);
            }

        }

        // Third Row Pixels
        // Upper
        for(int i = 1; i < 4; i++ ){
            if(isWhite(x + i, y + 2 )){
                bitmap.setPixel( x + i, y + 2, Color.GREEN);
                shadowMap.setPixel( x + i, y + 2, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y + 1);
            }
            if(isWhite(x - i, y + 2 )){
                bitmap.setPixel( x - i, y + 2, Color.GREEN);
                shadowMap.setPixel( x - i, y + 2, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y + 2);
            }

        }
        // Lower
        for(int i = 1; i < 4; i++ ){
            if(isWhite(x + i, y - 2 )){
                bitmap.setPixel( x + i, y - 2, Color.GREEN);
                shadowMap.setPixel( x + i, y - 2, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y - 2);
            }
            if(isWhite(x - i, y - 2 )){
                bitmap.setPixel( x - i, y - 2, Color.GREEN);
                shadowMap.setPixel( x - i, y - 2, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y - 2);
            }

        }

        // Fourth Row Pixels
        // Upper
        for(int i = 1; i < 4; i++ ){
            if(isWhite(x + i, y + 3 )){
                bitmap.setPixel( x + i, y + 3, Color.GREEN);
                shadowMap.setPixel( x + i, y + 3, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y + 3);
            }
            if(isWhite(x - i, y + 3 )){
                bitmap.setPixel( x - i, y + 3, Color.GREEN);
                shadowMap.setPixel( x - i, y + 3, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y + 3);
            }

        }
        // Lower
        for(int i = 1; i < 4; i++ ){
            if(isWhite(x + i, y - 3 )){
                bitmap.setPixel( x + i, y - 3, Color.GREEN);
                shadowMap.setPixel( x + i, y - 3, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y - 3);
            }
            if(isWhite(x - i, y - 3 )){
                bitmap.setPixel( x - i, y - 3, Color.GREEN);
                shadowMap.setPixel( x - i, y - 3, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y - 3);
            }

        }

        // Fifth Row Pixels
        // Upper
        for(int i = 1; i < 2; i++ ){
            if(isWhite(x + i, y + 4 )){
                bitmap.setPixel( x + i, y + 4, Color.GREEN);
                shadowMap.setPixel( x + i, y + 4, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y + 4);
            }
            if(isWhite(x - i, y + 4 )){
                bitmap.setPixel( x - i, y + 4, Color.GREEN);
                shadowMap.setPixel( x - i, y + 4, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y + 4);
            }

        }
        // Lower
        for(int i = 1; i < 2; i++ ){
            if(isWhite(x + i, y - 4 )){
                bitmap.setPixel( x + i, y - 4, Color.GREEN);
                shadowMap.setPixel( x + i, y - 4, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y - 4);
            }
            if(isWhite(x - i, y - 4 )){
                bitmap.setPixel( x - i, y - 4, Color.GREEN);
                shadowMap.setPixel( x - i, y - 4, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y - 4);
            }

        }


    }

    private Boolean isWhite(int x, int y){
        return (shadowMap.getPixel(x, y) == Color.WHITE);
    }

    private void circle(int x, int y){
        for(int i = 1; i < 5; i++ ){
            if(isWhite(x + i, y )){
                bitmap.setPixel( x + i, y, Color.GREEN);
                shadowMap.setPixel( x + i, y, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y);
            }
            if(isWhite(x - i, y )){
                bitmap.setPixel( x - i, y, Color.GREEN);
                shadowMap.setPixel( x - i, y, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y);
            }
        }

        // Second Row Pixels
        // Upper
        for(int i = 1; i < 5; i++ ){
            if(isWhite(x + i, y + 1 )){
                bitmap.setPixel( x + i, y + 1, Color.GREEN);
                shadowMap.setPixel( x + i, y + 1, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y + 1);
            }
            if(isWhite(x - i, y + 1 )){
                bitmap.setPixel( x - i, y + 1, Color.GREEN);
                shadowMap.setPixel( x - i, y + 1, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y + 1);
            }
            // Lower
            if(isWhite(x + i, y - 1 )){
                bitmap.setPixel( x + i, y - 1, Color.GREEN);
                shadowMap.setPixel( x + i, y - 1, Color.GREEN);
                cords_x.add(x + i);
                cords_y.add(y - 1);
            }
            if(isWhite(x - i, y - 1 )){
                bitmap.setPixel( x - i, y - 1, Color.GREEN);
                shadowMap.setPixel( x - i, y - 1, Color.GREEN);
                cords_x.add(x - i);
                cords_y.add(y - 1);
            }

        }
    }


    private void createCircle(int x, int y){
//        checkPixel(x, y, 0, 4);
//        checkPixel(x, y, 1, 4);
//        checkPixel(x, y, 2, 3);
//        checkPixel(x, y, 3, 3);
//        checkPixel(x, y, 4, 1);
        checkPixel(x, y, 0, 8);
        checkPixel(x, y, 1, 8);
        checkPixel(x, y, 2, 8);
        checkPixel(x, y, 3, 7);
        checkPixel(x, y, 5, 6);
        checkPixel(x, y, 6, 6);
        checkPixel(x, y, 7, 4);
    }

    private void addPixel(int x, int y, boolean add){
        bitmap.setPixel(x, y, Color.GREEN);
        shadowMap.setPixel(x, y, Color.GREEN);
        if(add){
            cords_x.add(x);
            cords_y.add(y);
        }
    }

    private void checkPixel(int x, int y, int d, int j){
        for(int i = 1; i < j + 1; i++) {
            boolean outer = false;
        if ((i == j) & (d == 3 || d == 5 || d == 0) ){
                outer = true;
            }
            // Bottom right
            if (isWhite(x + i, y + d)) {
                addPixel(x + i, y + d, outer);
            }

            // Bottom left
            if (isWhite(x - d, y + i)) {
                addPixel(x - d, y + i, outer);
            }

            // top left
            if (isWhite(x - i, y - d)) {
                addPixel(x - i, y - d, outer);
            }

            // top right
            if (isWhite(x + d, y - i)) {
                addPixel(x + d, y - i, outer);
            }
        }

    }
}
