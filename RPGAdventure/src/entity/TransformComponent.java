package entity;

import view.Vertex;

public class TransformComponent extends Component {

    private Vertex location = new Vertex(0,0,0);
    private Vertex rotation = new Vertex(0,0,0);

    public TransformComponent() {
        // TODO Auto-generated constructor stub
    }

    public TransformComponent(Vertex location, Vertex rotation) {
        // TODO Auto-generated constructor stub
        this.location = location;
        this.rotation = rotation;
    }
    
    public Vertex getLocation() {
        return location;
    }

    public void setLocation(Vertex location) {
        this.location = location;
    }

    public Vertex getRotation() {
        return rotation;
    }

    public void setRotation(Vertex rotation) {
        this.rotation = rotation;
    }

    public void move(float x, float y, float z) {
        location = location.add(new Vertex(x,y,z));
    }
    
    public void rotate(float x, float y, float z) {
        rotation = rotation.add(new Vertex(x,y,z));
    }


}
