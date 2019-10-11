package manager;

import domin.BaseWare;

/**
 * Created by wangxiaodi1 on 2019/2/13.
 */
public interface BaseManager<T extends BaseWare>  {
    void get(T t);
}
