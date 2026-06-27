# NanoLimboPlugin
An implementation of Nan1t's [NanoLimbo](https://github.com/Nan1t/NanoLimbo) for use in Velocity proxies. Fork of [bivashy](https://github.com/bivashy/NanoLimboPlugin)'s plugin.

---
### Version support
Supports all *releases* between 1.7 and 26.2. Only Velocity is supported for now.

### Commands
* `limbohelp` - Show help message
* `limboconn [server]` - Display number of connections for a server
* `mem` - Display memory usage stats
* `limbostart [server]` - Start a server
* `limbostop [server]` - Stop a server

### Installation
Required software: JRE 21+  
The installation process is simple.

1. Download the latest version of the plugin [**here**](https://github.com/Nan1t/NanoLimbo/releases).
2. Put the jar file in your `plugins` folder.
3. Configure the plugin and limbo servers within the `nanolimbovelocity` folder.

### Player info forwarding
The server supports player info forwarding from the proxy. There are several types of info forwarding:
* `LEGACY` - The **BungeeCord** IP forwarding.
* `MODERN` - **Velocity** native info forwarding type.
* `BUNGEE_GUARD` - **BungeeGuard** forwarding type.

If you use Velocity with `LEGACY` forwarding, just set this type in the config.  
If you use Velocity with `MODERN` info forwarding, set this type and paste the secret key from
Velocity config into `secret` field.  
If you are using BungeeGuard forwarding, then use `BUNGEE_GUARD` forwarding type.
Then add your tokens to `tokens` list.

---
## Developer API
> [!WARNING]  
> Artifact repositories are WIP.

If you want to create a NanoLimbo instance using the API,  you can follow these steps:
### Get started
#### How to use the API?
It's easy!
```java
LimboConfig config = new YamlLimboConfig(Paths.get("./"), classLoader).load();
CommandHandler<Command> commandHandler = new ConsoleCommandHandler();
LimboServer server = new LimboServer(config, commandHandler, getClass().getClassLoader());
server.start();

// When you are done
server.stop();
```
Here we are passing 3 arguments:
1. LimboConfig - Configures limbo server, defines `SocketAddress`, join message, title, dimension type, and etc.
2. CommandHandler - Simple CommandHandler. It used by limbo for registering commands. Useful for console only, redundant for Bukkit, Bungee, and other platforms.
3. ClassLoader - used only for loading dummy dimension files from the "resources" directory. It loads all dimensions from the "dimension" folder, which can be found at [this link](https://github.com/bivashy/NanoLimboPlugin/tree/main/api/src/main/resources/dimension).
---

If you don't want to create or load config file, just implement `LimboConfig` interface, just like this:
```java
import java.net.SocketAddress;
import net.kyori.adventure.text.Component;

public class CustomLimboConfig implements LimboConfig {
   /**
    * Disables debug entirely
    */
   @Override
   public int getDebugLevel() {
      return -1;
   }

   /**
    * Set F3 brand text to be "Some brand text"
    */
   @Override
   public Component getBrandName() {
      return Component.text("Some brand text");
   }

   /**
    * Launches limbo on the localhost:25555
    */
   @Override
   public SocketAddress getAddress() {
       return new InetSocketAddress("localhost", 25555);
   }

   // Implement all methods
}
```
Then just pass to the LimboServer:
```java
LimboConfig config = new CustomLimboConfig();
LimboServer server = new LimboServer(config, commandHandler, getClass().getClassLoader()); 
```

---

If you don't want limbo commands:
```java
import java.util.Collections;

public class CustomCommandHandler implements CommandHandler<Command> {
   @Override
   public Collection<Command> getCommands() {
      return Collections.emptyList();
   }

   public void register(T command) {
   }

   public boolean executeCommand(String input) {
   }
}
```

Then just pass to the LimboServer:
```java
CommandHandler<Command> commandHandler = new CustomCommandHandler();
LimboServer server = new LimboServer(config, commandHandler, getClass().getClassLoader()); 
```
---

### Credits
This release is built on top of community contributions across multiple forks.
Huge thanks to everyone listed below — expand each section to see what they contributed.

<details>
<summary><b>Nan1t</b> — original NanoLimbo author &amp; maintainer</summary>
The entire foundation of NanoLimbo:

- Netty pipeline, packet system, multi-version protocol skeleton up to 1.21
- BungeeCord and Velocity info forwarding
- Configuration framework, command system, dimension registry
- Project structure, build setup, release process

Source: https://github.com/Nan1t/NanoLimbo
</details>

<details>
<summary><b>bivashy</b> — original NanoLimboPlugin author
</summary>
Velocity & Bungeecord implementation. Original modifications to the NanoLimbo server.

Source: https://github.com/bivashy/NanoLimboPlugin
</details>

<details>
<summary><b>BoomEaro</b> (Valentine) — Minecraft 1.21.2 → 26.1, modernization</summary>

The bulk of post-1.21 protocol work and toolchain modernization:

- Protocol support for **1.21.2, 1.21.3, 1.21.4, 1.21.5, 1.21.6, 1.21.7, 1.21.8, 1.21.9, 1.21.10, 1.21.11, 26.1** and **26.2**
- Rewrote the login → configuration phase for the post-1.20.5 known-packs handshake: `PacketKnownPacks`, `PacketUpdateTags`, per-version `PacketRegistryData`
- Rewrote play packets for the 1.21.x line: `PacketLogin` (formerly `PacketJoinGame`), `PacketChunkWithLight` (real heightmaps + biome palette + light update), `PacketPlayerPositionAndLook` for the 1.21.2 teleport-flags redesign, `PacketGameEvent` with `start_waiting_for_chunks`
- Build modernization: Gradle Kotlin DSL, version catalog, **Java 17**, Lombok, GitHub Actions for build & release
- Adventure stack (api / gson / legacy / plain / json / nbt) + **MiniMessage** support in all text fields
- Netty 4.2 split modules with native transports: epoll, io_uring (Linux x86_64 / aarch64) and kqueue (macOS x86_64 / aarch64); new `TransportType` enum
- Per-connection traffic rate limiting in `ChannelTrafficHandler`
- Refactored configuration serializers, `VersionedDimension`, `version` command

Source: https://github.com/BoomEaro/NanoLimbo &nbsp;·&nbsp; upstream PR: [#98](https://github.com/Nan1t/NanoLimbo/pull/98)
</details>

<details>
<summary><b>YueMi-Development</b> — external secret files</summary>

- `@`-prefix support in `infoForwarding.secret` and `infoForwarding.tokens`: values starting with `@` are read from a file relative to the working directory. Lets you keep credentials out of `settings.yml` (Docker / Kubernetes secrets, SOPS, etc.).

Source: https://github.com/YueMi-Development/NanoLimbo
</details>

<details>
<summary><b>Biquaternions</b> — IP logging privacy switch</summary>

- New `logPlayersIp` config flag. When `false`, player IP addresses are redacted in connection logs (shown as `<redacted>`). Useful for GDPR / privacy-compliant deployments.

Source: https://github.com/Biquaternions/NanoLimbo &nbsp;·&nbsp; upstream PR: [#96](https://github.com/Nan1t/NanoLimbo/pull/96)
</details>

<details>
<summary><b>SuperN64</b> — NanoLimboPlugin maintainer</summary>

- Added the ability to automatically add a server into the connection attempts list
- Port BoomEaro's patches into NanoLimboPlugin

Source: this! The thing you're looking at right now!
</details>

### Contributing

Feel free to create a pull request if you find some bug or optimization opportunity, or if you want
to add some functionality that is suitable for a limbo server and won't significantly load the server.

### Building

Required software:

* JDK 21+
* Gradle 9+ (optional)

To build a minimized jar, go to the project root directory and run in the terminal:

```
./gradlew build
```