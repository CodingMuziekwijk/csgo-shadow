package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.graphics.Bitmap;
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
    private int myMapId;
    private Bitmap bitmap;
    private Bitmap shadowMap;
    private TextView testTextView;
    private int pixel;
    //private List<Integer> cords_x = new ArrayList<>();
    //private List<Integer> cords_y = new ArrayList<>();
    private ArrayList<Integer> cords_x = new ArrayList<>();
    private ArrayList<Integer> cords_y = new ArrayList<>();



    ShadowFragmentListener activityCommander;

    public interface ShadowFragmentListener{
        //void setMap(int mapOverview);
        void setMapOverview(int myMapId);
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

        mapOverview = (ImageView) view.findViewById(R.id.mapOverview);
        testTextView = (TextView) view.findViewById(R.id.testTextView);

        mapOverview.setDrawingCacheEnabled(true);
        mapOverview.buildDrawingCache(true);



        mapOverview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                // Turn on/off for only one/2/3/4 shadows
                mapOverview.setOnTouchListener(null);

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {


                    cords_x.add((int) motionEvent.getX());
                    cords_y.add((int) motionEvent.getY());


                    bitmap = mapOverview.getDrawingCache();
                    shadowMap = mapOverview.getDrawingCache();

                    bitmap.setPixel(cords_x.get(0), cords_y.get(0), Color.GREEN);

                    pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());
                    final int touch_x = (int) motionEvent.getX();
                    final int touch_y = (int) motionEvent.getY();
                    Log.i("System.out", "" + motionEvent.getX());
                    Log.i("System.out", "" + motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);


                    //bitmap.setPixel((int)motionEvent.getX(), (int) motionEvent.getY(), -1);

                    //testTextView.setBackgroundColor(Color.rgb(r, g, b));
                    //testTextView.setText("R(" + r + ")\n" + "G(" + g + ")\n" + "B(" + b + ")");

                    int initialDelay = 0; // start after 30 seconds
                    int period = 27;        // repeat every 5 seconds
                    final Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        public void run() {
                            // job code here
                            ArrayList<Integer> cords_xx = new ArrayList<>(cords_x);
                            ArrayList<Integer> cords_yy = new ArrayList<>(cords_y);

                            if (whiteInMap()) {
                                timer.cancel();
                                Log.i("System.out", "Finished!");
                                updateTextViewOverview();
                            }

                            cords_x.clear();
                            cords_y.clear();


                            for (int i = 0; i < cords_xx.size(); i++) {

                                // Iterate though all neighbours
                                for (int j = 0; j < 3; j++) {
                                    for (int k = 0; k < 3; k++) {

                                        int new_pixel = bitmap.getPixel(cords_xx.get(i) + j - 1, cords_yy.get(i) + k - 1);

                                        if (new_pixel == Color.WHITE) {
                                            //Log.i("System.out", "White!");
                                            bitmap.setPixel(cords_xx.get(i) - 1 + j, cords_yy.get(i) - 1 + k, Color.GREEN);
                                            cords_x.add(cords_xx.get(i) - 1 + j);
                                            cords_y.add(cords_yy.get(i) - 1 + k);
                                        }
                                    }
                                }
                            }
                            updateMapOverview();
                        }
                    };
                    timer.scheduleAtFixedRate(task, initialDelay, period);

                }

                return true;
            }
        });

        return view;
    }

    public void setMapOverview(int myMapId){
        mapOverview.setImageResource(myMapId);

    }

    public void updateMapOverview(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will always run on the UI thread, therefore is safe to modify UI elements.
                mapOverview.setImageBitmap(bitmap);
            }
        });
    }

    public void updateTextViewOverview(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will always run on the UI thread, therefore is safe to modify UI elements.
                testTextView.setText("Finished!");
            }
        });
    }

    public boolean whiteInMap(){
        if(cords_x.size() == 0){
            return true;
        }
//        for (int x = 1; x < bitmap.getWidth(); x++) {
//
//            for (int y = 1; y < bitmap.getHeight(); y++) {
//
//                int tmp_pixel = bitmap.getPixel(x,y) ;
//
//                if(tmp_pixel == Color.WHITE){
//                    return true;
//                }
//
//            }
//
//        }
        return false;
    }

}
