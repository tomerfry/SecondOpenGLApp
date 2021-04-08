package com.example.secondopenglapp;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.io.IOException;

public class MyGLSurfaceView extends GLSurfaceView {
    private MyGLRenderer renderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        renderer = new MyGLRenderer();
        setRenderer(renderer);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
