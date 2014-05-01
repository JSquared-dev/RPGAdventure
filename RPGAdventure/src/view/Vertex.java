package view;

public class Vertex {

    float x, y, z; // location
    float r, g, b, a; // colour channels - red, green, blue and alpha
    float u, v; // texture co-ordinates
    public Vertex(float nX, float nY, float nZ) {
        // TODO Auto-generated constructor stub
        x = nX; y = nY; z = nZ;
        v = u = 0f;
    }

    float getX() {
        return x;
    }
    float getY() {
        return y;
    }
    float getZ() {
        return z;
    }
    float getU() {
        return u;
    }
    float getV() {
        return v;
    }

    void setX(float nX) {
        x = nX;
    }
    void setY(float nY) {
        y = nY;
    }
    void setZ(float nZ) {
        z = nZ;
    }
    void setU(float nU) {
        u = nU;
    }
    void setV(float nV) {
        v = nV;
    }

    public Vertex add(Vertex other) {
        return new Vertex(other.x + x, other.y + y, other.z+z);
    }
}
