import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/11 17:09
 * 说明：
 */
public class Calculator extends RecursiveTask<Integer> {

    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        for (int i = start; i < end / 2; i++) {
            Calculator calculator = new Calculator(start, end / 2);
            calculator.fork();
        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinTask<Integer> fjt = fjp.submit(new Calculator(1000, 10000));
        System.out.println(fjt.get());
        fjp.shutdown();
    }

}
