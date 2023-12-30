package ch.andre601.fluxpoint4j.mc;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.request.GenericAPIResponse;
import ch.andre601.fluxpoint4j.request.RequestHandler;
import ch.andre601.fluxpoint4j.request.mc.MCPlayerResponse;
import ch.andre601.fluxpoint4j.request.mc.MCServerPingResponse;
import ch.andre601.fluxpoint4j.request.mc.MCSkinResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class MCRequest{
    
    private final String token;
    private final RequestHandler handler;
    
    public MCRequest(@NotNull String token, @NotNull RequestHandler handler){
        CheckUtil.notNullOrEmpty(token, "Token");
        CheckUtil.notNull(handler, "Handler");
        
        this.token = token;
        this.handler = handler;
    }
    
    /**
     * Performs a request towards the Fluxpoint API with the provided {@link Player Player info} to retrieve a player's
     * name and UUID.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be onw of two instances:
     * <ul>
     *     <li>Instance of {@link MCPlayerResponse MCPlayerResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request.</li>
     * </ul>
     *
     * Please make sure to check for the right instance using {@code instanceof} calls where necessary:
     * <pre>{@code
     * GenericAPIResponse response = api.getMCRequestBuilder().getPlayer(player);
     *
     * // Direct cast in if-block only usable in newer Java versions
     * if(response instanceof FailedAPIResponse failedResponse){
     *     System.out.println("Request not successful!");
     *     System.out.println("Received Code: " + failedResponse.getCode());
     *     System.out.println("Received Message: " + failedResponse.getMessage());
     *
     *     return;
     * }
     *
     * // The request wasn't a failed one, so cast should be safe here.
     * MCPlayerResponse mcResponse = (MCPlayerResponse)response;
     * }</pre>
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
     * <ul>
     *     <li>The provided {@link Player Player instance} was null.</li>
     * </ul>
     *
     * @param  player
     *         {@link Player player instance} to use for the request.
     *
     * @return A {@link GenericAPIResponse GenericAPIResponse} after a request has been made.
     */
    public GenericAPIResponse getPlayer(@NotNull Player player){
        CheckUtil.notNull(player, "Player");
        return handler.performMCRequest(token, "uuid", MCPlayerResponse.class, player.getParameters());
    }
    
    /**
     * Calls {@link #getPlayer(Player) getPlayer(Player)} and wraps it into a CompletableFuture to do asynchronously.
     * <br>Please see {@link #getPlayer(Player) getPlayer(Player)} for further details.
     *
     * @param  player
     *         {@link Player player instance} to use for the request.
     *
     * @return CompletableFuture containing a {@link GenericAPIResponse GenericAPIResponse}.
     */
    public CompletableFuture<GenericAPIResponse> queuePlayer(@NotNull Player player){
        CheckUtil.notNull(player, "Player");
        return CompletableFuture.supplyAsync(() -> this.getPlayer(player));
    }
    
    /**
     * Performs a request towards the Fluxpoint API with the provided {@link PlayerSkin Player skin info} to retrieve a player's
     * skin as image.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be onw of two instances:
     * <ul>
     *     <li>Instance of {@link MCSkinResponse MCSkinResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request.</li>
     * </ul>
     *
     * Please make sure to check for the right instance using {@code instanceof} calls where necessary:
     * <pre>{@code
     * GenericAPIResponse response = api.getMCRequestBuilder().getSkin(playerSkin);
     *
     * // Direct cast in if-block only usable in newer Java versions
     * if(response instanceof FailedAPIResponse failedResponse){
     *     System.out.println("Request not successful!");
     *     System.out.println("Received Code: " + failedResponse.getCode());
     *     System.out.println("Received Message: " + failedResponse.getMessage());
     *
     *     return;
     * }
     *
     * // The request wasn't a failed one, so cast should be safe here.
     * MCSkinResponse mcResponse = (MCSkinResponse)response;
     * }</pre>
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
     * <ul>
     *     <li>The provided {@link PlayerSkin PlayerSkin instance} was null.</li>
     * </ul>
     *
     * @param  playerSkin
     *         {@link PlayerSkin PlayerSkin instance} to use for the request.
     *
     * @return A {@link GenericAPIResponse GenericAPIResponse} after a request has been made.
     */
    public GenericAPIResponse getSkin(@NotNull PlayerSkin playerSkin){
        CheckUtil.notNull(playerSkin, "PlayerSkin");
        return handler.performMCRequest(token, "skin", MCSkinResponse.class, playerSkin.getParameters());
    }
    
    /**
     * Calls {@link #getSkin(PlayerSkin) getSkin(PlayerSkin)} and wraps it into a CompletableFuture to do asynchronously.
     * <br>Please see {@link #getSkin(PlayerSkin) getSkin(PlayerSkin)} for further details.
     *
     * @param  playerSkin
     *         {@link PlayerSkin PlayerSkin instance} to use for the request.
     *
     * @return CompletableFuture containing a {@link GenericAPIResponse GenericAPIResponse}.
     */
    public CompletableFuture<GenericAPIResponse> queueSkin(@NotNull PlayerSkin playerSkin){
        CheckUtil.notNull(playerSkin, "PlayerSkin");
        return CompletableFuture.supplyAsync(() -> this.getSkin(playerSkin));
    }
    
    /**
     * Performs a request towards the Fluxpoint API with the provided {@link Server Server info} to retrieve a server's
     * details.
     * <br>This is a full replacement for the now deprecated builder methods used previously to define the properties for the request.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be onw of two instances:
     * <ul>
     *     <li>Instance of {@link MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request.</li>
     * </ul>
     *
     * Please make sure to check for the right instance using {@code instanceof} calls where necessary:
     * <pre>{@code
     * GenericAPIResponse response = api.getMCRequestBuilder().getServer(server);
     *
     * // Direct cast in if-block only usable in newer Java versions
     * if(response instanceof FailedAPIResponse failedResponse){
     *     System.out.println("Request not successful!");
     *     System.out.println("Received Code: " + failedResponse.getCode());
     *     System.out.println("Received Message: " + failedResponse.getMessage());
     *
     *     return;
     * }
     *
     * // The request wasn't a failed one, so cast should be safe here.
     * MCServerPingResponse mcResponse = (MCServerPingResponse)response;
     * }</pre>
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
     * <ul>
     *     <li>The provided {@link Server Server instance} was null.</li>
     * </ul>
     *
     * @param  server
     *         {@link Server Server instance} to use for the request.
     *
     * @return A {@link GenericAPIResponse GenericAPIResponse} after a request has been made.
     */
    public GenericAPIResponse getServer(@NotNull Server server){
        CheckUtil.notNull(server, "Server");
        return handler.performMCRequest(token, "ping", MCServerPingResponse.class, server.getParameters());
    }
    
    /**
     * Calls {@link #getServer(Server) getServer(Server)} and wraps it into a CompletableFuture to do asynchronously.
     * <br>Please see {@link #getServer(Server) getServer(Server)} for further details.
     *
     * @param  server
     *         The {@link Server Server instance} to use for the request.
     *
     * @return CompletableFuture containing a {@link GenericAPIResponse GenericAPIResponse}.
     */
    public CompletableFuture<GenericAPIResponse> queueServer(@NotNull Server server){
        CheckUtil.notNull(server, "Server");
        return CompletableFuture.supplyAsync(() -> this.getServer(server));
    }
    
    /**
     * Simple class used for the {@link #getPlayer(Player) getPlayer(Player) method}.
     * <br>The {@link #withName(String) player name} needs to be set, or else it will throw an
     * {@link java.lang.IllegalArgumentException IllegalArgumentException} when {@link #getParameters() retrieving the parametes}.
     */
    public static class Player{
        private String name = null;
        
        /**
         * Sets the name that should be looked up by the API.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
         * <ul>
         *     <li>Name is null or empty.</li>
         * </ul>
         *
         * @param  name
         *         Player name to look up.
         *
         * @return The Player instance. Useful for chaining.
         */
        public Player withName(@NotNull String name){
            CheckUtil.notNullOrEmpty(name, "Name");
            
            this.name = name;
            return this;
        }
        
        /**
         * Generates a String array with query parameters created from the set values.
         * <br>This is used internally by Fluxpoint4J to create the proper request URL.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
         * <ul>
         *     <li>Name is null or empty.</li>
         * </ul>
         *
         * @return String array containing query parameters in the format {@code <key>=<value>}
         */
        public String[] getParameters(){
            CheckUtil.notNullOrEmpty(name, "Player Name");
            
            return new String[]{
                "player=" + name
            };
        }
    }
    
    public static class PlayerSkin extends Player{
        
        private String name = null;
        private String type = SkinType.FULL.name().toLowerCase(Locale.ROOT);
        
        /**
         * {@inheritDoc}
         *
         * @param  name
         *         Player name to look up.
         *
         * @return The PlayerSkin instance. Useful for chaining.
         */
        @Override
        public PlayerSkin withName(@NotNull String name){
            CheckUtil.notNullOrEmpty(name, "Name");
            
            this.name = name;
            return this;
        }
        
        /**
         * Sets the {@link SkinType Skin type} to use.
         * <br>This effectively alters how the skin will be displayed in the returned URL by the API.
         *
         * <p>The following types can be defined:
         * <ul>
         *     <li>{@link SkinType#BODY SkinType.BODY}</li>
         *     <li>{@link SkinType#CUBE SkinType.CUBE}</li>
         *     <li>{@link SkinType#HEAD SkinType.HEAD}</li>
         *     <li>{@link SkinType#FULL SkinType.FULL} (Default)</li>
         *     <li>{@link SkinType#ALL SkinType.ALL}</li>
         * </ul>
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
         * <ul>
         *     <li>Type is null.</li>
         * </ul>
         *
         * @param  type
         *         How the skin should be displayed.
         *
         * @return The PlayerSkin instance. Useful for chaining
         */
        public PlayerSkin withType(@NotNull SkinType type){
            CheckUtil.notNull(type, "Skin Type");
            
            this.type = type.getName();
            return this;
        }
        
        /**
         * Generates a String array with query parameters created from the set values.
         * <br>This is used internally by Fluxpoint4J to create the proper request URL.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
         * <ul>
         *     <li>Name is null or empty.</li>
         *     <li>Type is null or empty.</li>
         * </ul>
         *
         * @return String array containing query parameters in the format {@code <key>=<value>}
         */
        @Override
        public String[] getParameters(){
            CheckUtil.notNullOrEmpty(name, "Player Name");
            CheckUtil.notNullOrEmpty(type, "Type");
            
            return new String[]{
                "player=" + name,
                "type=" + type
            };
        }
    }
    
    public static class Server{
        private String host = null;
        private int port = 25565;
        private boolean withIcon = false;
        
        public Server withHost(@NotNull String host){
            CheckUtil.notNullOrEmpty(host, "Host");
            this.host = host;
            return this;
        }
        
        public Server withPort(int port){
            CheckUtil.isPositive(port, "Port");
            this.port = port;
            return this;
        }
        
        public Server includeIcon(boolean withIcon){
            this.withIcon = withIcon;
            return this;
        }
        
        /**
         * Generates a String array with query parameters created from the set values.
         * <br>This is used internally by Fluxpoint4J to create the proper request URL.
         *
         * @return String array containing query parameters in the format {@code <key>=<value>}
         */
        public String[] getParameters(){
            return new String[]{
                "host=" + host,
                "port=" + port,
                "icon=" + withIcon
            };
        }
    }
    
    public enum SkinType{
        BODY,
        CUBE,
        HEAD,
        FULL,
        ALL;
        
        public String getName(){
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
