package ch.andre601.fluxpoint4j.image.format;

import ch.andre601.fluxpoint4j.CheckUtil;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class Text{
    
    @SerializedName("x")
    protected int posX = 0;
    @SerializedName("y")
    protected int posY = 0;
    
    protected int size = 1;
    
    protected String color = "black";
    
    protected String font = "Sans Serif";
    
    protected boolean bold = false;
    protected boolean unicode = false;
    
    protected int outline = 0;
    @SerializedName("outlinecolor")
    protected String outlineColor = "white";
    
    public abstract Text withPosX(int posX);
    
    public abstract Text withPosY(int posY);
    
    public abstract Text withSize(int size);
    
    public abstract Text withColor(@NotNull Color color);
    
    public abstract Text withColor(@NotNull String color);
    
    public abstract Text withColor(int r, int g, int b);
    
    public abstract Text withColor(int r, int g, int b, int a);
    
    public abstract Text withFont(@NotNull String font);
    
    public abstract Text asBold(boolean bold);
    
    public abstract Text supportUnicode(boolean unicode);
    
    public abstract Text withOutline(int outline);
    
    public abstract Text withOutlineColor(@NotNull String color);
    
    public static class SingleLine extends Text{
        
        private final String text;
        
        public SingleLine(@NotNull String line){
            CheckUtil.notEmpty(line, "Text");
            
            this.text = line;
        }
        
        @Override
        public SingleLine withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
            
            this.posX = posX;
            return this;
        }
    
        @Override
        public SingleLine withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        @Override
        public SingleLine withSize(int size){
            CheckUtil.largerThan(size, 1, "Size");
            
            this.size = size;
            return this;
        }
        
        @Override
        public SingleLine withColor(@NotNull Color color){
            this.color = String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
            return this;
        }
        
        @Override
        public SingleLine withColor(@NotNull String color){
            this.color = color;
            return this;
        }
        
        @Override
        public SingleLine withColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            
            this.color = String.format("%d,%d,%d", r, g, b);
            return this;
        }
        
        @Override
        public SingleLine withColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
            
            this.color = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
        
        @Override
        public SingleLine withFont(@NotNull String font){
            CheckUtil.notEmpty(font, "Font");
            
            this.font = font;
            return this;
        }
    
        @Override
        public SingleLine asBold(boolean bold){
            this.bold = bold;
            return this;
        }
    
        @Override
        public SingleLine supportUnicode(boolean unicode){
            this.unicode = unicode;
            return this;
        }
    
        @Override
        public SingleLine withOutline(int outline){
            CheckUtil.isPositive(outline, "Outline");
            
            this.outline = outline;
            return this;
        }
    
        @Override
        public SingleLine withOutlineColor(@NotNull String color){
            this.color = color;
            return this;
        }
    }
    
    public static class MultiLine extends Text{
        
        private String[] texts;
        
        public MultiLine(@NotNull String... lines){
            CheckUtil.noneEmpty("Line array", lines);
            
            this.texts = lines;
        }
        
        @Override
        public MultiLine withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
    
            this.posX = posX;
            return this;
        }
    
        @Override
        public MultiLine withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        @Override
        public MultiLine withSize(int size){
            CheckUtil.largerThan(size, 1, "Size");
    
            this.size = size;
            return this;
        }
    
        @Override
        public MultiLine withColor(@NotNull Color color){
            this.color = String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
            return this;
        }
    
        @Override
        public MultiLine withColor(@NotNull String color){
            this.color = color;
            return this;
        }
    
        @Override
        public MultiLine withColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
        
            this.color = String.format("%d,%d,%d", r, g, b);
            return this;
        }
    
        @Override
        public MultiLine withColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
        
            this.color = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
    
        @Override
        public MultiLine withFont(@NotNull String font){
            CheckUtil.notEmpty(font, "Font");
    
            this.font = font;
            return this;
        }
    
        @Override
        public MultiLine asBold(boolean bold){
            this.bold = bold;
            return this;
        }
    
        @Override
        public MultiLine supportUnicode(boolean unicode){
            this.unicode = unicode;
            return this;
        }
    
        @Override
        public MultiLine withOutline(int outline){
            CheckUtil.isPositive(outline, "Outline");
    
            this.outline = outline;
            return this;
        }
    
        @Override
        public MultiLine withOutlineColor(@NotNull String color){
            this.color = color;
            return this;
        }
    }
}
