package base;

/**
 * equals测试
 */
public class EqualsTest {
    public static void main(String[] args) {
        EqualsPerson equalsPerson = new EqualsPerson(22, "a");
        EqualsPerson equalsPerson2 = new EqualsPerson(22, "a");
        System.out.println(equalsPerson.equals(equalsPerson2));
    }
}

/**
 * 自定义对象比较 需要重写equals，String，Integer 在java标准库中已经实现了equals方法
 */
class EqualsPerson  {
    private int age;
    private String name;

    public EqualsPerson(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o !=null) {
            EqualsPerson equalsPerson = (EqualsPerson) o;
            if (this.age == equalsPerson.age && this.name.equals(equalsPerson.name) ){
                return true;
            }
        }

        return false;
    }
}
