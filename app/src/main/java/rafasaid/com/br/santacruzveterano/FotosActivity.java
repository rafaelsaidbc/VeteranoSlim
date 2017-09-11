package rafasaid.com.br.santacruzveterano;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FotosActivity extends AppCompatActivity {


    Button chooseImgFotos, uploadImgFotos;

    ImageView imgViewFotos;

    int PICK_IMAGE_REQUEST = 1011;
    Uri filePath;
    ProgressDialog pd;

    //creating reference to firebase storage
    FirebaseStorage storage = FirebaseStorage.getInstance();

    //change the url according to your firebase app
    StorageReference storageRef = storage.getReferenceFromUrl("gs://santa-cruz-veterano.appspot.com/fotos_activity");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseImgFotos = (Button) findViewById(R.id.choose_fotos);      //choose button
        uploadImgFotos = (Button) findViewById(R.id.upload_fotos);      //upload button
        imgViewFotos = (ImageView) findViewById(R.id.imageview_fotos);  //image view

        pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setTitle("Upload Process");                              //progress dialog box title
        // progress spinner


        chooseImgFotos.setOnClickListener(new View.OnClickListener() {       //Image Selection start
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });                                                             // Image Selection end.

        uploadImgFotos.setOnClickListener(new View.OnClickListener() {       // uploading the image start
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    pd.setMax(100);
                    pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    pd.show();


                    StorageReference childRef = storageRef.child(filePath.getLastPathSegment());


                    UploadTask uploadTask = childRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(FotosActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(FotosActivity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    @SuppressWarnings("VisibleForTests") double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();                // for displaying the upload percentage in progress bar.
                                    pd.setMessage(((int) progress) + "% Uploaded..");
                                }
                            });
                } else {
                    Toast.makeText(FotosActivity.this, "Select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Image Upload end

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();


            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);            //getting image from gallery


                imgViewFotos.setImageBitmap(bitmap);                                                               //Setting image to ImageView
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}