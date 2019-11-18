package com.example.gameproject.puzzle_game;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Database for storing customized images.
 */
class CustomImageDatabase implements ImageDatabaseGateway, Serializable {

    private static HashMap<String, ImageView> imageHashMap = new HashMap<>();
    private static int idCounter = 0;


    /**
     * Add image to imageHashMap and return its key.
     * @param image image to be saved
     * @return key
     */
    private static String addImage(ImageView image) {
        String key = createId();
        imageHashMap.put(key, image);
        return key;
    }

    /**
     * get images from imageHashMap using keyList and return an ArrayList of images.
     * @param keyList list of keys connected by underscore symbol.
     * @return ArrayList of images if keyList is not null. Otherwise return empty ArrayList.
     */
    public static ArrayList<ImageView> getImageList(String keyList) {
        ArrayList<ImageView> imageList = new ArrayList<>();
        if (keyList == null) {
            return imageList;
        }
        String[] keys = keyList.split("_");
        for (int i = 0; i < keys.length; i++) {
            imageList.add(imageHashMap.get(keys[i]));
        }
        return imageList;
    }

    /**
     * Save a list of images to imageHashMap.
     * If no images are saved, return null.
     * @return a keyList String containing all keys for the list of images.
     */
    public static String saveImageList(ArrayList<ImageView> imageList) {
        if (imageList.size() == 0) {
            return null;
        }
        String keyList = "";
        for (int i = 0; i < imageList.size(); i++) {
            keyList += addImage(imageList.get(i));
            if (i < (imageList.size() - 1)) {
                keyList += "_";
            }
        }
        return keyList;
    }

    private static String createId() {
        String newId = Integer.toString(idCounter);
        idCounter++;
        return newId;
    }
}
