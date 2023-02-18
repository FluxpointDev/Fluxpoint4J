package ch.andre601.fluxpoint4j.mc;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.request.GenericAPIResponse;
import ch.andre601.fluxpoint4j.request.RequestHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Builder class to set up a new request to the Fluxpoint API for the MC Server API.
 * 
 * <p>This class allows you to {@link #withHost(String) set a host}, {@link #withPort(int) set a port} and
 * {@link #withIcon(boolean) if icon should be included}.
 */
public class MCRequestBuilder{
    
    private final String token;
    private final RequestHandler handler;
    
    private String host = null;
    private int port = 25565;
    private boolean withIcon = false;
    
    public MCRequestBuilder(String token, RequestHandler handler){
        this.token = token;
        this.handler = handler;
    }
    
    /**
     * Sets the domain/IP that should be pinged by the API. Cannot be null or empty.
     * 
     * @param  host
     *         The domain/IP to ping.
     * 
     * @return This builder after the host has been set. Useful for chaining.
     */
    public MCRequestBuilder withHost(@NotNull String host){
        this.host = host;
        return this;
    }
    
    /**
     * Sets the port that should be pinged. Default is 25565 and the provided number cannot be negative.
     * 
     * @param  port
     *         The port to ping.
     * 
     * @return This builder after the port has been set. Useful for chaining.
     */
    public MCRequestBuilder withPort(int port){
        this.port = port;
        return this;
    }
    
    /**
     * Sets whether the FluxpointAPI should also include the Server's icon in its response.
     * <br>The returned icon is accessible through {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse#getIcon() MCServerPing's getIcon()}
     * method and is a Base64-encoded String of the icon.
     * 
     * @param  withIcon
     *         Whether the server icon should be included or not.
     * 
     * @return This builder after the boolean has been set. Useful for chaining.
     */
    public MCRequestBuilder withIcon(boolean withIcon){
        this.withIcon = withIcon;
        return this;
    }
    
    /**
     * Performs a request towards the Fluxpoint API to check a MC server and receive possible information from it.
     * 
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be onw of two instances:
     * <ul>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request.</li>
     * </ul>
     * 
     * Please make sure to check for the right instance using {@code instanceof} calls where necessary:
     * <pre>{@code 
     * GenericAPIResponse response = api.getMCRequestBuilder().withHost("example.com").performRequest();
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
     *     <li>{@link #withHost(String) Host} is null or empty.</li>
     *     <li>{@link #withPort(int) Port} is not a positive number.</li>
     * </ul>
     * 
     * @return A {@link GenericAPIResponse GenericAPIResponse} after a request has been made.
     */
    public GenericAPIResponse performRequest(){
        CheckUtil.notNullOrEmpty(host, "Host");
        CheckUtil.isPositive(port, "Port");
        
        return handler.getMcServerResponse(token, host, port, withIcon);
    }
    
    /**
     * Performs a request towards the Fluxpoint API to check a MC server and receive possible information from it and
     * wraps it into a {@link CompletableFuture CompletableFuture} for you to handle.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request.</li>
     * </ul>
     *
     * Please make sure to check for the right instance using {@code instanceof} calls where necessary:
     * <pre>{@code
     * CompletableFuture<GenericAPIResponse> future = api.getMCRequestBuilder().withHost("example.com").queueRequest();
     * 
     * future.whenComplete((response, throwable) -> {
     *     if(throwable != null){
     *         System.out.println("Encountered Exception: " + throwable.getMessage());
     *         return;
     *     }
     * 
     *     // Direct cast in if-block only usable in newer Java versions
     *     if(response instanceof FailedAPIResponse failedResponse){
     *         System.out.println("Request not successful!");
     *         System.out.println("Received Code: " + failedResponse.getCode());
     *         System.out.println("Received Message: " + failedResponse.getMessage());
     *         
     *         return;
     *     }
     *     
     *     // The request wasn't a failed one, so cast should be safe here.
     *     MCServerPingResponse mcResponse = (MCServerPingResponse)response;
     * }
     * }</pre>
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
     * <ul>
     *     <li>{@link #withHost(String) Host} is null or empty.</li>
     *     <li>{@link #withPort(int) Port} is not a positive number.</li>
     * </ul>
     *
     * @return A {@link GenericAPIResponse GenericAPIResponse} after a request has been made.
     */
    public CompletableFuture<GenericAPIResponse> queueRequest(){
        return CompletableFuture.supplyAsync(this::performRequest);
    }
}
