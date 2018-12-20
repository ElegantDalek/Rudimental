package com.example.theoden.rudimental;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MetronomeUI.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MetronomeUI#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MetronomeUI extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextView tempoText;
    private Button record, toggleMetronome;
    private Metronome metronome;
    private SeekBar tempoControl;
    private MediaRecorder mMediaRecorder;

    private boolean isRecording = false;

    public MetronomeUI() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MetronomeUI.
     */
    public static MetronomeUI newInstance() {
        MetronomeUI fragment = new MetronomeUI();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_metronome_ui, container, false);
        tempoText = rootView.findViewById(R.id.tempoText);
        record = rootView.findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecord();
            }
        });

        initializeMetronome(rootView);
        initializeSlider(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initializeMetronome(View view) {
        metronome = new Metronome(getActivity());

        toggleMetronome = view.findViewById(R.id.play);
        toggleMetronome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metronome.toggle();
                toggleMetronome.setText((metronome.getPlayState()) ? "Pause" : "Play");
            }
        });

    }

    private void initializeSlider(View rootView) {
        tempoControl = rootView.findViewById(R.id.tempoControl);
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
    public void toggleRecord() {
        // Requests permission if not active, see below
        // https://stackoverflow.com/questions/37290752/java-lang-runtimeexception-setaudiosource-failed
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO},
                    10);
        } else if (!isRecording) {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS);
            File newFile = new File(getActivity().getExternalFilesDir(null), "demoFile.mp3");
            mMediaRecorder.setOutputFile(newFile);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            try {
                mMediaRecorder.prepare();
                mMediaRecorder.start();
                isRecording = !isRecording;
                record.setText("STOP");
                Toast.makeText(getActivity(), "Recording... ", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(getActivity(), "IO Exception", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            isRecording = !isRecording;
            record.setText("Record");
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
