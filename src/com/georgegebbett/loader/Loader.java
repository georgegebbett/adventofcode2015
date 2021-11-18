package com.georgegebbett.loader;

import com.georgegebbett.days.day1.Day1;
import com.georgegebbett.days.day2.Day2;

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
        }
    }

}
