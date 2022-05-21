package ch.andre601.fluxpoint4j.request;

import ch.andre601.fluxpoint4j.image.CustomImage;
import ch.andre601.fluxpoint4j.welcome.WelcomeImage;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class containing the generated Image from a successful request with either
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#getCustomImage(CustomImage) getCustomImage(CustomImage)} or
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#getWelcomeImage(WelcomeImage) getWelcomeImage(WelcomeImage)} in the
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J Fluxpoint4J class}.
 * 
 * This class allows you to get the image as either {@link #getAsInputStream() InputStream} or
 * {@link #getAsBufferedImage() BufferedImage}.
 */
public class GeneratedImage{
    
    private final InputStream inputStream;
    
    public GeneratedImage(InputStream inputStream){
        this.inputStream = inputStream;
    }
    
    /**
     * Gets the Generated Image as an {@link java.io.InputStream InputStream}.
     * 
     * @return InputStream containing the image.
     */
    public InputStream getAsInputStream(){
        return inputStream;
    }
    
    /**
     * Gets the generated image as a {@link java.awt.image.BufferedImage BufferedImage}.
     * 
     * @return Possibly-null {@link java.awt.image.BufferedImage BufferedImage} of the generated image.
     */
    @Nullable
    public BufferedImage getAsBufferedImage(){
        try{
            return ImageIO.read(inputStream);
        }catch(IOException ex){
            return null;
        }
    }
}
