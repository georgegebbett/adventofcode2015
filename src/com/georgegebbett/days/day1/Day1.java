package com.georgegebbett.days.day1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {


    public static void main() {
        File inputFile = new File("/Users/george/IdeaProjects/adventofcode2015/src/com/georgegebbett/days/day1/input.txt");
        if (inputFile.exists()) {
            String inputString = "";
            LocationTracker tracker = new LocationTracker();
            try {
                Scanner inputReader = new Scanner(inputFile);
                while (inputReader.hasNextLine()) {
                    inputString = inputReader.nextLine();
                }
                Pattern instructionPattern = Pattern.compile("((L|R)(\\d+))");
                Matcher instructionMatcher = instructionPattern.matcher(inputString);
                Scanner optionScanner = new Scanner(System.in);
                System.out.println("Part 1 or 2?");
                int partChoice = Integer.parseInt(optionScanner.nextLine());
                while (!instructionMatcher.hitEnd()) {
                    instructionMatcher.find();
                    switch (partChoice) {
                        case 1:
                            tracker.doWalk(instructionMatcher.group(2).charAt(0), Integer.parseInt(instructionMatcher.group(3)));
                            break;
                        case 2:
                            if (tracker.doWalk2(instructionMatcher.group(2).charAt(0), Integer.parseInt(instructionMatcher.group(3)))) {
                                System.out.println(String.format("You have moved %d blocks horizontally and %d blocks vertically.", tracker.getHOffset(), tracker.getVOffset()));
                                System.out.println(String.format("This represents a total offset of %d blocks", tracker.getTotalOffset()));
                                return;
                            }
                            break;
                        default:
                            throw new IllegalStateException(partChoice + " is not a valid part");
                    }
                }
                System.out.println(String.format("You have moved %d blocks horizontally and %d blocks vertically.", tracker.getHOffset(), tracker.getVOffset()));
                System.out.println(String.format("This represents a total offset of %d blocks", tracker.getTotalOffset()));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            System.out.println("No file");
        }

    }


}

class LocationTracker {
    private char currentDirection;
    private int vOffset;
    private int hOffset;
    private ArrayList<Integer> currentLocation;
    private ArrayList<ArrayList> locationHistory;

    public LocationTracker() {
        this.currentDirection = 'N';
        this.vOffset = 0;
        this.hOffset = 0;
        this.currentLocation = new ArrayList<>(0);
        this.currentLocation.add(0);
        this.currentLocation.add(0);
        this.locationHistory = new ArrayList<>(0);
        this.locationHistory.add(new ArrayList(currentLocation));
    }

    public ArrayList<ArrayList> getLocationHistory() {
        return locationHistory;
    }

    public int getHOffset() {
        return hOffset;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void incHOffset(int hOffset) {
        this.hOffset = this.hOffset + hOffset;
        this.currentLocation.set(0, this.currentLocation.get(0) + hOffset);
    }

    public void incVOffset(int vOffset) {
        this.vOffset = this.vOffset + vOffset;
        this.currentLocation.set(1, this.currentLocation.get(1) + vOffset);
    }

    public int getVOffset() {
        return vOffset;
    }

    public int getTotalOffset() {
        return Math.abs(vOffset) + Math.abs(hOffset);
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    private void addCurrentLocationToHistory() {
        locationHistory.add(new ArrayList(currentLocation));
    }

    private boolean beenHereBefore() {
        return locationHistory.contains(currentLocation);
    }

    public void doWalk(char turnDirection, int steps) {
        switch (getCurrentDirection()) {
            case 'N':
                if (turnDirection == 'L') {
                    setCurrentDirection('W');
                } else {
                    setCurrentDirection('E');
                }
                break;
            case 'E':
                if (turnDirection == 'L') {
                    setCurrentDirection('N');
                } else {
                    setCurrentDirection('S');
                }
                break;
            case 'S':
                if (turnDirection == 'L') {
                    setCurrentDirection('E');
                } else {
                    setCurrentDirection('W');
                }
                break;
            case 'W':
                if (turnDirection == 'L') {
                    setCurrentDirection('S');
                } else {
                    setCurrentDirection('N');
                }
                break;
        }


        switch (getCurrentDirection()) {
            case 'N':
                incVOffset(steps);
                break;
            case 'S':
                incVOffset(-steps);
                break;
            case 'E':
                incHOffset(steps);
                break;
            case 'W':
                incHOffset(-steps);
                break;
        }
    }

    public boolean doWalk2(char turnDirection, int steps) {
        switch (getCurrentDirection()) {
            case 'N':
                if (turnDirection == 'L') {
                    setCurrentDirection('W');
                } else {
                    setCurrentDirection('E');
                }
                break;
            case 'E':
                if (turnDirection == 'L') {
                    setCurrentDirection('N');
                } else {
                    setCurrentDirection('S');
                }
                break;
            case 'S':
                if (turnDirection == 'L') {
                    setCurrentDirection('E');
                } else {
                    setCurrentDirection('W');
                }
                break;
            case 'W':
                if (turnDirection == 'L') {
                    setCurrentDirection('S');
                } else {
                    setCurrentDirection('N');
                }
                break;
        }

        int currentStep = 0;

        switch (getCurrentDirection()) {
            case 'N':
                while (currentStep < steps) {
                    incVOffset(1);
                    currentStep++;
                    if (beenHereBefore()) {
                        return true;
                    } else {
                        addCurrentLocationToHistory();
                    }
                }
                break;
            case 'S':
                while (currentStep < steps) {
                    incVOffset(-1);
                    currentStep++;
                    if (beenHereBefore()) {
                        return true;
                    } else {
                        addCurrentLocationToHistory();
                    }
                }
                break;
            case 'E':
                while (currentStep < steps) {
                    incHOffset(1);
                    currentStep++;
                    if (beenHereBefore()) {
                        return true;
                    } else {
                        addCurrentLocationToHistory();
                    }
                }
                break;
            case 'W':
                while (currentStep < steps) {
                    incHOffset(-1);
                    currentStep++;
                    if (beenHereBefore()) {
                        return true;
                    } else {
                        addCurrentLocationToHistory();
                    }
                }
                break;
        }
        return false;
    }
}