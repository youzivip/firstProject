package jvmTest;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myclassLocader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName =  name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream in = getClass().getResourceAsStream(fileName);
                    if(in == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj =  myclassLocader.loadClass("jvmTest.DeadLoopClass").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof DeadLoopClass);
     }
}
