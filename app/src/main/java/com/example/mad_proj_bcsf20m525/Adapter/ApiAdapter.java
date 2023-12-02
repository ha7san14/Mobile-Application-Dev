package com.example.mad_proj_bcsf20m525.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mad_proj_bcsf20m525.Activities.API.Quote;
import com.example.mad_proj_bcsf20m525.R;

import java.util.List;

public class ApiAdapter extends ArrayAdapter<Quote> {

    public ApiAdapter(Context context, List<Quote> quotes) {
        super(context, 0, quotes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Quote quote = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_api, parent, false);
        }

        TextView quoteText = convertView.findViewById(R.id.textQuote);
        TextView authorText = convertView.findViewById(R.id.textAuthor);

        if (quote != null) {
            quoteText.setText(quote.getQuote());
            authorText.setText(quote.getAuthor());
        }

        return convertView;
    }
}
