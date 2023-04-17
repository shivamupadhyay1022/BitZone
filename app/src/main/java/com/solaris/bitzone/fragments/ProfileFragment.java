package com.solaris.bitzone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.solaris.bitzone.MainActivity;
import com.solaris.bitzone.R;
import com.solaris.bitzone.sharedpreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    TextView name;
    TextView email;
    TextView branch;
    TextView semester;
    TextView section;
    TextView year;
    TextView roll;
    Button logout;
    String txt_roll;
    ArrayList<HashMap> mArrayList = new ArrayList<HashMap>();
    DocumentSnapshot document;
    Map<String, Object> document2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = v.findViewById(R.id.profile_name);
        email = v.findViewById(R.id.profile_email);
        branch = v.findViewById(R.id.profile_branch);
        semester = v.findViewById(R.id.profile_sem);
        section = v.findViewById(R.id.profile_section);
        year = v.findViewById(R.id.profile_year);
        logout = v.findViewById(R.id.profile_logout);
        roll = v.findViewById(R.id.profile_roll);

//        if(sharedpreferences.user != null ) {
//            DocumentReference documentReference = sharedpreferences.mRootRef.collection("users").document(sharedpreferences.id);
//            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                @Override
//                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                    name.setText(value.getString("name"));
//                    email.setText(value.getString("email"));
//                    branch.setText(value.getString("branch"));
//                    semester.setText(value.getString("semester"));
//                    section.setText(value.getString("section"));
//                    year.setText(value.getString("admission_year"));
//                    roll.setText(value.getString("roll"));
//
//                }
//            });
//        }
        name.setText(sharedpreferences.txt_name);
        email.setText(sharedpreferences.txt_email);
        branch.setText(sharedpreferences.txt_branch);
        semester.setText(sharedpreferences.txt_semester);
        section.setText(sharedpreferences.txt_section);
        year.setText(sharedpreferences.txt_year);
        roll.setText(sharedpreferences.txt_roll);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                if (sharedpreferences.mAuth.getCurrentUser() == null){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}