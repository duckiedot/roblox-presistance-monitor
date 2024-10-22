package com.presence.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RobloxPresenceStatus {
  OFFLINE((short) 0),
  ONLINE((short) 1),
  IN_GAME((short) 2),
  IN_STUDIO((short) 3);

  private final short apiCode;

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
   * @param apiCode the API code returned from Roblox for which to retrieve the corresponding {@link RobloxPresenceStatus}
   * @return the {@link RobloxPresenceStatus} associated with the given API code
   * @throws IllegalArgumentException if the API code does not map to any known status
   */
  public RobloxPresenceStatus getByApiCode(final short apiCode) {
    return Optional.ofNullable(CODE_TO_STATUS_MAP.get(apiCode))
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    "RobloxPresenceStatus received invalid apiCode: " + apiCode));
  }
}
