package ru.sbt.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class FileEncryptor{
    public static void main (String [] args ) {


        try {
            String isPath = "src/resources/TestClassFrom.class"  ;
            FileInputStream is = new FileInputStream(isPath);
            FileOutputStream os = new FileOutputStream("src/resources/Encrypted/TestClassFrom.class" ); //( "../resources/TestClassFrom1.class"  );
            long length = new File(isPath).length();
            byte[] bytes = new byte[ ( int ) length ];
            byte[] bytesCoded = new byte[ ( int ) length ];
            int bytesCounter=-1;
            int key = 3;
            byte ch;
            while (is.available()>0)
            {
                bytesCounter++;
                byte data = ( byte ) is.read(  );
                bytes[bytesCounter] = ( byte ) (data+13);
                byte datawrite = bytes[bytesCounter] ;
                os.write( datawrite);
            }

            for (int i=0; i<bytes.length; i++) {
                System.out.println( bytes[i] );
            }

            is.close();
            os.close();


        } catch ( java.io.IOException e ) {
            System.out.println( "Такого файла нет" );
            e.printStackTrace( );

        }
    }


}
