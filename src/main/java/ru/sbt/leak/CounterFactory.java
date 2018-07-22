package ru.sbt.leak;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Home on 29.06.2018.
 */
public class CounterFactory {
    public static ICounter newInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        URLClassLoader tmp = new URLClassLoader( new URL[]{ getClassPath() } ) {
            public Class loadClass( String name ) throws ClassNotFoundException {
                if ( "ru.sbt.leak.Counter".equals( name ) )
                    return findClass( name );
                  return super.loadClass( name );
            }
        };

        return ( ICounter ) tmp.loadClass( "ru.sbt.leak.Counter" ).newInstance();
    }

    private static URL getClassPath() throws MalformedURLException {
        return new URL( "file:///H:/I_-_social/работа/java/JavaProgram/1_5-Загрузчики%20классов/classloaders/target/classes/" );
    }
}
