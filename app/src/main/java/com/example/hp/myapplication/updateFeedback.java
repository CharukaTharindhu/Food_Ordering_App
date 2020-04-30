package com.example.hp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.myapplication.Model.Feedback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateFeedback extends AppCompatActivity {

    private TextView editText_updateFeedback;
    private Button btn_update,btn_delete,btn_back;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feedback);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("feedbackId");

        editText_updateFeedback = (EditText) findViewById(R.id.editText_updateFeedback);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_back = (Button) findViewById(R.id.btn_back);

        feedback = (Feedback) getIntent().getSerializableExtra("Feedback");
        editText_updateFeedback.setText(feedback.getDescription());

        //update feedback

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    feedback.setDescription(editText_updateFeedback.getText().toString());

                    databaseReference = FirebaseDatabase.getInstance().getReference("Feedback").child(getIntent().getStringExtra("itemId")).child(feedback.getFeedbackId());

                    databaseReference.setValue(feedback);
                    Toast.makeText(getBaseContext(), "Feedback updated!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(updateFeedback.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFeedback();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToFoodDetails();
            }
        });
    }

    //delete feedback

    private void deleteFeedback() {
        Intent intentdelete = new Intent(this, FoodDetail.class);

        DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference("Feedback").child(getIntent().getStringExtra("itemId")).child(feedback.getFeedbackId());

        deleteRef.removeValue();

        Toast.makeText(getBaseContext(), "Feedback deleted!", Toast.LENGTH_SHORT).show();
        intentdelete.putExtra("FoodId", feedback.getFeedbackId());
        startActivity(intentdelete);
    }

    private void backToFoodDetails() {
        Intent intentBack = new Intent(this, FoodDetail.class);
        intentBack.putExtra("FoodId", feedback.getFeedbackId());
        startActivity(intentBack);
    }


}
