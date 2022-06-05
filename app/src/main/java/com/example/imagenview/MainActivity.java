package com.example.imagenview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ImagenModel> imagenModelArrayList;
    private RecyclerImagenAdapter recyclerImagenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recyclerView);

        //recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerImagenAdapter);

        imagenModelArrayList = new ArrayList<>();

        clearAll();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("imagenes");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    ImagenModel imagenModel = new ImagenModel();
                    imagenModel.setImagenurl(snapshot.getValue().toString());

                    imagenModelArrayList.add(imagenModel);
                }
                recyclerImagenAdapter = new  RecyclerImagenAdapter(getApplicationContext(),imagenModelArrayList);
                recyclerView.setAdapter(recyclerImagenAdapter);
                recyclerImagenAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Error : "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearAll() {

        if(imagenModelArrayList !=null){
            imagenModelArrayList.clear();

            if (recyclerImagenAdapter !=null){
                recyclerImagenAdapter.notifyDataSetChanged();
            }
        }
        imagenModelArrayList= new ArrayList<>();
    }

}