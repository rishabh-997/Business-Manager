package com.example.businessmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddContact extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText e1,e2;
    Button b1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Contacts");

        e1=findViewById(R.id.add_name);
        e2=findViewById(R.id.add_num);
        b1=findViewById(R.id.add_contact);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String name=e1.getText().toString();
                String number=e2.getText().toString();
                Contact_model model=new Contact_model(name,number);
                databaseReference.child(name).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(AddContact.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(AddContact.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(AddContact.this,MainActivity.class));
    }
}
