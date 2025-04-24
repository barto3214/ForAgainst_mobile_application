package com.example.bankobserver;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class B_adapter extends ArrayAdapter<Argument> {
    private Context context;
    private List<Argument> items;
    public B_adapter(Context context,List<Argument> items){
        super(context,0,items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Argument argument = items.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.argument_item, parent, false);
        }

        TextView itemText = convertView.findViewById(R.id.itemText);


        itemText.setText("Argument: " + argument.getIndex() + "\nTreść: " + argument.getTytul() + "\nPriorytet: " + argument.getPriority());

        if (argument.isCzy_za()) {
            itemText.setBackgroundResource(R.drawable.green_border);
        } else {
            itemText.setBackgroundResource(R.drawable.red_border);
        }
        return convertView;
    }
}
