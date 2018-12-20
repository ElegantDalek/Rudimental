package com.example.theoden.rudimental;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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

        initializeMetronome(rootView);
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
