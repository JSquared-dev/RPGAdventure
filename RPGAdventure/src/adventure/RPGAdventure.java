/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package adventure;

import view.View;
import engine.SGEngine;
import engine.Space;
import entity.Entity;
import entity.HealthComponent;
import entity.MeshComponent;
import event.Command;
import event.CommandEvent;
import event.CommandListener;

public class RPGAdventure extends SGEngine implements CommandListener {
    
    public RPGAdventure() {
        // TODO Auto-generated constructor stub
        eventManager.listen(CommandEvent.class, this);
    }

    @Override
    public void executeCommand(Command command) {
        // TODO Auto-generated method stub
        if (command.getCommandLine().trim().equals("exit")) {
            running = false;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RPGAdventure adventure = new RPGAdventure();
        // create a view to display the game
        View view = new View(adventure.getEventManager());
        view.setTitle("RPG Adventure");
        adventure.attach(view);
        view.setSpace(new Space());
        
        // load entities
        Entity testEntity = new Entity();
        testEntity.attach(new HealthComponent());
        testEntity.attach(new MeshComponent());
        view.getSpace().addEntity(testEntity);
        // game loop
        adventure.mainLoop();
        
        // exit after break out of game loop
    }
}
