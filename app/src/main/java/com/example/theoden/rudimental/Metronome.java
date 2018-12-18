package com.example.theoden.rudimental;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.Timer;
import java.util.TimerTask;

public class Metronome{
    private SoundPool soundPool;
    private int soundID;
    private boolean play = false;
    private boolean isPaused = false; // Set to true only when progress bar is being moved
    private Timer timer;
    private TimerTask beep;
    private int tempo = 120;

    Metronome(Context context) {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(context, R.raw.cowbell, 1);
    }

    public void toggle() {
        play = !play;
        if (play) {
            timer = new Timer("MetronomeTimer", true);
            beep = generateTask();
            timer.scheduleAtFixedRate(beep, 0, bpmToMillisec(tempo));
        } else {
            timer.cancel();
        }
    }

    public boolean getPlayState() {
        return this.play;
    }
    public void setPlay(boolean play) {
        this.play = play;
    }


    public void setPause(boolean pause) {
        this.isPaused = pause;
    }


    public void setTempo(int bpm) {
        this.tempo = bpm;
        if (play) { // If is playing, need to generate new tempoTask
            this.toggle();
            this.toggle();
        }
    }

    private TimerTask generateTask() {
        return new TimerTask() {
            @Override
            public void run() {
                soundPool.play(soundID, 1, 1, 0, 0, 1);
            }
        };
    }

    private int bpmToMillisec(int bpm) {
        return (int) (60.0 / ((double) bpm) * 1000.0);
    }

}
