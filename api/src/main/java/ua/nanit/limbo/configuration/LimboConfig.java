package ua.nanit.limbo.configuration;

import java.net.SocketAddress;

import net.kyori.adventure.text.Component;

import ua.nanit.limbo.server.TransportType;
import ua.nanit.limbo.server.data.BossBar;
import ua.nanit.limbo.server.data.InfoForwarding;
import ua.nanit.limbo.server.data.PingData;
import ua.nanit.limbo.server.data.Title;
import ua.nanit.limbo.world.DimensionType;

public interface LimboConfig {
    SocketAddress getAddress();

    int getMaxPlayers();

    PingData getPingData();

    DimensionType getDimensionType();

    int getGameMode();

    boolean isSecureProfile();

    InfoForwarding getInfoForwarding();

    long getReadTimeout();

    int getDebugLevel();

    boolean isLogPlayersIp();

    boolean isUseBrandName();

    boolean isUseJoinMessage();

    boolean isUseBossBar();

    boolean isUseTitle();

    boolean isUsePlayerList();

    boolean isUseHeaderAndFooter();

    Component getBrandName();

    Component getJoinMessage();

    BossBar getBossBar();

    Title getTitle();

    String getPlayerListUsername();

    Component getPlayerListHeader();

    Component getPlayerListFooter();

    TransportType getTransportType();

    int getBossGroupSize();

    int getWorkerGroupSize();

    default boolean isUseTrafficLimits() {
        return false;
    }

    default int getMaxPacketSize() {
        return -1;
    }

    default int getMaxPacketsPerSec() {
        return -1;
    }

    default int getMaxBytesPerSec() {
        return -1;
    }

    double getInterval();

    double getMaxPacketRate();

    double getMaxPacketBytesRate();

}
