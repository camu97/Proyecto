package com.example.came.cameselleabreujavier_proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class Game extends Escena {

    private Fondo[] fondo;
    private Capa c1, c2;
    private Bitmap bitmapFondo, bitmapAux, bmEspejo;
    private ArrayList<Bitmap> bmFondo, textura, bitmapsNubes;
    private Utils u;
    private Random gen;
    private ArrayList<Nube> arrayNube;
    private Nube x, y;

    public Game(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        gen = new Random();
        u = new Utils(context);

        bmFondo = new ArrayList<>();
        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
        bmFondo.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla, altoPantalla, false));
        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
//        bmEspejo = u.espejo(bitmapAux, true);
        bmFondo.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla, altoPantalla, false));

        textura = new ArrayList<>();
        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
        textura.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla, altoPantalla, false));
        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
        textura.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla, altoPantalla, false));
//
        bitmapsNubes = new ArrayList<>();
        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.clouds);
        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
//        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.clouds);
//        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
//        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nubes6);
//        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nubes6);
        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
//        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nubes7);
//        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
//        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.clouds);
//        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
//        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.clouds);
//        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));
//        bitmapAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.clouds);
//        bitmapsNubes.add(Bitmap.createScaledBitmap(bitmapAux, anchoPantalla / 3, altoPantalla / 5, false));


        c1 = new Capa(context, anchoPantalla, altoPantalla, bmFondo);
        c1.setVelocidad(-7);

        c2 = new Capa(context, anchoPantalla, altoPantalla, textura);
        c2.setVelocidad(-10);
        c2.setPosY(altoPantalla / 2);

        x = new Nube(context, anchoPantalla, altoPantalla, bitmapsNubes.get(0));
        y = new Nube(context, anchoPantalla, altoPantalla, bitmapsNubes.get(1));

//        int fin = (int) (Math.random() * 7 + 1);
//        for (int i = 0; i < fin; i++) {
//            arrayNube.add(new Nube(context, anchoPantalla, altoPantalla, bitmapsNubes.get((int) (Math.random() * bitmapsNubes.size()))));
//        }

    }

    public void actualizarFisica() {
        c1.mover();
        c2.mover();
        x.mover();
        y.mover();
//        for (int i = 0; i < arrayNube.size(); i++) {
//            arrayNube.get(i).mover();
//        }

//        fondo[0].mover(10);
//        fondo[1].mover(10);
////        fondo[2].mover(10);
//
//        if (fondo[0].direccion == -1) {
//
//            if (fondo[0].posicion.x + fondo[0].imagen.getWidth() < 0) {
//                fondo[0].posicion.x = fondo[0].posicion.x + fondo[0].imagen.getWidth() + fondo[1].imagen.getWidth();
//            }
//            if (fondo[1].posicion.x + fondo[1].imagen.getWidth() < 0) {
//                fondo[1].posicion.x = fondo[1].posicion.x + fondo[1].imagen.getWidth() + fondo[0].imagen.getWidth();
//            }
//        } else if (fondo[0].direccion == 1) {
//            if (fondo[0].posicion.x > anchoPantalla) {
//                fondo[0].posicion.x = fondo[0].posicion.x - fondo[1].imagen.getWidth();
//            }
//            if (fondo[1].posicion.x > anchoPantalla) {
//                fondo[1].posicion.x = fondo[1].posicion.x - fondo[0].imagen.getWidth();
//            }
//        }

    }

    public void dibujar(Canvas c) {
        try {
            c1.dibujar(c);
            c2.dibujar(c);
            x.dibujar(c);
            y.dibujar(c);
//            for (int i = 0; i < bitmapsNubes.size(); i++) {
//                arrayNube.get(i).dibujar(c);
//                Log.i("AAAA", arrayNube.get(i).getPosX() + "--" + arrayNube.get(i).getPosY());
//            }


            super.dibujar(c);
        } catch (Exception e) {
            Log.i("ERROR AL DIBUJAR", e.getLocalizedMessage());
        }

    }

    public boolean pulsa(Rect boton, MotionEvent event) {
        if (boton.contains((int) event.getX(), (int) event.getY())) {
            return true;
        } else return false;
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;

            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                break;


            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }

        int idPadre = super.onTouchEvent(event);
        if (idPadre != idEscena) {
            return idPadre;
        }
        return idEscena;
    }

}