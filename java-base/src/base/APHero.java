package base;

public class APHero implements Mortal{
    @Override
    public void die() {
        System.out.println("我是AP,我被盖伦杀了");
    }
}
