package arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;

public class ArithmeticTest {
    public static void main(String[] args) {
        /**
         * 冒泡排序
         */
        System.out.println("-----------冒泡【升序】-----------");
        int[] a = {1, 5, 9, 3, 2, 6, 8};
        //升序
        for (int j = a.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {//i+1最大=length-1,这里第一次比较6次，第二次就是5次，第三次就是4次。。。。
                if (a[i] > a[i + 1]) {
                    Integer temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
        for (int i : a) {
            System.out.println(i);
        }
        System.out.println("-----------冒泡【降序】-----------");
        //降序
        for (int j = a.length-1; j >0 ; j--) {
            for (int i = 0; i < j; i++) {
                if(a[i]<a[i+1]){
                    int temp=a[i];
                    a[i]=a[i+1];
                    a[i+1]=temp;
                }
            }
        }
        for (int i : a) {
            System.out.println(i);
        }
        System.out.println("重新练习");
        int[] c = {1, 5, 9, 3, 2, 6, 8};
        for (int k = c.length; k >0 ; k--) {
            for (int i = 0; i < c.length - 1; i++) {
                if(c[i]<c[i+1]){
                    int temp=c[i];
                    c[i]=c[i+1];
                    c[i+1]=temp;
                }
            }
        }
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }


    }

}
