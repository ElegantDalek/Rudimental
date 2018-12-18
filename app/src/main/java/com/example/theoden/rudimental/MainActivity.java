package com.example.theoden.rudimental;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button toggleMetronome;
    private SeekBar tempoControl;
    private TextView tempoText;
    private MediaPlayer mp;
    private Metronome metronome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempoText = findViewById(R.id.tempoText);

        initializeMetronome();
        initializeSlider();


    }
    private void initializeMetronome() {
        metronome = new Metronome(this);

        toggleMetronome = findViewById(R.id.play);
        toggleMetronome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metronome.toggle();
                toggleMetronome.setText((metronome.getPlayState()) ? "Pause" : "Play");
            }
        });

    }

    private void initializeSlider() {
        tempoControl = findViewById(R.id.tempoControl);
        tempoControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int newTempo = progress + 50;
                metronome.setTempo(newTempo);
                tempoText.setText(Integer.toString(newTempo));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
