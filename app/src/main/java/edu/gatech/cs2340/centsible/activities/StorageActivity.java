package edu.gatech.cs2340.centsible.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.firebase.ui.auth.util.ExtraConstants;
import com.firebase.ui.auth.IdpResponse;

import edu.gatech.cs2340.centsible.R;

@SuppressWarnings("ALL")
public class StorageActivity extends AppCompatActivity {
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    private final FirebaseStorage storage = FirebaseStorage.getInstance();
    private final StorageReference storageReference = storage.getReference();
    // [END storage_field_declaration]

    @NonNull
    /**
     * create intent of context to get storage activity
     *
     * @param context of nonnull context of get storage
     * @param response creates intent depending on response
     * @return intent of storage of item
     */
    public static Intent createIntent(@NonNull Context context,
                                      @Nullable IdpResponse response) {
        return new Intent().setClass(context, StorageActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, response);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage1);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Button signout = findViewById(R.id.sign_out_button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(StorageActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(LandingPageActivity.createIntent(
                                            StorageActivity.this));
                                    finish();
                                } else {
                                    Log.d("ERROR", "Could not sign out");
                                }
                            }
                        });
            }
        });

        Button addDonation = findViewById(R.id.add_donation_button);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddDonationActivity.createIntent(StorageActivity.this));
            }
        });

        Button listDonation = findViewById(R.id.list_donation_button);
        listDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DonationActivity.createIntent(StorageActivity.this));
            }
        });

        Button goToDownload = findViewById(R.id.goToDownload);
        Button btnChoose = findViewById(R.id.btnChoose);
        Button btnUpload = findViewById(R.id.btnUpload);
        //imageView = (ImageView) findViewById(R.id.imgView);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseText();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadText();
            }
        });

        goToDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DataPullActivity.createIntent(StorageActivity.this));
            }
        });
    }

    private void chooseText() {
        Intent intent = new Intent();
        intent.setType("text/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            /*try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    private void uploadText() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("locations/" + "currentLocations");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(StorageActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(StorageActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
