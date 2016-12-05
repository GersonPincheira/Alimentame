package cl.telematica.android.alimentame;

import android.*;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import cl.telematica.android.alimentame.Models.Localizacion;
import cl.telematica.android.alimentame.Models.Peticiones;
import cl.telematica.android.alimentame.POST.GPSTracker;
import cl.telematica.android.alimentame.POST.Publicar;
import cl.telematica.android.alimentame.Presenters.ConectionPresentersImpl;
import cl.telematica.android.alimentame.Presenters.Contact.ConectionPresenters;
import cl.telematica.android.alimentame.Presenters.GoogleApi;

public class UserActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button ver;
    GPSTracker gps;
    protected GoogleApiClient mGoogleApiClient;
    protected ArrayList<Geofence> mGeofenceList;
    private PendingIntent mGeofencePendingIntent;
    private Button mAddGeofencesButton;
    private Button mRemoveGeofencesButton;
    private HashMap<String,LatLng> area;
    private RequestQueue requestQueue;
    private Peticiones peticion;
    private ConectionPresenters conectionPresenters;
    private GoogleApi googleApi;
    View activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ver= (Button)findViewById(R.id.button4);
        mGeofenceList = new ArrayList<Geofence>();
        /*mGeofencePendingIntent = null;
        mRemoveGeofencesButton=ver;
        mAddGeofencesButton=ver;
        googleApi = new GoogleApi(mGoogleApiClient,this,mGeofencePendingIntent,mGeofenceList,mRemoveGeofencesButton,mAddGeofencesButton);
        googleApi.setButtonsEnabledState();
        googleApi.populateGeofenceList(Constants.BAY_AREA_LANDMARKS);
        googleApi.buildGoogleApiClient();
        peticion = Peticiones.getInstance(this.getApplicationContext());
        requestQueue = peticion.getRequestQueue();
        conectionPresenters = new ConectionPresentersImpl(activity,this,
                requestQueue,peticion,googleApi);*/




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UserActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            gps = new GPSTracker(this, UserActivity.this);
            // Check if GPS enabled
            if (gps.canGetLocation()) {
                if(String.valueOf(gps.getLongitude()).equalsIgnoreCase("0")&&
                        String.valueOf(gps.getLatitude()).equalsIgnoreCase("0"))
                    Toast.makeText(this,"error al encontrar ubicacion",Toast.LENGTH_SHORT).show();
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }
    ver.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent x = new Intent(UserActivity.this,TiendaActivity.class);
            startActivity(x);
        }
    });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(gps.getLatitude(), gps.getLongitude());
        mMap.setMinZoomPreference((float) 17);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Tu estas aqui!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       /* for (int i=0;i<conectionPresenters.getListado().size();i++) {
            Localizacion objeto = conectionPresenters.getListado().get(i);
            LatLng nuevo = new LatLng(objeto.getLatitud(),objeto.getLongitud());
            mMap.addMarker(new MarkerOptions().position(nuevo).title(objeto.getVendedor()));
        }*/


    }

}
