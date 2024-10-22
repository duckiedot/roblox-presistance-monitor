package com.presence.model.roblox.presence;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPresence {
  private RobloxPresenceStatus userPresenceType;
  private String lastLocation;
  private long userId;

  private Date lastOnline;
}
