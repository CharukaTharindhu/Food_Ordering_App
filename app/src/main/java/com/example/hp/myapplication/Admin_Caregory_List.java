package com.example.hp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hp.myapplication.Interface.ItemClickListener;
import com.example.hp.myapplication.Model.Category;
import com.example.hp.myapplication.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Caregory_List extends AppCompatActivity {

    //View
    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;

    //FireBase
    FirebaseDatabase database;
    DatabaseReference category;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__caregory__list);

        //Init Firebase:
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");


        //bind manu with firebase
        recycle_menu  = (RecyclerView)findViewById(R.id.recycler_menu);
        recycle_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle_menu.setLayoutManager(layoutManager);
        loadMenu();
    }
    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_list,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                //Picasso.with(Home.this).load(model.getImage()).into(viewHolder.imageView);
                //Picasso.get(getApplicationContext()).load(model.getImage()).into(viewHolder.imageView);
                Glide.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);

                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Admin_Caregory_List.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                        //Get catogary id and send to new activity
                        Intent foodlist = new Intent(Admin_Caregory_List.this,newFoodList.class);
                        //becouse categoryId is key,so we just get key of this item
                        foodlist.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodlist);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recycle_menu.setAdapter(adapter);
    }
}
