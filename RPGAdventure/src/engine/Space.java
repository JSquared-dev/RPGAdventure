package engine;

import java.util.ArrayList;
import java.util.List;

import view.View;
import entity.Entity;
import entity.MeshComponent;

public class Space {

    Entity camera = View.createCamera();
    List<Entity> entities = new ArrayList<Entity>();
    
    public Space() {
        // TODO Auto-generated constructor stub
    }
    
    public void addEntity(Entity nEntity) {
        if (nEntity.has(MeshComponent.class))
            entities.add(nEntity);
        else 
            System.out.println("Entity does not have a mesh component to render");
    }
    
    public void removeEntity(Entity nEntity) {
        entities.remove(nEntity);
    }
    
    public Entity getCamera() {
        return camera;
    }

    public Entity[] getAllEntities() {
        return entities.toArray(new Entity[0]);
    }
}
