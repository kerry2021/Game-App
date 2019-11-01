package com.example.gameproject.puzzle_game;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.example.gameproject.R;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import android.widget.LinearLayout;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    Uri selectedImage;
    private final int RESULT_LOAD_IMAGE = 1;
    public static ArrayList<ImageDividable> selectedImages = new ArrayList<>();
    private static final int NUM_DEFAULT_IMAGES = 2;
    private static final int STORAGE_PERMISSION_CODE = 1;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_puzzle_game_select_image);
        addDefaultImagesToList();
        if(ContextCompat.checkSelfPermission(ImageActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            String[] permissionArray = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(ImageActivity.this,
                    permissionArray,
                    STORAGE_PERMISSION_CODE);
        }
        else {
            alertDialogForCameraImage();
        }

        Button addImageButton, saveSelectionButton, cancelButton;
        addImageButton = findViewById(R.id.add_image_button);
        saveSelectionButton = findViewById(R.id.save_image_selection_button);
        cancelButton = findViewById(R.id.cancel_button);

        addImageButton.setOnClickListener(view1 -> {
            alertDialogForCameraImage();
        });

        saveSelectionButton.setOnClickListener(view12 -> {

            startActivity(new Intent(view12.getContext(), PuzzleGameIntroActivity.class));
        });

        cancelButton.setOnClickListener(view1 -> {
            ArrayList<ImageDividable> defaultImagesSelected = new ArrayList<>();
            for (int i = 0; i < NUM_DEFAULT_IMAGES; i++) {
                defaultImagesSelected.add(selectedImages.get(i));
            }
            selectedImages = defaultImagesSelected;
            startActivity(new Intent(view1.getContext(), PuzzleGameIntroActivity.class));
        });
    }

    private void addDefaultImagesToList() {
        ImageView default1View = (ImageView) findViewById(R.id.default1);
        ImageDividable default1Dividable = new ImageDividable(default1View);
        selectedImages.add(default1Dividable);
        ImageView default2View = (ImageView) findViewById(R.id.default2);
        ImageDividable default2Dividable = new ImageDividable(default2View);
        selectedImages.add(default2Dividable);
    }

    void pickImageFromGallery() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                //  takenPictureData = handleResultFromChooser(data);

                selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                ImageView imageView = new ImageView(ImageActivity.this);
                LinearLayout linearImages = (LinearLayout)
                        findViewById(R.id.linear_images);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                        findViewById(R.id.default2).getLayoutParams();
                linearImages.addView(imageView, layoutParams);
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                ImageDividable imageDividable = new ImageDividable(imageView);
                selectedImages.add(imageDividable);
            }
        }

        //And show the result in the image view when take picture from camera.

    }


    public void alertDialogForCameraImage() {
        AlertDialog.Builder adb = new AlertDialog.Builder(ImageActivity.this);
        adb.setTitle("Pick Image From Gallery: ");
        adb.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                pickImageFromGallery();

            }
        });
        adb.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults) {
        {
            super
                    .onRequestPermissionsResult(requestCode,
                            permissions,
                            grantResults);

            if (requestCode == STORAGE_PERMISSION_CODE) {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ImageActivity.this,
                            "Gallery Acces Permission Granted",
                            Toast.LENGTH_SHORT)
                            .show();
                    alertDialogForCameraImage();
                }
                else {
                    Toast.makeText(ImageActivity.this,
                            "Gallery Access Permission Denied",
                            Toast.LENGTH_SHORT)
                            .show();
                    startActivity(new Intent(ImageActivity.this,
                            PuzzleGameIntroActivity.class));
                }
            }
        }
    }




}
