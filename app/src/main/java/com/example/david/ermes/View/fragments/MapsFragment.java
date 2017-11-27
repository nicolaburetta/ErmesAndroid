package com.example.david.ermes.View.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.ermes.Presenter.FirebaseCallback;
import com.example.david.ermes.Presenter.Match;
import com.example.david.ermes.Presenter.User;
import com.example.david.ermes.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 17/07/2017.
 */

public class MapsFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    List<Match> match_list = new ArrayList<>();
    GoogleApiClient mGoogleApiClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                Match.fetchMatchesByIdOwner(User.getCurrentUser().getUID(), new FirebaseCallback() {
                    @Override
                    public void callback(List list) {
                        match_list = list;
                        for (Match match : match_list) {
                            LatLng location_latlng = new LatLng(
                                    match.getLocation().getLatitude(),
                                    match.getLocation().getLongitude()
                            );

                            googleMap.addMarker(new MarkerOptions()
                                    .position(location_latlng)
                                    .title(match.getLocation().getName())
                                    .snippet(match.getIdSport()));
                        }
                        LatLng randomlatlng = new LatLng(
                                match_list.get(0).getLocation().getLatitude(),
                                match_list.get(0).getLocation().getLongitude()
                        );
                        // For zooming automatically to the location of the marker
                        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(12).target(randomlatlng).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                });

                // For dropping a marker at a point on the Map
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}


