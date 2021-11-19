package com.georgegebbett.days.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main() {
        File inputFile = new File("/Users/george/IdeaProjects/adventofcode2015/src/com/georgegebbett/days/day3/input.txt");
        if (inputFile.exists()) {
            try {
                int validTriangles = 0;
                Scanner inputReader = new Scanner(inputFile);
                Scanner optionReader = new Scanner(System.in);
                System.out.println("Part 1 or 2?");
                String partChoice = optionReader.nextLine();
                String inputLine;
                Pattern sidePattern = Pattern.compile("\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
                switch (partChoice) {
                    case "1":
                        while (inputReader.hasNextLine()) {
                            inputLine = inputReader.nextLine();
                            Matcher sideMatcher = sidePattern.matcher(inputLine);
                            while (!sideMatcher.hitEnd()) {
                                sideMatcher.find();
                                int side1 = Integer.parseInt(sideMatcher.group(1));
                                int side2 = Integer.parseInt(sideMatcher.group(2));
                                int side3 = Integer.parseInt(sideMatcher.group(3));

                                Triangle testTriangle1 = new Triangle(side1, side2, side3);

                                if (testTriangle1.isValid()) validTriangles++;
                            }
                        }
                        break;
                    case "2":
                        while (inputReader.hasNextLine()) {
                            inputLine = inputReader.nextLine();
                            Matcher sideMatcher2 = sidePattern.matcher(inputLine);

                            int side1 = 0, side2 = 0, side3 = 0, side4 = 0, side5 = 0, side6 = 0, side7 = 0, side8 = 0, side9 = 0;


                            while (!sideMatcher2.hitEnd()) {
                                sideMatcher2.find();
                                side1 = Integer.parseInt(sideMatcher2.group(1));
                                side4 = Integer.parseInt(sideMatcher2.group(2));
                                side7 = Integer.parseInt(sideMatcher2.group(3));
                            }

                            inputLine = inputReader.nextLine();
                            sideMatcher2 = sidePattern.matcher(inputLine);

                            while (!sideMatcher2.hitEnd()) {
                                sideMatcher2.find();
                                side2 = Integer.parseInt(sideMatcher2.group(1));
                                side5 = Integer.parseInt(sideMatcher2.group(2));
                                side8 = Integer.parseInt(sideMatcher2.group(3));
                            }

                            inputLine = inputReader.nextLine();
                            sideMatcher2 = sidePattern.matcher(inputLine);

                            while (!sideMatcher2.hitEnd()) {

                                sideMatcher2.find();
                                side3 = Integer.parseInt(sideMatcher2.group(1));
                                side6 = Integer.parseInt(sideMatcher2.group(2));
                                side9 = Integer.parseInt(sideMatcher2.group(3));
                            }


                            Triangle testTriangle1 = new Triangle(side1, side2, side3);
                            Triangle testTriangle2 = new Triangle(side4, side5, side6);
                            Triangle testTriangle3 = new Triangle(side7, side8, side9);

                            if (testTriangle1.isValid()) validTriangles++;
                            if (testTriangle2.isValid()) validTriangles++;
                            if (testTriangle3.isValid()) validTriangles++;
                            }
                        
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + partChoice);
                }
                System.out.printf("There are %d valid triangles", validTriangles);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }

        } else {
            System.out.println("File not found");
        }
    }

}

class Triangle {

    private final int side1;
    private final int side2;
    private final int side3;

    Triangle(int side1, int side2, int side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public boolean isValid() {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }
}
