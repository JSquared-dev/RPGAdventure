package entity;

import java.io.IOException;

import view.Mesh;
import view.Triangle;
import view.Vertex;

public class MeshComponent extends Component {

    Mesh mesh;

    public MeshComponent() {
        // TODO Auto-generated constructor stub
        mesh = new Mesh();
        {
            Vertex[] tmpVerts = new Vertex[3];
            tmpVerts[0] = new Vertex(-1.0f, -1.0f, -6.0f);
            tmpVerts[1] = new Vertex(-1.0f,  1.0f, -6.0f);
            tmpVerts[2] = new Vertex( 1.0f,  1.0f, -6.0f);
            mesh.addTriangle(new Triangle(tmpVerts));
        }
        {
            Vertex[] tmpVerts = new Vertex[3];
            tmpVerts[0] = new Vertex( 1.0f,  1.0f, -6.0f);
            tmpVerts[1] = new Vertex(-1.0f, -1.0f, -6.0f);
            tmpVerts[2] = new Vertex( 1.0f, -1.0f, -6.0f);
            mesh.addTriangle(new Triangle(tmpVerts));
        }
    }

    /* load mesh from file */
    public void loadFromFile(String file) throws IOException {
        mesh = new Mesh(file);
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Mesh getMesh() {
        return mesh;
    }

}
