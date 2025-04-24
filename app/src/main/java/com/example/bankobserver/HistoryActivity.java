package com.example.bankobserver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.bankobserver.databinding.ActivityMainBinding;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    Button powrot;
    ListView lista;
    ArrayAdapter arrayAdapter;
    private List<Argument> arggumenty;
    Database_argument databaseArgument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        databaseArgument = Room.databaseBuilder(
                        getApplicationContext(),
                        Database_argument.class,
                        "Argumenty_DB")
                .fallbackToDestructiveMigration() // lub .addMigrations(...) jeśli masz migrację
                .allowMainThreadQueries() // jeśli używasz bazy w głównym wątku
                .build();

        powrot = findViewById(R.id.powrot);
        lista = findViewById(R.id.history_listview);

        powrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        arggumenty = databaseArgument.zwroc_Dao_arg().zwroc_argumenty();

        arrayAdapter = new ArrayAdapter<>(
                HistoryActivity.this,
                android.R.layout.simple_list_item_1,
                arggumenty
        );
        lista.setAdapter(arrayAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                databaseArgument.zwroc_Dao_arg().usun_argument(arggumenty.get(i));
                arggumenty.remove(i);
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }


}
