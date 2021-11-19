package com.georgegebbett.days.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {

    public static void main() {
        File inputFile = new File("/Users/george/IdeaProjects/adventofcode2015/src/com/georgegebbett/days/day2/input.txt");

        if (inputFile.exists()) {
            try {
                Scanner inputScanner = new Scanner(inputFile);
                Scanner optionScanner = new Scanner(System.in);
                System.out.println("Part one or two?");
                String selectedPart = optionScanner.nextLine();
                String inputLine;
                switch (selectedPart) {
                    case "1" -> {
                        PartOneKeypadTracker tracker = new PartOneKeypadTracker();
                        while (inputScanner.hasNextLine()) {
                            inputLine = inputScanner.nextLine();
                            char[] charArr = inputLine.toCharArray();
                            for (char ch : charArr) {
                                tracker.moveToNextKey(ch);
                            }
                            tracker.addKeyToCombination();
                        }
                        System.out.printf("The combination is %s%n", tracker.getCombination());
                    }
                    case "2" -> {
                        PartTwoKeypadTracker tracker2 = new PartTwoKeypadTracker();
                        while (inputScanner.hasNextLine()) {
                            inputLine = inputScanner.nextLine();
                            char[] charArr = inputLine.toCharArray();
                            for (char ch : charArr) {
                                tracker2.moveToNextKey(ch);
                            }
                            tracker2.addKeyToCombination();
                        }
                        System.out.printf("The combination is %s%n", tracker2.getCombination());
                    }
                    default -> {
                        throw new IllegalStateException(selectedPart + " is not a valid part");
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("File not found");
        }
    }


}

class PartOneKeypadTracker {

    private int[] currentKeyLocation;
    private int[][] keyLayout;
    private ArrayList<Integer> combination;

    PartOneKeypadTracker() {
        this.currentKeyLocation = new int[] {1, 1};
        this.keyLayout = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        this.combination = new ArrayList<>(4);
    }

    public void addKeyToCombination() {
        combination.add(keyLayout[currentKeyLocation[0]][currentKeyLocation[1]]);
    }

    public String getCombination() {
        return combination.toString();
    }

    public void moveToNextKey(char direction) {
        switch (direction) {
            case 'U':
                if (currentKeyLocation[0] == 0) return;
                currentKeyLocation[0]--;
                break;
            case 'D':
                if (currentKeyLocation[0] == 2) return;
                currentKeyLocation[0]++;
                break;
            case 'L':
                if (currentKeyLocation[1] == 0) return;
                currentKeyLocation[1]--;
                break;
            case 'R':
                if (currentKeyLocation[1] == 2) return;
                currentKeyLocation[1]++;
                break;
        }
    }
}

class PartTwoKeypadTracker {

    private int[] currentKeyLocation;
    private char[][] keyLayout;
    private ArrayList<Character> combination;

    PartTwoKeypadTracker() {
        this.currentKeyLocation = new int[] {2, 0};
        this.keyLayout = new char[][]{{'X', 'X', '1', 'X', 'X'}, {'X', '2', '3', '4', 'X'}, {'5', '6', '7', '8', '9'}, {'X', 'A', 'B', 'C', 'X'}, {'X', 'X', 'D', 'X', 'X'}};
        this.combination = new ArrayList<>(4);
    }

    public void addKeyToCombination() {
        combination.add(keyLayout[currentKeyLocation[0]][currentKeyLocation[1]]);
    }

    public char getCurrentKey() {
        return keyLayout[currentKeyLocation[0]][currentKeyLocation[1]];
    }

    private boolean invalidKeyAtLocation(int x, int y) {
        return keyLayout[x][y] == 'X';
    }

    public String getCombination() {
        return combination.toString();
    }

    public void moveToNextKey(char direction) {
        switch (direction) {
            case 'U' -> {
                if (currentKeyLocation[0] == 0) return;
                if (invalidKeyAtLocation(currentKeyLocation[0] - 1, currentKeyLocation[1])) return;
                currentKeyLocation[0]--;
            }
            case 'D' -> {
                if (currentKeyLocation[0] == 4) return;
                if (invalidKeyAtLocation(currentKeyLocation[0] + 1, currentKeyLocation[1])) return;
                currentKeyLocation[0]++;
            }
            case 'L' -> {
                if (currentKeyLocation[1] == 0) return;
                if (invalidKeyAtLocation(currentKeyLocation[0], currentKeyLocation[1] - 1)) return;
                currentKeyLocation[1]--;
            }
            case 'R' -> {
                if (currentKeyLocation[1] == 4) return;
                if (invalidKeyAtLocation(currentKeyLocation[0], currentKeyLocation[1] + 1)) return;
                currentKeyLocation[1]++;
            }
        }

    }
}