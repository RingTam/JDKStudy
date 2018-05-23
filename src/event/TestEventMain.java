package event;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：
 * 作者：Monster
 * 时间：2015/12/24 17:13
 * 说明：
 */
public class TestEventMain {
    public static void main(String[] args) {
        TestEvent event = new TestEvent(new Object());
        List<TestEventListener> listeners = new ArrayList<>();
        listeners.add(new TestEventAListener());
        listeners.add(new TestEventBListener());
        for (TestEventListener listener : listeners) {
            listener.event(event);
        }
    }
}
