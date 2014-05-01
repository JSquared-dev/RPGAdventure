package entity;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    public boolean isActive = false;
    private Map<Class<? extends Component>, Component> components = 
                                            new HashMap<Class<? extends Component>, Component>();
    public Entity() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean attach(Component nComponent) {
        if (components.containsKey(nComponent.getClass())) {
            System.out.println("Entity already contains component " + nComponent.getClass().getName());
            return false;
        }
        else {
            System.out.println("Adding component " + nComponent.getClass().getName() + " to Entity");
            nComponent.setParent(this);
            nComponent.init();
            components.put(nComponent.getClass(), nComponent);
            return true;
        }
    }
    
    public <T extends Component> boolean has(Class<T> comp) {
        return components.containsKey(comp);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Component> T get(Class<T> comp) {
        if (has(comp)) {
            return (T) components.get(comp);
        }
        else {
            return null;
        }
    }
    
    public void update() {
        if (isActive) {
            for (Component comp : components.values()) {
                comp.update();
            }
        }
    }
    
    public void setActive(boolean nActive) {
        isActive = nActive;
    }
    
    public boolean isActive() {
        return isActive;
    }

}
