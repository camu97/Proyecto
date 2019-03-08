package com.example.came.cameselleabreujavier_proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;
    }

    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().
                getMetrics(metrics);
        return (int) (dp * metrics.density);
    }

    public Bitmap escalaAnchura(int res, int nuevoAncho) {
        Bitmap bitmapAux = BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }

    public Bitmap escalaAltura(int res, int nuevoAlto) {
        Bitmap bitmapAux = BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(),
                nuevoAlto, true);
    }

    public Bitmap escalaAnchura(String fichero, int nuevoAncho) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }

    public Bitmap escalaAltura(String fichero, int nuevoAlto) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(),
                nuevoAlto, true);
    }

    public Bitmap[] getFrames(int numImg, String dir, String tag, int width) {
        Bitmap[] aux = new Bitmap[numImg];
        for (int i = 0; i < numImg; i++)
            aux[i] = escalaAnchura(dir + "/" + tag + (i + 1) + ".png", width);
        return aux;
    }


    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    // 1920x1280
    public int getDpW(int pixels) {
        int x = (int) ((pixels / 19.20) * SceneControl.screenWidth) / 100;
        if (x == 0) {   //comprobación para pantallas pequeñas( ej.u.getDpW(2)=0)
            return 1;
        }
        return x;
    }

    // 1920x1280
    public int getDpH(int pixels) {
        int y = (int) ((pixels / 10.80) * SceneControl.screenHeight) / 100;
        if (y == 0) {   ////comprobación para pantallas pequeñas( ej.u.getDpH(2)=0)
            return 1;
        }
        return y;
    }

    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }
}