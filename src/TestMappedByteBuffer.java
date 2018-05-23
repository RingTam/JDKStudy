import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/1/15 9:39
 * 说明：
 */
public class TestMappedByteBuffer {


    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src" + File.separator + "write.txt");
        File file = path.toFile();
        if(!file.exists()){
            System.out.println("创建文件：" + file.getPath() + " 结果：" + file.createNewFile());
        }
        FileChannel fc = FileChannel.open(path,
                StandardOpenOption.READ, StandardOpenOption.WRITE);
        // StandardOpenOption.DELETE_ON_CLOSE 测试删除4 删除结果：true
        FileLock fileLock = fc.tryLock(0, fc.size(), false);
        if(fileLock == null) {
            throw new NullPointerException("无法锁定文件！");
        }
        if(fileLock.isValid()) {
            System.out.println("独占锁定！");
        }
        //将文件映射到虚拟内存（不是物理内存）
        //访问映射缓存区的三种方式
        //1 MapMode.READ_ONLY 只读 对映射缓存区的更改将导致抛出 ReadOnlyBufferException
        //2 MapMode.READ_WRITE 读写 对映射缓存区的更改，影响到文件，其他程序可见性
        //3 MapModel.PRIVATE copy-on-write 私有 将映射缓存区拷贝一份副本并进行更改（写入） 不会影响到文件，其他程序不可见性

        //创建映射缓存区，使用虚拟内存页
        //问题
        //1 内存占用 2 文件关闭
        MappedByteBuffer mappedByteBuffer = fc.map(FileChannel.MapMode.PRIVATE, 0, 128);
        mappedByteBuffer.put("Hello World!".getBytes(StandardCharsets.UTF_8));
        mappedByteBuffer.force();//迫使缓存写入磁盘

        fileLock.release();
        //System.gc(); 测试结果：false 只是标记，未进行立刻回收
        //测试删除1 删除结果：false 无法删除
        /*System.out.println("第一次，被MappedByteBuffer打开的文件，只有在垃圾收集时才会被关闭。测试删除：" +
                path.toFile().delete());*/

        //测试删除2 在虚拟机退出时删除，再调用关闭虚拟机，结果：无法删除
        //path.toFile().deleteOnExit();
        //System.exit(0);

        //测试删除3 删除结果：
        //手动代码，回收
        /*AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner)
                            getCleanerMethod.invoke(mappedByteBuffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        System.out.println("第二次，被MappedByteBuffer打开的文件，只有在垃圾收集时才会被关闭。测试删除：" +
                path.toFile().delete());*/

        fc.close();

    }
}
