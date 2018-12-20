package com.example.theoden.rudimental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.activity_main, container, false);
        Bundle args = getArguments();
        TextView text1 = rootView.findViewById(R.id.text1);
        text1.setText(Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
