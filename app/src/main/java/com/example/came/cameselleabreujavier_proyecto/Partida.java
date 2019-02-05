package com.example.came.cameselleabreujavier_proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Partida extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;//contexto de la aplicacion

    private Bitmap bitmapFondo;
    static int anchoPantalla = 1, altoPantalla = 1;//se actualiza en surfaceChanged
    private Utils utils;
    private Hilo hilo;
    private SurfaceHolder surfaceHolder;//maneja superficie de dibujado
    private Fondo[] fondo;
    private boolean funcionando = false;//Control del hilo

    public Partida(Context context) {
        super(context);
        this.context = context;
        utils = new Utils(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        hilo = new Hilo();
        setFocusable(true);
        bitmapFondo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);
        bitmapFondo = Bitmap.createScaledBitmap(bitmapFondo, bitmapFondo.getWidth(), altoPantalla, true);
        fondo[0] = new Fondo(0,bitmapFondo, anchoPantalla,1);
        fondo[1] = new Fondo(0,bitmapFondo, anchoPantalla,1);

    }

    public void actualizarFisica() {
        fondo[0].mover(10);
        fondo[1].mover(10);

        if (fondo[0].posicion.x > anchoPantalla) {
            fondo[0].posicion.x = fondo[1].posicion.x - fondo[0].imagen.getWidth();
        }
        if (fondo[1].posicion.x > anchoPantalla) {
            fondo[1].posicion.x = fondo[0].posicion.x - fondo[1].imagen.getWidth();
        }
    }

    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(fondo[0].imagen, fondo[0].posicion.x, fondo[0].posicion.y, null);
            c.drawBitmap(fondo[1].imagen, fondo[1].posicion.x, fondo[1].posicion.y, null);
        } catch (Exception e) {
        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hilo.setFuncionando(true); // Se le indica al hilo que puede arrancar
        if (hilo.getState() == Thread.State.NEW)
            hilo.start(); // si el hilo no ha sido creado se crea;
        if (hilo.getState() == Thread.State.TERMINATED) {      // si el hilo ha sido finalizado se crea de nuevo;
            hilo = new Hilo();
            hilo.start(); // se arranca el hilo
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        hilo.getSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    class Hilo extends Thread {


        public Hilo() {

        }

        @Override
        public void run() {
            while (funcionando) {
                Canvas c = null;//repintar el lienzo
                try {
                    if (!surfaceHolder.getSurface().isValid()) continue;
                    synchronized (surfaceHolder) {
                        actualizarFisica();
                        dibujar(c);
                    }
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }


        public void getSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {
                if (bitmapFondo != null) {
                    bitmapFondo = Bitmap.createScaledBitmap(bitmapFondo, width, height, true);
                }
            }
        }

        public void setFuncionando(boolean flag) {
            funcionando = flag;
        }
    }

}