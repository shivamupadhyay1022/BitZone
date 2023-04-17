package com.solaris.bitzone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.solaris.bitzone.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SarcFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SarcFragment extends Fragment {

    ConstraintLayout intern;
    ConstraintLayout rel;
    TextView sarcnews;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SarcFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SarcFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SarcFragment newInstance(String param1, String param2) {
        SarcFragment fragment = new SarcFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sarc, container, false);
        sarcnews = v.findViewById(R.id.sarc_news);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("sarc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String url1 = snapshot.child("news").getValue().toString();
                    sarcnews.setText(url1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        intern = v.findViewById(R.id.sarc_intern);
        intern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new InternFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, fragment);
                transaction1.commit();
            }
        });
        rel=v.findViewById(R.id.sarc_rel);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RelFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, fragment);
                transaction1.commit();

            }
        });
        return v;
    }
}