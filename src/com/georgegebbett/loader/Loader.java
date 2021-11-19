package com.georgegebbett.loader;

import com.georgegebbett.days.day1.Day1;
import com.georgegebbett.days.day2.Day2;
import com.georgegebbett.days.day3.Day3;
import com.georgegebbett.days.day4.Day4;
import com.georgegebbett.days.day5.Day5;

import java.util.Scanner;

public class Loader {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Which day would you like to see?");
        int day = Integer.parseInt(inputScanner.nextLine());

        switch (day) {
            case 1:
                Day1.main();
                break;
            case 2:
                Day2.main();
                break;
            case 3:
                Day3.main();
                break;
            case 4:
                Day4.main();
            case 5:
                Day5.main();
            default:
                throw new IllegalStateException(day + " is not a valid day");
        }
    }

}
