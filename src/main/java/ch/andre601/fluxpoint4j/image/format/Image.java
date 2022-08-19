package ch.andre601.fluxpoint4j.image.format;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.util.ColorObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

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
 * Note that not all values may get used. Calling specific methods (i.e. {@link #withColor(ColorObject) withColor} on
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
    
    protected ColorObject color = ColorObject.getFromRGB(0, 255, 255);
    
    /**
     * Sets the position on the X (horizontal) axis for the image.
     * <br>The value can be both negative and positive.
     * 
     * @param  posX
     *         The positive, horizontal position of the image.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withPosX(int posX);
    
    /**
     * Sets the position on the Y (vertical) axis for the image.
     * <br>The value can be both negative and positive.
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
     *     <li>Width is less than 1 or larger than 3000.</li>
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
     *     <li>Height is less than 1 or larger than 3000.</li>
     * </ul>
     * 
     * @param  height
     *         The height of the image.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withHeight(int height);
    
    /**
     * Sets the color to use for the image.
     * <br>Please see the documentation of the {@link ColorObject ColorObject} for possible errors and limitations.
     * 
     * @param  color
     *         The {@link ColorObject ColorObject} to use.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image withColor(@NotNull ColorObject color);
    
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
            CheckUtil.inRange(width, 1, 3000, "Width");
            
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
            CheckUtil.inRange(height, 1, 3000, "Height");
            
            this.height = height;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The {@link ColorObject ColorObject} to use.
         *
         * @return The Rectangle instance. Useful for chaining.
         */
        @Override
        public Rectangle withColor(@NotNull ColorObject color){
            this.color = color;
            return this;
        }
    
        /**
         * Sets the round for the corners of the Rectangle.
         * 
         * @param  round
         *         How round the corners should be
         * 
         * @return This Rectangle instance. Useful for chaining.
         */
        public Rectangle withRound(int round){
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
        private boolean cache = false;
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
            CheckUtil.inRange(width, 1, 3000, "Width");
            
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
            CheckUtil.inRange(height, 1, 3000, "Height");
            
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
        public ImageURL withColor(@NotNull ColorObject color){
            throw new IllegalArgumentException("Cannot use withColor in image type ImageURL.");
        }
    
        /**
         * Sets the URL to get the image from.
         * 
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Url is an empty String.</li>
         * </ul>
         * 
         * @param  url
         *         The URL to get the image from.
         * 
         * @return The ImageURL instance. Useful for chaining.
         */
        public ImageURL withUrl(@NotNull String url){
            CheckUtil.notEmpty(url, "URL");
            
            this.url = url;
            return this;
        }
    
        /**
         * Sets whether the Fluxpoint API should cache the received image or not.
         * 
         * @param  cache
         *         Boolean to set if the Fluxpoint API should cache the received image.
         * 
         * @return The ImageURL instance. Useful for chaining.
         */
        public ImageURL withCaching(boolean cache){
            this.cache = cache;
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
         *         The {@link ColorObject ColorObject} to use.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle withColor(@NotNull ColorObject color){
            this.color = color;
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
            this.posY = posY;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  width
         *         The width of the image.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withWidth(int width){
            CheckUtil.isPositive(width, "Width");
            
            this.width = width;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  height
         *         The height of the image.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withHeight(int height){
            CheckUtil.isPositive(height, "Height");
            
            this.height = height;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The {@link ColorObject ColorObject} to use.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle withColor(@NotNull ColorObject color){
            this.color = color;
            return this;
        }
    
        /**
         * Sets where the missing piece of the triangle should be displayed.
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
    
    /**
     * Defines the missing part of the {@link Triangle Triangle shape}.
     * <br>As an example, using {@link Triangle#withCut(Cut) withCut(TOP_LEFT)} would result in a triangle that has a
     * 90° corner on the bottom-right with a diagonal on the top-left side.
     */
    public enum Cut{
        /**
         * Triangle with the diagonal being on the top-left.
         */
        TOP_LEFT("TopLeft"),
        /**
         * Triangle with the diagonal being on the top-right.
         */
        TOP_RIGHT("TopRight"),
        /**
         * Triangle with the diagonal being on the bottom-left.
         */
        BOTTOM_LEFT("BottomLeft"),
        /**
         * Triangle with the diagonal being on the bottom-right.
         */
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
