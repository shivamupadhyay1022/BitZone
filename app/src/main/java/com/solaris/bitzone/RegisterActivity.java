package com.solaris.bitzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText roll;
    private EditText sec;
    private TextInputLayout semester;
    private EditText admyear;
    private Button register;




    ProgressDialog pd;

    TextInputLayout lay_branch;
    AutoCompleteTextView act_branch;

    ArrayList<String> arraylist_branch;
    ArrayAdapter<String> arrayAdapter_branch;

    TextInputLayout lay_sem;
    AutoCompleteTextView act_sem;

    ArrayList<String> arraylist_sem;
    ArrayAdapter<String> arrayAdapter_sem;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        lay_branch =(TextInputLayout)findViewById(R.id.layout_branch);
        act_branch =(AutoCompleteTextView) findViewById(R.id.register_select_branch);

        arraylist_branch = new ArrayList<>();
        arraylist_branch.add("   ECE");
        arraylist_branch.add("   CSE");
        arraylist_branch.add("   IT");
        arraylist_branch.add("   Mechanical");
        arraylist_branch.add("   Chemistry");
        arraylist_branch.add("   QEDS");

        arrayAdapter_branch = new ArrayAdapter<>(getApplicationContext(),R.layout.dropdownlist,arraylist_branch);
        act_branch.setAdapter(arrayAdapter_branch);
        act_branch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sharedpreferences.txt_branch = (String) parent.getItemAtPosition(position);
            }
        });
        act_branch.setThreshold(1);

        lay_sem =(TextInputLayout)findViewById(R.id.layout_sem);
        act_sem =(AutoCompleteTextView) findViewById(R.id.register_select_sem);

        arraylist_sem = new ArrayList<>();
        arraylist_sem.add("  1st Semester");
        arraylist_sem.add("  2nd Semester");
        arraylist_sem.add("  3rd Semester");
        arraylist_sem.add("  4th Semester");
        arraylist_sem.add("  5th Semester");
        arraylist_sem.add("  6th Semester");
        arraylist_sem.add("  7th Semester");
        arraylist_sem.add("  8th Semester");

        arrayAdapter_sem = new ArrayAdapter<>(getApplicationContext(),R.layout.dropdownlist,arraylist_sem);
        act_sem.setAdapter(arrayAdapter_sem);
        act_sem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sharedpreferences.txt_semester = (String) parent.getItemAtPosition(position);
            }
        });
        act_sem.setThreshold(1);
        //=== how many characters requires spinner suggestion===

        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        register = findViewById(R.id.button_register);
        name = findViewById(R.id.register_name);
        sec = findViewById(R.id.register_section);
        admyear = findViewById(R.id.register_admyear);
        semester = findViewById(R.id.layout_sem);
        roll = findViewById(R.id.register_roll);
        pd = new ProgressDialog(this);



//        String txt_semester = semester.get.toString().trim();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences.txt_email = email.getText().toString().trim();
                sharedpreferences.txt_password = password.getText().toString().trim();
                sharedpreferences.txt_year = admyear.getText().toString().trim();
                sharedpreferences.txt_name = name.getText().toString().trim();
                sharedpreferences.txt_section = sec.getText().toString().trim();
                sharedpreferences.txt_roll = roll.getText().toString().toUpperCase(Locale.ROOT);
                String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                String mail_checker = sharedpreferences.txt_email.substring(sharedpreferences.txt_email.lastIndexOf("@") + 1).trim().toLowerCase();

                //Compile regular expression to get the pattern
                Pattern pattern = Pattern.compile(regex);
                //Iterate emails array list
                //Create instance of matcher
                Matcher matcher = pattern.matcher(sharedpreferences.txt_email);
//                || TextUtils.isEmpty(sharedpreferences.txt_email)
//                        || TextUtils.isEmpty(sharedpreferences.txt_section)  || TextUtils.isEmpty(sharedpreferences.txt_year)
//                        || TextUtils.isEmpty(sharedpreferences.txt_branch)  || TextUtils.isEmpty(sharedpreferences.txt_semester)
//                        || TextUtils.isEmpty(sharedpreferences.txt_password)

//                registerUser(sharedpreferences.txt_name , sharedpreferences.txt_email , sharedpreferences.txt_branch , sharedpreferences.txt_semester , sharedpreferences.txt_section, sharedpreferences.txt_year, sharedpreferences.txt_password);
                if (TextUtils.isEmpty(sharedpreferences.txt_email) )
                {
                    Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                }else if (matcher.matches()!= true){
                    Toast.makeText(RegisterActivity.this, "Email invalid", Toast.LENGTH_SHORT).show();
                }else if (!mail_checker.equals("bitmesra.ac.in")){
                    Toast.makeText(RegisterActivity.this, "Use valid BIT mail", Toast.LENGTH_SHORT).show();
                } else if (sharedpreferences.txt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(sharedpreferences.txt_name , sharedpreferences.txt_email , sharedpreferences.txt_branch ,
                            sharedpreferences.txt_semester , sharedpreferences.txt_section, sharedpreferences.txt_year,
                            sharedpreferences.txt_password,sharedpreferences.txt_roll);
//                    Toast.makeText(RegisterActivity.this, sharedpreferences.txt_roll, Toast.LENGTH_SHORT).show();
                }



            }
        });



    }

    private void registerUser(String name2, String email2,  String branch2,
                              String semester2,  String section2, String year2, String password2, String roll2) {
        pd.setMessage("Please Wait!");
        pd.show();

        sharedpreferences.mAuth.createUserWithEmailAndPassword(email2 , password2).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String , Object> map = new HashMap<>();
                map.put("name" , name2);
                map.put("email", email2);
                map.put("roll", roll2);
                map.put("branch" , branch2);
                map.put("semester" , semester2);
                map.put("section" , section2);
                map.put("admission_year" , year2);
                map.put("id" , sharedpreferences.mAuth.getCurrentUser().getUid());
                map.put("imageurl" , "default");
//                Toast.makeText(RegisterActivity.this, "Update the profile " +
//                        "for better expereince", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
////                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                sharedpreferences.id = sharedpreferences.mAuth.getCurrentUser().getUid();

                sharedpreferences.mRootRef.collection("users").document(sharedpreferences.id)
                        .set(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    pd.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Update the profile " +
                                            "for better expereince", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
//                            finish();
                                }
                            }
                        });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class EmailValidation1{

        //Regular Expression


    }
}
