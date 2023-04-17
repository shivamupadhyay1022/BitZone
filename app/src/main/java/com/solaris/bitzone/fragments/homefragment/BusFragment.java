package com.solaris.bitzone.fragments.homefragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.solaris.bitzone.R;
import com.solaris.bitzone.fragments.HomeFragment;
import com.solaris.bitzone.sharedpreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusFragment extends Fragment {

    ImageView back3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusFragment newInstance(String param1, String param2) {
        BusFragment fragment = new BusFragment();
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
        View v= inflater.inflate(R.layout.fragment_bus, container, false);
        back3 = v.findViewById(R.id.arrowback3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment fragment = new HomeFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, fragment);
                transaction1.commit();
            }
        });
        TableLayout tl3 = (TableLayout) v.findViewById(R.id.table_m3);
        sharedpreferences.mRootRef.collection("bus").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int i=0;
                    for (DocumentSnapshot document : task.getResult()) {
                        TableRow tr = new TableRow(getActivity());
                        tr.setOnClickListener(this::onClick);
                        //tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                        // Make TV to hold the details
                        TextView tv = new TextView(getActivity());
                        tv.setText(document.getId().toString().substring(2) +" : "+ document.getString("loc"));
                        tv.setPadding(80, 40, 30, 50);
                        tv.setTextSize(18);
                        tv.setTextColor(Color.WHITE);
//                        tv.setBackgroundResource(R.drawable.shape);
//                        tv.setHeight(32);
/*
            tv.setGravity(Gravity.CENTER);
*/
                        tr.addView(tv);
                        tl3.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                        ++i;
                    }
                }
            }

            private void onClick(View view) {
                Toast.makeText(getContext(), ""+getView().getId(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error: "+e, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}