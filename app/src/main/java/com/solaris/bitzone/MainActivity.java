package com.solaris.bitzone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Boolean islopggedin = false;
    ProgressDialog pd;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences.mAuth = FirebaseAuth.getInstance();
        sharedpreferences.mRootRef = FirebaseFirestore.getInstance();
        sharedpreferences.user = sharedpreferences.mAuth.getCurrentUser();
        pd = new ProgressDialog(this);


//        sharedpreferences.user = null;

        if (sharedpreferences.user != null) {
            pd.setMessage("Please Wait!");
            pd.show();
            storageReference = FirebaseStorage.getInstance().getReference("posters/poster4.jpg");
            try {
                File localfile = File.createTempFile("tempfile",".jpg");
                storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        sharedpreferences.bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                        sharedpreferences.loc = localfile.getAbsolutePath();
                        Toast.makeText(MainActivity.this, "Success imagerecievd", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            sharedpreferences.id = sharedpreferences.user.getUid();
            DocumentReference documentReference = sharedpreferences.mRootRef.collection("users").document(sharedpreferences.id);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    sharedpreferences.txt_name = (value.getString("name"));
                    sharedpreferences.txt_email =(value.getString("email"));
                    sharedpreferences.txt_branch =(value.getString("branch"));
                    sharedpreferences.txt_semester =(value.getString("semester"));
                    sharedpreferences.txt_section =(value.getString("section"));
                    sharedpreferences.txt_year =(value.getString("admission_year"));
                    sharedpreferences.txt_roll =(value.getString("roll"));

                }
            });
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
            pd.dismiss();
        } else {
            sharedpreferences.id = null;
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}