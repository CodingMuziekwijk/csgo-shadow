package com.evilcorp.csgo_shadow;


import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.ArrayList;

public class Shadow {

    //Global variables
    int xLocalOrigin;
    int yLocalOrigin;
    Bitmap shadowMap;
    Bitmap bitmap;
    ArrayList<Integer> xList = new ArrayList<>();
    ArrayList<Integer> yList = new ArrayList<>();
    ArrayList<Integer> xListContainer = new ArrayList<>();
    ArrayList<Integer> yListContainer = new ArrayList<>();

    //Initialize conditions
    public Shadow(Bitmap shadowMap, Bitmap bitmap) {
        this.shadowMap = shadowMap;
        this.bitmap = bitmap;
    }

    //Generates a new frame of the shadowmap & bitmap
    public Bitmap calculateNewFrame(){
        for (int i = 0; i < xListContainer.size(); i++) {
            createCircle(xListContainer.get(i), yListContainer.get(i));
        }
        return bitmap;
    }

    //Adds cords to the "Not yet visited" list
    public void addCordsToList(int x, int y, boolean outer){
        if(outer){
            xList.add(x);
            yList.add(y);
        }
    }

    //Defines a quarter of "circle" with offsets from the orignal cords
    public void createCircle(int x, int y){
        checkCircle(x, y, 0, 4);
        checkCircle(x, y, 1, 4);
        checkCircle(x, y, 2, 3);
        checkCircle(x, y, 3, 3);
        checkCircle(x, y, 4, 1);
    }

    //Mirrors the quarter of the circle so it becomes a circle and checks it
    public void checkCircle(int xOrigin, int yOrigin, int yOffset, int xOffset){
        for(int i = 1; i < xOffset + 1; i++) {
            boolean outer = false;
            xLocalOrigin = xOrigin;
            yLocalOrigin = yOrigin;

            if ( (i == 4 & yOffset == 0) || (i == 3 & yOffset == 3)){
                outer = true;
            }

            // Bottom right
            checkPixel(xOrigin + i, yOrigin + yOffset, outer);
            // Bottom left
            checkPixel(xOrigin - yOffset, yOrigin + i, outer);
            // top left
            checkPixel(xOrigin - i, yOrigin - yOffset, outer);
            // top right
            checkPixel(xOrigin + yOffset, yOrigin - i, outer);
        }
    }

    //The brains of the algorithm which checks all colors
    public void checkPixel(int x, int y, boolean outer){
        int color = getColor(x, y);
        int ownColor = getColor(xLocalOrigin, yLocalOrigin);

        if(isGray(ownColor)) {
            if(isGray(color) || color == Color.BLACK){
                return;
            }else if(isWhite(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }else if (isYellow(color)){
                shadowMap.setPixel(x, y, Color.YELLOW);
                addCordsToList(x, y, outer);
            }else if (isRed(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.RED);
                addCordsToList(x, y, outer);
            }else if (isBlue(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.BLUE);
                addCordsToList(x, y, outer);
            }else if (isGreen(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GREEN);
                addCordsToList(x, y, outer);
            }else if (isMagenta(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.MAGENTA);
                addCordsToList(x, y, outer);
            }else if (isCyan(color)){
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }
        }else if(isYellow(ownColor)){
            if(isYellow(color)){
                return;
            }else if(isCyan(color)){
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }
        }else if(isRed(ownColor)) {
            if(isRed(color) || isYellow(color)){
                return;
            }else if(isWhite(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.RED);
                addCordsToList(x, y, outer);
            }else if (isGreen(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }else if (isCyan(color)){
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }
        }else if(isBlue(ownColor)){
            if(isBlue(color) || isYellow(color)){
                return;
            }else if(isWhite(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.BLUE);
                addCordsToList(x, y, outer);
            }else if (isMagenta(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }else if (isCyan(color)){
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }
        }else if(isGreen(ownColor)){
            if(isGreen(color) || isYellow(color)){
                return;
            }else if(isWhite(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GREEN);
                addCordsToList(x, y, outer);
            }else if (isRed(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }else if (isCyan(color)){
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }
        }else if(isMagenta(ownColor)){
            if(isMagenta(color) || isYellow(color)){
                return;
            }else if(isWhite(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.MAGENTA);
                addCordsToList(x, y, outer);
            }else if (isBlue(color)){
                int new_pixel = makeNewPixel(bitmap.getPixel(x, y));
                bitmap.setPixel(x, y, new_pixel);
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }else if (isCyan(color)){
                shadowMap.setPixel(x, y, Color.GRAY);
                addCordsToList(x, y, outer);
            }
        }
    }

    //Checks if the shadow proces is completed
    public boolean whiteInMap(){
        return(xList.size() == 0);
    }

    //Gets a solid color from a pixel (because of interpolation)
    public int getColor(int x, int y){
        int pixel = shadowMap.getPixel(x, y);

        if ((Color.red(pixel) > 250) && (Color.green(pixel) > 250) && (Color.blue(pixel) > 250)){
            return Color.WHITE;
        }else if (pixel == Color.GRAY){
            return Color.GRAY;
        }else if((Color.red(pixel) > 250) && (Color.green(pixel) < 50) && (Color.blue(pixel) < 50)){
            return Color.RED;
        }else if((Color.red(pixel) > 250) && (Color.green(pixel) < 50) && (Color.blue(pixel) > 250)) {
            return Color.MAGENTA;
        }else if ((Color.red(pixel) < 50) && (Color.green(pixel) > 250) && (Color.blue(pixel) < 50)){
            return Color.GREEN;
        }else if ((Color.red(pixel) < 50) && (Color.green(pixel) < 50) && (Color.blue(pixel) > 250)){
            return Color.BLUE;
        }else if ((Color.red(pixel) > 250) && (Color.green(pixel) > 250) && (Color.blue(pixel) < 50)){
            return Color.YELLOW;
        }else if ((Color.red(pixel) < 50) && (Color.green(pixel) > 250) && (Color.blue(pixel) > 250)){
            return Color.CYAN;
        }
        return Color.BLACK;
    }

    private Boolean isWhite(int color){
        return Color.WHITE == color;
    }

    private Boolean isRed(int color){
        return Color.RED == color;
    }

    private Boolean isMagenta(int color){
        return Color.MAGENTA == color;
    }

    private Boolean isGreen(int color){
        return Color.GREEN == color;
    }

    private Boolean isBlue(int color){
        return Color.BLUE == color;
    }

    private Boolean isGray(int color){
        return Color.GRAY == color;
    }

    private Boolean isYellow(int color){
        return Color.YELLOW == color;
    }

    private Boolean isCyan(int color){
        return Color.CYAN == color;
    }

    public void clearCordList(){
        xList.clear();
        yList.clear();
    }

    public void clearCordListContainer(){
        xListContainer.clear();
        yListContainer.clear();
    }

    public void setNewCordList(){
        xListContainer = new ArrayList<>(xList);
        yListContainer = new ArrayList<>(yList);
    }

    //Creates a new pixel to display on the users screen
    private int makeNewPixel(int pixel){
        int pixelAlpha = Color.alpha(pixel);
        int red = Color.red(pixel);
        int green = Color.green(pixel) + 50;
        int blue = Color.blue(pixel);
        int new_pixel = Color.argb(pixelAlpha, red, green, blue);

        return new_pixel;
    }

    public void makePixelGreen(int x, int y){
        shadowMap.setPixel(x, y, Color.GRAY);
    }
}