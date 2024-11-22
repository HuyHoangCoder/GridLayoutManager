package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        List<String> itemList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            itemList.add("Item " + i);
        }

        // Thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3); // 3 cột
        recyclerView.setLayoutManager(gridLayoutManager);
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}