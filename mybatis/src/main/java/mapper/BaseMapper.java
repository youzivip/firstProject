package mapper;

import domin.BaseWare;

/**
 * Created by wangxiaodi1 on 2019/2/13.
 */
public interface BaseMapper<T extends BaseWare> {
     void update(T t);
}
