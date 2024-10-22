# Roblox Presence Checker

## Overview

The Roblox Presence Checker is a Spring Boot application designed to monitor the online presence of specified Roblox accounts. It retrieves the presence status of the accounts and sends updates to a designated Discord channel using a Discord webhook.

## Features

- Monitors the online status of multiple Roblox accounts.
- Sends presence updates to a Discord channel in real-time.
- Configurable settings through a JSON configuration file.

## Prerequisites

- Java 17 or higher
- A Roblox account with the account IDs you want to monitor.
-  **Discord User ID**: To find your Discord user ID, refer to the [Discord Support article](https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID).
-  **Discord Channel Webhook**: Please relate to this article [Intro to Webhooks](https://support.discord.com/hc/en-us/articles/228383668-Intro-to-Webhooks)

## Installation

1. **Download the latest release:**

   You can download the latest version of the application, which includes the JAR file and a sample `account-config.json`, from the [GitHub Releases](https://github.com/username/repo-name/releases](https://github.com/duckiedot/roblox-presistance-monitor/releases)) page. 

   - Click on the latest release.
   - Download the `roblox-presence-checker.zip` file.
   - Extract the contents to a preferred directory.

2. **Update Configuration:**

   Open the `account-config.json` file and update it with your desired settings:

   ```json
   {
     "accountsToMonitor": [
       123456789  // Replace with actual Roblox account IDs
     ],
     "discordUserId": 123456789, // Your Discord user ID
     "discordHook": "your-discord-hook-url" // Your Discord webhook URL
   }
   ```

## Running the Application
To run the application, follow the instructions based on your operating system:

#### On macOS / Unix:
Open a terminal, navigate to the directory where you extracted the files, and run the following command:

```bash
java -jar roblox-api-0.0.1-SNAPSHOT.jar
```

#### Windows
Open Command Prompt (CMD) or PowerShell, navigate to the directory where you extracted the files, and run:
```bash
java -jar roblox-api-0.0.1-SNAPSHOT.jar
```
