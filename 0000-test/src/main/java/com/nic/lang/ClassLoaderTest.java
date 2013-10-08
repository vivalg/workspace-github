package com.nic.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class ClassLoaderTest extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> loadClass = new CALoader().loadClass(null);
        System.out.println(loadClass);
    }
}

class CALoader extends ClassLoader {

    static {
        System.out.println(CALoader.class.getName() + " loaded. by " + CALoader.class.getClassLoader());
    }

    @Override
    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
        URL resource = CALoader.class.getClassLoader().getResource("com/nic/lang/CA.class");
        String file = resource.getFile();
        Class<?> c = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            System.out.println("bytes: " + bytes);
            c = defineClass("com.nic.lang.CA", bytes, 0, bytes.length);
            resolveClass(c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

}

class CA {
    static {
        System.out.println(CA.class.getName() + " loaded. by " + CA.class.getClassLoader());
    }
}
