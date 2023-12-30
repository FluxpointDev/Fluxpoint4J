package ch.andre601.fluxpoint4j.request.mc;

import ch.andre601.fluxpoint4j.mc.MCRequestBuilder;
import ch.andre601.fluxpoint4j.request.GenericAPIResponse;
import org.jetbrains.annotations.Nullable;

/**
 * Class used for the {@link MCRequestBuilder#performRequest() MCRequestBuilder.performRequest()} and
 * {@link MCRequestBuilder#queueRequest() MCRequestBuilder.queueRequest()} methods.
 */
public class MCServerPingResponse implements GenericAPIResponse{
    private int code;
    private String message;
    
    private boolean online;
    private String icon;
    private String motd;
    private int playersOnline;
    private int playersMax;
    private String version;
    private boolean fullQuery;
    private String[] players;
    private String status;
    private String rawIcon;
    
    /**
     * {@inheritDoc}
     * 
     * @return Status code from the API response.
     */
    @Override
    public int getCode(){
        return code;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return Possibly-empty/null message from the API response.
     */
    @Override
    @Nullable
    public String getMessage(){
        return message;
    }
    
    /**
     * Returns whether the pinged server is online or not.
     * 
     * @return True if the server is online, otherwise false.
     */
    public boolean isOnline(){
        return online;
    }
    
    /**
     * Gives you the URL to the server's icon.
     * <br>The URL is from <a href="https://mc-api.net" target="_blank">mc-api.net</a> and Fluxpoint is not affiliated nor endorsed by them.
     * 
     * @return String containing the Image URL to the server's icon.
     */
    public String getIcon(){
        return icon;
    }
    
    /**
     * The MOTD displayed by the server during the ping.
     * 
     * @return String containing the MOTD of the server.
     */
    public String getMotd(){
        return motd;
    }
    
    /**
     * Returns the online player count.
     * 
     * @return Amount of players online on the server.
     */
    public int getPlayersOnline(){
        return playersOnline;
    }
    
    /**
     * The total amount of players the server can accept.
     * 
     * @return Total amount of players allowed on the server.
     */
    public int getMaxPlayers(){
        return playersMax;
    }
    
    /**
     * Returns the MC version this server is running.
     * <br>The returned value can be anything as the server can manipulate what it returns.
     * 
     * @return String containing the supported version(s) for the server.
     */
    public String getVersion(){
        return version;
    }
    
    /**
     * Whether this returned info is the full query.
     * <br>When {@code false} does it mean that certain values (i.e. the {@link #getPlayers() player list}) are empty.
     * 
     * @return True if all values are returned, otherwise false.
     * 
     * @deprecated The returned boolean instance had a typo, making it not be handled by the JSON parser. Use {@link #isFullQuery() isFullQuery} instead.
     */
    @Deprecated
    public boolean isFullQuerry(){
        return fullQuery;
    }
    
    /**
     * Whether this returned info is the full query.
     * <br>When {@code false} does it mean that certain values (i.e. the {@link #getPlayers() player list}) are empty.
     *
     * @return True if all values are returned, otherwise false.
     */
    public boolean isFullQuery(){
        return fullQuery;
    }
    
    /**
     * Gives you an Array of the online player names on the server.
     * <br>This may be empty if the server doesn't provide (hides) the players.
     * 
     * @return Possibly-empty String array containing the names of the online players.
     */
    public String[] getPlayers(){
        return players;
    }
    
    /**
     * Returns the Status. This can be "ok", but also something completely different.
     * 
     * @return Status of the server.
     */
    public String getStatus(){
        return status;
    }
    
    /**
     * Returns the Base64-encoded image used by the server.
     * <br>To get the icon, set {@link MCRequestBuilder#withIcon(boolean) withIcon(true)} in
     * the {@link MCRequestBuilder MCRequestBuilder}.
     * 
     * @return possibly-null/Empty, Base64-encoded String representing the Server's icon.
     */
    public String getRawIcon(){
        return rawIcon;
    }
}
