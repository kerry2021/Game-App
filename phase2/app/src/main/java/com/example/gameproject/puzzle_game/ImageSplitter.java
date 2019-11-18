package com.example.gameproject.puzzle_game;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Class responsible for cutting ImageView images
 */
class ImageSplitter {
    private int numColumns;

    ImageSplitter(int numColumns) {
        this.numColumns = numColumns;
    }

    /**
     * Splits the source image and show them all into a grid in a new activity
     *
     * @param image        The source image to split.
     * @return A bitmap ArrayList of divided images.
     */
    ArrayList<Bitmap> splitImage(ImageView image) {

        //For height and width of the small image chunks
        int chunkHeight, chunkLength;

        //To store all the small image chunks in bitmap format in this list
        ArrayList<Bitmap> dividedImages = new ArrayList<Bitmap>(numColumns * numColumns);

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
        return dividedImages;
    }
}
