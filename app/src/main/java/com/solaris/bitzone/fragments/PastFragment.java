package com.solaris.bitzone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.solaris.bitzone.R;
import com.solaris.bitzone.sharedpreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastFragment extends Fragment {

    ImageView back6;
    TextView event;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastFragment newInstance(String param1, String param2) {
        PastFragment fragment = new PastFragment();
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
        View v= inflater.inflate(R.layout.fragment_past, container, false);
        event = v.findViewById(R.id.past_event);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        switch (sharedpreferences.club) {
            case "nss":
                databaseReference.child("Clubs").child("nss").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("past").getValue().toString();
                            event.setText(url1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;

            case "robolution":
                DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference();
                databaseReference3.child("Clubs").child("robolution").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("past").getValue().toString();
                            event.setText(url1);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case "iet":
                databaseReference.child("Clubs").child("iet").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("past").getValue().toString();
                            event.setText(url1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            default:
                Toast.makeText(getContext(), sharedpreferences.club, Toast.LENGTH_SHORT).show();
        back6 = v.findViewById(R.id.arrowba);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ClubinfoFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, fragment);
                transaction1.commit();
            }
        });
    }
        return v;
    }}