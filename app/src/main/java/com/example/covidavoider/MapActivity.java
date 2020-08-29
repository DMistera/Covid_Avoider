package com.example.covidavoider;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.example.covidavoider.models.CountryInfo;
import com.example.covidavoider.models.covidapi.CountryStatistics;
import com.example.covidavoider.models.covidapi.CovidAPIBody;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Location lastKnownLocation;
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 1;
    private static final String TAG = MapActivity.class.getSimpleName();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private List<CountryInfo> countryInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

        try {
            loadCountryLocations();
            getMapCovidData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.getResult();
                        if (lastKnownLocation != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lastKnownLocation.getLatitude(),
                                            lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");
                        Log.e(TAG, "Exception: %s", task.getException());
                        map.moveCamera(CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                        map.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                }
            });
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getMapCovidData() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://covid-193.p.rapidapi.com/statistics")
                .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "cf1a098d2fmsh67561ae14e0b471p1b37c6jsn5a3667b860b2")
                .build();

        Log.d("API", "Request sent!");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("API", e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseJSON = response.body().string();
                Gson gson = new Gson();
                final CovidAPIBody body = gson.fromJson(responseJSON, CovidAPIBody.class);
                runOnUiThread(new Runnable(){
                    public void run(){
                        for(CountryStatistics country: body.response) {
                            LatLng point = getCountryLocation(country.country);
                            if(point != null) {
                                double radius = Math.sqrt(country.cases.total) * 400.0;
                                map.addCircle(new CircleOptions().center(point).radius(radius).fillColor(0x300000ff).strokeWidth(1.0f).strokeColor(0xffffffff));
                            }
                        }
                    }
                });
            }
        });
    }

    private LatLng getCountryLocation(String countryName) {
        for(CountryInfo countryInfo : countryInfos) {
            if(countryInfo.name.equals(countryName)) {
                return countryInfo.point;
            }
        }
        return null;
    }

    private void loadCountryLocations() throws IOException {
        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier("countries",
                        "raw", getPackageName()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        while(reader.ready()) {
            String line = reader.readLine();
            String[] tokens = line.split("\t");
            if(tokens.length >= 4) {
                CountryInfo country = new CountryInfo();
                double latitude = Double.parseDouble(tokens[1]);
                double longitude = Double.parseDouble(tokens[2]);
                country.name = tokens[3];
                country.point = new LatLng(latitude, longitude);
                countryInfos.add(country);
            }
        }
    }
}