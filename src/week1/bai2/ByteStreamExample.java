package week1.bai2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamExample {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream("/home/hoangquoctrung/IdeaProjects/File/input.txt");
            outputStream = new FileOutputStream("/home/hoangquoctrung/IdeaProjects/File/output.txt");
            int c;
            while ((c = inputStream.read()) != -1) {
                System.out.println(c + " " + (char) c);
                outputStream.write(c);
            }
            System.out.println("Read and write finished.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
