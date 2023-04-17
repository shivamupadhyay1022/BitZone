package com.solaris.bitzone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.solaris.bitzone.fragments.homefragment.AttendanceFragment;
import com.solaris.bitzone.fragments.homefragment.BusFragment;
import com.solaris.bitzone.fragments.homefragment.HolidayFragment;
import com.solaris.bitzone.fragments.homefragment.MapFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    HashMap<String, Integer> HashMapForLocalRes ;
    ConstraintLayout attendance;
    ConstraintLayout bus;
    ConstraintLayout holiday;
    ConstraintLayout map;
    TextView notice;
//    Drawable d = new BitmapDrawable(getResources(), sharedpreferences.bitmap);
//    int locA = getResources().getIdentifier( "poster1", "drawable", getActivity().getPackageName());
//    int[] images = {R.drawable.poster1,
//            R.drawable.poster3,
//            R.drawable.poster2,};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        notice= v.findViewById(R.id.textnews);
        map= v.findViewById(R.id.home_map);
        holiday = v.findViewById(R.id.home_holiday);
        bus = v.findViewById(R.id.home_bus);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        attendance= v.findViewById(R.id.home_attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment Profilefragment = new AttendanceFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, Profilefragment);
                transaction1.commit();
            }
        });

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment holidayfragment = new HolidayFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, holidayfragment);
                transaction1.commit();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mapfragment = new MapFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, mapfragment);
                transaction1.commit();
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment busfragment = new BusFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, busfragment);
                transaction1.commit();
            }
        });
//        sliderView = v.findViewById(R.id.image_slider);
//
//        SliderAdapter sliderAdapter = new SliderAdapter(images);
//
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String url1 = snapshot.child("Notice").getValue().toString();
                    notice.setText(url1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("posters").child("nss").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String url1 = snapshot.child("poster1").getValue().toString();
                    String url2 = snapshot.child("poster2").getValue().toString();
                    String url3 = snapshot.child("poster3").getValue().toString();

                    String string1 = snapshot.child("string1").getValue().toString();
                    String string2 = snapshot.child("string2").getValue().toString();
                    String string3 = snapshot.child("string3").getValue().toString();

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


        return v;
    }
}