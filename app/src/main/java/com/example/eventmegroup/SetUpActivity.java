package com.example.eventmegroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class SetUpActivity extends AppCompatActivity {

    private ImageView circleImageView;
    private EditText mProfileName;
    private EditText bday;
    private Button mSaveBtn;
    private Button logOutBtn;
    private FirebaseAuth auth;
    private Uri imageUri;
    private String uid;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseFirestore db;
    private Uri downloadUri = null;
    private String ogImgUri;
    private List<String> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        circleImageView = findViewById(R.id.circleImageView);
        mProfileName = findViewById(R.id.profile_name);
        bday = findViewById(R.id.bday);
        mSaveBtn = findViewById(R.id.edit_btn);
        logOutBtn = findViewById(R.id.log_out_btn);

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        db = FirebaseFirestore.getInstance();

        db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DocumentSnapshot ds = task.getResult();
                        String name = task.getResult().getString("name");
                        String birthdate = task.getResult().getString("bday");
                        ogImgUri = task.getResult().getString("image");
                        events = (List<String>) ds.get("events");
                        mProfileName.setText(name);
                        bday.setText(birthdate);
                        Glide.with(SetUpActivity.this).load(ogImgUri).into(circleImageView);
                    }
                }
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if(ContextCompat.checkSelfPermission(SetUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(SetUpActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                    else {
                        choosePicture();
                    }
                }
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                finish();
                startActivity(new Intent(SetUpActivity.this, MainActivity.class));
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mProfileName.getText().toString();
                String birthdate = bday.getText().toString();
                System.out.println("Btn clicked " + name + birthdate);
                System.out.println("imageUri = " + imageUri + " ogImgUri = " + ogImgUri);
                System.out.println("Non empty");
                if(!name.isEmpty() && !birthdate.isEmpty() && imageUri == null) {
                    System.out.println("Pic is the same");
                    HashMap<String, Object> nameToImg = new HashMap<>();
                    nameToImg.put("name", name);
                    nameToImg.put("bday", birthdate);
                    nameToImg.put("image", ogImgUri);
                    nameToImg.put("events", events);
                    db.collection("Users").document(uid).set(nameToImg).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SetUpActivity.this, "Successfully saved profile settings", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SetUpActivity.this, Profile.class));
                                finish();
                            }
                            else {
                                Toast.makeText(SetUpActivity.this, "Failed to save profile settings", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else if(!name.isEmpty() && !birthdate.isEmpty() && imageUri != null) {
                    // Save the photo to firebase
                    final ProgressDialog pd = new ProgressDialog(SetUpActivity.this);
                    pd.setTitle("Uploading Image...");
                    pd.show();
                    StorageReference sr = storageReference.child("Profile_pics").child(uid + ".jpg");
                    sr.putFile(imageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    saveToFireStore(name, birthdate, sr);
                                    Toast.makeText(SetUpActivity.this, "Upload Successful",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SetUpActivity.this, Profile.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                    double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                                    pd.setMessage("Progress: " + (int) progressPercent + "%");
                                }
                            });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Cannot have empty picture, name, or birthday", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void saveToFireStore(String name, String birthdate, StorageReference sr) {
        sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                downloadUri = uri;
                HashMap<String, Object> nameToImg = new HashMap<>();
                nameToImg.put("name", name);
                nameToImg.put("bday", birthdate);
                nameToImg.put("image", downloadUri.toString());
                nameToImg.put("events", events);

                db.collection("Users").document(uid).set(nameToImg).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SetUpActivity.this, "Successfully save profile settings", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SetUpActivity.this, Profile.class));
                            finish();
                        }
                        else {
                            Toast.makeText(SetUpActivity.this, "Failed to save profile settings", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void choosePicture() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getResult.launch(openGalleryIntent);
    }

    private ActivityResultLauncher<Intent> getResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Toast.makeText(SetUpActivity.this, "Got picture", Toast.LENGTH_LONG).show();
                        try {
                            Intent intent = result.getData();
                            imageUri = intent.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            circleImageView.setImageDrawable(null);
                            circleImageView.setImageBitmap(selectedImage);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(SetUpActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(SetUpActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
                    }
                }
            }
    );
}