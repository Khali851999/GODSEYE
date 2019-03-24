package com.example.godseye;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {


    private FirebaseAuth mAuth;

    ProgressDialog mDialog;
    private DatabaseReference mDatabase;


    EditText etname,etemailreg,etpassreg,etphone,etrcno,etcarno;
    Button btnreg;
    TextView alreadyamem;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mDialog=new ProgressDialog(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Profile");
        mAuth = FirebaseAuth.getInstance();

        mDatabase.keepSynced(true);



        etname=findViewById(R.id.etname);
        etemailreg=findViewById(R.id.etemailreg);
        etpassreg=findViewById(R.id.etpassreg);
        etphone=findViewById(R.id.etphone);
        etcarno=findViewById(R.id.etcarno);
        etrcno=findViewById(R.id.etrcno);
        btnreg=findViewById(R.id.btnreg);
        alreadyamem=findViewById(R.id.tvalreadyamem);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = etname.getText().toString().trim();
                final String email=etemailreg.getText().toString().trim();
                final String pass = etpassreg.getText().toString().trim();
                final String phone=etphone.getText().toString().trim();
                final String carno = etcarno.getText().toString().trim();
                final String rcno=etrcno.getText().toString().trim();

                if (name.isEmpty()||email.isEmpty()||pass.isEmpty()||phone.isEmpty()||carno.isEmpty()||rcno.isEmpty())
                {
                    Toast.makeText(registration.this,"Fields Cannot Be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    mDialog.setMessage("Processing");
                    mDialog.show();
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(registration.this, "Successful", Toast.LENGTH_SHORT).show();


                                FirebaseUser mUser = mAuth.getCurrentUser();
                                String Uid = mUser.getUid();
                                mDatabase.child(Uid).child("Name").setValue(name);
                                mDatabase.child(Uid).child("Mobile").setValue(phone);
                                mDatabase.child(Uid).child("Email").setValue(email);
                                mDatabase.child(Uid).child("Carno").setValue(carno);
                                mDatabase.child(Uid).child("RCno").setValue(rcno);





                                Intent intent = new Intent(registration.this, otpverification.class);
                                startActivity(intent);
                                mDialog.dismiss();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(registration.this, " Authentication failed.", Toast.LENGTH_LONG).show();
                                Log.w("error112", "signInWithEmail:failure", task.getException());
                                mDialog.dismiss();
                            }
                }
            });
                };
            }
        });
    }
    public void alreadyamem(View view)
    {
        Intent intent=new Intent(registration.this,MainActivity.class);
        startActivity(intent);
    }

}
