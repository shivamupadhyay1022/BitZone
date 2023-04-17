package com.solaris.bitzone.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.solaris.bitzone.R;
import com.solaris.bitzone.modal.DataModal;
import com.solaris.bitzone.sharedpreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int count = 0;
    int co = 0;
    ListView coursesLV;
    ArrayList<DataModal> dataModalArrayList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        View v= inflater.inflate(R.layout.fragment_course, container, false);
//        coursesLV = v.findViewById(R.id.idLVCourses);
//        dataModalArrayList = new ArrayList<>();
//        loadDatainListview();
        TableLayout tl = (TableLayout) v.findViewById(R.id.table_m);
        sharedpreferences.mRootRef.collection("courses").document("ece").collection("first").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        tv.setText(document.getId() +" : "+ document.getString("syllabus"));
                        tv.setPadding(80, 40, 30, 50);
                        tv.setTextSize(18);
                        tv.setTextColor(Color.WHITE);
//                        tv.setBackgroundResource(R.drawable.shape);
//                        tv.setHeight(32);
/*
            tv.setGravity(Gravity.CENTER);
*/
                        tr.addView(tv);
                        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
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
//
//        for (int i = 0; i <= sharedpreferences.count; i++) {
//            // Make TR
//            TableRow tr = new TableRow(getActivity());
//            tr.setId(2456 + i);
//            tr.setOnClickListener(this::onClick);
//            //tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//
//            // Make TV to hold the details
//            TextView tv = new TextView(getActivity());
//            tv.setText("Elements.subjects[i][0] " + co);
//            tv.setPadding(80, 40, 30, 50);
//            tv.setTextSize(18);
//            tv.setTextColor(Color.WHITE);
///*
//            tv.setGravity(Gravity.CENTER);
//*/
//            tr.addView(tv);
//
//
//
//            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        }

        return v;
    }

//    private void loadDatainListview() {
//        // below line is use to get data from Firebase
//        // firestore using collection in android.
//        sharedpreferences.mRootRef.collection("courses").document("ece").collection("first").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        // after getting the data we are calling on success method
//                        // and inside this method we are checking if the received
//                        // query snapshot is empty or not.
//                        if (!queryDocumentSnapshots.isEmpty()) {
//                            // if the snapshot is not empty we are hiding
//                            // our progress bar and adding our data in a list.
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d : list) {
//                                // after getting this list we are passing
//                                // that list to our object class.
//                                DataModal dataModal = d.toObject(DataModal.class);
//
//                                // after getting data from Firebase we are
//                                // storing that data in our array list
//                                dataModalArrayList.add(dataModal);
//                            }
//                            // after that we are passing our array list to our adapter class.
//                            CoursesLVAdapter adapter = new CoursesLVAdapter(getContext(), dataModalArrayList);
//
//                            // after passing this array list to our adapter
//                            // class we are setting our adapter to our list view.
//                            coursesLV.setAdapter(adapter);
//                        } else {
//                            // if the snapshot is empty we are displaying a toast message.
//                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // we are displaying a toast message
//                        // when we get any error from Firebase.
//                        Toast.makeText(getContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    @Override
    public void onClick(View view) {
        int goo = view.getId();
        int got = goo - 2456;
        Toast.makeText(getActivity(), "Clicked on Button:- " + goo + " Atm no " + got, Toast.LENGTH_SHORT).show();
        sharedpreferences.mRootRef.collection("courses").document("ece").collection("first").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    Toast.makeText(getContext(), "No of cout: "+count, Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error: "+e, Toast.LENGTH_SHORT).show();
            }
        });


    }
}