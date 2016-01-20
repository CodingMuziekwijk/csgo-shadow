package com.evilcorp.csgo_shadow;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class BombTimerFragment extends Fragment {

    private TextView countDownText;
    private Button start_countdown_button;
    CountDownTimer countDownTimer;

    private static final String FORMAT = "%02d:%03d";

    BombTimerFragmentListener activityCommander;

    public interface BombTimerFragmentListener{
        void setMapOverview(int myMapId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (BombTimerFragmentListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bomb_timer_fragment, container, false);

        countDownText=(TextView)view.findViewById(R.id.timeView);
        createCountdownButton(view);

        return view;
    }

    @Override
    public void onDestroy() {
        start_countdown_button.setOnClickListener(null);
        if(countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        super.onDestroy();
    }

    private void createCountdownButton(View view){

        start_countdown_button = (Button) view.findViewById(R.id.start_countdown_button);

        start_countdown_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_countdown_button.setVisibility(View.INVISIBLE);
                countDownTimer = new CountDownTimer(45000, 9) { // adjust the milli seconds here


                    public void onTick(long millisUntilFinished) {



                        String message = ""+String.format(FORMAT,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),  // Seconds
                                millisUntilFinished - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))); // Millis

                        updateTextView(message);
                    }

                    public void onFinish() {
                        countDownText.setText("Bomb Exploded!");
                    }
                }.start();
            }
        });
    }

    public void updateTextView( final String message){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will run on the UI thread
                countDownText.setText(message);
            }
        });
    }
}
