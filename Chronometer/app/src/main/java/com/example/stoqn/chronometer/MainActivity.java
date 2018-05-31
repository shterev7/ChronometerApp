package com.example.stoqn.chronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private Chronometer xChronometer;

    private long lastPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_button);
        pauseButton = (Button) findViewById(R.id.pause_button);
        resetButton = (Button) findViewById(R.id.reset_button);
        xChronometer = (Chronometer) findViewById(R.id.chronometer);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (lastPause!=0){
                    xChronometer.setBase(xChronometer.getBase()+SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    xChronometer.setBase(SystemClock.elapsedRealtime());
                }
                xChronometer.start();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                lastPause = SystemClock.elapsedRealtime();
                xChronometer.stop();
                pauseButton.setEnabled(false);
                startButton.setEnabled(true);

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                xChronometer.stop();
                xChronometer.setBase(SystemClock.elapsedRealtime());
                lastPause=0;
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
            }
        });

    }
}
