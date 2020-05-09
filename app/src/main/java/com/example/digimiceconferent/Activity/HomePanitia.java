package com.example.digimiceconferent.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.digimiceconferent.Fragment.AkunFragment;
import com.example.digimiceconferent.Fragment.EventFragment;
import com.example.digimiceconferent.Fragment.PaketFragment;
import com.example.digimiceconferent.Fragment.PembayaranFragment;
import com.example.digimiceconferent.Fragment.PesertaFragment;
import com.example.digimiceconferent.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePanitia extends AppCompatActivity {

    final Fragment fragmentEvent = new EventFragment();
    final Fragment fragmentPaket = new PaketFragment();
    final Fragment fragmentPeserta = new PesertaFragment();
    final Fragment fragmentPembayaran = new PembayaranFragment();
    final Fragment fragmentAkun = new AkunFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentPaket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_panitia);

        getSupportActionBar().setTitle("Pilih Paket Event");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationView.getMenu().findItem(R.id.navigation_panitia).setChecked(true);

        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentEvent, "1").hide(fragmentEvent).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentPaket, "2").commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentPeserta, "3").hide(fragmentPeserta).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentPembayaran, "4").hide(fragmentPembayaran).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentAkun, "5").hide(fragmentAkun).commit();

        TextView largeTextView = bottomNavigationView.findViewById(R.id.bottom_nav).findViewById(com.google.android.material.R.id.largeLabel);
        TextView smallTextView = bottomNavigationView.findViewById(R.id.bottom_nav).findViewById(com.google.android.material.R.id.smallLabel);
        largeTextView.setVisibility(View.GONE);
        smallTextView.setVisibility(View.VISIBLE);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_event:
                    fm.beginTransaction().hide(active).show(fragmentEvent).commit();
                    active = fragmentEvent;
                    getSupportActionBar().setTitle("Event");
                    return true;
                case R.id.navigation_panitia:
                    fm.beginTransaction().hide(active).show(fragmentPaket).commit();
                    active = fragmentPaket;
                    getSupportActionBar().setTitle("Pilih Paket Event");
                    return true;
                case R.id.navigation_peserta:
                    fm.beginTransaction().hide(active).show(fragmentPeserta).commit();
                    active = fragmentPeserta;
                    getSupportActionBar().setTitle("Peserta");
                    return true;
                case R.id.navigation_pembayaran:
                    fm.beginTransaction().hide(active).show(fragmentPembayaran).commit();
                    active = fragmentPembayaran;
                    getSupportActionBar().setTitle("Pembayaran");
                    return true;
                case R.id.navigation_akun:
                    fm.beginTransaction().hide(active).show(fragmentAkun).commit();
                    active = fragmentAkun;
                    getSupportActionBar().setTitle("Akun");
                    return true;
            }
            return false;
        }
    };

    private boolean connected(){
        ConnectivityManager connection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connection.getActiveNetworkInfo() != null;
    }



}
