package base;

public class ADHero implements Mortal{
    @Override
    public void die() {
        System.out.println("我是AD，我被盖伦杀了");
    }
}
