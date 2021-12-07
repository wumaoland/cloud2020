package base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*请编写一个根据name查找score的程序，并利用Map充当缓存，以提高查找效率：*/
public class MapTest {
    public static void main(String[] args) {
        List<Teacher> teachers = Arrays.asList(new Teacher("田惜君", 32),
                new Teacher("刘德华", 32),
                new Teacher("周杰伦", 45),
                new Teacher("张译", 42));

        Students students = new Students(teachers);
        System.out.println(students.getScore("刘德华"));
    }
}
class Students {
    List<Teacher> list;
    Map<String, Integer> cache;

    Students(List<Teacher> list) {
        this.list = list;
        cache = new HashMap<>();
    }

    //	将每次查询的历史记录下来，使用map保存充当缓存，优先从缓存里面查找，以便下次重复查询，提供查询效率
    int getScore(String name) {
        // 先在Map中查找:
        Integer score = this.cache.get(name);
        if (score == null) {
            // TODO:
            score = findInList(name);
            if(score != null) {
                // 存在，缓存到MAP，下次查找则直接查询MAP
                this.cache.put(name, score);
            }else {
                System.out.println(String.format("学生名：%s, 系统中不存在!", name));
            }
        }
        return score == null ? -1 : score.intValue();
    }

    Integer findInList(String name) {
        for (Teacher ss : this.list) {
            if (ss.name.equals(name)) {
                return ss.score;
            }
        }
        return null;
    }
}

class Teacher {
    String name;
    int score;

    Teacher(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

