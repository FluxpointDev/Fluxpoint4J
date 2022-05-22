package ch.andre601.fluxpoint4j;

import ch.andre601.fluxpoint4j.image.CustomImage;
import ch.andre601.fluxpoint4j.request.GeneratedImage;
import ch.andre601.fluxpoint4j.request.RequestHandler;
import ch.andre601.fluxpoint4j.util.ColorObject;
import ch.andre601.fluxpoint4j.util.ColorObjectSerializer;
import ch.andre601.fluxpoint4j.welcome.WelcomeImage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Main class to generate and retrieve images with.
 */
public class Fluxpoint4J{
    
    private String token = null;
    
    private final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(ColorObject.class, new ColorObjectSerializer())
        .create();
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
     * @param  image
     *         The {@link CustomImage CustomImage} to generate.
     * 
     * @return Possibly-null {@link GeneratedImage GeneratedImage instance}.
     */
    @Nullable
    public GeneratedImage getCustomImage(@NotNull CustomImage image){
        return requestHandler.getCustomImage(token, GSON.toJson(image));
    }
    
    /**
     * Calls {@link #getCustomImage(CustomImage) getCustomImage(CustomImage)} and wraps it into a
     * {@link CompletableFuture CompletableFuture&lt;CustomImage&gt;} for you to use.
     * 
     * @param  image
     *         The {@link CustomImage CustomImage} to generate.
     * 
     * @return {@link CompletableFuture CompletableFuture} with a possibly-null {@link GeneratedImage GeneratedImage instance}.
     * 
     * @see #getCustomImage(CustomImage) getCustomImage
     */
    public CompletableFuture<GeneratedImage> queueCustomImage(@NotNull CustomImage image){
        return CompletableFuture.supplyAsync(() -> getCustomImage(image));
    }
    
    /**
     * Generates a Welcome image using the provided {@link WelcomeImage WelcomeImage instance}.
     *
     * @param  image
     *         The {@link WelcomeImage WelcomeImage} to generate.
     * 
     * @return Possibly-null {@link GeneratedImage GeneratedImage instance}.
     */
    @Nullable
    public GeneratedImage getWelcomeImage(@NotNull WelcomeImage image){
        return requestHandler.getWelcomeImage(token, GSON.toJson(image));
    }
    
    /**
     * Calls {@link #getWelcomeImage(WelcomeImage) getWelcomeImage(WelcomeImage)} and wraps it into a
     * {@link CompletableFuture CompletableFuture&lt;GeneratedImage&gt;} for you to use.
     * 
     * @param  image
     *         The {@link WelcomeImage WelcomeImage} to generate.
     *
     * @return {@link CompletableFuture CompletableFuture} with a possibly-null {@link GeneratedImage GeneratedImage instance}.
     * 
     * @see #getWelcomeImage(WelcomeImage) getWelcomeImage
     */
    public CompletableFuture<GeneratedImage> queueWelcomeImage(@NotNull WelcomeImage image){
        return CompletableFuture.supplyAsync(() -> getWelcomeImage(image));
    }
}
