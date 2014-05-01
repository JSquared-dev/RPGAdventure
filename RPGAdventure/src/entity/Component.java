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
