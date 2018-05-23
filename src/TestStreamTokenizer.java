import java.io.*;

/**
 * @author Monster
 */
public class TestStreamTokenizer {

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(
                new InputStreamReader(new FileInputStream(
                        Constants.getSrcInternalFile("TestStreamTokenizer.txt")), "UTF-8"));
        while(tokenizer.nextToken() != StreamTokenizer.TT_EOF){
            System.out.print(tokenizer.sval + "\t");
        }
        System.out.println();
    }
}
