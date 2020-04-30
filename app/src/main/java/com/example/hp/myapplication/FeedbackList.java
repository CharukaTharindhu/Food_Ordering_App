package com.example.hp.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp.myapplication.Model.Feedback;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedbackList extends AppCompatActivity {

    private ListView feedback_list;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        feedback = new Feedback();
        feedback_list = (ListView) findViewById(R.id.feedback_list);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Feedback");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.feedback_info, R.id.feedback_info,list);

        //view feedback list
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    feedback = ds.getValue(Feedback.class);
                    list.add(feedback.getDescription().toString());
                }
                feedback_list.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
