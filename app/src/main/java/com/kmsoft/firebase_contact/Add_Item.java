package com.kmsoft.firebase_contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kmsoft.firebase_contact.databinding.ActivityAddItemBinding;

public class Add_Item extends AppCompatActivity {

    ActivityAddItemBinding binding;
    DatabaseReference myRef;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String button = getIntent().getStringExtra("button");

        if(button.equals("add")){
            binding.addbtn.setVisibility(View.VISIBLE);
        }
        if(button.equals("update")){
            binding.updatebtn.setVisibility(View.VISIBLE);
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Contact").push();

        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModal modal = new DataModal(binding.name.getText().toString(),binding.number.getText().toString());
                myRef.setValue(modal);
                Intent intent = new Intent(Add_Item.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String id = String.valueOf(getIntent().getIntExtra("id",0));
        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");

        binding.name.setText(name);
        binding.number.setText(number);

        binding.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Item.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}