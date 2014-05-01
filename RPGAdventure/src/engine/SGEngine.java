/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package engine;

import java.util.Comparator;
import java.util.PriorityQueue;

import view.View;
import view.ViewCloseEvent;
import view.ViewCloseListener;
import entity.EntityManager;
import event.EventManager;

public class SGEngine implements Comparator<SGSystem>, ViewCloseListener {

    protected boolean running = true;
    protected EventManager eventManager = new EventManager();
    protected EntityManager entityManager = new EntityManager(eventManager);
    
    // ordered queue of systems to be called every update.
    PriorityQueue<SGSystem> systems = new PriorityQueue<SGSystem>(11, this);

    public SGEngine() {
        // TODO Auto-generated constructor stub
        eventManager.listen(ViewCloseEvent.class, this);
        systems.add(entityManager);
    }
    
    // returns whether the engine is running
    public boolean isRunning() {
        return running;
    }
    // change the engine running state
    public void setRunning(boolean isrunning) {
        running = isrunning;
    }
    
    // return the event manager for this engine instance
    public EventManager getEventManager() {
        return eventManager;
    }
    
    // return the entity manager for this engine instance
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    // attach a system to the engine
    public void attach(SGSystem nSystem) {
        systems.add(nSystem);
    }
    
    // call all attached systems to update themselves.
    public void update() {
        for (SGSystem sys : systems.toArray(new SGSystem[0])) {
            sys.update();
        }
    }
    
    // main loop. calls update repeatedly until isRunning() returns false
    public void mainLoop() {
        while (isRunning()) {
            update();
        }
    }

    // Comparator for ordered queue of systems.
    @Override
    public int compare(SGSystem arg0, SGSystem arg1) {
        if (arg0.priority < arg1.priority) {
            return -1;
        }
        if (arg0.priority > arg1.priority) {
            return  1;
        }
        else {
            return 0;
        }
    }

    // called when a View object is closing.
    @Override
    public synchronized void shutdownView(View view) {
        systems.remove(view); // remove view from system calls
        view.shutdown(); // shutdown the view appropriately
        running = false; // set running to false, engine will exit on next loop
    }

}
