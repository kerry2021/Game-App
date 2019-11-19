package com.example.gameproject.puzzle_game;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.HashMap;

public class CustomImageManager {

    /**
     * save a list of bitmap images to external storage and get code for retrieving these images.
     * @param imageList list of bitmap images to save.
     * @param originalFileNames code for retrieving previously saved images.
     * @param context application context for getting directory.
     * @return a String of code for retrieving saved images.
     */
    static String saveImageList(Bitmap[] imageList, String originalFileNames, Context context) {
        String[] originalFiles = decode(originalFileNames);
        int numOriginalFiles = originalFiles.length;
        int numNewFiles = imageList.length;
        StringBuilder newFileNames;
        if (originalFileNames == null) {
            newFileNames = new StringBuilder("");
        } else {
            newFileNames = new StringBuilder(originalFileNames);
        }
        HashMap<String, Bitmap> imageHashMap = new HashMap<>();
        if (numOriginalFiles > 0) {
            newFileNames.append("_");
        }
        for (int i = 0; i < numNewFiles; i++) {
            String pathname = Integer.toString(i + numOriginalFiles);
            imageHashMap.put(pathname, imageList[i]);
            newFileNames.append(pathname);
            if (i < (numNewFiles - 1)) {
                newFileNames.append("_");
            }
        }
        CustomImageInteractor.saveImageList(imageHashMap, context);
        return newFileNames.toString();
    }

    /**
     * Get a list of bitmap images using a code and context that provides directory.
     * @param code encodes the path names of image files.
     * @param context application context.
     * @return list of retrieved bitmap images.
     */
    static Bitmap[] getImageList(String code, Context context) {
        return CustomImageInteractor.getImageList(decode(code), context);
    }

    private static String[] decode(String code) {
        String[] pathnames;
        if (code == null) {
            pathnames = new String[0];
        } else {
            pathnames = code.split("_");
        }
        return pathnames;
    }
}
