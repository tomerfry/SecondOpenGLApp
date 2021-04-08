package com.example.secondopenglapp;

import android.opengl.GLES31;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class CircleModel {

    private ArrayList<Float> circleCoords;
    private float xPos, yPos, radius;
    private FloatBuffer vertexBuffer;
    private int mPorgram;
    private int positionHandle, colorHandle, vPMatrixHandle;
    private float[] color;


    static final short steps = 40;
    static final float angle = (float) Math.PI * 2.0f / steps;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "uniform mat4 uMVPMatrix;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    public CircleModel(float xPos, float yPos, float radius, float[] color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.color = color;

        float prevX = this.xPos, prevY = this.yPos - this.radius;
        float newX, newY;

        this.circleCoords = new ArrayList<Float>();

        for (int i = 0; i <= steps; i++) {
            newX = radius * (float) (Math.sin((double) angle * i));
            newY = -radius * (float) (Math.cos((double) angle * i));

            this.circleCoords.add(prevX);
            this.circleCoords.add(prevY);
            this.circleCoords.add(0.0f);


            this.circleCoords.add(newX);
            this.circleCoords.add(newY);
            this.circleCoords.add(0.0f);


            this.circleCoords.add(this.xPos);
            this.circleCoords.add(this.yPos);
            this.circleCoords.add(0.0f);

            prevX = newX;
            prevY = newY;
        }

        this.createModelVertexBuffer();
        this.loadShaderCodes();
    }

    public void createModelVertexBuffer() {
        ByteBuffer bb = ByteBuffer.allocateDirect(circleCoords.size() * Float.BYTES);
        bb.order(ByteOrder.nativeOrder());

        this.vertexBuffer = bb.asFloatBuffer();
        float[] floats = new float[this.circleCoords.size()];
        int i = 0;
        for (float f:this.circleCoords) {
            floats[i] = f;
            i++;
        }

        this.vertexBuffer.put(floats);
        this.vertexBuffer.position(0);
    }

    public void loadShaderCodes() {
        int vertexShader = MyGLRenderer.loadShader(GLES31.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES31.GL_FRAGMENT_SHADER, fragmentShaderCode);

        this.mPorgram = GLES31.glCreateProgram();
        GLES31.glAttachShader(this.mPorgram, vertexShader);
        GLES31.glAttachShader(this.mPorgram, fragmentShader);
        GLES31.glLinkProgram(this.mPorgram);
    }

    public void draw(float[] mvpMatrix) {
        GLES31.glUseProgram(this.mPorgram);

        this.positionHandle = GLES31.glGetAttribLocation(mPorgram, "vPosition");
        this.vPMatrixHandle = GLES31.glGetUniformLocation(this.mPorgram, "uMVPMatrix");
        GLES31.glUniformMatrix4fv(this.vPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES31.glEnableVertexAttribArray(this.positionHandle);
        GLES31.glVertexAttribPointer(this.positionHandle, 3, GLES31.GL_FLOAT, false, 3 * Float.BYTES, this.vertexBuffer);

        colorHandle = GLES31.glGetUniformLocation(mPorgram, "vColor");
        GLES31.glUniform4fv(colorHandle, 1, this.color, 0);

        GLES31.glDrawArrays(GLES31.GL_TRIANGLES, 0, this.circleCoords.size() / 3);

        GLES31.glDisableVertexAttribArray(this.positionHandle);
    }
}
