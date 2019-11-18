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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.example.gameproject.R;
import com.example.gameproject.User;

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
    private ArrayList<ImageView> selectedImages = new ArrayList<>();
    private static final int STORAGE_PERMISSION_CODE = 1;
    private AlertDialog ad;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_puzzle_game_select_image);
        User currentUser = (User) getIntent().getSerializableExtra("user");
        selectedImages = CustomImageDatabase.getImageList(currentUser.get("puzzle_game_custom_images"));
        for (int i = 0; i < selectedImages.size(); i++) {
            showImageInLayout(selectedImages.get(i));
        }
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
            ad.dismiss();
            alertDialogForCameraImage();
        });

        saveSelectionButton.setOnClickListener(view12 -> {
            String value = CustomImageDatabase.saveImageList(selectedImages);
            currentUser.set("puzzle_game_custom_images", value);
            currentUser.write();
            Intent backIntro = new Intent(view12.getContext(), PuzzleGameIntroActivity.class);
            backIntro.putExtra("user", currentUser);
            startActivity(backIntro);
        });

        cancelButton.setOnClickListener(view1 -> {
            Intent backIntroCancel = new Intent(view1.getContext(), PuzzleGameIntroActivity.class);
            backIntroCancel.putExtra("user", currentUser);
            startActivity(backIntroCancel);
        });
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
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                showImageInLayout(imageView);
                selectedImages.add(imageView);
            }
        }
    }

    private void showImageInLayout(ImageView imageView) {
        if(imageView.getParent() != null) {
            ((ViewGroup)imageView.getParent()).removeView(imageView);
        }
        LinearLayout linearImages = findViewById(R.id.linear_images);
        int pixels =  (int) (100 * imageView.getResources().getDisplayMetrics().density);
        int topMargin = (int) (10 * imageView.getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(pixels, pixels);
        layoutParams.topMargin = topMargin;
        imageView.setLayoutParams(layoutParams);
        linearImages.addView(imageView, layoutParams);
    }


    public void alertDialogForCameraImage() {
        AlertDialog.Builder adb = new AlertDialog.Builder(ImageActivity.this);
        adb.setTitle("Pick Image From Gallery: ");
        adb.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                pickImageFromGallery();
            }
        });
        ad = adb.show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ad.dismiss();
    }
}
