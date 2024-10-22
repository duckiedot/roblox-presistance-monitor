package com.presence.model.discord;

import static org.junit.jupiter.api.Assertions.*;

import com.presence.model.roblox.presence.RobloxPresenceStatus;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DiscordMessageTest {

  @Test
  void testToString_WhenNoOfflineAccounts() {
    Map<RobloxPresenceStatus, Set<String>> userPresences = new HashMap<>();
    Set<String> onlineAccounts = new HashSet<>();
    onlineAccounts.add("Player1");
    onlineAccounts.add("Player2");
    userPresences.put(RobloxPresenceStatus.ONLINE, onlineAccounts);

    DiscordMessage discordMessage = new DiscordMessage(123456789, userPresences);

    String message = discordMessage.toString();

    assertFalse(message.contains("Some of your Roblox accounts are offline!"));
  }

  @Test
  void testToString_WhenOfflineAccountsPresent() {
    Map<RobloxPresenceStatus, Set<String>> userPresences = new HashMap<>();
    Set<String> offlineAccounts = new HashSet<>();
    offlineAccounts.add("Player3");

    Set<String> onlineAccounts = new HashSet<>();
    onlineAccounts.add("Player1");

    userPresences.put(RobloxPresenceStatus.OFFLINE, offlineAccounts);
    userPresences.put(RobloxPresenceStatus.ONLINE, onlineAccounts);

    DiscordMessage discordMessage = new DiscordMessage(123456789, userPresences);

    String message = discordMessage.toString();

    assertTrue(message.contains("Some of your Roblox accounts are offline!"));
    assertTrue(message.contains("Offline:\nPlayer3\n"));
    assertTrue(message.contains("Online:\nPlayer1\n"));
  }

  @Test
  void testToString_WhenMultipleStatusesPresent() {
    Map<RobloxPresenceStatus, Set<String>> userPresences = new HashMap<>();
    Set<String> offlineAccounts = new LinkedHashSet<>();
    offlineAccounts.add("Player3");

    Set<String> onlineAccounts = new LinkedHashSet<>();
    onlineAccounts.add("Player1");
    onlineAccounts.add("Player2");

    Set<String> inGameAccounts = new LinkedHashSet<>();
    inGameAccounts.add("Player4");

    userPresences.put(RobloxPresenceStatus.OFFLINE, offlineAccounts);
    userPresences.put(RobloxPresenceStatus.ONLINE, onlineAccounts);
    userPresences.put(RobloxPresenceStatus.IN_GAME, inGameAccounts);

    DiscordMessage discordMessage = new DiscordMessage(123456789, userPresences);

    String message = discordMessage.toString();

    assertTrue(message.contains("Some of your Roblox accounts are offline!"));
    assertTrue(message.contains("Offline:\nPlayer3"));
    assertTrue(message.contains("In-game:\nPlayer4"));
    assertTrue(message.contains("Online:\nPlayer1, Player2"));
  }
}
