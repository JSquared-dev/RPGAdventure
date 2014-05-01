package view;

public class Triangle {

    Vertex[] vertices;
    
    public Triangle(Vertex[] nVerts) {
        if (nVerts.length != 3) {
            System.err.println("Triangle requires 3 vertices!");
            return;
        }
        else {
            vertices = nVerts;
        }
    }
    public Triangle(float[] nVerts) {
        if (nVerts.length != 9) {
            System.err.println("Triangle requires 3 vertices!");
            return;
        }
        else {
            vertices = new Vertex[3];
            vertices[0] = new Vertex(nVerts[0],nVerts[1],nVerts[2]);
            vertices[1] = new Vertex(nVerts[3],nVerts[4],nVerts[5]);
            vertices[2] = new Vertex(nVerts[6],nVerts[7],nVerts[8]);
        }
    }
    
    Vertex[] getVertices() {
        return vertices;
    }
    
    void setVertex(int n, Vertex vert) {
        if (n >=0 && n < 3) {
            vertices[n] = vert;
        }
        else {
            System.err.println("Invalid vertex id when setting vertex of Triangle object");
        }
    }

}
