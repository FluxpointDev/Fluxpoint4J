package ch.andre601.fluxpoint4j.image;

import ch.andre601.fluxpoint4j.image.format.Image;
import ch.andre601.fluxpoint4j.image.format.Text;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomImage{
    
    private final Image base;
    private final List<Image> images;
    private final List<Text> texts;
    
    private CustomImage(Image base, List<Image> images, List<Text> texts){
        this.base = base;
        this.images = images;
        this.texts = texts;
    }
    
    public static class Builder{
        
        private final Image base;
        private final List<Image> images = new ArrayList<>();
        private final List<Text> texts = new ArrayList<>();
        
        private Builder(@NotNull Image base){
            this.base = base;
        }
        
        public static Builder createBase(@NotNull Image base){
            return new Builder(base);
        }
        
        public Builder addImage(@NotNull Image image){
            this.images.add(image);
            return this;
        }
        
        public Builder addText(@NotNull Text text){
            this.texts.add(text);
            return this;
        }
        
        public CustomImage build(){
            return new CustomImage(base, images, texts);
        }
    }
    
}
