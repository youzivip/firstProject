package isolation;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class Event<T> {
    private T t;
    private T[] tArray;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T[] gettArray() {
        return tArray;
    }

    public void settArray(T[] tArray) {
        this.tArray = tArray;
    }
}
