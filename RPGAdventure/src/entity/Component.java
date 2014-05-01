package entity;

public abstract class Component {

    protected Entity parent;
    protected boolean isActive = true;
    
    public final Entity getParent() {
        return parent;
    }
    public final void setParent(Entity parent) {
        this.parent = parent;
    }
    
    public final boolean isActive() {
        return isActive;
    }
    public final void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void init() {
        
    }
    
    public void update() {
        
    }
    
    public void shutdown() {
        
    }
    
}
