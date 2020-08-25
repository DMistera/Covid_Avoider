package com.example.covidavoider;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment extends Fragment implements View.OnClickListener {

    public NavigationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NavigationFragment newInstance() {
        NavigationFragment fragment = new NavigationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_navigation, container, false);

        final ImageButton b1 = (ImageButton) v.findViewById(R.id.mapButton);
        final ImageButton b2 = (ImageButton) v.findViewById(R.id.historyButton);
        final ImageButton b3 = (ImageButton) v.findViewById(R.id.settingsButton);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        return v;
    }

    public void openMap() {
        startActivity(MapActivity.class);
    }

    public void openHistory() {
        startActivity(HistoryActivity.class);
    }

    public void openSettings() {
        startActivity(SettingsActivity.class);
    }

    private void startActivity(Class<?> activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mapButton:
                openMap();
                break;
            case R.id.historyButton:
                openHistory();
                break;
            case R.id.settingsButton:
                openSettings();
                break;
        }
    }
}