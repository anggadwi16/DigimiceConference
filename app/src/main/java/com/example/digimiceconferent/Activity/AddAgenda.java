package com.example.digimiceconferent.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.digimiceconferent.Fragment.DatePickerFragment;
import com.example.digimiceconferent.Fragment.TimePickerFragment;
import com.example.digimiceconferent.MainViewModel;
import com.example.digimiceconferent.Model.EventSession;
import com.example.digimiceconferent.R;
import com.example.digimiceconferent.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddAgenda extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    TextView namaEvent, tempatEvent, alamatEvent, waktuEvent;
    SharedPrefManager sharedPrefManager;
    EditText etNameAgenda, etDescAgenda, etStartDateAgenda, etStartTimeAgenda, etEndDateAgenda,
    etEndTimeAgenda;
    Spinner spSesi;
    Button btStartDateAgenda, btStartTimeAgenda, btEndDateAgenda, btEndTimeAgenda, btAddAgenda;

    final String START_DATE_PICKER = "start date picker";
    final String START_TIME_PICKER = "start time picker";
    final String END_DATE_PICKER = "end date picker";
    final String END_TIME_PICKER = "end time picker";

    String start;
    String end;
    String id_session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Tambah Agenda");
        }

        sharedPrefManager = new SharedPrefManager(this);
        namaEvent = findViewById(R.id.name_event_agenda);
        tempatEvent = findViewById(R.id.tempat_event_agenda);
        alamatEvent = findViewById(R.id.alamat_event_agenda);
        waktuEvent = findViewById(R.id.tanggal_event_agenda);

        etNameAgenda = findViewById(R.id.name_add_agenda);
        etDescAgenda = findViewById(R.id.deskripsi_add_agenda);
        etStartDateAgenda = findViewById(R.id.start_date_agenda);
        etStartTimeAgenda = findViewById(R.id.start_time_agenda);
        etEndDateAgenda = findViewById(R.id.end_date_agenda);
        etEndTimeAgenda = findViewById(R.id.end_time_agenda);

        spSesi = findViewById(R.id.spinner_session_agenda);

        btStartDateAgenda = findViewById(R.id.bt_start_date_agenda);
        btStartTimeAgenda = findViewById(R.id.bt_start_time_agenda);
        btEndDateAgenda = findViewById(R.id.bt_end_date_agenda);
        btEndTimeAgenda = findViewById(R.id.bt_end_time_agenda);
        btAddAgenda = findViewById(R.id.bt_add_agenda);

        namaEvent.setText(sharedPrefManager.getSpNameEvent());
        tempatEvent.setText(sharedPrefManager.getSpPlaceEvent());
        alamatEvent.setText(sharedPrefManager.getSpAddressEvent());
        waktuEvent.setText(sharedPrefManager.getSpWaktuEvent());

        btStartTimeAgenda.setOnClickListener(this);
        btStartDateAgenda.setOnClickListener(this);
        btEndDateAgenda.setOnClickListener(this);
        btEndTimeAgenda.setOnClickListener(this);
        btAddAgenda.setOnClickListener(this);

        RequestQueue queue = Volley.newRequestQueue(this);
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setListEventSessionPanitia(queue, this, sharedPrefManager.getSpIdEvent());
        mainViewModel.getEventSessionPanitia().observe(this, new Observer<ArrayList<EventSession>>() {
            @Override
            public void onChanged(final ArrayList<EventSession> eventSessions) {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < eventSessions.size(); i++) {
                    EventSession eventSession = eventSessions.get(i);
                    list.add(eventSession.getJudul());
                }

                if (eventSessions != null) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddAgenda.this, android.R.layout.simple_list_item_1, list);
                    spSesi.setAdapter(adapter);
                    spSesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            EventSession eventSession = eventSessions.get(position);
                            id_session = eventSession.getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_date_agenda:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), START_DATE_PICKER);
                break;
            case R.id.bt_start_time_agenda:
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getSupportFragmentManager(), START_TIME_PICKER);
                break;
            case R.id.bt_end_date_agenda:
                DatePickerFragment datePickerFragment2 = new DatePickerFragment();
                datePickerFragment2.show(getSupportFragmentManager(), END_DATE_PICKER);
                break;
            case R.id.bt_end_time_agenda:
                TimePickerFragment timePickerFragment1 = new TimePickerFragment();
                timePickerFragment1.show(getSupportFragmentManager(), END_TIME_PICKER);
                break;
            case R.id.bt_add_agenda:
                boolean isEmpty = false;
                String namaAgenda = etNameAgenda.getText().toString().trim();
                String descAgenda = etDescAgenda.getText().toString().trim();
                String startDate = etStartDateAgenda.getText().toString().trim();
                String startTime = etStartTimeAgenda.getText().toString().trim();
                String endDate = etEndDateAgenda.getText().toString().trim();
                String endTime = etEndTimeAgenda.getText().toString().trim();

                if (TextUtils.isEmpty(namaAgenda)) {
                    isEmpty = true;
                    etNameAgenda.setError("Nama tidak boleh kosong");
                }

                if (TextUtils.isEmpty(descAgenda)) {
                    isEmpty = true;
                    etDescAgenda.setError("Deskripsi tidak boleh kosong");
                }

                if (TextUtils.isEmpty(startDate)) {
                    isEmpty = true;
                    etStartDateAgenda.setError("Tanggal tidak boleh kosong");
                }

                if (TextUtils.isEmpty(endDate)) {
                    isEmpty = true;
                    etEndDateAgenda.setError("Tanggal tidak boleh kosong");
                }

                if (TextUtils.isEmpty(startTime)) {
                    isEmpty = true;
                    etStartTimeAgenda.setError("Waktu tidak boleh kosong");
                }
                if (TextUtils.isEmpty(endTime)) {
                    isEmpty = true;
                    etEndTimeAgenda.setError("Waktu tidak boleh kosong");
                }

                if (!isEmpty) {

                    addAgenda();
                }

                break;
        }
    }

    @Override
    public void onDialogDataSet(String tag, int year, int mount, int dayOfMount) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, mount, dayOfMount);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        switch (tag) {
            case START_DATE_PICKER:
                etStartDateAgenda.setText(dateFormat.format(calendar.getTime()));
                break;
            case END_DATE_PICKER:
                etEndDateAgenda.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }

    }

    @Override
    public void onDialogTimeSet(String tag, int hourDay, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourDay);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        switch (tag) {
            case START_TIME_PICKER:
                etStartTimeAgenda.setText(dateFormat.format(calendar.getTime()));
                break;
            case END_TIME_PICKER:
                etEndTimeAgenda.setText(dateFormat.format(calendar.getTime()));
            default:
                break;
        }
    }

    private void addAgenda() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.3.5/myAPI/public/add-agenda";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<String, String>();
                data.put("name", etNameAgenda.getText().toString());
                data.put("description", etDescAgenda.getText().toString());
                start = etStartDateAgenda.getText().toString() + " " + etStartTimeAgenda.getText().toString();
                end = etEndDateAgenda.getText().toString() + " " + etEndTimeAgenda.getText().toString();
                data.put("start", start);
                data.put("end", end);
                data.put("event_session_id", id_session);
                data.put("event_session_event_id", sharedPrefManager.getSpIdEvent());
                data.put("event_session_event_event_type_id", "3");
                return data;
            }
        };

        queue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
