package com.jar.customdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateTaskFunction extends AppCompatActivity {
    Button button, add, cancel;
    EditText taskName, taskDate, taskStartTime, taskEndTime, taskNote;
    Dialog dialog;
    String tname, tdate, tstart, tend, tnote;
    TextView tnameTV, tdateTV, tstartTV, tendTV, tnoteTV;
    private DatabaseReference root, user_name, backlog;
    ValueEventListener readUserData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createfunction);


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT; // Specify desired width
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; // Specify desired height
        dialog.getWindow().setAttributes(layoutParams);

        button = findViewById(R.id.button);

        taskName = dialog.findViewById(R.id.taskName);
        taskDate = dialog.findViewById(R.id.taskDate);
        taskStartTime = dialog.findViewById(R.id.taskStartTime);
        taskEndTime = dialog.findViewById(R.id.taskEndTime);
        taskNote = dialog.findViewById(R.id.taskNote);
        add = dialog.findViewById(R.id.buttonAdd);
        cancel = dialog.findViewById(R.id.buttonCancel);
        tnameTV = findViewById(R.id.tnameTV);
        tdateTV = findViewById(R.id.tdateTV);
        tstartTV = findViewById(R.id.tstartTV);
        tendTV = findViewById(R.id.tendTV);



        //initialize components for database
        //realtime database


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tname = taskName.getText().toString();
                tdate = taskDate.getText().toString();
                tstart = taskStartTime.getText().toString();
                tend = taskEndTime.getText().toString();
                tnote = taskNote.getText().toString();

                root = FirebaseDatabase.getInstance().getReference();
                user_name = root.child("users").child("Joar");
                createTask(tname, tdate, tstart, tend, tnote);
                backlog = user_name.child("backlog").child("userTodo").child("Joar");
                readUserData = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        TaskDataClass taskData = snapshot.getValue(TaskDataClass.class);
                        tname = taskData.getTname();
                        tdate = taskData.getTdate();
                        tstart = taskData.getTstart();
                        tend = taskData.getTend();
                        tnote = taskData.getTnote();

                        tnameTV.setText(tname);
                        tdateTV.setText(tdate);
                        tstartTV.setText(tstart);
                        tendTV.setText(tend);
                        tnoteTV.setText(tnote);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                };
                user_name.addValueEventListener(readUserData);

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
    void createTask(String tname, String tdate, String tstart, String tend, String tnote){
        TaskDataClass taskData = new TaskDataClass(tname, tdate, tstart, tend, tnote);
        user_name = root.child("users").child("Joar");
        user_name.setValue(taskData);
    }
}