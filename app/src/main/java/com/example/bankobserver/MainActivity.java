package com.example.bankobserver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bankobserver.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private List<Argument> argumenty = new ArrayList<>();
    private B_adapter adapter;
    private int ar_poz;
    private int ar_neg;
    ActivityMainBinding binding;
    Database_argument databaseArgument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        RoomDatabase.Callback callback = new RoomDatabase.Callback() {   // callback to chyba działanie w tle
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        };
        databaseArgument = Room.databaseBuilder(
                MainActivity.this,
                Database_argument.class,
                "Argumenty_DB").fallbackToDestructiveMigration().addCallback(callback).allowMainThreadQueries().build();

        adapter = new B_adapter(this,argumenty);
        binding.listView.setAdapter(adapter);

        binding.dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String za = binding.editTextTextZa.getText().toString().trim();
                String przeciw = binding.editTextTextprzeciw.getText().toString().trim();

                if (za.isEmpty() && przeciw.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wpisz jakiś argument!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int prio = Integer.parseInt(binding.spinner.getSelectedItem().toString());
                int index = argumenty.isEmpty() ? 0 : argumenty.get(argumenty.size() - 1).getIndex() + 1;

                if (!za.isEmpty()) {
                    Argument argument = new Argument(index,prio, true, za);
                    argumenty.add(argument);
                    ar_poz++;
                    dodajargdobazy(argument);
                }

                if (!przeciw.isEmpty()) {
                    Argument argument = new Argument(index,prio, false, przeciw);
                    argumenty.add(argument);
                    ar_neg++;
                    dodajargdobazy(argument);
                }

                binding.dane.setText("Argumenty pozytywne: " + ar_poz + "\nArgumenty negatywne: " + ar_neg);
                adapter.notifyDataSetChanged();
                binding.editTextTextZa.setText("");
                binding.editTextTextprzeciw.setText("");
            }
        });
        binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });



        binding.sortujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Argument> posort = databaseArgument.zwroc_Dao_arg().zwroc_posortowane();
                adapter = new B_adapter(MainActivity.this,posort);
                binding.listView.setAdapter(adapter);

            }
        });


    }
    private void dodajargdobazy(Argument argument) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                databaseArgument.zwroc_Dao_arg().wstaw_argument(argument);
            }
        });
    }
}
