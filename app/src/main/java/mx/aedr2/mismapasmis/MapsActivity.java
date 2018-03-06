package mx.aedr2.mismapasmis;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitud;
    private double longitud;
    private int localizacion;
    private String nombreLocalizacion;
    private MarkerOptions marcador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        latitud = extras.getDouble(this.getString(R.string.extraLatitud));
        longitud = extras.getDouble(this.getString(R.string.extraLongitud));
        localizacion = extras.getInt(this.getString(R.string.extraLocalizacion));
        nombreLocalizacion = extras.getString(this.getString(R.string.extraNombreLocalizacion));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(latitud, longitud);
        marcador = new MarkerOptions().position(latLng).title(nombreLocalizacion);
        if(localizacion == 0)
            mMap.addMarker(marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
       else if (localizacion == 1)
            mMap.addMarker(marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        else if (localizacion == 2)
            mMap.addMarker(marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        else if (localizacion == 3)
            mMap.addMarker(marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        float nivelZoom = 15;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, nivelZoom));
    }
}
