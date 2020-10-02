package week1.bai2;

import java.io.*;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

public class FileReaderBuffer {
    public static void main(String[] args) { //
        try (
                FileReader fileReader = new FileReader("/home/hoangquoctrung/IdeaProjects/File/input.txt");
                FileWriter fileWriter = new FileWriter("/home/hoangquoctrung/IdeaProjects/File/output.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            String str;
            List<String> words = new ArrayList<>();
            while ((str = bufferedReader.readLine()) != null) {
                str = str.replaceAll("[^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ" +
                        "ưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\w]", " ");
                str = removeAccent(str);
                Arrays.stream(str.split("\\s+")).filter(s -> !"".equals(s)).forEach(s -> {
                    s = s.toLowerCase();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
