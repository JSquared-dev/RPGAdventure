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
