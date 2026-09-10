package com.example.taskmaster;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.example.taskmaster.R;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button btnCreateTask = findViewById(R.id.btnCreateTask);
        // Aquí iría la inicialización del RecyclerView y el Adapter

        // btnCreateTask.setOnClickListener removed as AddTaskActivity is removed
    }
}