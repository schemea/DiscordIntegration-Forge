package de.erdbeerbaerlp.dcintegration.forge.config;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import dcshadow.com.moandjiezana.toml.Toml;
import dcshadow.com.moandjiezana.toml.TomlWriter;


public class NotificationConfiguration {

    private static final File CONFIG_FILE = new File("./DiscordIntegration-Data/Notifications.toml");

    private static NotificationConfiguration INSTANCE;
    public boolean inGameChatMessage = false;
    public boolean playerJoin = false;
    public boolean playerLeave = false;
    public boolean playerDeath = true;
    public boolean playerAdvancement = false;

    public boolean serverStarting = true;
    public boolean serverStarted = true;

    public static NotificationConfiguration instance() {
        return INSTANCE;
    }

    public static void loadConfig() {
        if (!CONFIG_FILE.exists()) {
            INSTANCE = new NotificationConfiguration();
            saveConfig();
        }

        final Toml toml = new Toml().read(CONFIG_FILE);
        INSTANCE = toml.to(NotificationConfiguration.class);
    }

    public static void saveConfig() {
        var writer = new TomlWriter.Builder()
                .indentValuesBy(2)
                .indentTablesBy(4)
                .padArrayDelimitersBy(2)
                .build();

        try {
            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.createNewFile();
            }
            writer.write(new NotificationConfiguration(), CONFIG_FILE);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
