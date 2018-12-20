package com.example.theoden.rudimental;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MetronomeUI.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private ViewPageManager viewPageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPageManager = new ViewPageManager(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(viewPageManager);
        viewPager.setOffscreenPageLimit(5);
    }

    public void onFragmentInteraction(Uri uri) {

    }

}
