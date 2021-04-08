# Vertex Shader
attribute vec4 vPosition;

void main() {
    gl_Position = vPosition;
}

# Fragment Shader
precision mediump float;
uniform vec4 vColor;

void main() {
    gl_FragColor = vColor;
}