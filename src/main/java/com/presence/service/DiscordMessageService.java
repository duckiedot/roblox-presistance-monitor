package com.presence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.presence.model.AccountConfig;
import com.presence.model.discord.DiscordMessage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DiscordMessageService {
  @Value("${discord.api.avatar}")
  private String discordAvatar;

  @Value("${discord.api.username}")
  private String discordUsername;

  private final AccountConfig accountConfig;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public void sendMessage(final DiscordMessage message) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

    String jsonPayload = this.getApiMessage(message.toString());

    HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(accountConfig.discordHook(), HttpMethod.POST, entity, String.class);
    System.out.println("Response: " + response.getStatusCode());
  }

  private String getApiMessage(final String message) {
    Map<String, Object> messageData = new HashMap<>();
    messageData.put("content", message);
    messageData.put("username", this.discordUsername);
    messageData.put("avatar_url", this.discordAvatar);
    messageData.put("embeds", this.getAuthorEmbed());

    try {
      return objectMapper.writeValueAsString(messageData);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Map<String, Object>> getAuthorEmbed() {
    List<Map<String, Object>> embeds = new LinkedList<>();

    // Create the embed map
    Map<String, Object> embed = new HashMap<>();
    Map<String, String> author = new HashMap<>();

    author.put("name", "Made by [0FCL] \uD83C\uDDF5\uD83C\uDDF1 mati03pl");
    author.put("url", "https://www.roblox.com/users/11777112/profile");
    author.put(
        "icon_url",
        "https://tr.rbxcdn.com/30DAY-AvatarHeadshot-C0620BDEE311B38523B84ABF1CCFAEF5-Png/150/150/AvatarHeadshot/Webp/noFilter");

    embed.put("author", author);
    embed.put(
        "description",
        "Links: \n"
            + "https://www.linkedin.com/in/mateusz-malinkiewicz/ \n "
            + "https://github.com/duckiedot");

    // Add the embed to the embeds list
    embeds.add(embed);

    return embeds;
  }
}
