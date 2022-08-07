package base;

import java.util.Scanner;

/**
 * 引用练习
 */
public class Hero {
    //int i = 1; //属性名是i
    public void  method1(final int i){ //参数也是i
        System.out.println(i);
    }
    public void kill(Mortal m){
        m.die();
    }
    public static void main(String[] args) {
//        Hero hero = new Hero();
//        Hero hero1=new Hero();
//        Hero h3;
//        Hero h4;
//        h3=hero;
//        h4=h3;
//        if(h4 == hero1){
//            System.out.println("yes");
//        }
//        //字符串左边补0
//        String a=String.format("%08d%n",2022);
//        System.out.println(a);
//        System.out.println("请输入地名:");
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        System.out.println(String.format("您输入的地名是%s",s));


            //new Hero().method1(5);
            //结果打印出来是 1还是5?
//        int i = 1;
//        int j = i++ + ++i + ++i    + i-- + i++;//1+3+4+4+3
//        System.out.println(j);

        //长路与  两边都走
        //短路与  左边false，右边不走
        //长路或  两边都走
        //短路或  左边true,右边不走
        //长路或  无论第一个表达式的值是true或者false,第二个的值，都会被运算
//        System.out.println(2<<3);
        Hero hero = new Hero();
        ADAPHero adapHero = new ADAPHero();
        ADHero adHero = new ADHero();
        APHero apHero = new APHero();
        hero.kill(adapHero);
        hero.kill(adHero);
        hero.kill(apHero);

    }
}
