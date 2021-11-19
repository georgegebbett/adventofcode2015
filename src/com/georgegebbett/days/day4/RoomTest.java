package com.georgegebbett.days.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void checkRoomValid() {
        Room validRoom = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        Room invalidRoom = new Room("totally-real-room-200[decoy]");

        assertTrue(validRoom.isValid());
        assertFalse(invalidRoom.isValid());
    }

    @Test
    void decodeName() {
        Room testRoom = new Room("qzmt-zixmtkozy-ivhz-343[zimth]");
        assertEquals(testRoom.getRoomName(), "qzmt-zixmtkozy-ivhz");
        assertEquals(testRoom.decodeName(), "very encrypted name");
    }

    @Test
    void badRoomStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Room("dsfesdfgaerg"));
    }
}