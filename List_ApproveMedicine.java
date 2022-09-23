package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.miniproject.Class.HomeIsolationPatient;
import com.example.miniproject.Class.Medicine_Request;
import com.example.miniproject.Class.MyAdapter2;
import com.example.miniproject.Class.MyAdapter_ApproveMedicine;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class List_ApproveMedicine extends AppCompatActivity  {

    RecyclerView recyclerView ;
    ArrayList <Medicine_Request> list ;
    DatabaseReference databaseReference ;
    MyAdapter_ApproveMedicine myAdapterApproveMedicine ;
    FirebaseDatabase database;





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(List_ApproveMedicine.this,Physician_MainPage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_approve_medicine);
        recyclerView = findViewById(R.id.recyclerviewRequest);
        databaseReference  = FirebaseDatabase.getInstance("https://projecthomeisolation-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Login/Requst_medicine");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterApproveMedicine =new MyAdapter_ApproveMedicine(this,list);
        recyclerView.setAdapter(myAdapterApproveMedicine);
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Medicine_Request medicine_request = snapshot1.getValue(Medicine_Request.class);
                    list.add(medicine_request);

                }
                myAdapterApproveMedicine.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void  ClickApprove (View view){
         Toast.makeText(List_ApproveMedicine.this,"Approve",Toast.LENGTH_LONG).show();
    }


}