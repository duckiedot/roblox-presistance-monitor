package com.presence.model.roblox.presence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RobloxPresenceStatus {
  OFFLINE((short) 0, "\uD83D\uDD34 Offline"),
  ONLINE((short) 1, "\uD83D\uDD35 Online"),
  IN_GAME((short) 2, "\uD83D\uDFE2 In-game"),
  IN_STUDIO((short) 3, "\uD83D\uDFE0 In-studio");

  private final short apiCode;

  @Getter private final String displayName;

  private static final Map<Short, RobloxPresenceStatus> CODE_TO_STATUS_MAP = new HashMap<>();

  // Static block to populate the map at class loading time
  static {
    for (RobloxPresenceStatus status : RobloxPresenceStatus.values()) {
      CODE_TO_STATUS_MAP.put(status.apiCode, status);
    }
  }

  /**
   * Retrieves the {@link RobloxPresenceStatus} associated with the specified API code.
   *
   * @param apiCode the API code returned from Roblox for which to retrieve the corresponding {@link
   *     RobloxPresenceStatus}
   * @return the {@link RobloxPresenceStatus} associated with the given API code
   * @throws IllegalArgumentException if the API code does not map to any known status
   */
  public static RobloxPresenceStatus getByApiCode(final short apiCode) {
    return Optional.ofNullable(CODE_TO_STATUS_MAP.get(apiCode))
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    "RobloxPresenceStatus received invalid apiCode: " + apiCode));
  }
}
