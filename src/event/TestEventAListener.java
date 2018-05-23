package event;

/**
 * 类名：
 * 作者：Monster
 * 时间：2015/12/24 17:16
 * 说明：
 */
public class TestEventAListener implements TestEventListener {

    @Override
    public void event(TestEvent event) {
        System.out.println(event.getSource());
        System.out.println(event.hashCode() + ":Hello TestA!");
    }
}
