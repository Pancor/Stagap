package com.stagap.stagap;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.Map;


public class AccountFragment extends Fragment {
    private TextView mName;
    private TextView mSurname;
    private FirebaseFirestore mDataBase;
    private FirebaseAuth mAuth;
    DocumentReference documentReference;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        mName = view.findViewById(R.id.nameTextView);
        mSurname = view.findViewById(R.id.surnameTextView);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseFirestore.getInstance();

        String userId = mAuth.getCurrentUser().getUid();

        documentReference = mDataBase.collection("Users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                mName.setText(documentSnapshot.getString("Name"));
                mSurname.setText(documentSnapshot.getString("Surname"));
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
    }
}
