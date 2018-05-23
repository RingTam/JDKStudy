import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 类名：测试 文件锁
 * 作者：Monster
 * 时间：2016/1/13 11:50
 * 说明：
 */
public class TestFileLock {

    public static void main(String[] args) {
        testWrite();
        System.out.println("-----------------------------------------------------------");
        testRead();
    }

    private static void testWrite() {
        Path targetPath = Paths.get("src" + File.separator + "write.txt");
        FileChannel channel = null;
        try {
            channel = FileChannel.open(targetPath,
                    StandardOpenOption.READ, //Lock
                    StandardOpenOption.WRITE //Lock
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer buffer = null;
        try {
            buffer = ByteBuffer.wrap("你好，File Lock世界！！！！！！！！！！！".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FileLock fileLock = null;
        FileLock fileLock2 = null;
        try {
            if (channel == null) {
                throw new NullPointerException("通道为空！");
            }
            //尝试锁定0~10, 10~20 如果两次范围重叠，报OverlappingFileLockException

            //1 测试 独占false 独占false 结果：第一次，写入通道成功！
            //fileLock = channel.tryLock(0, 10, false);
            //fileLock2 = channel.tryLock(10, 20, false);

            //2 测试 共享true 独占false 结果：锁1发布，第二次，写入通道成功！
            //fileLock = channel.tryLock(0, 10, true);
            //fileLock2 = channel.tryLock(10, 20, false);

            //3 测试 共享true 共享true 结果：锁2发布，第三次，写入通道成功！（锁1已发布）
            //fileLock = channel.tryLock(0, 10, true);
            //fileLock2 = channel.tryLock(10, 20, true);

            //4 测试 共享false 共享true 结果：锁2发布，第三次，写入通道成功！（实际被锁2造成）
            fileLock = channel.tryLock(0, 10, false);
            fileLock2 = channel.tryLock(10, 20, true);

            //总结：共享锁不能进行写操作，要先解锁
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileLock == null) {
            throw new NullPointerException("1文件锁为空！");
        }
        if (fileLock.isShared()) {
            System.out.println("1共享锁");
        }
        if (fileLock.isValid()) {
            System.out.println("1锁有效");
        }
        if(fileLock.overlaps(0, 10)) {
            System.out.println("1是重叠0~10");
        }
        if(fileLock.overlaps(10, 20)) {
            System.out.println("1是重叠10~20");
        }

        if (fileLock2 == null) {
            throw new NullPointerException("2文件锁为空！");
        }
        if (fileLock2.isShared()) {
            System.out.println("2共享锁");
        }
        if (fileLock2.isValid()) {
            System.out.println("2锁有效");
        }
        if(fileLock2.overlaps(0, 10)) {
            System.out.println("2是重叠0~10");
        }
        if(fileLock2.overlaps(10, 20)) {
            System.out.println("2是重叠10~20");
        }
        try {
            channel.write(buffer);
            System.out.println("第一次，写入通道成功！");
        } catch (IOException e) {
            System.out.println("第一次，写入通道失败！");
            try {
                fileLock.release();
                System.out.println("锁1发布");
            } catch (IOException e1) {
                //Ignore
            }
            try {
                channel.write(buffer);
                System.out.println("第二次，写入通道成功！");
            } catch (IOException e1) {
                System.out.println("第二次，写入通道失败！");
                try {
                    fileLock2.release();
                    System.out.println("锁2发布");
                } catch (IOException e2) {
                    //Ignore
                }
                try {
                    channel.write(buffer);
                    System.out.println("第三次，写入通道成功！");
                } catch (IOException e2) {
                    System.out.println("第三次，写入通道失败！");
                    System.out.println("锁1锁2均已发布");
                }
            }
        }
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testRead() {
        Path targetPath = Paths.get("src" + File.separator + "read.txt");
        FileChannel channel = null;
        try {
            channel = FileChannel.open(targetPath,
                    StandardOpenOption.READ, //Lock
                    StandardOpenOption.WRITE //Lock
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileLock fileLock = null;
        FileLock fileLock2 = null;
        try {
            if (channel == null) {
                throw new NullPointerException("通道为空！");
            }
            //尝试锁定0~10, 10~20 如果两次范围重叠，报OverlappingFileLockException

            //1 测试 独占false 独占false 结果：第一次，通道读出缓冲区成功！
            //fileLock = channel.tryLock(0, 10, false);
            //fileLock2 = channel.tryLock(10, 20, false);

            //2 测试 共享true 独占false 结果：第一次，通道读出缓冲区成功！
            //fileLock = channel.tryLock(0, 10, true);
            //fileLock2 = channel.tryLock(10, 20, false);

            //3 测试 共享true 共享true 结果：第一次，通道读出缓冲区成功！
            //fileLock = channel.tryLock(0, 10, true);
            //fileLock2 = channel.tryLock(10, 20, true);

            //4 测试 共享false 共享true 结果：第一次，通道读出缓冲区成功！
            fileLock = channel.tryLock(0, 10, false);
            fileLock2 = channel.tryLock(10, 20, true);

            //总结：锁对读操作没有影响
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileLock == null) {
            throw new NullPointerException("1文件锁为空！");
        }
        if (fileLock.isShared()) {
            System.out.println("1共享锁");
        }
        if (fileLock.isValid()) {
            System.out.println("1锁有效");
        }
        if(fileLock.overlaps(0, 10)) {
            System.out.println("1是重叠0~10");
        }
        if(fileLock.overlaps(10, 20)) {
            System.out.println("1是重叠10~20");
        }

        if (fileLock2 == null) {
            throw new NullPointerException("2文件锁为空！");
        }
        if (fileLock2.isShared()) {
            System.out.println("2共享锁");
        }
        if (fileLock2.isValid()) {
            System.out.println("2锁有效");
        }
        if(fileLock2.overlaps(0, 10)) {
            System.out.println("2是重叠0~10");
        }
        if(fileLock2.overlaps(10, 20)) {
            System.out.println("2是重叠10~20");
        }
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        try {
            channel.read(buffer);
            System.out.printf("position: %d - limit: %d - capacity: %d\n",
                    buffer.position(), buffer.limit(), buffer.capacity());
            System.out.println("第一次，通道读出缓冲区成功！");
        } catch (IOException e) {
            System.out.println("第一次，通道读出缓冲区失败！");
            try {
                fileLock.release();
                System.out.println("锁1发布");
            } catch (IOException e1) {
                //Ignore
            }
            try {
                channel.read(buffer);
                System.out.println("第二次，通道读出缓冲区成功！");
            } catch (IOException e1) {
                System.out.println("第二次，通道读出缓冲区失败！");
                try {
                    fileLock2.release();
                    System.out.println("锁2发布");
                } catch (IOException e2) {
                    //Ignore
                }
                try {
                    channel.read(buffer);
                    System.out.println("第三次，通道读出缓冲区成功！");
                } catch (IOException e2) {
                    System.out.println("第三次，通道读出缓冲区失败！");
                    System.out.println("锁1锁2均已发布");
                }
            }
        }
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
