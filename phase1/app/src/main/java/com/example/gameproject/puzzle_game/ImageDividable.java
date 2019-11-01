package com.example.gameproject.puzzle_game;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import com.example.gameproject.R;

import java.util.ArrayList;

public class ImageDividable {
    private ImageView image;
    private ArrayList<Bitmap> dividedImages = new ArrayList<>();
    private int numColumns = 3;

    /**
     * Splits the source image and show them all into a grid in a new activity
     *
     * @param image        The image
     */
    ImageDividable(ImageView image) {
        this.image = image;
    }

    ImageDividable(ImageView image, int numColumns) {
        this.image = image;
        setNumColumns(numColumns);
    }

    public ImageView getImage() {
        return image;
    }

    void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        splitImage(image, numColumns);
    }

    public ArrayList<Bitmap> getDividedImages() {
        return dividedImages;
    }

    /**
     * Splits the source image and show them all into a grid in a new activity
     *
     * @param image        The source image to split
     * @param numColumns The target number of small image chunks to be formed from the   source image
     */
    private void splitImage(ImageView image, int numColumns) {

        //For height and width of the small image chunks
        int chunkHeight, chunkLength;

        //To store all the small image chunks in bitmap format in this list
        dividedImages = new ArrayList<Bitmap>(numColumns * numColumns);

        //Getting the scaled bitmap of the source image
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
    /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);*/
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(),
                bitmap.getHeight(), true);

        chunkLength = bitmap.getHeight() / numColumns;
    /*chunkHeight = 300/rows;
    chunkLength = 300/cols;*/

        //xCoordinate and yCoordinate are the pixel positions of the image chunks
        int yCoordinate = 0;
        for (int x = 0; x < numColumns; x++) {
            int xCoordinate = 0;
            for (int y = 0; y < numColumns; y++) {
                dividedImages.add(Bitmap.createBitmap(scaledBitmap, xCoordinate, yCoordinate,
                        chunkLength, chunkLength));
                xCoordinate += chunkLength;
            }
            yCoordinate += chunkLength;
        }

    }
}
