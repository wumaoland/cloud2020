package base;

/**
 * List测试
 */
public class ArrayTest {
    public static void main(String[] args) {
        //分割词，需要注意转义
        String abc="你好|我是";
        String[] split = abc.split("\\|");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
