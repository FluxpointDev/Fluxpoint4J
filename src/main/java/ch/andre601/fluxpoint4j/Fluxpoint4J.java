package ch.andre601.fluxpoint4j;

import ch.andre601.fluxpoint4j.image.CustomImage;
import ch.andre601.fluxpoint4j.mc.MCRequestBuilder;
import ch.andre601.fluxpoint4j.request.GeneratedImage;
import ch.andre601.fluxpoint4j.request.GenericAPIResponse;
import ch.andre601.fluxpoint4j.request.RequestHandler;
import ch.andre601.fluxpoint4j.welcome.WelcomeImage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Main class to interact with the Fluxpoint API.
 * 
 * <p>It currently supports the following features:
 * <ul>
 *     <li>Creating custom Images:
 *     <ul>
 *         <li>{@link #getCustomImage(CustomImage) getCustomImage(Image)}</li>
 *         <li>{@link #queueCustomImage(CustomImage) queueCustomImage(CustomImage)}</li>
 *     </ul>
 *     </li>
 *     <li>Creating Welcome Images:
 *     <ul>
 *         <li>{@link #getWelcomeImage(WelcomeImage) getWelcomeImage(WelcomeImage)}</li>
 *         <li>{@link #queueWelcomeImage(WelcomeImage) queueWelcomeImage(WelcomeImage)}</li>
 *     </ul>
 *     </li>
 *     <li>Getting MC Server information:
 *     <ul>
 *         <li>{@link #getMCServerInfo(String) getMCServerInfo(String)}</li>
 *         <li>{@link #getMCServerInfo(String, int) getMCServerInfo(String, int)}</li>
 *         <li>{@link #queueMCServerInfo(String) queueMCServerInfo(String)}</li>
 *         <li>{@link #queueMCServerInfo(String, int) queueMCServerInfo(String, int)}</li>
 *     </ul>
 *     </li>
 * </ul>
 */
public class Fluxpoint4J{
    
    private String token = null;
    private final RequestHandler requestHandler = new RequestHandler();
    
    /**
     * Sets the API token to use for the Fluxpoint API.
     * 
     * @param token
     *        The API token to use.
     */
    public void setToken(@NotNull String token){
        CheckUtil.notNullOrEmpty(token, "Token");
        
        this.token = token;
    }
    
    /**
     * Generates a custom image using the provided {@link CustomImage CustomImage instance}.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link GeneratedImage GeneratedImage} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getCustomImage(someCustomImage);
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (GeneratedImage)response;
     * }</pre>
     * 
     * @param  image
     *         The {@link CustomImage CustomImage} to generate.
     * 
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either the GeneratedImage or FailedAPIResponse instance.
     */
    @Nullable
    public GenericAPIResponse getCustomImage(@NotNull CustomImage image){
        return requestHandler.getCustomImage(token, image);
    }
    
    /**
     * Calls {@link #getCustomImage(CustomImage) getCustomImage(CustomImage)} and wraps it into a
     * {@link CompletableFuture CompletableFuture&lt;GenericAPIResponse&gt;} for you to use.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link GeneratedImage GeneratedImage} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getCustomImage(someCustomImage);
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (GeneratedImage)response;
     * }</pre>
     * 
     * @param  image
     *         The {@link CustomImage CustomImage} to generate.
     * 
     * @return {@link CompletableFuture CompletableFuture} with a {@link GenericAPIResponse GenericAPIResponse} instance.
     * 
     * @see #getCustomImage(CustomImage) getCustomImage
     */
    public CompletableFuture<GenericAPIResponse> queueCustomImage(@NotNull CustomImage image){
        return CompletableFuture.supplyAsync(() -> getCustomImage(image));
    }
    
    /**
     * Generates a Welcome image using the provided {@link WelcomeImage WelcomeImage instance}.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link GeneratedImage GeneratedImage} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getWelcomeImage(someWelcomeImage);
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (GeneratedImage)response;
     * }</pre>
     *
     * @param  image
     *         The {@link WelcomeImage WelcomeImage} to generate.
     *
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either the GeneratedImage or a failed API response.
     */
    @Nullable
    public GenericAPIResponse getWelcomeImage(@NotNull WelcomeImage image){
        return requestHandler.getWelcomeImage(token, image);
    }
    
    /**
     * Calls {@link #getWelcomeImage(WelcomeImage) getWelcomeImage(WelcomeImage)} and wraps it into a
     * {@link CompletableFuture CompletableFuture&lt;GeneratedImage&gt;} for you to use.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link GeneratedImage GeneratedImage} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getWelcomeImage(someWelcomeImage);
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (GeneratedImage)response;
     * }</pre>
     * 
     * @param  image
     *         The {@link WelcomeImage WelcomeImage} to generate.
     *
     * @return {@link CompletableFuture CompletableFuture} with a {@link GenericAPIResponse GenericAPIResponse} instance.
     * 
     * @see #getWelcomeImage(WelcomeImage) getWelcomeImage
     */
    public CompletableFuture<GenericAPIResponse> queueWelcomeImage(@NotNull WelcomeImage image){
        return CompletableFuture.supplyAsync(() -> getWelcomeImage(image));
    }
    
    /**
     * Creates and returns a new instance of the {@link MCRequestBuilder MCRequestBuilder} to use, which allows you
     * to set various things in the request such as domain, port and if icon should be included.
     * 
     * @return A new, usable instance of {@link MCRequestBuilder MCRequestBuilder}.
     */
    public MCRequestBuilder getNewMCRequestBuilder(){
        return new MCRequestBuilder(token, requestHandler);
    }
    
    /**
     * Performs a request to the Fluxpoint API to check a Server and receive possible information from it.
     * <br>This particular method will ping the server with the default port 25565. If you want to set a own port, use
     * {@link #getMCServerInfo(String, int) getMCServerInfo(String, int)} instead.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getMCServerInfo("mc.example.com");
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a MCServerPingResponse instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     * 
     * @param  host
     *         The server to ping. This can be a domain or IP.
     * 
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     * 
     * @deprecated Use the {@link #getNewMCRequestBuilder() new Builder} instead.
     * 
     * @see #getMCServerInfo(String, int) getMCServerInfo  
     */
    @Deprecated
    public GenericAPIResponse getMCServerInfo(@NotNull String host){
        return getMCServerInfo(host, 25565);
    }
    
    /**
     * Performs a request to the Fluxpoint API to check a Server and receive possible information from it.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getMCServerInfo("mc.example.com");
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a MCServerPingResponse instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     * 
     * @param  host
     *         The server to ping. This can be a domain or IP.
     * @param  port
     *         The port to ping the server on.
     *
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     *
     * @deprecated Use the {@link #getNewMCRequestBuilder() new Builder} instead.
     */
    @Deprecated
    public GenericAPIResponse getMCServerInfo(@NotNull String host, int port){
        return requestHandler.getMcServerResponse(token, host, port, false);
    }
    
    /**
     * Calls {@link #getMCServerInfo(String) getMCServerInfo(String)} and wraps it into a
     * {@link CompletableFuture CompletableFuture&lt;GeneratedImage&gt;} for you to use.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getMCServerInfo("mc.example.com");
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a MCServerPingResponse instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     *
     * @param  host
     *         The server to ping. This can be a domain or IP.
     *
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     *
     * @deprecated Use the {@link #getNewMCRequestBuilder() new Builder} instead.
     * 
     * @see #getMCServerInfo(String) getMCServerInfo
     */
    @Deprecated
    public CompletableFuture<GenericAPIResponse> queueMCServerInfo(@NotNull String host){
        return CompletableFuture.supplyAsync(() -> getMCServerInfo(host));
    }
    
    /**
     * Calls {@link #getMCServerInfo(String, int) getMCServerInfo(String, int)} and wraps it into a
     * {@link CompletableFuture CompletableFuture&lt;GeneratedImage&gt;} for you to use.
     *
     * <p>The returned {@link GenericAPIResponse GenericAPIResponse} can be one of two instances:
     * <ul>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.MCServerPingResponse MCServerPingResponse} on a successful request.</li>
     *     <li>Instance of {@link ch.andre601.fluxpoint4j.request.FailedAPIResponse FailedAPIResponse} on a failed request</li>
     * </ul>
     *
     * to see if the request was successful or not can you do this simple check:
     * <pre>{@code
     * GenericAPIResponse response = getMCServerInfo("mc.example.com");
     *
     * if(response instanceof FailedAPIResponse){
     *     // The request was not successful!
     *     System.out.println("Request failed!");
     *     return;
     * }
     *
     * // Request was successful. Response is a MCServerPingResponse instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     *
     * @param  host
     *         The server to ping. This can be a domain or IP.
     * @param  port
     *         The port to ping the server on.
     *
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     *
     * @deprecated Use the {@link #getNewMCRequestBuilder() new Builder} instead.
     * 
     * @see #getMCServerInfo(String, int) getMCServerInfo
     */
    @Deprecated
    public CompletableFuture<GenericAPIResponse> queueMCServerInfo(@NotNull String host, int port){
        return CompletableFuture.supplyAsync(() -> getMCServerInfo(host, port));
    }
}
