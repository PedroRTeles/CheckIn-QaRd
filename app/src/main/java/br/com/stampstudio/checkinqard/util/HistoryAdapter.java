package br.com.stampstudio.checkinqard.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.stampstudio.checkinqard.R;
import br.com.stampstudio.checkinqard.model.Day;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<Day> mDays;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDay;
        public TextView txtHourT1;
        public TextView txtHourT2;
        public TextView txtHourT3;
        public TextView txtHourT4;

        ViewHolder(View view) {
            super(view);

            txtDay = view.findViewById(R.id.txtDay);
            txtHourT1 = view.findViewById(R.id.txtHourT1);
            txtHourT2 = view.findViewById(R.id.txtHourT2);
            txtHourT3 = view.findViewById(R.id.txtHourT3);
            txtHourT4 = view.findViewById(R.id.txtHourT4);
        }
    }

    public HistoryAdapter(ArrayList<Day> days) {
        mDays = days;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View historyView = inflater.inflate(R.layout.history_list, parent, false);

        return new ViewHolder(historyView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int position) {
        Day day = mDays.get(position);

        String time[] = day.getTime();

        TextView txtDay = viewHolder.txtDay;
        TextView txtHourT1 = viewHolder.txtHourT1;
        TextView txtHourT2 = viewHolder.txtHourT2;
        TextView txtHourT3 = viewHolder.txtHourT3;
        TextView txtHourT4 = viewHolder.txtHourT4;

        txtDay.setText(day.getDate());
        txtHourT1.setText(time[0]);
        txtHourT2.setText(time[1]);
        txtHourT3.setText(time[2]);
        txtHourT4.setText(time[3]);
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }
}
