package view;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.FPSAnimator;

import engine.SGSystem;
import engine.Space;
import entity.Entity;
import entity.MeshComponent;
import entity.TransformComponent;
import event.EventManager;

public class View extends SGSystem implements WindowListener, GLEventListener {

    GLProfile glProfile;
    GLCapabilities glCaps;
    GLCanvas glCanvas;

    Frame frame;
    FPSAnimator animator;

    EventManager eventManager;
    Space space = null;

    public View(EventManager nEvtManager) {
        super(Integer.MAX_VALUE);
        // TODO Auto-generated constructor stub
        eventManager = nEvtManager;

        glProfile = GLProfile.get(GLProfile.GL2);
        glCaps = new GLCapabilities(glProfile);
        glCanvas = new GLCanvas(glCaps);

        frame = new Frame("View");
        frame.setSize(800, 600);
        frame.add(glCanvas);
        frame.setVisible(true);

        // eventManager should catch all events, and notify listeners
        // appropriately
        frame.addWindowListener(this);
        glCanvas.addGLEventListener(this);

        animator = new FPSAnimator(glCanvas, 60);
        animator.start();
    }

    public synchronized void shutdown() {
        // shutdown this view. hiding it from the user.
        animator.stop();
        frame.setVisible(false);
        frame.remove(glCanvas);
        frame.removeWindowListener(this);
        frame.dispose();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

        // System.out.println("Updated View");
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        eventManager.notify(new ViewCloseEvent(this));
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        // draw space
        Vertex translation;
        GL2 gl = drawable.getGL().getGL2();
        // GLU glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        if (space != null) {
            gl.glPushMatrix(); // save translation/rotation state

            // camera rotations
            translation = space.getCamera().get(TransformComponent.class).getRotation();
            gl.glRotatef(-translation.x, 1.0f, 0.0f, 0.0f);
            gl.glRotatef(-translation.y, 0.0f, 1.0f, 0.0f);
            gl.glRotatef(-translation.z, 0.0f, 0.0f, 1.0f);
            gl.glPushMatrix(); // save the rotation for use later

            translation = space.getCamera().get(TransformComponent.class).getLocation();
            // camera translations are opposite to normal translations
            gl.glTranslatef( translation.x,  translation.y,  translation.z);

            gl.glColor3f(1.0f, 1.0f, 1.0f);
            // array of meshes representing the environment to render
            /*
             * Cell[] cellListToRender = scene.world.toArray(new Cell[0]); for
             * (int i = 0; i < cellListToRender.length; i++) { renderCell(gl,
             * cellListToRender[i]); }
             */
            /*
             * Render all meshes stored in space.
             */
            Entity[] entityList = space.getAllEntities();
            for (Entity ent : entityList) {
                gl.glPushMatrix();
                
                TransformComponent transComp = ent.get(TransformComponent.class);
                if (transComp != null) {/*
                    gl.glRotatef(transComp.getRotation().x, 1.0f, 0, 0);
                    gl.glRotatef(transComp.getRotation().y, 0, 1.0f, 0);
                    gl.glRotatef(transComp.getRotation().z, 0, 0, 1.0f);

                    gl.glTranslatef(transComp.getLocation().getX(), transComp.getLocation().getY(),
                            transComp.getLocation().getZ());*/
                }
                
                MeshComponent meshComp = ent.get(MeshComponent.class);
                if (meshComp != null) {
                    renderMesh(gl, meshComp.getMesh());
                }
                gl.glPopMatrix();
            }
            gl.glPopMatrix();
            gl.glPopMatrix();
        }
        gl.glFlush();
    }

    private void renderMesh(GL2 gl, Mesh mesh) {
        gl.glBegin(GL.GL_TRIANGLES);
        {
            Triangle[] toRender = mesh.getTriangles();
            if (toRender != null) {
                for (int j = 0; j < toRender.length; j++) {
                    Vertex[] vert = toRender[j].getVertices();
                    if (vert.length == 3) {
                        for (int k = 0; k < 3; k++) {
                            gl.glVertex3f(vert[k].getX(), vert[k].getY(), vert[k].getZ());
                        }
                    }
                    else {
                        System.err.println("Attempting to render invalid triangle");
                    }
                }
            }
        }
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        // GL object being destroyed
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // initialise the GL display
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        gl.glShadeModel(GL2.GL_SMOOTH);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        // reshape the GL display
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        height = (height <= 0) ? 1 : height; // ensure we don't divide by zero
        float aspect = width / height;
        gl.glViewport(0, 0, width, height);

        // modify perspective of projection
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);

        // return to model view
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static Entity createCamera() {
        Entity nEntity = new Entity();
        TransformComponent cameraTrans = new TransformComponent();
        cameraTrans.setLocation(new Vertex(0, 0, -6.0f));
        nEntity.attach(cameraTrans);
        return nEntity;
    }
}
