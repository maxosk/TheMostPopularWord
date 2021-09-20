package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

    // main() method - entry point to start execution
    public static void main(String[] args) throws IOException {
        String fileName= "./BRN2.txt";
       // String oldLine = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File("./BRN2.txt");
        BufferedReader fin = new BufferedReader(new FileReader(f));
        String name;
        String readLine;
        String outputLine=null;
        int ik=0;
        while ((readLine = fin.readLine()) != null) outputLine=readLine;
        System.out.println(outputLine);


        // тест
        String testStr = "Science blank Maths blank blank"
                + " Physics blank Maths";

        // вызов метода с сортировкой
        countAndPrintRepeatedWordOccurences(outputLine);
    }

    /**
     * this method is used
     * to count number repeated word occurrences
     * @param //fileName
     */
    public static void countAndPrintRepeatedWordOccurences(
            String strContent) {

        // Step 1: создаем Map String-Integer
        Map<String, Integer> mapOfRepeatedWord =
                new HashMap<String, Integer>();

        // Step 2: разделяем строку на слова испльзуя split
        String[] words = strContent.split(" ");

        // Step 3: Проходим по массиву String
        for (String word : words) {

            // Step 4: конвертируем в toLowerCase,

            String tempUCword = word.toLowerCase();

            // Step 5: проверка, содержит ли Map определенное слово
            if (mapOfRepeatedWord.containsKey(tempUCword)) {

                // Step 6: Если содеожит,увеличчиваем на один
                mapOfRepeatedWord.put(tempUCword,
                        mapOfRepeatedWord.get(tempUCword) + 1);
            } else {

                // Step 7: таким образом, создаем новую запись
                mapOfRepeatedWord.put(tempUCword, 1);
            }
        }

        System.out.println("Before sorting : \n");
        System.out.println("Words" + "\t\t" + "Count");
        System.out.println("======" + "\t\t" + "=====");

        // Step 8: напечатаем слово вместе с его количеством
        for (Map.Entry<String, Integer> entry :
                mapOfRepeatedWord.entrySet()) {
            System.out.println(entry.getKey()
                    + "\t\t" + entry.getValue());
        }

        // Step 9: Логика сортировки путем вызова sortByCountValue()
        Map<String, Integer> wordLHMap = sortByCountValue(
                mapOfRepeatedWord);

        System.out.println("\n\nAfter sorting"
                + " in descending order of count : \n");
        System.out.println("Words" + "\t\t" + "Count");
        System.out.println("======" + "\t\t" + "=====");


        try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

            StringBuilder sb = new StringBuilder();


            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

            StringBuilder sb = new StringBuilder();
            // Step 10: Again print after sorting




            for (Map.Entry<String, Integer> entry :
                    wordLHMap.entrySet()) {
                sb.append(entry.getKey());
                sb.append(';');
                sb.append(entry.getValue());
                sb.append('\n');
                System.out.println(entry.getKey()
                        + "\t\t" + entry.getValue());



                //System.out.println("done!");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

        /**
     * this method sort acc. to count value
     * @param mapOfRepeatedWord
     * @return
     */
    public static Map<String, Integer> sortByCountValue(
            Map<String, Integer> mapOfRepeatedWord) {

        // получить набор записей из объекта HashMap
        Set<Map.Entry<String, Integer>> setOfWordEntries =
                mapOfRepeatedWord.entrySet();

        // конвертация HashMap to List
        List<Map.Entry<String, Integer>> listOfwordEntry =
                new ArrayList<Map.Entry<String, Integer>>(
                        setOfWordEntries);

        // сортировка списка записей с помощью Collections.sort(ls, cmptr);
        Collections.sort(listOfwordEntry,
                new Comparator<Map.Entry<String, Integer>>() {

                    @Override
                    public int compare(Entry<String, Integer> es1,
                                       Entry<String, Integer> es2) {
                        return es2.getValue().compareTo(es1.getValue());
                    }
                });

        // сохраниt в LinkedHashMap для сохранения вставки
        Map<String, Integer> wordLHMap =
                new LinkedHashMap<String, Integer>();

        // повторение списка и сохранение в LinkedHahsMap
        for(Map.Entry<String, Integer> map : listOfwordEntry){
            wordLHMap.put(map.getKey(), map.getValue());
        }

        return wordLHMap;
    }
}