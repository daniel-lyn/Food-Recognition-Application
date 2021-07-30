package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;

    private Marker EXP,Lion_Rock_Bistro,City_Express,YoLi,Fairwood,Aroi_Thai,Sushiro
                    ,HOKO_Farm,Ho_Yuen_Bakery,Cafe_de_Itamomo,Oi_Kwan_Fast_Food_Restaurant
                    ,SuperNormal,Beans,Yuan_Is_Here,Pho_Le,Meokbang,Joy_Cuisine,Taiwan_Kitchen
                    ,Da_Hong_Pao_Hotpot,HeSheEat,Kobekyu,Master_Beef,A4Noodle,Satay_King,Red_Tea_Cafe
                    ,Big_Sweet_House,Dine_Inn,Yee_Shun,Burgeroom,DAMA,Seorae,Zi,Years,Chayamachi
                    ,Rustico,Picoso;

    private final LatLng EXP_l= new LatLng(22.337235, 114.1744709);
    private final LatLng Init= new LatLng(22.336800, 114.172409);
    private final LatLng Lion_Rock_Bistro_l= new LatLng(22.339435, 114.180926);
    private final LatLng City_Express_l= new LatLng(22.33744, 114.1716492);
    private final LatLng YoLi_l= new LatLng(22.3380161, 114.1869067);
    private final LatLng Fairwood_l= new LatLng(22.341158, 114.187408);
    private final LatLng Aroi_Thai_l = new LatLng(22.3419751, 114.1921735);
    private final LatLng Sushiro_l= new LatLng(22.3420156, 114.1971141);
    private final LatLng HOKO_Farm_l= new LatLng(22.340729, 114.202296);
    private final LatLng Ho_Yuen_Bakery_l= new LatLng(22.341721, 114.200273);
    private final LatLng Cafe_de_Itamomo_l= new LatLng(22.3333884, 114.2154066);
    private final LatLng Oi_Kwan_Fast_Food_Restaurant_l= new LatLng(22.335265, 114.205406);
    private final LatLng SuperNormal_l  = new LatLng(22.3244781, 114.2163556);
    private final LatLng Beans_l = new LatLng(22.3215129, 114.2132529);
    private final LatLng Yuan_Is_Here_l= new LatLng(22.3128357, 114.2197389);
    private final LatLng Pho_Le_l= new LatLng(22.312401, 114.225222);
    private final LatLng Meokbang_l= new LatLng(22.306055, 114.2512668);
    private final LatLng Joy_Cuisine_l= new LatLng(22.3065191, 114.2525191);
    private final LatLng Taiwan_Kitchen_l= new LatLng(22.322567, 114.169053);
    private final LatLng Da_Hong_Pao_Hotpot_l= new LatLng(22.3255805, 114.1678315);
    private final LatLng HeSheEat_l = new LatLng(22.317606, 114.1730947);
    private final LatLng Kobekyu_l = new LatLng(22.3211526, 114.1690855);
    private final LatLng Master_Beef_l= new LatLng(22.317836, 114.169911);
    private final LatLng A4Noodle_l= new LatLng(22.3169889, 114.1728218);
    private final LatLng Satay_King_l= new LatLng(22.313343, 114.170851);
    private final LatLng Red_Tea_Cafe_l= new LatLng(22.3118166, 114.1711296);
    private final LatLng Big_Sweet_House_l= new LatLng(22.3062007, 114.1702471);
    private final LatLng Dine_Inn_l= new LatLng(22.3038854, 114.1705191);
    private final LatLng Yee_Shun_l= new LatLng(22.3048399, 114.1710219);
    private final LatLng Burgeroom_l= new LatLng(22.300931, 114.17218);
    private final LatLng DAMA_l= new LatLng(22.294059, 114.1741227);
    private final LatLng Seorae_l= new LatLng(22.301919, 114.17517);
    private final LatLng Zi_l= new LatLng(22.3025722, 114.1725229);
    private final LatLng Years_l = new LatLng(22.332573, 114.160693);
    private final LatLng Chayamachi_l= new LatLng(22.3306166, 114.1622627);
    private final LatLng Rustico_l= new LatLng(22.336194, 114.1487448);
    private final LatLng Picoso_l= new LatLng(22.3366889, 114.1478102);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Init, 15.0f));

        EXP = mMap.addMarker(new MarkerOptions()
                .position(EXP_l)
                .title("EXP"));

        Lion_Rock_Bistro = mMap.addMarker(new MarkerOptions()
                .position(Lion_Rock_Bistro_l)
                .title("Lion Rock Bistro"));

        City_Express = mMap.addMarker(new MarkerOptions()
                .position(City_Express_l)
                .title("City Express"));

        YoLi = mMap.addMarker(new MarkerOptions()
                .position(YoLi_l)
                .title("YoLi"));

        Picoso = mMap.addMarker(new MarkerOptions()
                .position(Picoso_l)
                .title("Picoso"));

        Fairwood = mMap.addMarker(new MarkerOptions()
                .position(Fairwood_l)
                .title("Fairwood"));

        Aroi_Thai = mMap.addMarker(new MarkerOptions()
                .position(Aroi_Thai_l)
                .title("Aroi Thai"));

        Rustico = mMap.addMarker(new MarkerOptions()
                .position(Rustico_l)
                .title("Rustico"));

        Sushiro = mMap.addMarker(new MarkerOptions()
                .position(Sushiro_l)
                .title("Sushiro"));

        HOKO_Farm = mMap.addMarker(new MarkerOptions()
                .position(HOKO_Farm_l)
                .title("HOKO Farm"));

        Ho_Yuen_Bakery = mMap.addMarker(new MarkerOptions()
                .position(Ho_Yuen_Bakery_l)
                .title("Ho Yuen Bakery"));

        Cafe_de_Itamomo = mMap.addMarker(new MarkerOptions()
                .position(Cafe_de_Itamomo_l)
                .title("Cafe de Itamomo"));

        Chayamachi = mMap.addMarker(new MarkerOptions()
                .position(Chayamachi_l)
                .title("Chayamachi"));

        Oi_Kwan_Fast_Food_Restaurant = mMap.addMarker(new MarkerOptions()
                .position(Oi_Kwan_Fast_Food_Restaurant_l)
                .title("Oi Kwan Fast Food Restaurant"));

        SuperNormal = mMap.addMarker(new MarkerOptions()
                .position(SuperNormal_l)
                .title("SuperNormal"));

        Beans = mMap.addMarker(new MarkerOptions()
                .position(Beans_l)
                .title("Beans"));

        Yuan_Is_Here = mMap.addMarker(new MarkerOptions()
                .position(Yuan_Is_Here_l)
                .title("Yuan Is Here"));

        Pho_Le = mMap.addMarker(new MarkerOptions()
                .position(Pho_Le_l)
                .title("Pho Le"));

        Meokbang = mMap.addMarker(new MarkerOptions()
                .position(Meokbang_l)
                .title("Meokbang"));

        Joy_Cuisine = mMap.addMarker(new MarkerOptions()
                .position(Joy_Cuisine_l)
                .title("Joy Cuisine"));

        Taiwan_Kitchen = mMap.addMarker(new MarkerOptions()
                .position(Taiwan_Kitchen_l)
                .title("Taiwan Kitchen"));

        Da_Hong_Pao_Hotpot = mMap.addMarker(new MarkerOptions()
                .position(Da_Hong_Pao_Hotpot_l)
                .title("Da Hong Pao Hotpot"));

        HeSheEat = mMap.addMarker(new MarkerOptions()
                .position(HeSheEat_l)
                .title("HeSheEat"));

        Kobekyu = mMap.addMarker(new MarkerOptions()
                .position(Kobekyu_l)
                .title("Kobekyu"));

        Master_Beef = mMap.addMarker(new MarkerOptions()
                .position(Master_Beef_l)
                .title("Master Beef"));

        A4Noodle = mMap.addMarker(new MarkerOptions()
                .position(A4Noodle_l)
                .title("A4Noodle"));

        Satay_King = mMap.addMarker(new MarkerOptions()
                .position(Satay_King_l)
                .title("Satay King"));

        Red_Tea_Cafe = mMap.addMarker(new MarkerOptions()
                .position(Red_Tea_Cafe_l)
                .title("Red Tea Cafe"));

        Big_Sweet_House = mMap.addMarker(new MarkerOptions()
                .position(Big_Sweet_House_l)
                .title("Big Sweet House"));

        Dine_Inn = mMap.addMarker(new MarkerOptions()
                .position(Dine_Inn_l)
                .title("Dine Inn"));

        Yee_Shun = mMap.addMarker(new MarkerOptions()
                .position(Yee_Shun_l)
                .title("Yee Shun"));

        Burgeroom = mMap.addMarker(new MarkerOptions()
                .position(Burgeroom_l)
                .title("Burgeroom"));

        DAMA = mMap.addMarker(new MarkerOptions()
                .position(DAMA_l)
                .title("DAMA"));

        Seorae = mMap.addMarker(new MarkerOptions()
                .position(Seorae_l)
                .title("Seorae"));

        Zi = mMap.addMarker(new MarkerOptions()
                .position(Zi_l)
                .title("Zi"));

        Years = mMap.addMarker(new MarkerOptions()
                .position(Years_l)
                .title("Years"));

        enableMyLocation();

        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the data from the marker.
        String locName = marker.getTitle();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(locName);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Rest").child(locName).child("Menu(100g)");
        String[] menu = new String[5];
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String M1 = snapshot.child("M1").getValue().toString();
                String M2 = snapshot.child("M2").getValue().toString();
                String M3 = snapshot.child("M3").getValue().toString();
                String M4 = snapshot.child("M4").getValue().toString();
                String M5 = snapshot.child("M5").getValue().toString();
                menu[0] = M1;
                menu[1] = M2;
                menu[2] = M3;
                menu[3] = M4;
                menu[4] = M5;
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return false;
    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }
}