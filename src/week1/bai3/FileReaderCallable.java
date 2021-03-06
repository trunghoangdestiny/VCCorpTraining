package week1.bai3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileReaderCallable implements Callable<LinkedHashMap<String, Integer>> {
    //doc file va co return nen phai dung Callable
    private String filename;

    public FileReaderCallable(String filename) {
        this.filename = filename;
    }

    @Override
    public LinkedHashMap<String, Integer> call() throws Exception {
        LinkedHashMap<String, Integer> countWords = new LinkedHashMap<>();
        Stream<String> stream = Files.lines(Paths.get(this.filename)); //create a stream to get data in each line
        stream.forEach(line -> {
            line = line.replaceAll("[^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ" +
                    "ưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\w]", " ");
            line = removeAccent(line);
            String dataOfEachLine[] = line.split("\\s+");
            for (String data : dataOfEachLine) {
                if (!data.equals("")) {
                    if (countWords.containsKey(data)) {
                        countWords.put(data, countWords.get(data) + 1);
                    } else {
                        countWords.put(data, 1);
                    }
                }
            }
        });
        return countWords;
    }

    private static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
