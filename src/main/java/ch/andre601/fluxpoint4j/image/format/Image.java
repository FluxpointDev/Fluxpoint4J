package ch.andre601.fluxpoint4j.image.format;

import ch.andre601.fluxpoint4j.CheckUtil;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Base class used to hold common values found in any of the following subclasses:
 * 
 * <ul>
 *     <li>{@link Rectangle Square}</li>
 *     <li>{@link Image.ImageURL ImageURL}</li>
 *     <li>{@link Image.Circle Circle}</li>
 *     <li>{@link Image.Triangle Triangle}</li>
 * </ul>
 * 
 * Note that not all values may get used. Calling specific methods (i.e. {@link #withColor(String) withColor} on
 * {@link Image.ImageURL ImageURL}) can throw an {@link IllegalArgumentException}.
 */
public abstract class Image{
    
    protected String type = "bitmap";
    
    @SerializedName("x")
    protected int posX = 0;
    @SerializedName("y")
    protected int posY = 0;
    
    protected int width = 1;
    protected int height = 1;
    
    protected String color = String.format("%d,%d,%d", Color.CYAN.getRed(), Color.CYAN.getGreen(), Color.CYAN.getBlue());
    
    /**
     * Sets the position on the X (horizontal) axis for the image.
     * <br>Can be any value between 0 and {@link java.lang.Integer#MAX_VALUE Integer.MAX_VALUE}, but not negative.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>PosX is less than 0.</li>
     * </ul>
     * 
     * @param  posX
     *         The positive, horizontal position of the image.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withPosX(int posX);
    
    /**
     * Sets the position on the Y (vertical) axis for the image.
     * <br>Can be any value between 0 and {@link java.lang.Integer#MAX_VALUE Integer.MAX_VALUE}, but not negative.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>PosY is less than 0.</li>
     * </ul>
     *
     * @param  posY
     *         The positive, vertical position of the image.
     *
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withPosY(int posY);
    
    /**
     * Sets the width of the Image.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>Width is less than 1.</li>
     * </ul>
     * 
     * @param  width
     *         The width of the image.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withWidth(int width);
    
    /**
     * Sets the height of the Image.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>Height is less than 1.</li>
     * </ul>
     * 
     * @param  height
     *         The height of the image.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withHeight(int height);
    
    /**
     * Sets the color used to fill the image with.
     * 
     * @param  color
     *         {@link java.awt.Color Color instance} to set.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withColor(@NotNull Color color);
    
    /**
     * Sets the color used to fill the image with.
     * 
     * <p>Make sure the provided String is any of the following patterns:
     * <ul>
     *     <li>{@code r,g,b} for RGB values between 0-255 each.</li>
     *     <li>{@code r,g,b,a} for RGBA values between 0-255 each.</li>
     *     <li>{@code #rrggbb} for HEX color value.</li>
     *     <li>Valid <a target="_blank" href="https://www.htmlcsscolor.com/web-safe-colors">HTML color name</a>.</li>
     * </ul>
     * <b>The library will not try to validate your value at all!</b>
     * 
     * @param  color
     *         The String to use as the color value.
     *
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withColor(@NotNull String color);
    
    /**
     * Sets the color used to fill the image with using the provided RGB values.
     * 
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
     * <ul>
     *     <li>R is less than 0 or larger than 255.</li>
     *     <li>G is less than 0 or larger than 255.</li>
     *     <li>B is less than 0 or larger than 255.</li>
     * </ul>
     * 
     * @param  r
     *         The Red value to use. Has to be between 0 and 255.
     * @param  g
     *         The Green value to use. Has to be between 0 and 255.
     * @param  b
     *         The Blue value to use. Has to be between 0 and 255.
     *
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withColor(int r, int g, int b);
    
    /**
     * Sets the color used to fill the image with using the provided RGB values.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
     * <ul>
     *     <li>R is less than 0 or larger than 255.</li>
     *     <li>G is less than 0 or larger than 255.</li>
     *     <li>B is less than 0 or larger than 255.</li>
     *     <li>A is less than 0 or larger than 255.</li>
     * </ul>
     *
     * @param  r
     *         The Red value to use. Has to be between 0 and 255.
     * @param  g
     *         The Green value to use. Has to be between 0 and 255.
     * @param  b
     *         The Blue value to use. Has to be between 0 and 255.
     * @param  a
     *         The Alpha value to use. Has to be between 0 and 255.
     *
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withColor(int r, int g, int b, int a);
    
    /**
     * Class used to create the JSON for a Rectangle-shaped image (Referred to as "bitmap" in the fluxpoint API).
     * 
     * <p>Supported options:
     * <ul>
     *     <li>Set X position.</li>
     *     <li>Set Y position.</li>
     *     <li>Set width.</li>
     *     <li>Set height.</li>
     *     <li>Set color.</li>
     *     <li>Set rounding of corners.</li>
     * </ul>
     */
    public static class Rectangle extends Image{
        
        private int round = 0;
        
        public Rectangle(){
            this.type = "bitmap";
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posX
         *         The positive, horizontal position of the image.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
            
            this.posX = posX;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posY
         *         The positive, vertical position of the image.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  width
         *         The width of the image.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withWidth(int width){
            CheckUtil.largerThan(width, 1, "Width");
            
            this.width = width;
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  height
         *         The height of the image.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withHeight(int height){
            CheckUtil.largerThan(height, 1, "Height");
            
            this.height = height;
            return this;
        }
    
        /**
         * {@inheritDoc}
         *
         * @param  color
         *         {@link java.awt.Color Color instance} to set.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withColor(@NotNull Color color){
            this.color = String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
            return this;
        }
    
        /**
         * {@inheritDoc}
         *
         * @param  color
         *         The String to use as the color value.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withColor(@NotNull String color){
            this.color = color;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  r
         *         The Red value to use. Has to be between 0 and 255.
         * @param  g
         *         The Green value to use. Has to be between 0 and 255.
         * @param  b
         *         The Blue value to use. Has to be between 0 and 255.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            
            this.color = String.format("%d,%d,%d", r, g, b);
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  r
         *         The Red value to use. Has to be between 0 and 255.
         * @param  g
         *         The Green value to use. Has to be between 0 and 255.
         * @param  b
         *         The Blue value to use. Has to be between 0 and 255.
         * @param  a
         *         The Alpha value to use. Has to be between 0 and 255.
         *
         * @return The Square instance. Useful for chaining.
         */
        @Override
        public Rectangle withColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
            
            this.color = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
    
        /**
         * Set an optional rounding of the Square corners.
         * 
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Round is less than 0.</li>
         * </ul>
         * 
         * @param  round
         *         The strength of rounding the corner.
         * 
         * @return The Square instance. Useful for chaining.
         */
        public Rectangle withRound(int round){
            CheckUtil.isPositive(round, "Round");
            
            this.round = round;
            return this;
        }
    }
    
    /**
     * Class used to create the JSON for an image sourced from a URL.
     *
     * <p>Supported options:
     * <ul>
     *     <li>Set X position.</li>
     *     <li>Set Y position.</li>
     *     <li>Set width.</li>
     *     <li>Set height.</li>
     *     <li>Set rounding of corners.</li>
     *     <li>Set URL from the Source image (Required)</li>
     * </ul>
     */
    public static class ImageURL extends Image{
        
        private String url = null;
        private int round = 0;
        
        public ImageURL(){
            this.type = "url";
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posX
         *         The positive, horizontal position of the image.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        @Override
        public ImageURL withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
            
            this.posX = posX;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posY
         *         The positive, vertical position of the image.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        @Override
        public ImageURL withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  width
         *         The width of the image.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        @Override
        public ImageURL withWidth(int width){
            CheckUtil.largerThan(width, 1, "Width");
            
            this.width = width;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  height
         *         The height of the image.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        @Override
        public ImageURL withHeight(int height){
            CheckUtil.largerThan(height, 1, "Height");
            
            this.height = height;
            return this;
        }
    
        /**
         * This method cannot be used in the ImageURL image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * @return Nothing.
         */
        @Override
        public ImageURL withColor(@NotNull Color color){
            throw new IllegalArgumentException("Cannot use withColor in image type ImageURL.");
        }
        
        /**
         * This method cannot be used in the ImageURL image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * @return Nothing.
         */
        @Override
        public ImageURL withColor(@NotNull String color){
            throw new IllegalArgumentException("Cannot use withColor in image type ImageURL.");
        }
    
        /**
         * This method cannot be used in the ImageURL image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * @return Nothing.
         */
        @Override
        public ImageURL withColor(int r, int g, int b){
            throw new IllegalArgumentException("Cannot use withColor in image type ImageURL.");
        }
    
        /**
         * This method cannot be used in the ImageURL image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * @return Nothing.
         */
        @Override
        public ImageURL withColor(int r, int g, int b, int a){
            throw new IllegalArgumentException("Cannot use withColor in image type ImageURL.");
        }
    
        /**
         * Set an optional rounding of the Square corners.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Round is less than 0.</li>
         * </ul>
         *
         * @param  round
         *         The strength of rounding the corner.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        public ImageURL withRound(int round){
            CheckUtil.isPositive(round, "Round");
            
            this.round = round;
            return this;
        }
    }
    
    /**
     * Class used to create the JSON for a Circle-shaped image.
     *
     * <p>Supported options:
     * <ul>
     *     <li>Set X position.</li>
     *     <li>Set Y position.</li>
     *     <li>Set color.</li>
     *     <li>Set radius.</li>
     * </ul>
     */
    public static class Circle extends Image{
        
        private int radius = 0;
        
        public Circle(){
            this.type = "circle";
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posX
         *         The positive, horizontal position of the image.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
            
            this.posX = posX;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posY
         *         The positive, vertical position of the image.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        /**
         * This method cannot be used in the Circle image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         * 
         * <p>Use {@link #withRadius(int) withRadius(int)} instead.
         * 
         * @return Nothing.
         */
        @Override
        public Circle withWidth(int width){
            throw new IllegalArgumentException("Cannot use withWidth in image type Circle.");
        }
        
        /**
         * This method cannot be used in the Circle image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * <p>Use {@link #withRadius(int) withRadius(int)} instead.
         *
         * @return Nothing.
         */
        @Override
        public Circle withHeight(int height){
            throw new IllegalArgumentException("Cannot use withHeight in image type Circle.");
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         {@link java.awt.Color Color instance} to set.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withColor(@NotNull Color color){
            this.color = String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The String to use as the color value.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withColor(@NotNull String color){
            this.color = color;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  r
         *         The Red value to use. Has to be between 0 and 255.
         * @param  g
         *         The Green value to use. Has to be between 0 and 255.
         * @param  b
         *         The Blue value to use. Has to be between 0 and 255.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            
            this.color = String.format("%d,%d,%d", r, g, b);
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  r
         *         The Red value to use. Has to be between 0 and 255.
         * @param  g
         *         The Green value to use. Has to be between 0 and 255.
         * @param  b
         *         The Blue value to use. Has to be between 0 and 255.
         * @param  a
         *         The Alpha value to use. Has to be between 0 and 255.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
            
            this.color = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
    
        /**
         * Sets the radius the circle should have.
         * 
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Radius is less than 1.</li>
         * </ul>
         * 
         * @param  radius
         *         The radius to use for the circle.
         * 
         * @return The Circle instance. Useful for chaining.
         */
        public Circle withRadius(int radius){
            CheckUtil.largerThan(radius, 1, "Radius");
            
            this.radius = radius;
            return this;
        }
    }
    
    /**
     * Class used to create the JSON for a Triangle-shaped image.
     *
     * <p>Supported options:
     * <ul>
     *     <li>Set X position.</li>
     *     <li>Set Y position.</li>
     *     <li>Set color.</li>
     *     <li>Set cut position.</li>
     * </ul>
     */
    public static class Triangle extends Image{
        
        private String cut = Cut.TOP_LEFT.getName();
        
        public Triangle(){
            this.type = "triangle";
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posX
         *         The positive, horizontal position of the image.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
            
            this.posX = posX;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posY
         *         The positive, vertical position of the image.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        /**
         * This method cannot be used in the Circle image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * @return Nothing.
         */
        @Override
        public Triangle withWidth(int width){
            throw new IllegalArgumentException("Cannot use withWidth in image type Triangle.");
        }
    
        /**
         * This method cannot be used in the Circle image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * @return Nothing.
         */
        @Override
        public Triangle withHeight(int height){
            throw new IllegalArgumentException("Cannot use withHeight in image type Triangle.");
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         {@link java.awt.Color Color instance} to set.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withColor(@NotNull Color color){
            this.color = String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The String to use as the color value.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withColor(@NotNull String color){
            this.color = color;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  r
         *         The Red value to use. Has to be between 0 and 255.
         * @param  g
         *         The Green value to use. Has to be between 0 and 255.
         * @param  b
         *         The Blue value to use. Has to be between 0 and 255.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withColor(int r, int g, int b){
            this.color = String.format("%d,%d,%d", r, g, b);
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  r
         *         The Red value to use. Has to be between 0 and 255.
         * @param  g
         *         The Green value to use. Has to be between 0 and 255.
         * @param  b
         *         The Blue value to use. Has to be between 0 and 255.
         * @param  a
         *         The Alpha value to use. Has to be between 0 and 255.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withColor(int r, int g, int b, int a){
            this.color = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
    
        /**
         * Sets the {@link Cut cut position} for the triangle
         * 
         * @param  cut
         *         The {@link Cut cut position} to use.
         * 
         * @return The Triangle instance. Useful for chaining.
         */
        public Triangle withCut(@NotNull Cut cut){
            this.cut = cut.getName();
            return this;
        }
    }
    
    public enum Cut{
        TOP_LEFT("TopLeft"),
        TOP_RIGHT("TopRight"),
        BOTTOM_LEFT("BottomLeft"),
        BOTTOM_RIGHT("BottomRight");
        
        private final String name;
        
        Cut(String name){
            this.name = name;
        }
    
        public String getName(){
            return name;
        }
    }
}
