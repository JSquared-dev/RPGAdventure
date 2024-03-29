/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewEvent implements Event<WindowListener> {

    enum Type {
        ACTIVATED, CLOSED, CLOSING, DEACTIVATED, DEICONIFIED, ICONIFIED, OPENED
    }
    
    Type type;
    WindowEvent event;
    
    public ViewEvent(Type nType, WindowEvent nEvent) {
        // TODO Auto-generated constructor stub
        type = nType;
        event = nEvent;
    }

    @Override
    public void notify(WindowListener listener) {
        System.out.println("window event " + type.toString());
        // TODO Auto-generated method stub
        switch(type) {
            case ACTIVATED:
                listener.windowActivated(event);
                break;
            case CLOSED:
                listener.windowClosed(event);
                break;
            case CLOSING:
                listener.windowClosing(event);
                break;
            case DEACTIVATED:
                listener.windowDeactivated(event);
                break;
            case DEICONIFIED:
                listener.windowDeiconified(event);
                break;
            case ICONIFIED:
                listener.windowIconified(event);
                break;
            case OPENED:
                listener.windowOpened(event);
                break;
            default:
                System.err.println("Unknown View Event type");
                break;
        }
    }

}
