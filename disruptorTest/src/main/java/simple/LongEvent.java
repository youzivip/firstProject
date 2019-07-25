package simple;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class LongEvent {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "value:"+value;
    }
}
