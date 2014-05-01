package engine;

public abstract class SGSystem {

    public int priority;
    
    public SGSystem(int nPriority) {
        // TODO Auto-generated constructor stub
        priority = nPriority;
    }
    
    public abstract void update();

}
