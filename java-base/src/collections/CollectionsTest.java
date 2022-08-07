package collections;

import java.util.concurrent.ConcurrentHashMap;

public class CollectionsTest{
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        objectObjectConcurrentHashMap.put("a", "1");
        objectObjectConcurrentHashMap.put("b", "2");
        objectObjectConcurrentHashMap.put("c", "3");

        for (String s : objectObjectConcurrentHashMap.keySet()) {
            System.out.println(objectObjectConcurrentHashMap.get(s));
        }
    }
}
