package com.example.lmsclone.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lmsclone.Prevalent.Prevelant;
import com.example.lmsclone.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity
{


    private CircleImageView profileImageView;
    private EditText fullNameEditText, userPhoneEditText, addressEditText, usernameEditText, idEditText, degreeEditText, batchEditText;
    private TextView profileChangeTextBtn, closeTextBtn, saveTextBtn;
    private Uri imageUri;
    private String myUrl = "";
    private StorageReference storageProfilePictureRef;
    private String checker = "";
    private StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        profileImageView = (CircleImageView) findViewById(R.id.settingsProfile_img);
        fullNameEditText = (EditText) findViewById(R.id.settingsFullName);
        userPhoneEditText = (EditText) findViewById(R.id.settingsPhoneNumber);
        addressEditText = (EditText) findViewById(R.id.settingsAddress);
        idEditText = (EditText) findViewById(R.id.settingsID);
        degreeEditText = (EditText) findViewById(R.id.settingsDegree);
        batchEditText = (EditText) findViewById(R.id.settingsBatch);
        profileChangeTextBtn = (TextView) findViewById(R.id.profileImageChangeBtn);
        closeTextBtn = (TextView) findViewById(R.id.closeSettingsBtn);
        saveTextBtn = (TextView) findViewById(R.id.updateAccountSettingsBtn);


        userInfoDisplay(profileImageView, idEditText, degreeEditText, batchEditText, userPhoneEditText, fullNameEditText, addressEditText);


        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });


        saveTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }
            }
        });

        profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checker = "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity.this);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profileImageView.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(this, "Error, try again.", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
            finish();
        }
    }

    private void updateOnlyUserInfo()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name", fullNameEditText.getText().toString());
        userMap.put("address", addressEditText.getText().toString());
        userMap.put("phone", userPhoneEditText.getText().toString());
        userMap.put("id", idEditText.getText().toString());
        userMap.put("degree", degreeEditText.getText().toString());
        userMap.put("batch", batchEditText.getText().toString());
        ref.child(Prevelant.currentOnlineUser.getUsername()).updateChildren(userMap);


        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
        Toast.makeText(SettingsActivity.this, "Profile info Updated", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void userInfoSaved()
    {
        if(TextUtils.isEmpty(fullNameEditText.getText().toString()))
        {
            Toast.makeText(this, "Name is mandatory!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Address is mandatory!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(idEditText.getText().toString()))
        {
            Toast.makeText(this, "NSBM ID is mandatory!", Toast.LENGTH_SHORT).show();
        }
        else if(!TextUtils.isEmpty(idEditText.getText().toString()))
        {
            idEditText.setEnabled(false);
        }
        else if(TextUtils.isEmpty(degreeEditText.getText().toString()))
        {
            Toast.makeText(this, "Degree is mandatory!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(batchEditText.getText().toString()))
        {
            Toast.makeText(this, "Batch is mandatory!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userPhoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Phone Number is mandatory!", Toast.LENGTH_SHORT).show();
        }

        else if(checker.equals("clicked"))
        {
            uploadImage();
        }
    }

    private void uploadImage()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Update Profile");
        progressDialog.setMessage("Please wait, while we're updating your account information");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if(imageUri != null)
        {
            final StorageReference fileRef = storageProfilePictureRef
                    .child(Prevelant.currentOnlineUser.getUsername() + ".jpg");
            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {

                    if(!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task)
                        {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                myUrl = downloadUrl.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put("image", myUrl);
                                userMap.put("id", idEditText.getText().toString());
                                userMap.put("degree", degreeEditText.getText().toString());
                                userMap.put("batch", batchEditText.getText().toString());
                                userMap.put("phone", userPhoneEditText.getText().toString());
                                userMap.put("name", fullNameEditText.getText().toString());
                                userMap.put("address", addressEditText.getText().toString());

                                ref.child(Prevelant.currentOnlineUser.getUsername()).updateChildren(userMap);

                                progressDialog.dismiss();

                                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                                Toast.makeText(SettingsActivity.this, "Profile info updated Successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SettingsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "Image is not selected.", Toast.LENGTH_SHORT).show();
        }
    }


    private void userInfoDisplay(final CircleImageView profileImageView, final EditText idEditText, final EditText degreeEditText, final EditText batchEditText, final EditText userPhoneEditText, final EditText fullNameEditText, final EditText addressEditText)
    {
        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevelant.currentOnlineUser.getUsername());

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.child("image").exists())
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        String id = dataSnapshot.child("id").getValue().toString();
                        String degree = dataSnapshot.child("degree").getValue().toString();
                        String batch = dataSnapshot.child("batch").getValue().toString();
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String address = dataSnapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);
                        idEditText.setText(id);
                        degreeEditText.setText(degree);
                        batchEditText.setText(batch);
                        userPhoneEditText.setText(phone);
                        fullNameEditText.setText(name);
                        addressEditText.setText(address);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
