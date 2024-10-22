package com.presence.model;

import static org.junit.jupiter.api.Assertions.*;

import com.presence.model.roblox.presence.RobloxPresenceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RobloxPresenceStatusTest {

  @Test
  @DisplayName("Presence status returns correct statues")
  void testGetByApiCode_ValidApiCode() {
    assertEquals(RobloxPresenceStatus.OFFLINE, RobloxPresenceStatus.getByApiCode((short) 0));
    assertEquals(RobloxPresenceStatus.ONLINE, RobloxPresenceStatus.getByApiCode((short) 1));
    assertEquals(RobloxPresenceStatus.IN_GAME, RobloxPresenceStatus.getByApiCode((short) 2));
    assertEquals(RobloxPresenceStatus.IN_STUDIO, RobloxPresenceStatus.getByApiCode((short) 3));
  }

  @Test
  @DisplayName("Presence status correctly handles invalid inputs")
  void testGetByApiCode_InvalidApiCode() {
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              RobloxPresenceStatus.getByApiCode((short) 99);
            });

    assertEquals("RobloxPresenceStatus received invalid apiCode: 99", thrown.getMessage());
  }
}
