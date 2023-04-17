package com.solaris.bitzone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.solaris.bitzone.R;
import com.solaris.bitzone.sharedpreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubFragment extends Fragment implements View.OnClickListener {

    ImageView test;
    boolean count = true;
    TextView nss;
    TextView litsoc;
    TextView iet;
    TextView robolution;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubFragment newInstance(String param1, String param2) {
        ClubFragment fragment = new ClubFragment();
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
        View v =  inflater.inflate(R.layout.fragment_club, container, false);
        nss = v.findViewById(R.id.textView_nss);
        iet = v.findViewById(R.id.textView_iet);
        robolution = v.findViewById(R.id.textView_robolution);
        nss.setOnClickListener(this::onClick);
        iet.setOnClickListener(this::onClick);
        robolution.setOnClickListener(this::onClick);
        return v;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        String id2 = String.valueOf(id);
        switch (id) {
            case R.id.textView_nss:
                sharedpreferences.club = "nss";
                openfragment();
                break;
            case R.id.textView_iet:
                sharedpreferences.club = "iet";
                openfragment();
                break;
            case R.id.textView_robolution:
                sharedpreferences.club = "robolution";
                openfragment();
                break;
            default:
                Toast.makeText(getContext(), sharedpreferences.club, Toast.LENGTH_SHORT).show();
        }

    }
    public void openfragment(){
        Fragment fragment = new ClubinfoFragment();
        androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
        transaction1.replace(R.id.fragment_container, fragment);
        transaction1.commit();
    }
}