package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShadowFragment extends Fragment {

    private ImageView mapOverview;
    private int myMapId;
    private Bitmap bitmap;
    private TextView testTextView;

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

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    bitmap = mapOverview.getDrawingCache();

                    int pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);


                    //bitmap.setPixel((int)motionEvent.getX(), (int) motionEvent.getY(), -1);

                    testTextView.setBackgroundColor(Color.rgb(r, g, b));
                    testTextView.setText("R(" + r + ")\n" + "G(" + g + ")\n" + "B(" + b + ")");
                }

                return true;
            }
        });

        return view;
    }

    public void setMapOverview(int myMapId){
        mapOverview.setImageResource(myMapId);

    }

}
