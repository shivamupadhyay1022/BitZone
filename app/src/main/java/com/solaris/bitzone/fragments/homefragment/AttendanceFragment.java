package com.solaris.bitzone.fragments.homefragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.solaris.bitzone.R;
import com.solaris.bitzone.fragments.ClubinfoFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceFragment extends Fragment implements View.OnClickListener {


    private float progress = 0;
    Button buttonIncrement;
    Button buttonDecrement;
    ImageView back8;
    TextView textView_progress;
    CircularProgressBar circularProgressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewGroup viewGroup;

    public AttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceFragment newInstance(String param1, String param2) {
        AttendanceFragment fragment = new AttendanceFragment();
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_attendance, container, false);

        back8 = v.findViewById(R.id.arrowback8);
        back8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClubinfoFragment fragment = new ClubinfoFragment();
                androidx.fragment.app.FragmentManager fm1 = getActivity().getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction transaction1 = fm1.beginTransaction();
                transaction1.replace(R.id.fragment_container, fragment);
                transaction1.commit();
            }
        });
        buttonDecrement = (Button) v.findViewById(R.id.button_decr);
        buttonIncrement = (Button) v.findViewById(R.id.button_incr);
        textView_progress = (TextView) v.findViewById(R.id.text_view_progress);
        circularProgressBar = v.findViewById(R.id.progress_bar);
        circularProgressBar.setProgressWithAnimation(progress, (long) 1000.0);
        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress <= 90) {
                    progress -= 10;
                    int pr = (int) progress;
                    textView_progress.setText(""+ pr+"%");
                    circularProgressBar.setProgressWithAnimation(progress, (long) 1000.0);
                    updateProgressBar();
                }

            }
        });

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress <= 90) {
                    progress += 10;
                    int pr = (int) progress;
                    textView_progress.setText(""+ pr+"%");
                    circularProgressBar.setProgressWithAnimation(progress, (long) 1000.0);
                    updateProgressBar();
                }

            }
        });


        return v;
    }


    @Override
    public void onClick(View v) {

    }

    private void updateProgressBar() {
//        progressBar.setProgress(progress);
//        textView.setText(String.valueOf(progress));
    }
}