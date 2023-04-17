package com.solaris.bitzone.modal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solaris.bitzone.R;

public class customattendance extends LinearLayout {

    private final TypedArray attributes;
    private final TextView subject;
    private final TextView att_days;
    private final TextView day_possible;
    private final TextView percent;


    public customattendance(Context context, AttributeSet attrs){
        super(context, attrs);
        inflate(context, R.layout.customattendance,this);
        attributes = context.obtainStyledAttributes(attrs,R.styleable.customattendance);
        subject = findViewById(R.id.attendance_subject);
        att_days = findViewById(R.id.attendance_days_no);
        day_possible = findViewById(R.id.attendance_status);
        percent = findViewById(R.id.text_view_progress);
        setSubject(attributes.getString(R.styleable.customattendance_subject));
        setAttdays(attributes.getString(R.styleable.customattendance_attendance_days));
        setDaypossible(attributes.getString(R.styleable.customattendance_days_possible));
        setPercent(attributes.getString(R.styleable.customattendance_percentage));
    }

    private void setPercent(String string) {
        percent.setText(string);
    }

    private void setDaypossible(String string) {
        day_possible.setText(string);
    }

    private void setAttdays(String string) {
        att_days.setText(string);
    }

    private void setSubject(String string) {
        subject.setText(string);
    }
}