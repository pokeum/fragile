package non_official.pokeum.fragile.etc.reflection.java.person;

import org.junit.Test;

import java.lang.reflect.Modifier;

public class PersonTest {

    @Test
    public void main() {
    }

    /*
    private String getClassDescription(Class<?> clazz) {
        StringBuilder classDescription = new StringBuilder();
        // Prepare the modifiers and construct keyword (class, enum, interface etc.)
        int modifierBits = 0;
        String keyword = " ";
        // Add keyword @interface, interface or enum or class
        if (clazz.isPrimitive()) {
            // We do not need to add anything
        } else if (clazz.isInterface()) {
            modifierBits = clazz.getModifiers() & Modifier.interfaceModifiers();
            // An annotation is an interface
            if (clazz.isAnnotation()) {
                keyword = "@interface";
            } else {
                keyword = "interface";
            }
        } else if (clazz.isEnum()) {
            modifierBits = clazz.getModifiers() & Modifier.classModifiers();
            keyword = "enum";
        } else {
            modifierBits = clazz.getModifiers() & Modifier.classModifiers();
            keyword = "class";
        }
        // Convert modifiers to their string representation
        String modifiers = Modifier.toString(modifierBits);
    }
    */
}
