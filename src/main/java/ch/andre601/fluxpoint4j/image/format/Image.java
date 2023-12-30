package ch.andre601.fluxpoint4j.image.format;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.util.ColorObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class used to hold common values found in any of the following subclasses:
 * 
 * <ul>
 *     <li>{@link Rectangle Square}</li>
 *     <li>{@link Image.ImageURL ImageURL}</li>
 *     <li>{@link Image.Circle Circle}</li>
 *     <li>{@link Image.Triangle Triangle}</li>
 *     <li>{@link Image.SVG SVG}</li>
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
    
    protected ColorObject color = null;
    
    @SerializedName("skip")
    protected boolean hidden = false;
    
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
     * Sets the image's hidden status.
     * <br>Should {@code true} be provided will the image be hidden and not shown in the final image.
     * 
     * @param  hidden
     *         Whether to show or hide the image. True hides it.
     * 
     * @return The Image class. Useful for chaining.
     */
    public abstract Image hide(boolean hidden);
    
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
            CheckUtil.notNull(color, "Color");
            
            this.color = color;
            return this;
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  hidden
         *         Whether to show or hide the image. True hides it.
         *
         * @return The Rectangle instance. Useful for chaining.
         */
        @Override
        public Rectangle hide(boolean hidden){
            this.hidden = hidden;
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
         * Sets the background color to use to fill any transparency in the image.
         * <br>Set this to {@code null} (Default) to not set any color.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        @Override
        public ImageURL withColor(@Nullable ColorObject color){
            this.color = color;
            return this;
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  hidden
         *         Whether to show or hide the image. True hides it.
         *
         * @return The ImageURL instance. Useful for chaining.
         */
        @Override
        public ImageURL hide(boolean hidden){
            this.hidden = hidden;
            return this;
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
         * {@inheritDoc}
         *
         * @param  hidden
         *         Whether to show or hide the image. True hides it.
         *
         * @return The Circle instance. Useful for chaining.
         */
        @Override
        public Circle hide(boolean hidden){
            this.hidden = hidden;
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
         * {@inheritDoc}
         *
         * @param  hidden
         *         Whether to show or hide the image. True hides it.
         *
         * @return The Triangle instance. Useful for chaining.
         */
        @Override
        public Triangle hide(boolean hidden){
            this.hidden = hidden;
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
     * Class used to create the JSON for an SVG image.
     * 
     * <p>Supported options:
     * <ul>
     *     <li>Set X position.</li>
     *     <li>Set Y position.</li>
     *     <li>Set color.</li>
     *     <li>Set SVG path content.</li>
     *     <li>Set Size.</li>
     * </ul>
     */
    public static class SVG extends Image{
        
        private String path = null;
        private int size = 0;
        
        public SVG(){
            this.type = "svg";
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  posX
         *         The positive, horizontal position of the image.
         *
         * @return The SVG instance. Useful for chaining.
         */
        @Override
        public SVG withPosX(int posX){
            this.posX = posX;
            return this;
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  posY
         *         The positive, vertical position of the image.
         *
         * @return The SVG instance. Useful for chaining.
         */
        @Override
        public SVG withPosY(int posY){
            this.posY = posY;
            return this;
        }
        
        /**
         * This method cannot be used in the SVG image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * <p>Use {@link #withSize(int) withSize(int)} instead.
         *
         * @return Nothing.
         */
        @Override
        public SVG withWidth(int width){
            throw new IllegalArgumentException("Cannot use withWidth in image type SVG.");
        }
        
        /**
         * This method cannot be used in the SVG image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * <p>Use {@link #withSize(int) withSize(int)} instead.
         *
         * @return Nothing.
         */
        @Override
        public SVG withHeight(int height){
            throw new IllegalArgumentException("Cannot use withHeight in image type SVG.");
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  color
         *         The {@link ColorObject ColorObject} to use.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Color is null.</li>
         * </ul>
         *
         * @return The SVG instance. Useful for chaining.
         */
        @Override
        public SVG withColor(@NotNull ColorObject color){
            CheckUtil.notNull(color, "Color");
            this.color = color;
            return this;
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  hidden
         *         Whether to show or hide the image. True hides it.
         *
         * @return The SVG instance. Useful for chaining.
         */
        @Override
        public SVG hide(boolean hidden){
            this.hidden = hidden;
            return this;
        }
        
        /**
         * Sets the SVG's {@code path} properties to use for displaying the SVG itself.
         * <br>The provided path should <b>only</b> be the String content of the {@code &lt;path&gt;} HTML object and
         * not include anything else.
         *
         * @param  path
         *         The path content to render the SVG from.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Path is null or empty.</li>
         * </ul>
         *
         * @return The SVG instance. Useful for chaining.
         */
        public SVG withPath(@NotNull String path){
            CheckUtil.notNullOrEmpty(path, "Path");
            this.path = path;
            return this;
        }
        
        /**
         * Sets the size (width and height) of the SVG to use.
         *
         * @param  size
         *         The positive size of the SVG in pixels.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Size is less than 0.</li>
         * </ul>
         *
         * @return The SVG instance. Useful for chaining.
         */
        public SVG withSize(int size){
            CheckUtil.largerThan(size, 0, "Size");
            
            this.size = size;
            return this;
        }
    }
    
    public static class Icon extends Image{
        
        private String icon = null;
        private int size = 0;
        
        public Icon(){
            this.type = "icon";
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  posX
         *         The positive, horizontal position of the image.
         *
         * @return The Icon instance. Useful for chaining.
         */
        @Override
        public Icon withPosX(int posX){
            this.posX = posX;
            return this;
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  posY
         *         The positive, vertical position of the image.
         *
         * @return The Icon instance. Useful for chaining.
         */
        @Override
        public Icon withPosY(int posY){
            this.posY = posY;
            return this;
        }
        
        /**
         * This method cannot be used in the Icon image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * <p>Use {@link #withSize(int) withSize(int)} instead.
         *
         * @return Nothing.
         */
        @Override
        public Icon withWidth(int width){
            throw new IllegalArgumentException("Cannot use withWidth in image type SVG.");
        }
        
        /**
         * This method cannot be used in the Icon image type.
         * <br>Throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} when called.
         *
         * <p>Use {@link #withSize(int) withSize(int)} instead.
         *
         * @return Nothing.
         */
        @Override
        public Icon withHeight(int height){
            throw new IllegalArgumentException("Cannot use withHeight in image type SVG.");
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  color
         *         The {@link ColorObject ColorObject} to use.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Color is null.</li>
         * </ul>
         *
         * @return The Icon instance. Useful for chaining.
         */
        @Override
        public Icon withColor(@NotNull ColorObject color){
            CheckUtil.notNull(color, "Color");
            
            this.color = color;
            return this;
        }
        
        /**
         * {@inheritDoc}
         *
         * @param  hidden
         *         Whether to show or hide the image. True hides it.
         *
         * @return The Icon instance. Useful for chaining.
         */
        @Override
        public Icon hide(boolean hidden){
            this.hidden = hidden;
            return this;
        }
        
        /**
         * Sets the icon that should be used for the image.
         * <br>The provided String can be any valid identifier and name available in <a href="https://iconify.design" target="_blank">iconify.design</a>.
         * 
         * <p>Provided icon always has to be in the format {@code &lt;icon-set&gt;:&lt;icon&gt;}.
         * <br>Example: {@code octicons:alert-24} for the <a href="https://primer.style/foundations/icons/alert-24" target="_blank">24px version of Octicons Alert SVG</a>.
         * 
         * <p>A complete list of all available Icon sets can be found <a href="https://icon-sets.iconify.design/" target="_blank">here</a>.
         * 
         * @param  icon
         *         The icon to use. Needs to be in the format {@code &lt;icon-set&gt;:&lt;icon&gt;}.
         * 
         * @return The Icon instance. Useful for chaining.
         */
        public Icon withIcon(@NotNull String icon){
            CheckUtil.notNullOrEmpty(icon, "Icon");
            
            this.icon = icon;
            return this;
        }
        
        /**
         * Sets the size (width and height) of the Icon to use.
         *
         * @param  size
         *         The positive size of the Icon in pixels.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
         * <ul>
         *     <li>Size is less than 0.</li>
         * </ul>
         *
         * @return The Icon instance. Useful for chaining.
         */
        public Icon withSize(int size){
            CheckUtil.largerThan(size, 0, "Size");
            
            this.size = size;
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
