package com.evilcorp.csgo_shadow;


import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;

public class Shadow {

    int x;
    int y;
    Bitmap shadowMap;
    Bitmap bitmap;
    ArrayList<Integer> xList;
    ArrayList<Integer> yList;

    public Shadow(Bitmap shadowMap, Bitmap bitmap) {
        this.shadowMap = shadowMap;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap(){
        return shadowMap;
    }

    public void calculateNewFrame(){

    }

    public void addCordsToList(int x, int y){
        xList.add(x);
        yList.add(y);
    }

    public void createCircle(int x, int y){
        checkCircle(x, y, 0, 4);
        checkCircle(x, y, 1, 4);
        checkCircle(x, y, 2, 3);
        checkCircle(x, y, 3, 3);
        checkCircle(x, y, 4, 1);

        // 8 hoek
//        checkPixels(x, y, 0, 8);
//        checkPixels(x, y, 1, 8);
//        checkPixels(x, y, 2, 8);
//        checkPixels(x, y, 3, 7);
//        checkPixels(x, y, 5, 6);
//        checkPixels(x, y, 6, 6);
//        checkPixels(x, y, 7, 4);
    }

    public void checkCircle(int xOrigin, int yOrigin, int yOffset, int xOffset){
        for(int i = 1; i < xOffset + 1; i++) {
            boolean outer = false;
            if ( (i == 4 & yOffset == 0) || (i == 3 & yOffset == 3) || ( i == 4 & yOffset == 4) ){  //& (d == 5 || d == 0)) || (i == 0 & d == 7)
                outer = true;
            }
            // Bottom right
            checkPixel(x + i, y + yOffset, outer);
            // Bottom left
            checkPixel(x - yOffset, y + i, outer);
            // top left
            checkPixel(x - i, y - yOffset, outer);
            // top right
            checkPixel(x + yOffset, y - i, outer);
        }
    }

    private void checkPixel(int x, int y, boolean outer){
        if(isWhite(x, y)){
            bitmap.setPixel(x, y, Color.GREEN);
            shadowMap.setPixel(x, y, Color.GREEN);
            addCordsToList(x, y);
        }
    }

    private Boolean isWhite(int x, int y){
        return (shadowMap.getPixel(x, y) == Color.WHITE);
    }
}