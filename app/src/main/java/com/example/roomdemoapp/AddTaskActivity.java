package com.example.roomdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    EditText editTextTask, editTextDesc, editTextFinishBy;
    Button button_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        button_save = findViewById(R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

    }

    public void saveTask()
    {
        String sTask = editTextTask.getText().toString().trim();
        String sDesc = editTextDesc.getText().toString().trim();
        String sFinishBy = editTextFinishBy.getText().toString().trim();


        //Validate and show errors
        if (sTask.isEmpty()) {
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.setError("Finish by required");
            editTextFinishBy.requestFocus();
            return;
        }



        class SaveTask extends AsyncTask<Void, Void, Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {

                Task task = new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

                //adding to database using DatabaseClient
                //
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;


            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
               Intent in = new Intent(getApplicationContext(), MainActivity.class);
               startActivity(in);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }


        SaveTask st = new SaveTask();
        st.execute();


    }
}

    //1. The above code is very simple, we created an AsyncTask to perform our operation because
    //   if we will try to perform the database operation in main thread it will crash our application.
   // 2. For saving the task we just created the object and called the insert method that we created
    //   in our TaskDao interface.
    //3. You can try testing this but first you need to open this activity and for this you need to
    //   code the open functionality in the MainActivity. So what you have to do to test it is, attach a click listener on the add button that we have created in MainActivity and open AddTaskActivity when the add button is clicked.