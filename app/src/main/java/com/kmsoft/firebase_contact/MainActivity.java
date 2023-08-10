package com.kmsoft.firebase_contact;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kmsoft.firebase_contact.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView_Adapter adapter;
    RecyclerView recyclerView;
    ActivityMainBinding binding;
    DatabaseReference myRef;
    FirebaseDatabase database;

    ArrayList<DataModal> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView  =findViewById(R.id.recyclerview);
        adapter = new RecyclerView_Adapter(MainActivity.this,list);
        LinearLayoutManager manager= new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Contact").push();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    DataModal modal = dataSnapshot1.getValue(DataModal.class);
                    list.add(modal);
                }
                adapter.notifyDataSetChanged();
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("TTT", "Value is: " + value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TTT", "Failed to read value.", error.toException());
            }
        });


        binding.floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Item.class);
                intent.putExtra("button","add");
                startActivity(intent);
            }
        });
    }
}