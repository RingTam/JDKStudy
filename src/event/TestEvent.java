package event;

import java.util.EventObject;

/**
 * 类名：
 * 作者：Monster
 * 时间：2015/12/24 17:01
 * 说明：
 */
public class TestEvent extends EventObject {

    /**
     * 构造一个原始事件
     * @param source 最初发生的对象的事件
     */
    public TestEvent(Object source) {
        super(source);
    }
}
