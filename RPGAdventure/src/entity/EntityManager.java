/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

import engine.SGSystem;
import event.EventManager;

public class EntityManager extends SGSystem {
    
    List<Entity> entities = new ArrayList<Entity>();
    List<Entity> toRemove = new ArrayList<Entity>();
    List<Entity> toAdd = new ArrayList<Entity>();
    

    public EntityManager(EventManager eventManager) {
        // TODO Auto-generated constructor stub
        super(Integer.MAX_VALUE-1);
    }

    /*
     * update entities list to include all entities added/removed since last update.
     * call .update() on all entities in list.
     */
    public void update() {
        if (!toRemove.isEmpty()) {
            entities.removeAll(toRemove);
            toRemove.clear();
        }
        if (!toAdd.isEmpty()) {
            entities.addAll(toAdd);
            toAdd.clear();
        }
        for (Entity ent : entities.toArray(new Entity[0])) {
            if (ent.isActive()) {
                ent.update();
            }
        }
    }

    public void add(Entity nEntity) {
        toAdd.add(nEntity);
    }
    
    public void remove(Entity nEntity) {
        toRemove.add(nEntity);
    }
}
