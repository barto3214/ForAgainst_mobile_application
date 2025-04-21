package com.example.bankobserver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

                int id = argumenty.isEmpty() ? 0 : argumenty.get(argumenty.size() - 1).getID() + 1;
                int prio = Integer.parseInt(binding.spinner.getSelectedItem().toString());

                if (!za.isEmpty()) {
                    argumenty.add(new Argument(prio,id, true, za));
                    ar_poz++;
                }

                if (!przeciw.isEmpty()) {
                    argumenty.add(new Argument(prio,id, false, przeciw));
                    ar_neg++;
                }
                binding.dane.setText("Argumenty pozytywne: " + ar_poz + "\nArgumenty negatywne: " + ar_neg);
                adapter.notifyDataSetChanged();
                binding.editTextTextZa.setText("");
                binding.editTextTextprzeciw.setText("");
            }
        });



    }
}
