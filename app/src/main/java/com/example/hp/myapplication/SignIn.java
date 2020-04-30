package com.example.hp.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.myapplication.Common.Common;
import com.example.hp.myapplication.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnSignIn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.userType, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String item = spinner.getSelectedItem().toString();

                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Please Be Patience...!");
                progressDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if user not exisit in db
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {

                            //Get User Information
                            progressDialog.dismiss();

                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());

                            if (user.getPassword().equals(edtPassword.getText().toString()) && item.equals("Admin")) {

                                Toast.makeText(SignIn.this, "Welcome to Food App Admin..!", Toast.LENGTH_SHORT).show();

                                Intent admin = new Intent(SignIn.this, Admin.class);
                                Common.currentUser = user;
                                startActivity(admin);
                                finish();

                                Toast.makeText(SignIn.this, "Welcome to Admin Homne..!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SignIn.this, "Phone no. or Password is incorrect..", Toast.LENGTH_SHORT).show();
                            }

                            if (user.getPassword().equals(edtPassword.getText().toString()) && item.equals("User")) {

                                Toast.makeText(SignIn.this, "Welcome to Food App..!", Toast.LENGTH_SHORT).show();

                                Intent home = new Intent(SignIn.this, Home.class);
                                Common.currentUser = user;
                                startActivity(home);
                                finish();

                                Toast.makeText(SignIn.this, "Welcome to Homne..!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SignIn.this, "Phone no. or Password is incorrect..", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "Please Sign Up First..!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
