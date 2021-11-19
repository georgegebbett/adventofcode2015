package com.georgegebbett.days.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    public static void main() {
        File inputFile = new File("/Users/george/IdeaProjects/adventofcode2015/src/com/georgegebbett/days/day4/input.txt");

        if (inputFile.exists()) {
            try {
                Scanner inputReader = new Scanner(inputFile);


                Scanner optionScanner = new Scanner(System.in);
                System.out.println("Part one or two?");
                String selectedPart = optionScanner.nextLine();

                switch (selectedPart) {
                    case "1":
                        int validRoomSectors = 0;
                        while (inputReader.hasNextLine()) {
                            Room checkRoom = new Room(inputReader.nextLine());
                            if (checkRoom.isValid()) validRoomSectors += checkRoom.getSectorId();
                        }
                        System.out.printf("The sum of the sectors of the valid rooms is %d", validRoomSectors);

                        break;
                    case "2":
                        while (inputReader.hasNextLine()) {
                            Room checkRoom2 = new Room(inputReader.nextLine());
                            if (checkRoom2.isValid()) {
                                if (Objects.equals(checkRoom2.decodeName(), "northpole object storage")) {
                                    System.out.printf("North Pole objects are stored in sector %d", checkRoom2.getSectorId());
                                }
                            }
                        }
                        break;
                    default:
                        throw new IllegalStateException(selectedPart + " is not a valid part");
                }


            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("File not found");
        }
    }


}

class Room {
    private final String roomName;
    private final int sectorId;
    private final String checksum;

    private char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f' ,'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    Room(String roomString) throws IllegalArgumentException {
        Pattern roomPattern = Pattern.compile("^([a-z-]+)-(\\d+)\\[([a-z]+)\\]$");
        Matcher roomMatcher = roomPattern.matcher(roomString);
        if (roomMatcher.find()) {
            this.roomName = roomMatcher.group(1);
            this.sectorId = Integer.parseInt(roomMatcher.group(2));
            this.checksum = roomMatcher.group(3);
        } else {
            throw new IllegalArgumentException("Badly formed room string");
        }

    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isValid() {
        String filteredName = roomName.replaceAll("-", "");
        char[] charArr = filteredName.toCharArray();
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char ch: charArr) {
            charCount.putIfAbsent(ch, 0);
            charCount.computeIfPresent(ch, (key, val) -> val + 1);
        }

        List<Map.Entry<Character, Integer>> list = new LinkedList<>(charCount.entrySet());

        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o2.getValue().compareTo(o1.getValue()));

        String calculatedChecksum = list.stream().limit(5).map(item -> item.getKey().toString()).collect(Collectors.joining());

        return calculatedChecksum.equals(checksum);

    }

    public String decodeName() {

        List<Character> charList = roomName.chars()
                        .mapToObj(e ->(char)e).collect(Collectors.toList());

        int offset = sectorId % 26;

        List <Character> mapList = charList.stream().map(character -> {
            int charNo = Character.getNumericValue(character);

            if (charNo == -1) return ' ';

            int newVal = charNo + offset;

            if (newVal > 35) {
                newVal -= 26;
            }


            return alphabet[newVal-10];
        }).collect(Collectors.toList());


        return mapList.stream().map(Object::toString).collect(Collectors.joining());

    }

    public int getSectorId() {
        return sectorId;
    }
}
