package com.example.godseye;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tabtwo extends Fragment {


    private DatabaseReference mdatabaseref;
    private FirebaseAuth mAuth;
    private String key;

    private ListView lv2;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tabtwo,null);

      lv2 = view.findViewById(R.id.lv2);

        //firebase

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        key = mUser.getUid();
        mdatabaseref = FirebaseDatabase.getInstance().getReference();
        mdatabaseref.keepSynced(true);

        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                showdata(dataSnapshot);

                viewprofile v1=dataSnapshot.getValue(viewprofile.class);
                String name=v1.getName();
                String phone=v1.getMobile();
                String email=v1.getEmail();
                String carno=v1.getCarno();
                String rcno=v1.getRcno();

                ArrayList<String> array =new ArrayList<>();

                array.add(name);
                array.add(phone);
                array.add(email);
                array.add(carno);
                array.add(rcno);

                ArrayAdapter<String> arrayAdapter =new ArrayAdapter (getActivity(),R.layout.simplelistview,R.id.tv,array);
                lv2.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

//    private void showdata(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds :dataSnapshot.getChildren())
//        { viewprofile user=new viewprofile();
//            user.setName(ds.child(key).getValue(viewprofile.class).getName());
//            user.setEmail(ds.child(key).getValue(viewprofile.class).getEmail());
//            user.setMobile(ds.child(key).getValue(viewprofile.class).getMobile());
//            user.setCarno(ds.child(key).getValue(viewprofile.class).getCarno());
//            user.setRcno(ds.child(key).getValue(viewprofile.class).getRcno());
//        }

//    }
}
