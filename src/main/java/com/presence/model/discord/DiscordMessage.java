package com.presence.model.discord;

import com.presence.model.roblox.presence.RobloxPresenceStatus;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiscordMessage {
  private final long discordUserId;
  private final Map<RobloxPresenceStatus, Set<String>> userPresences;

  @Override
  public String toString() {
    // Create a StringBuilder to build the message
    StringBuilder message = new StringBuilder();

    if (userPresences.containsKey(RobloxPresenceStatus.OFFLINE)) {
      // Mention the user only if one or more account is offline
      message
          .append("<@")
          .append(discordUserId)
          .append("> Some of your Roblox accounts are offline! \n\n");
    }

    userPresences.forEach(
        (status, accounts) -> {
          message.append(status.getDisplayName()).append(":\n").append(String.join(", ", accounts));
          message.append('\n');
        });

    // Return the final message string
    return message.toString();
  }
}
