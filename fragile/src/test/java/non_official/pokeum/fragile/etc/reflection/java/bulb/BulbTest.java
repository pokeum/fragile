package non_official.pokeum.fragile.etc.reflection.java.bulb;

import org.junit.Test;

public class BulbTest {

    @Test
    public void main() {
        //classLiteral();
        //forNameVersion1();
        forNameVersion2();
    }

    private void classLiteral() {
        // Will load the class, but won't initialize it.
        Class<Bulb> clazz = Bulb.class;
    }

    private void forNameVersion1() {
        try {
            String className = "non_official.pokeum.fragile.etc.reflection.java.bulb.Bulb";
            // Will load and initialize the class
            Class clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void forNameVersion2() {
        try {
            String className = "non_official.pokeum.fragile.etc.reflection.java.bulb.Bulb";
            boolean initialize = false;
            // Get the classloader for the current class
            ClassLoader classLoader = BulbTest.class.getClassLoader();
            // Will load, but not initialize the class,
            // because we have set the initialize variable to false
            Class clazz = Class.forName(className, initialize, classLoader);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
