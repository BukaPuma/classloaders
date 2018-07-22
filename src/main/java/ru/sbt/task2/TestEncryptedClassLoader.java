package ru.sbt.task2;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import  java.lang.ClassLoader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class TestEncryptedClassLoader {
    public static void main( String[] args ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        ClassLoader cl = new ClassLoader( ) {
            @Override
            public Class< ? > loadClass( String name ) throws ClassNotFoundException {
                return super.loadClass( name );
            }
        };
        EncryptedClassLoader tmp = new EncryptedClassLoader( "13", new File( "src/resources/Encrypted/" ), cl);
        Class clazz = tmp.loadClass("TestClassFrom" );
        Object object= clazz.newInstance();
        System.out.println(object);

    }

}
