package ch.andre601.fluxpoint4j;

import ch.andre601.fluxpoint4j.image.CustomImage;
import ch.andre601.fluxpoint4j.request.GeneratedImage;
import ch.andre601.fluxpoint4j.request.GenericAPIResponse;
import ch.andre601.fluxpoint4j.request.RequestHandler;
import ch.andre601.fluxpoint4j.welcome.WelcomeImage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Main class to generate and retrieve images with.
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
        if(token.isEmpty())
            throw new NullPointerException("Token may not be null.");
        
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
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     * 
     * @param  host
     *         The server to ping. This can be a domain or IP.
     * 
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     * 
     * @see #getMCServerInfo(String, int) getMCServerInfo  
     */
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
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     * 
     * @param  host
     *         The server to ping. This can be a domain or IP.
     * @param  port
     *         The port to ping the server on.
     *
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     */
    public GenericAPIResponse getMCServerInfo(@NotNull String host, int port){
        return requestHandler.getMcServerResponse(token, host, port);
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
     * // Request was successful. Response is a GeneratedImage instance!
     * GeneratedImage image = (MCServerPingResponse)response;
     * }</pre>
     *
     * @param  host
     *         The server to ping. This can be a domain or IP.
     *
     * @return {@link GenericAPIResponse GenericAPIResponse} that is either a MCServerPingResponse or FailedAPIResponse instance.
     * 
     * @see #getMCServerInfo(String) getMCServerInfo
     */
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
     * // Request was successful. Response is a GeneratedImage instance!
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
     * @see #getMCServerInfo(String, int) getMCServerInfo
     */
    public CompletableFuture<GenericAPIResponse> queueMCServerInfo(@NotNull String host, int port){
        return CompletableFuture.supplyAsync(() -> getMCServerInfo(host, port));
    }
}
