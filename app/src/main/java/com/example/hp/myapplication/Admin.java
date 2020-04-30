package com.example.hp.myapplication;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hp.myapplication.Common.Common;
import com.example.hp.myapplication.Interface.ItemClickListener;
import com.example.hp.myapplication.Model.Category;
import com.example.hp.myapplication.Service.ListenOrder;
import com.example.hp.myapplication.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Objects;

public class Admin extends AppCompatActivity {


    ActionBar actionbar;
    Button food_add_button;
    Button category_add_button;
    TextView txtFullName;

    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    //FireBase
    FirebaseDatabase database;
    DatabaseReference category;
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;

    //View
    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;
    private MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        food_add_button = (Button)findViewById(R.id.food_add_btn);
        category_add_button = (Button)findViewById(R.id.Category_add_btn);

        //Init Firebase:
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        Toolbar toolbar = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        food_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin.this, "Welcome for Adding New Food", Toast.LENGTH_SHORT).show();

                Intent addingFood = new Intent(Admin.this,Admin_Caregory_List.class);
                //Common.currentUser = user;
                startActivity(addingFood);
            }
        });
        category_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin.this, "Welcome for Adding New Category", Toast.LENGTH_SHORT).show();

                Intent addingCategory = new Intent(Admin.this, add_category.class);
                //Common.currentUser = user;
                startActivity(addingCategory);
            }
        });



        //Register Service
        Intent service = new Intent(Admin.this, ListenOrder.class);
        startService(service);

    }

    /*`   public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {

        } else if (id == R.id.nav_cart) {
            Intent cartIntent = new Intent(Admin.this,Cart.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_orders) {
            Intent newOrderIntent = new Intent(Admin.this,order_status.class);
            startActivity(newOrderIntent);

        } else if (id == R.id.nav_log_out) {

            //Log out
            Intent signIn = new Intent(Admin.this,SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
