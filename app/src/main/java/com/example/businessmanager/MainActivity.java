package com.example.businessmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements Contact_Adapter.onNoteListener
{
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<Contact_model> arrayList;
    Contact_Adapter adapter;
    RecyclerView recyclerView;
    Contact_model model;

    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=findViewById(R.id.search);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Contacts");

        dl=findViewById(R.id.drawer_layout);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navview=findViewById(R.id.nav_view);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                filter(s.toString());
            }
        });

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id=item.getItemId();
                if(id==R.id.nav_add)
                {
                    finish();
                    startActivity(new Intent(MainActivity.this,AddContact.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Inactive Link",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        arrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter=new Contact_Adapter(this,arrayList,this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.child("name").getValue().toString();
                    String number=ds.child("contact").getValue().toString();
                    model=new Contact_model(name,number);
                    arrayList.add(model);
                }

                Collections.sort(arrayList,Contact_model.NameCompare);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    @Override
    public void onNoteClick(int position) {
        //arrayList.get(position);
        //function to be performed
        String name=arrayList.get(position).getName();
        String contact=arrayList.get(position).getContact();

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(name);
        builder.setMessage(contact);
        builder.show();

    }
    public void filter(String str)
    {
        ArrayList<Contact_model> newArraylist=new ArrayList<>();
        for(Contact_model data : arrayList)
        {
            if(data.getName().toLowerCase().contains(str.toLowerCase()))
            {
                newArraylist.add(data);
            }
        }
        adapter.filter(newArraylist);
    }
}
