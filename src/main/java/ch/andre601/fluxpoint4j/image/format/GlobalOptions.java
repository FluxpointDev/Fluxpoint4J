package ch.andre601.fluxpoint4j.image.format;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.util.ColorObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

/**
 * Class used to define default Options (Currently limited to text options) that should be used in case no matching option
 * is present in one of the provided {@link Image image} or {@link Text text} templates.
 */
public class GlobalOptions{
    
    // general properties
    private Integer textSize = null;
    private ColorObject textColor = null;
    private String textFont = null;
    
    // Text outline
    @SerializedName("textOutlineWidth")
    protected Integer outlineWidth = null;
    @SerializedName("textOutlineColor")
    protected ColorObject outlineColor = null;
    @SerializedName("textOutlineBlur")
    protected Integer outlineBlur = null;
    
    // Text alignment
    @SerializedName("textAlign")
    protected String textAlignment = null;
    
    // Text X position
    @SerializedName("textX")
    protected Integer posX = null;
    
    /**
     * Sets the default text size that should be used.
     * <br>Set to {@code null} (Default) to not set a default font size.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>TextSize is less than 1.</li>
     * </ul>
     * 
     * @param  textSize
     *         The font size to use by default.
     * 
     * @return Instance used, useful for chaining
     */
    public GlobalOptions withTextSize(@Nullable Integer textSize){
        if(textSize != null)
            CheckUtil.largerThan(textSize, 1, "TextSize");
        
        this.textSize = textSize;
        return this;
    }
    
    /**
     * Sets the default text color that should be used.
     * <br>Set to {@code null} (Default) to not set a default font color.
     * 
     * @param  textColor
     *         The text color to use by default.
     *
     * @return Instance used, useful for chaining
     */
    public GlobalOptions withTextColor(@Nullable ColorObject textColor){
        this.textColor = textColor;
        return this;
    }
    
    /**
     * Sets the default font to use.
     * <br>Set to {@code null} (Default) to not set a default font.
     * 
     * @param  textFont
     *         The font to use by default.
     *
     * @return Instance used, useful for chaining
     */
    public GlobalOptions withTextFont(@Nullable String textFont){
        this.textFont = textFont;
        return this;
    }
    
    /**
     * Sets the default outline width to use.
     * <br>Set to {@code null} (Default) to not set a default outline width.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>OutlineWidth is less than 0.</li>
     * </ul>
     * 
     * @param  outlineWidth
     *         The outline width to use by default.
     * 
     * @return The GlobalOptions instance. Useful for chaining.
     */
    public GlobalOptions withOutlineWidth(@Nullable Integer outlineWidth){
        if(outlineWidth != null)
            CheckUtil.isPositive(outlineWidth, "Outline Width");
        
        this.outlineWidth = outlineWidth;
        return this;
    }
    
    /**
     * Sets the default outline color to use.
     * <br>Set to {@code null} (Default) to not set a default outline color.
     * 
     * @param  color
     *         The outline color to use by default.
     *
     * @return The GlobalOptions instance. Useful for chaining.
     */
    public GlobalOptions withOutlineColor(@Nullable ColorObject color){
        this.outlineColor = color;
        return this;
    }
    
    /**
     * Sets the default outline blur to use.
     * <br>Set to {@code null} (Default) to not set a default outline blur.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>OutlineBlur is less than 0.</li>
     * </ul>
     * 
     * @param  outlineBlur
     *         The outline blur to use by default
     *
     * @return The GlobalOptions instance. Useful for chaining.
     */
    public GlobalOptions withOutlineBlur(@Nullable Integer outlineBlur){
        if(outlineBlur != null)
            CheckUtil.isPositive(outlineBlur, "Outline Blur");
        
        this.outlineBlur = outlineBlur;
        return this;
    }
    
    /**
     * Sets the default text alignment to use.
     * <br>Set to {@code null} (Default) to not set a default text alignment.
     * 
     * @param  textAlignment
     *         The text alignment to use by default.
     *
     * @return The GlobalOptions instance. Useful for chaining.
     */
    public GlobalOptions withTextAlignment(@Nullable Text.TextAlignment textAlignment){
        this.textAlignment = textAlignment == null ? null : textAlignment.getName();
        return this;
    }
    
    /**
     * Sets the default X position of the text.
     * <br>Set to {@code null} (Default) to not set a default X position.
     * 
     * @param  posX
     *         The X position to use by default.
     * 
     * @return The GlobalOptions instance. Useful for chaining.
     */
    public GlobalOptions withPosX(@Nullable Integer posX){
        this.posX = posX;
        return this;
    }
}
