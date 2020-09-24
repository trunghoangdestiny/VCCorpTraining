package week1.bai3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class FileReaderCallableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(6); //create a thread pool with maximum 6 threads
        List<FileReaderCallable> fileReaderCallables = new ArrayList<>(); //create a list of file in input folder
        List<Future<LinkedHashMap<String, Integer>>> futureList = new ArrayList<>(); //create a list of future to get result
        LinkedHashMap<String, Integer> finalResult = new LinkedHashMap<>();

        //create a list of files that in input folder
        String folderPath = "/home/hoangquoctrung/IdeaProjects/File";
        File files = new File(folderPath);
        File listOfFiles[] = {};
        if (!files.isFile()) {
            listOfFiles = files.listFiles();
        }
        for (int i = 0; i < listOfFiles.length; i++) {
            fileReaderCallables.add(new FileReaderCallable(listOfFiles[i].getAbsolutePath()));
        }

        //submitting tasks with callable
        for (int i = 0; i < fileReaderCallables.size(); i++) {
            futureList.add(executorService.submit(fileReaderCallables.get(i)));
        }

        //processing future result
        for (int i = 0; i < futureList.size(); i++) {
            LinkedHashMap<String, Integer> temp = futureList.get(i).get();
            temp.forEach((key, value) -> {
                if (finalResult.containsKey(key)) {
                    finalResult.put(key, finalResult.get(key) + value);
                } else {
                    finalResult.put(key, value);
                }
            });
        }

        FileWriter fileOutput = new FileWriter("/home/hoangquoctrung/IdeaProjects/File/output.txt");
        FileWriter fileMostOccurrence = new FileWriter("/home/hoangquoctrung/IdeaProjects/File/the_most_occurrence.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
        BufferedWriter bufferedWriter1 = new BufferedWriter(fileMostOccurrence);

        //render data to the output file
        finalResult.forEach((key, value) -> {
            try {
                bufferedWriter.write(key + ": " + value);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //render data to the the_most_occurrence file
        Stream<Map.Entry<String, Integer>> sortedResult = finalResult
                .entrySet()
                .stream()
                .sorted((e1, e2)->Integer.compare(e2.getValue(), e1.getValue()))
                .limit(10);
        bufferedWriter1.write("Top 10 the most occurrence words:\n");
        sortedResult.forEach(element->{
            try {
                bufferedWriter1.write(element.getKey() + ": " + element.getValue());
                bufferedWriter1.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bufferedWriter.close();
        bufferedWriter1.close();
        fileOutput.close();
        fileMostOccurrence.close();
        executorService.shutdown();
    }
}
