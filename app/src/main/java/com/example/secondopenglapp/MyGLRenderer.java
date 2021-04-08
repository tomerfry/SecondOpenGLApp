package com.example.secondopenglapp;

import android.opengl.GLES31;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private CircleModel circleModel;
    private float[] vPMatrix = new float[16];
    private float[] projectionMatrix = new float[16];
    private float[] viewMatrix = new float[16];

    private float xTranslation = 0.0f;
    private long initialTime;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i("SurfaceCreated", "onSurfaceCreated: ");
        GLES31.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        float[] color = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};
        this.circleModel = new CircleModel(0.0f, 0.0f, 0.2f, color);
        this.initialTime = System.nanoTime();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES31.glViewport(0, 0, width, height);
        float ratio = (float) width / height;

        Matrix.frustumM(this.projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES31.glClear(GLES31.GL_COLOR_BUFFER_BIT | GLES31.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
        Matrix.translateM(vPMatrix, 0, 0.1f, 0.1f, 0.1f);


        this.circleModel.draw(vPMatrix);
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES31.glCreateShader(type);
        GLES31.glShaderSource(shader, shaderCode);
        GLES31.glCompileShader(shader);

        return shader;
    }
}
