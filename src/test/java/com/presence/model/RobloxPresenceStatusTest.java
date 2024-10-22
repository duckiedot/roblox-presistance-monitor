package com.presence.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RobloxPresenceStatusTest {

    @Test
    @DisplayName("Presence status returns correct statues")
    void testGetByApiCode_ValidApiCode() {
        assertEquals(RobloxPresenceStatus.OFFLINE, RobloxPresenceStatus.OFFLINE.getByApiCode((short) 0));
        assertEquals(RobloxPresenceStatus.ONLINE, RobloxPresenceStatus.ONLINE.getByApiCode((short) 1));
        assertEquals(RobloxPresenceStatus.IN_GAME, RobloxPresenceStatus.IN_GAME.getByApiCode((short) 2));
        assertEquals(RobloxPresenceStatus.IN_STUDIO, RobloxPresenceStatus.IN_STUDIO.getByApiCode((short) 3));
    }

    @Test
    @DisplayName("Presence status correctly handles invalid inputs")
    void testGetByApiCode_InvalidApiCode() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            RobloxPresenceStatus.OFFLINE.getByApiCode((short) 99);
        });

        assertEquals("RobloxPresenceStatus received invalid apiCode: 99", thrown.getMessage());
    }
}