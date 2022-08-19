package ch.andre601.fluxpoint4j.image;

import ch.andre601.fluxpoint4j.image.format.Image;
import ch.andre601.fluxpoint4j.image.format.Text;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for the {@link ch.andre601.fluxpoint4j.Fluxpoint4J#getCustomImage(CustomImage) getCustomImage(CustomImage)} and
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#queueCustomImage(CustomImage) queueCustomImage(CustomImage)} methods in the
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J Fluxpoint4J class}.
 * 
 * <p>To create an instance of this class will you need to use the {@link Builder nested builder class}.
 */
public class CustomImage{
    
    private final Image base;
    private final List<Image> images;
    private final List<Text> texts;
    
    private CustomImage(Image base, List<Image> images, List<Text> texts){
        this.base = base;
        this.images = images;
        this.texts = texts;
    }
    
    /**
     * Builder class to create a new {@link CustomImage CustomImage instance}.
     * 
     * To get a new Builder instance will you need to call {@link #createBase(Image) createBase(Image)} with a valid
     * {@link ch.andre601.fluxpoint4j.image.format.Image Image instance}.
     * <br>Additional images may be added using {@link #addImage(Image) addImage(Image)} and Text can be added using
     * {@link #addText(Text) addText(Text)} with a valid {@link ch.andre601.fluxpoint4j.image.format.Text Text instance}
     * provided.
     */
    public static class Builder{
        
        private final Image base;
        private final List<Image> images = new ArrayList<>();
        private final List<Text> texts = new ArrayList<>();
        
        private Builder(@NotNull Image base){
            this.base = base;
        }
    
        /**
         * Creates a new instance of this Builder using the provided {@link ch.andre601.fluxpoint4j.image.format.Image Image instance}.
         * <br>The provided Image instance will set a few things such as the final image's width and height.
         * 
         * @param  base
         *         The {@link ch.andre601.fluxpoint4j.image.format.Image Image instance} to use as the base layer of the image.
         * 
         * @return New instance of this Builder class to use.
         */
        public static Builder createBase(@NotNull Image base){
            return new Builder(base);
        }
    
        /**
         * Adds an additional {@link ch.andre601.fluxpoint4j.image.format.Image Image instance} to the image.
         * <br>This image will be added as a new layer in the final image by the API.
         * 
         * <p>Calling this method again or {@link #addText(Text) addText(Text)} can result in parts of the provided image
         * being covered by the new Image or Text provided in those extra calls. This depends on if parts of the image
         * and text overlap with each other.
         * 
         * @param  image
         *         The {@link ch.andre601.fluxpoint4j.image.format.Image Image instance} to add as a new layer for the image.
         * 
         * @return The builder instance after the Image has been added. Useful for chaining.
         */
        public Builder addImage(@NotNull Image image){
            this.images.add(image);
            return this;
        }
    
        /**
         * Adds a {@link ch.andre601.fluxpoint4j.image.format.Text Text instance} to the image.
         * <br>This text will be added as a new layer in the final image by the API.
         *
         * <p>Calling this method again or {@link #addImage(Image) addImage(Image)} can result in parts of the provided image
         * being covered by the new Image or Text provided in those extra calls. This depends on if parts of the image
         * and text overlap with each other.
         * 
         * @param  text
         *         The {@link ch.andre601.fluxpoint4j.image.format.Text Text instance} to add as a new layer for the image.
         * 
         * @return The builder instance after the Image has been added. Useful for chaining.
         */
        public Builder addText(@NotNull Text text){
            this.texts.add(text);
            return this;
        }
    
        /**
         * Generates a new {@link CustomImage CustomImage instance} to use for the actual API calls.
         * 
         * @return New CustomImage instance to use.
         */
        public CustomImage build(){
            return new CustomImage(base, images, texts);
        }
    }
    
}
