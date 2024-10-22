package com.presence.model.roblox.presence;

import java.util.Set;
import lombok.Data;

@Data
public class RobloxPresenceApiResponse {
  private Set<UserPresence> userPresences;
}
