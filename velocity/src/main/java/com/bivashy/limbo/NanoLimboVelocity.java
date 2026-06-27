/*
 * Copyright (C) 2020 Nan1t
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.bivashy.limbo;

import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.bivashy.limbo.command.LampVelocityCommandHandler;
import com.bivashy.limbo.config.LimboConfig;
import com.bivashy.limbo.config.model.VelocityLimboServer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerInfo;

import ua.nanit.limbo.LimboConstants;
import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.CommandHandler;
import ua.nanit.limbo.server.LimboServer;

@Plugin(id = "nanolimbovelocity", name = "NanoLimboVelocity", version = "1.13.0", authors = "bivashy, Nan1t")
public class NanoLimboVelocity {
    static {
        LimboConstants.class.getName(); // For preventing shadow jar minimizing
    }

    private static NanoLimboVelocity instance;
    private final Map<String, LimboServer> servers = new HashMap<>();
    private final ProxyServer server;
    private final Path dataFolder;
    private final LimboConfig limboConfig;

    @Inject
    public NanoLimboVelocity(ProxyServer server, @DataDirectory Path dataFolder) {
        instance = this;
        this.server = server;
        this.dataFolder = dataFolder;
        this.limboConfig = new LimboConfig(this);
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent e) {
        CommandHandler<Command> commandHandler = new LampVelocityCommandHandler(this).registerAll();
        for (VelocityLimboServer velocityLimboServer : limboConfig.getServers()) {
            LimboServer server = new LimboServer(velocityLimboServer.getLimboConfig(), commandHandler,
                    getClass().getClassLoader());

            ServerInfo serverInfo = new ServerInfo(velocityLimboServer.getLimboName(),
                    (InetSocketAddress) velocityLimboServer.getLimboConfig().getAddress());
            servers.put(serverInfo.getName(), server);
            this.server.registerServer(serverInfo);
            if (velocityLimboServer.getAddToTryList()) {
                this.server.getConfiguration().getAttemptConnectionOrder().add(serverInfo.getName());
            }
            try {
                server.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ProxyServer getServer() {
        return server;
    }

    public Path getDataFolder() {
        return dataFolder;
    }

    public LimboConfig getLimboConfig() {
        return limboConfig;
    }

    public Map<String, LimboServer> getServers() {
        return servers;
    }

    public static NanoLimboVelocity getInstance() {
        return instance;
    }
}
