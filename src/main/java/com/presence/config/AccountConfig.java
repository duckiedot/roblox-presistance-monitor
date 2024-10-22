package com.presence.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AccountConfig {
  /**
   * Loads the account configuration from a JSON file and maps it to the {@link AccountConfig}
   * class.
   *
   * <p>This method reads the {@code account-config.json} file located in the project's root
   * directory using the Jackson {@link ObjectMapper}, and converts the JSON content to an instance
   * of {@link com.presence.model.AccountConfig}. It throws a {@link RuntimeException} if the file
   * cannot be read or if there's an error during JSON deserialization.
   *
   * @return an instance of {@link com.presence.model.AccountConfig} loaded with values from the
   *     JSON file
   * @throws RuntimeException if an {@link IOException} occurs while reading or parsing the file
   */
  @Primary
  @Bean
  public com.presence.model.AccountConfig getAccountConfig() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(
          new File("account-config.json"), com.presence.model.AccountConfig.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
