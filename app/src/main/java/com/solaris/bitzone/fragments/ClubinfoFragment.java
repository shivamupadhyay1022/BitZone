package com.solaris.bitzone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.solaris.bitzone.R;
import com.solaris.bitzone.fragments.homefragment.HolidayFragment;
import com.solaris.bitzone.sharedpreferences;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClubinfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubinfoFragment extends Fragment implements View.OnClickListener {
    TextView news;
    TextView title;
    ImageView back;
    ArrayList<SlideModel> imageList = new ArrayList<>();
    ConstraintLayout member;
    ConstraintLayout past;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClubinfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubinfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubinfoFragment newInstance(String param1, String param2) {
        ClubinfoFragment fragment = new ClubinfoFragment();
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
        View v= inflater.inflate(R.layout.fragment_clubinfo, container, false);

        member= v.findViewById(R.id.info_member);
        past = v.findViewById(R.id.info_past);
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment holidayfragment = new PastFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, holidayfragment);
                transaction1.commit();
            }
        });
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment holidayfragment = new MemberFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, holidayfragment);
                transaction1.commit();
            }
        });

        back= v.findViewById(R.id.arrowback);
        back.setOnClickListener(this::onClick);
        news =v.findViewById(R.id.textView_news);
        title = v.findViewById(R.id.clubinfo_title);
        news.setText("Latest News");
        title.setText(sharedpreferences.club);
        switch (sharedpreferences.club) {
            case "nss":
                title.setText("NSS");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Clubs").child("nss").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("news").getValue().toString();
                            news.setText(url1);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                databaseReference.child("Clubs").child("nss").child("posters").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("one").getValue().toString();
                            String url2 = snapshot.child("two").getValue().toString();
                            String url3 = snapshot.child("three").getValue().toString();

                            String string1 = snapshot.child("stringone").getValue().toString();
                            String string2 = snapshot.child("stringtwo").getValue().toString();
                            String string3 = snapshot.child("stringthree").getValue().toString();

                            imageList.add(new SlideModel(url1, string1, ScaleTypes.CENTER_INSIDE));
                            imageList.add(new SlideModel(url2, string2, ScaleTypes.CENTER_INSIDE));
                            imageList.add(new SlideModel(url3, string3, ScaleTypes.CENTER_INSIDE));
                            ImageSlider imageSlider = v.findViewById(R.id.image_slider);
                            imageSlider.setImageList(imageList);


                            imageSlider.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemSelected(int i) {
                                    if (i==0){
                                        Toast.makeText(getContext(), string1, Toast.LENGTH_SHORT).show();
                                    }else if(i==1){
                                        Toast.makeText(getContext(), string2, Toast.LENGTH_SHORT).show();
                                    }else if(i==2){
                                        Toast.makeText(getContext(), string3, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;

            case "robolution":
                title.setText("Robolution");
                DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference();
                databaseReference3.child("Clubs").child("robolution").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("news").getValue().toString();
                            news.setText(url1);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                databaseReference3.child("Clubs").child("robolution").child("posters").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("one").getValue().toString();
                            String url2 = snapshot.child("two").getValue().toString();
                            String url3 = snapshot.child("three").getValue().toString();

                            String string1 = snapshot.child("stringone").getValue().toString();
                            String string2 = snapshot.child("stringtwo").getValue().toString();
                            String string3 = snapshot.child("stringthree").getValue().toString();

                            imageList.add(new SlideModel(url1, string1, ScaleTypes.CENTER_INSIDE));
                            imageList.add(new SlideModel(url2, string2, ScaleTypes.CENTER_INSIDE));
                            imageList.add(new SlideModel(url3, string3, ScaleTypes.CENTER_INSIDE));
                            ImageSlider imageSlider = v.findViewById(R.id.image_slider);
                            imageSlider.setImageList(imageList);


                            imageSlider.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemSelected(int i) {
                                    if (i==0){
                                        Toast.makeText(getContext(), string1, Toast.LENGTH_SHORT).show();
                                    }else if(i==1){
                                        Toast.makeText(getContext(), string2, Toast.LENGTH_SHORT).show();
                                    }else if(i==2){
                                        Toast.makeText(getContext(), string3, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case "iet":
                title.setText("IET");
                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference();
                databaseReference2.child("Clubs").child("iet").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("news").getValue().toString();
                            news.setText(url1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                databaseReference2.child("Clubs").child("iet").child("posters").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String url1 = snapshot.child("one").getValue().toString();
                            String url2 = snapshot.child("two").getValue().toString();
                            String url3 = snapshot.child("three").getValue().toString();

                            String string1 = snapshot.child("stringone").getValue().toString();
                            String string2 = snapshot.child("stringtwo").getValue().toString();
                            String string3 = snapshot.child("stringthree").getValue().toString();

                            imageList.add(new SlideModel(url1, string1, ScaleTypes.CENTER_INSIDE));
                            imageList.add(new SlideModel(url2, string2, ScaleTypes.CENTER_INSIDE));
                            imageList.add(new SlideModel(url3, string3, ScaleTypes.CENTER_INSIDE));
                            ImageSlider imageSlider = v.findViewById(R.id.image_slider);
                            imageSlider.setImageList(imageList);


                            imageSlider.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemSelected(int i) {
                                    if (i==0){
                                        Toast.makeText(getContext(), string1, Toast.LENGTH_SHORT).show();
                                    }else if(i==1){
                                        Toast.makeText(getContext(), string2, Toast.LENGTH_SHORT).show();
                                    }else if(i==2){
                                        Toast.makeText(getContext(), string3, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            default:
                Toast.makeText(getContext(), sharedpreferences.club, Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch (id) {
            case R.id.arrowback:
                ClubFragment fragment = new ClubFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, fragment);
                transaction1.commit();
                break;
            default:
                Toast.makeText(getContext(), sharedpreferences.club, Toast.LENGTH_SHORT).show();
        }

    }
}