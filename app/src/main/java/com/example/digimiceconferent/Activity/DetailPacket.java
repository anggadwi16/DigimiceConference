package com.example.digimiceconferent.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.digimiceconferent.Model.EventPacket;
import com.example.digimiceconferent.R;
import com.example.digimiceconferent.SharedPrefManager;

public class DetailPacket extends AppCompatActivity {
    public static final String EXTRA_EVENT_PACKET = "packet_event";
    TextView tvNamePanitia, tvEmail, tvNamePacket, tvMaxParticipant, tvPricePacket, tvTeam;
    Button btKelolaPaket;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_packet);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Packet");
        }

        tvEmail = findViewById(R.id.email_user_detail_packet);
        tvNamePacket = findViewById(R.id.name_detail_packet);
        tvNamePanitia = findViewById(R.id.name_user_detail_packet);
        tvMaxParticipant = findViewById(R.id.max_participant_detail_packet);
        tvTeam = findViewById(R.id.team_detail_packet);
        tvPricePacket = findViewById(R.id.price_detail_packet);
        btKelolaPaket = findViewById(R.id.bt_kelola_paket);
        sharedPrefManager = new SharedPrefManager(this);

        EventPacket eventPacket = getIntent().getParcelableExtra(EXTRA_EVENT_PACKET);
        if (eventPacket != null) {
            tvEmail.setText(sharedPrefManager.getSpEmail());
            tvTeam.setText("("+sharedPrefManager.getSpNameTeam()+")");
            tvNamePanitia.setText(sharedPrefManager.getSpName());
            tvNamePacket.setText(eventPacket.getName_packet());
            tvMaxParticipant.setText(eventPacket.getMax_participant());
            tvPricePacket.setText(eventPacket.getPrice());
        }
    }
}
