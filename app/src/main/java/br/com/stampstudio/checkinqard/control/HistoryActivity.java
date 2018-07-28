package br.com.stampstudio.checkinqard.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.stampstudio.checkinqard.HistoryAdapter;
import br.com.stampstudio.checkinqard.R;
import br.com.stampstudio.checkinqard.model.Day;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rcvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<Day> listDays = (ArrayList<Day>) getIntent().getSerializableExtra("HISTORY_LIST");

        rcvHistory = findViewById(R.id.rcvHistory);

        HistoryAdapter rcvHistoryAdapter = new HistoryAdapter(listDays);

        rcvHistory.setAdapter(rcvHistoryAdapter);

        rcvHistory.setLayoutManager(new LinearLayoutManager(this));
    }
}
