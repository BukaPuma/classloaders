package ru.sbt.task2;

import java.io.*;
import java.net.URLClassLoader;

/**
 * Данный класслоадер умеет загружать классы из файлов дешифрую их. Ваша задача переопределить метод findClass().
 * В нем лоадер считывает зашифрованный массив байт, дешифрует его и превращает в класс (с помощью метода defineClass).
 * <p>
 * На вход класслодер принимает ключ шифрования, рутовую папку, в которой будет искать классы, родительский класслодер.
 * Логика шифрования/дешифрования с использованием ключа может быть любой на ваш вкус
 * (например, каждый считаный байт класса увеличить на определение число).
 */
public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader( String key, File dir, ClassLoader parent ) {
        super( parent );
        this.key = key;
        this.dir = dir;
    }


        @Override
        public Class<?> findClass(String className) throws ClassNotFoundException {
            try {
                byte b[] = fetchClassFromFS(dir + "\\" + className + ".class", key);
                return defineClass(className, b, 0, b.length);
            } catch (FileNotFoundException ex) {
               System.out.println( dir + "\\" + className + ".class"+ "не найден");
                return super.findClass(className);
            } catch (IOException ex) {
                return super.findClass(className);
            }

        }
    private byte[] fetchClassFromFS(String path, String key) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(new File(path));
        OutputStream os = new FileOutputStream(new File("TestClassEncoded.java"));


        long length = new File(path).length();
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File is too large "+path);
        }

        byte[] bytes = new byte[(int)length];
        int bytesCounter = -1;
        while (is.available()>0)
        {
            bytesCounter++;
            byte data = ( byte ) is.read(  );
            byte datawrite;
            try {
                int iKey = Integer.parseInt( key );
                bytes[ bytesCounter ] = ( byte ) ( data - iKey );
                os.write( bytes[ bytesCounter ] );
            }
            catch ( NumberFormatException e ) {
                System.out.println( "Введите целое число." );
            }

        }

        is.close();
        os.close();

        return bytes;

    }
}






