package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Mesh {

    List<Triangle> triangles; // list of triangles describing mesh
    Vertex location, rotation; // center location of mesh and rotation
    
    public Mesh() {
        // TODO Auto-generated constructor stub
        triangles = new ArrayList<Triangle>();
        location = new Vertex(0.0f, 0.0f, 0.0f);
        rotation = new Vertex(0.0f, 0.0f, 0.0f);
    }
    
    public Mesh(String fileName) throws IOException {
        List<Vertex> vertices = new ArrayList<Vertex>();
        triangles = new ArrayList<Triangle>();
        location = new Vertex(0.0f, 0.0f, 0.0f);
        rotation = new Vertex(0.0f, 0.0f, 0.0f);
        InputStream fStream = this.getClass().getResourceAsStream(fileName);
        BufferedReader fReader = new BufferedReader(new InputStreamReader(fStream));
        while (fReader.ready()) {
            String toParse = fReader.readLine();
            if (toParse.startsWith("v ")) {
                float[] tmpVerts = new float[3];
                toParse = toParse.substring(2) + " "; // cut the 'v ' from the beginning of the line
                for (int i = 0; i < 3; i++) {
                    tmpVerts[i] = Float.parseFloat(toParse.substring(0, toParse.indexOf(" ")));
                    toParse = toParse.substring(toParse.indexOf(" ")+1);
                }
                vertices.add(new Vertex(tmpVerts[0], tmpVerts[1], tmpVerts[2])); // add a new vertex described by current line in toParse
            }
            else if (toParse.startsWith("f ")) {
                toParse = toParse.substring(2).concat(" ");
                Vertex[] triVerts = new Vertex[3];
                for (int i = 0; i < 3; i ++) {
                    int index = Integer.parseInt(toParse.substring(0, toParse.indexOf(" "))) - 1;
                    if (index >= 0 && index < vertices.size()) {
                        triVerts[i] = vertices.get(index);
                        // cut first int from toParse
                        toParse = toParse.substring(toParse.indexOf(" ")+1);
                    }
                    else {
                        System.out.println("Invalid Vertex ID for triangle in " + fileName);
                    }
                        
                }
                addTriangle(new Triangle(triVerts));
            }
        }
    }

    public Triangle[] getTriangles() {
        return (Triangle[]) triangles.toArray(new Triangle[1]);
    }
    
    public boolean addTriangle(Triangle tri) {
        return triangles.add(tri);
    }

    public Vertex getLocation() {
        return location;
    }
    
    public void setLocation(Vertex nLoc) {
        location = nLoc;
    }

    public Vertex getRotation() {
        return rotation;
    }
    
    public void setRotation(Vertex nRot) {
        rotation = nRot;
    }
}
