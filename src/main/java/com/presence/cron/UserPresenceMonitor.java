package com.presence.cron;

import com.presence.model.AccountConfig;
import com.presence.model.discord.DiscordMessage;
import com.presence.model.roblox.presence.RobloxPresenceStatus;
import com.presence.service.DiscordMessageService;
import com.presence.service.PresenceCheckService;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserPresenceMonitor {
  private final AccountConfig accountConfig;
  private final DiscordMessageService messageService;
  private final PresenceCheckService presenceCheckService;

  // Every 5 minutes
  @Scheduled(cron = "*/5 * * * *")
  public void monitorRobloxPresenceStatus() {
    Map<RobloxPresenceStatus, Set<String>> userPresences =
        presenceCheckService.getGroupedUserPresences(accountConfig.accountsToMonitor());

    messageService.sendMessage(new DiscordMessage(accountConfig.discordUserId(), userPresences));
  }
}
