package com.georgegebbett.days.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    public static void main() throws FileNotFoundException {
        File inputFile = new File("/Users/george/IdeaProjects/adventofcode2015/src/com/georgegebbett/days/day6/input.txt");
        if (inputFile.exists()) {
            Scanner inputReader = new Scanner(inputFile);
            Scanner choiceReader = new Scanner(System.in);

            System.out.println("Part one or two?");
            String partChoice = choiceReader.nextLine();

                    ArrayList<HashMap<Character, Integer>> mapArr = new ArrayList<HashMap<Character, Integer>>();
                    while (inputReader.hasNextLine()) {
                        String currentLine = inputReader.nextLine();

                        char[] charArr = currentLine.toCharArray();



                        if (mapArr.isEmpty()) {
                            for (char ch: charArr) {
                                HashMap<Character, Integer> emptyHmap = new HashMap<>();
                                emptyHmap.put(ch, 1);
                                mapArr.add(emptyHmap);
                            }
                        } else {
                            int currentChar = 0;
                            for (char ch: charArr) {
                                HashMap<Character, Integer> gotMap = mapArr.get(currentChar);
                                gotMap.putIfAbsent(ch, 0);
                                gotMap.computeIfPresent(ch, (key, val) -> val + 1);
                                currentChar++;
                            }
                        }

                    }

                    StringBuilder answer = new StringBuilder();

                    for (HashMap<Character, Integer> map: mapArr) {

                        List<Map.Entry<Character, Integer>> list = new LinkedList<>(map.entrySet());

                        switch (partChoice) {
                            case "1":
                                list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                                break;
                            case "2" :
                                list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
                                break;
                            default:
                                throw new IllegalStateException(partChoice + " is not a valid part");

                        }


                        String mostFrequentChar = list.stream().limit(1).map(item -> item.getKey().toString()).collect(Collectors.joining());

                        answer.append(mostFrequentChar);

                    }


                    System.out.println(answer);


        }
    }

}
