package view;

import event.Event;

public class ViewCloseEvent implements Event<ViewCloseListener> {

    View view;
    public ViewCloseEvent(View nView) {
        // TODO Auto-generated constructor stub
        view = nView;
    }
    @Override
    public void notify(ViewCloseListener listener) {
        // TODO Auto-generated method stub
        listener.shutdownView(view);
    }

}
