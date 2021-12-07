package base;

/**
 * 继承测试
 */
public class ExtendTest {
    public static void main(String[] args) {
        Person2 sturent = new Student();

    }

}

 class Person2 {
   private String name;
    private int age;

    public Person2(){

    }
     public Person2(String name, int age) {
         this.name = name;
         this.age = age;
     }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}

class Student extends Person2{
    private String className;

    public Student(){

    }

    public Student(String name, int age, String className) {
        super(name, age);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
