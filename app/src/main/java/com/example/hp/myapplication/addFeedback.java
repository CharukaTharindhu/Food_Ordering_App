package com.example.hp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.myapplication.Model.Feedback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addFeedback extends AppCompatActivity {

    private EditText editText_feedback;
    private Button btnAddFeedback;
    private String feedback;
    String itemId;
    Feedback fb;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

         itemId = getIntent().getStringExtra("foodId");

        databaseReference = FirebaseDatabase.getInstance().getReference("Feedback").child(itemId);

        editText_feedback = (EditText) findViewById(R.id.editText_feedback);
        btnAddFeedback = (Button) findViewById(R.id.btnAddFeedback);

        btnAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeedback();
            }
        });
    }

    //add feedback

    public void addFeedback(){
        feedback = editText_feedback.getText().toString();

        if(!TextUtils.isEmpty(feedback)){
            String id = databaseReference.push().getKey();

            fb = new Feedback(feedback);
            databaseReference.child(fb.getFeedbackId()).setValue(fb);
            editText_feedback.setText("");
            openUpdateFeedback();
        }
        else
            Toast.makeText(addFeedback.this,"please enter your feedback!", Toast.LENGTH_LONG).show();
    }

    public void openUpdateFeedback(){
        Toast.makeText(getBaseContext(), feedback, Toast.LENGTH_SHORT).show();
        Intent intentupdate = new Intent(this,updateFeedback.class);
        intentupdate.putExtra("Feedback", fb);
        intentupdate.putExtra("itemId", itemId);
        startActivity(intentupdate);
        finish();
    }
}
