package ch.andre601.fluxpoint4j.util;

import ch.andre601.fluxpoint4j.CheckUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Simple class to allow having a unified format for colors in the Fluxpoint API.
 */
public class ColorObject{
    
    private final String color;
    
    private ColorObject(String color){
        this.color = color;
    }
    
    /**
     * Creates a new ColorObject instance using the provided {@link java.awt.Color Color object}.
     * 
     * @param  color
     *         The color to use for this ColorObject instance.
     * 
     * @return New ColorObject instance containing the color values.
     */
    public static ColorObject getFromColor(@NotNull Color color){
        return new ColorObject(String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue()));
    }
    
    /**
     * Creates a new ColorObject instance using the provided String.
     * 
     * <p>You can use the following formats for the String:
     * <ul>
     *     <li>{@code r,g,b} for RGB color values ranging from 0 to 255 each.</li>
     *     <li>{@code r,g,b,a} for RGBA color values ranging from 0 to 255 each.</li>
     *     <li>{@code #rrggbb} for hexadecimal color values.</li>
     *     <li>Any <a target="_blank" href="https://www.htmlcsscolor.com/web-safe-colors">valid HTML/CSS color name</a>.</li>
     * </ul>
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>Color is an empty String.</li>
     * </ul>
     * 
     * @param  color
     *         The color to use for this ColorObject instance.
     * 
     * @return New ColorObject instance containing the color values.
     */
    public static ColorObject getFromString(@NotNull String color){
        CheckUtil.notEmpty(color, "Color");
        
        return new ColorObject(color);
    }
    
    /**
     * Creates a new ColorObject instance using the provided r, g and b values.
     * 
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in any of the following cases:
     * <ul>
     *     <li>R is less than 0 or larger than 255.</li>
     *     <li>G is less than 0 or larger than 255.</li>
     *     <li>B is less than 0 or larger than 255.</li>
     * </ul>
     * 
     * @param  r
     *         Red color value to use.
     * @param  g
     *         Green color value to use.
     * @param  b
     *         Blue color value to use.
     * 
     * @return New ColorObject instance containing the color values.
     */
    public static ColorObject getFromRGB(int r, int g, int b){
        CheckUtil.inRange(r, 0, 255, "Red value");
        CheckUtil.inRange(g, 0, 255, "Green value");
        CheckUtil.inRange(b, 0, 255, "Blue value");
        
        return new ColorObject(String.format("%d, %d, %d", r, g, b));
    }
    
    /**
     * Creates a new ColorObject instance using the provided r, g, b and a values.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in any of the following cases:
     * <ul>
     *     <li>R is less than 0 or larger than 255.</li>
     *     <li>G is less than 0 or larger than 255.</li>
     *     <li>B is less than 0 or larger than 255.</li>
     *     <li>A is less than 0 or larger than 255.</li>
     * </ul>
     *
     * @param  r
     *         Red color value to use.
     * @param  g
     *         Green color value to use.
     * @param  b
     *         Blue color value to use.
     * @param  a
     *         Alpha value to use.
     * 
     * @return New ColorObject instance containing the color values.
     */
    public static ColorObject getFromRGBA(int r, int g, int b, int a){
        CheckUtil.inRange(r, 0, 255, "Red value");
        CheckUtil.inRange(g, 0, 255, "Green value");
        CheckUtil.inRange(b, 0, 255, "Blue value");
        CheckUtil.inRange(a, 0, 255, "Alpha value");
        
        return new ColorObject(String.format("%d,%d,%d,%d", r, g, b, a));
    }
    
    /**
     * Returns the String holding the color value of this ColorObject instance.
     * 
     * <p>When using {@link #getFromColor(Color) getFromColor(Color)} or
     * {@link #getFromRGB(int, int, int) getFromRGB(int, int, int)} will the returned String always be in the format
     * {@code r,g,b}. When using {@link #getFromRGBA(int, int, int, int) getFromRGBA(int, int, int, int)} will the
     * returned String always be in the format {@code r,g,b,a}.
     * <br>In any other case is the returned String not guaranteed to follow any particular format.
     * 
     * @return The String created when using any of the {@code getFromX} methods.
     */
    public String getColor(){
        return color;
    }
    
    @Override
    public String toString(){
        return getClass().getName() + "[color=" + color + "]";
    }
    
}
