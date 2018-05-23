package event;

import java.util.EventListener;

/**
 * 类名：
 * 作者：Monster
 * 时间：2015/12/24 17:11
 * 说明：
 */
public interface TestEventListener extends EventListener {

    void event(TestEvent event);
}
