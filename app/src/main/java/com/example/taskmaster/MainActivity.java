package com.example.taskmaster;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList;
    private TaskAdapter taskAdapter;

    private EditText etTaskTitle;
    private Spinner spinnerCategory;
    private RadioGroup rgPriority;
    private CheckBox cbReminder;
    private ProgressBar progressBar;
    private RatingBar ratingBar;
    private Button btnSaveTask;
    private RecyclerView rvTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        etTaskTitle = findViewById(R.id.etTaskTitle);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        rgPriority = findViewById(R.id.rgPriority);
        cbReminder = findViewById(R.id.cbReminder);
        progressBar = findViewById(R.id.progressBar);
        ratingBar = findViewById(R.id.ratingBar);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        rvTasks = findViewById(R.id.rvTasks);


        taskAdapter = new TaskAdapter(taskList);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskAdapter);


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTaskTitle.getText().toString().trim();
                

                String priority = "Baja";
                int selectedPriorityId = rgPriority.getCheckedRadioButtonId();
                if (selectedPriorityId == R.id.rbHigh) {
                    priority = "Alta";
                }
                

                boolean isUrgent = priority.equals("Alta") || ratingBar.getRating() >= 4.0;


                String category = "Categoría Seleccionada";
                if (spinnerCategory.getSelectedItem() != null) {
                    category = spinnerCategory.getSelectedItem().toString();
                }
                
                if (title.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor ingrese un título", Toast.LENGTH_SHORT).show();
                    return;
                }

                Task newTask = new Task(title, category, priority, isUrgent);
                taskList.add(newTask);
                taskAdapter.notifyItemInserted(taskList.size() - 1);
                
            
                etTaskTitle.setText("");
                rgPriority.clearCheck();
                cbReminder.setChecked(false);
                ratingBar.setRating(0);
                Toast.makeText(MainActivity.this, "Tarea Guardada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks) {
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task task = tasks.get(position);
            holder.tvTituloTarea.setText(task.getTitle());
            holder.tvEstadoTarea.setText("Cat: " + task.getCategory() + " - Pri: " + task.getPriority());

            if (task.isUrgent()) {
                holder.tvUrgenteBadge.setVisibility(View.VISIBLE);
            } else {
                holder.tvUrgenteBadge.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        class TaskViewHolder extends RecyclerView.ViewHolder {
            TextView tvTituloTarea;
            TextView tvEstadoTarea;
            TextView tvUrgenteBadge;

            public TaskViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTituloTarea = itemView.findViewById(R.id.tvTituloTarea);
                tvEstadoTarea = itemView.findViewById(R.id.tvEstadoTarea);
                tvUrgenteBadge = itemView.findViewById(R.id.tvUrgenteBadge);
            }
        }
    }
}