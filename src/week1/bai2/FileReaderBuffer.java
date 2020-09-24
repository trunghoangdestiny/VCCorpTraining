package week1.bai2;

import java.io.*;
import java.util.*;

public class FileReaderBuffer {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("/home/hoangquoctrung/IdeaProjects/File/input.txt");
            FileWriter fileWriter = new FileWriter("/home/hoangquoctrung/IdeaProjects/File/output.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str;
            List<String> words = new ArrayList<>();
            while ((str = bufferedReader.readLine()) != null) {
                str = str.replaceAll("[^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ" +
                        "ưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\w]", " ");
                Arrays.stream(str.split("\\s+")).forEach(s -> {
                    words.add(s);
                });
            }
            Map<String, Integer> countWords = new HashMap<>();
            words.forEach(word -> {
                if (countWords.containsKey(word)) {
                    countWords.put(word, countWords.get(word) + 1);
                } else {
                    countWords.put(word, 1);
                }
            });
            countWords.forEach((key, value) -> {
                try {
                    bufferedWriter.write(key + ": " + value);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedReader.close();
            fileReader.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
