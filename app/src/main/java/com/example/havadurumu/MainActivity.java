package com.example.havadurumu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView sehirKodu,sehirAdi,sicaklik,hissedilenSicaklik,ruzgarHizi,rakim,nem;
    Retrofit retrofit;
    String temelUrl="https://api.openweathermap.org/data/2.5/";
    AnaSinif anaSinif;
    AnaInterface anaInterface;
    Call<AnaSinif> AnaSinifiCagir;
    ImageView havaIkon;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sehirKodu=findViewById(R.id.tv_city_code);
        sehirAdi=findViewById(R.id.tv_city_name);
        sicaklik=findViewById(R.id.sicaklik);
        hissedilenSicaklik=findViewById(R.id.hissedilen_sicaklik);
        ruzgarHizi=findViewById(R.id.ruzgar_hizi);
        rakim=findViewById(R.id.rakim);
        nem=findViewById(R.id.nem);
        havaIkon=findViewById(R.id.img_weather_pictures);
        client = LocationServices.getFusedLocationProviderClient(this);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                getmyLocation();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(MainActivity.this, "Uygulamanın kullanılabilmesi için konum izni vermelisiniz!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();


    }
    public void getmyLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latlng= new LatLng(location.getLatitude(),location.getLongitude());
                Geocoder geocoder = new Geocoder((MainActivity.this));
                String country = "";
                String city = "";
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    Log.d("getCountryName", addressList.get(0).getCountryName());
                    country = addressList.get(0).getCountryName();
                    Log.d("getFeatureName", addressList.get(0).getAdminArea());
                    city = addressList.get(0).getAdminArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                retrofitAyarlari(location.getLatitude(),location.getLongitude(), country, city);
            }
        });
    }
    public void  retrofitAyarlari(double latitude, double longitude,  String country,  String city){

        retrofit=new Retrofit.Builder()
                .baseUrl(temelUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        anaInterface=retrofit.create(AnaInterface.class);
        AnaSinifiCagir=anaInterface.hava(latitude,longitude,"75cf252bb4b919b27c36b8221db9c518","metric");
        AnaSinifiCagir.enqueue(new Callback<AnaSinif>() {

            @Override
            public void onResponse(Call<AnaSinif> call, Response<AnaSinif> response) {
                if(response.isSuccessful()){
                    anaSinif= response.body();
                    if(anaSinif!=null){
                        sehirKodu.setText(anaSinif.getSys().getCountry());
                        //sehirAdi.setText(city);
                        Float floatSicaklik = Float.valueOf(anaSinif.getMain_class().getTemp());
                        int intSicaklik=Math.round(floatSicaklik);
                        sicaklik.setText(""+ intSicaklik+"°C");
                        Float floatHissedilen = Float.valueOf(anaSinif.getMain_class().getFeels_like());
                        int intHissedilen = Math.round(floatHissedilen);
                        hissedilenSicaklik.setText(""+intHissedilen+"°C");
                        rakim.setText(""+anaSinif.getMain_class().getSea_level()+" m");
                        nem.setText(""+"%"+anaSinif.getMain_class().getHumidity());
                        ruzgarHizi.setText(""+anaSinif.getRuzgar().getSpeed()+" km/sa");

                        String iconUrlPath = "http:openweathermap.org/img/wn/"+ anaSinif.getHava().get(0).getIcon() +"@2x.png";
                        Glide.with(getApplicationContext())
                                .load(iconUrlPath)
                                .into(havaIkon);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Üzgünüm hava durumu bilgisi alınamıyor.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AnaSinif> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }
}