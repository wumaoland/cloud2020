package base;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ArrayTestTest {

    @Test
    public void testRetainList(){
        List<String> a=new ArrayList<>();
//        a.add("1");
        a.add("2");
//        a.add("3");
        List<String> b = new ArrayList<>();
        b.add("3");
        b.add("5");
        b.add("6");
        a.retainAll(b);
        for (String s : a) {
            System.out.println(s);
        }
    }
}