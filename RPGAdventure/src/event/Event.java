package event;

public interface Event<T> {

    public void notify(T listener);

}
