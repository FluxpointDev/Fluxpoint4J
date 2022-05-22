package ch.andre601.fluxpoint4j.image.format;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.util.ColorObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

/**
 * Base class used for adding {@link Text.SingleLine single} or {@link MultiLine multi-line} text to your
 * {@link ch.andre601.fluxpoint4j.image.CustomImage custom image}.
 */
public abstract class Text{
    
    // Text position and alignment
    @SerializedName("x")
    protected int posX = 0;
    @SerializedName("y")
    protected int posY = 0;
    @SerializedName("align")
    protected String textAlignment = TextAlignment.LEFT.getName();
    
    // Text size and look
    protected int size = 1;
    protected String font = "Sans Serif";
    protected ColorObject color = ColorObject.getFromRGB(0, 0, 0);
    @SerializedName("back")
    protected ColorObject backgroundColor = ColorObject.getFromRGBA(0, 0, 0, 0);
    
    // Styling
    protected boolean bold = false;
    protected boolean italic = false;
    protected boolean underline = false;
    protected int weight = 500;
    
    // Limits
    @SerializedName("width")
    protected int maxWidth = 0;
    @SerializedName("height")
    protected int maxHeight = 0;
    
    // Outline
    protected boolean outline = false;
    @SerializedName("outlinewidth")
    protected int outlineWidth = 5;
    @SerializedName("outlinecolor")
    protected String outlineColor = "white";
    @SerializedName("outlineblur")
    protected int outlineBlur = 1;
    
    /**
     * Sets the relative X (horizontal) position of the text, where 0 is the very left of the image.
     * <br>The value can be 0 to {@link java.lang.Integer#MAX_VALUE Integer.MAX_VALUE}.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>PosX is less than 0.</li>
     * </ul>
     * 
     * @param  posX
     *         The horizontal position of the text, relative to the image itself.
     * 
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withPosX(int posX);
    
    /**
     * Sets the relative Y (vertical) position of the text, where 0 is the very top of the image.
     * <br>The value can be 0 to {@link java.lang.Integer#MAX_VALUE Integer.MAX_VALUE}.
     * 
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>PosX is less than 0.</li>
     * </ul>
     * 
     * @param  posY
     *         The vertical position of the text, relative to the image itself.
     * 
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withPosY(int posY);
    
    public abstract Text withTextAlignment(@NotNull TextAlignment textAlignment);
    
    /**
     * Sets the text size to use.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>Size is 0 or less.</li>
     * </ul>
     * 
     * @param  size
     *         The size of the text.
     * 
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withSize(int size);
    
    /**
     * Sets the font to use for the text.
     * <br>Whether the provided font is valid and/or usable by the API depends on the Fluxpoint API itself and is nothing
     * that can be checked in this library.
     *
     * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following case:
     * <ul>
     *     <li>Font is an empty String.</li>
     * </ul>
     *
     * @param  font
     *         The font to use.
     *
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withFont(@NotNull String font);
    
    /**
     * Sets the Text color to use.
     * <br>Please see the documentation of the {@link ColorObject ColorObject} for possible errors.
     * 
     * @param  color
     *         The Color to use for the Text.
     * 
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withColor(@NotNull ColorObject color);
    
    public abstract Text withBackgroundColor(@NotNull ColorObject backgroundColor);
    
    /**
     * Sets whether the text should be <b>bold</b> or not.
     * 
     * @param  bold
     *         Boolean to set if the text should be bold or not.
     * 
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text asBold(boolean bold);
    
    public abstract Text asItalic(boolean italic);
    
    public abstract Text asUnderline(boolean underline);
    
    public abstract Text withWeight(int weight);
    
    public abstract Text withMaxWidth(int maxWidth);
    
    public abstract Text withMaxHeight(int maxHeight);
    
    /**
     * Sets whether the text should have an outline or not.
     *
     * @param  outline
     *         Boolean to set if the text should have an outline.
     *
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withOutline(boolean outline);
    
    public abstract Text withOutlineWidth(int outlineWidth);
    
    /**
     * Sets the outline color to use.
     * <br>Please see the documentation of the {@link ColorObject ColorObject} for possible errors.
     *
     * <p>When {@link #withOutline(boolean) withOutline(boolean)} is set to {@code false} (default) will this option
     * have no effect.
     *
     * @param  color
     *         The color to use for the outline.
     *
     * @return The Text instance. Useful for chaining.
     */
    public abstract Text withOutlineColor(@NotNull ColorObject color);
    
    public abstract Text withOutlineBlur(int outlineBlur);
    
    /**
     * Represents a single text line to add.
     * <br>If you would like to add multiple lines, consider using {@link MultiLine MultiLine} instead.
     */
    public static class SingleLine extends Text{
        
        private final String text;
    
        /**
         * Creates a new SingleLine instance to use with the provided String as the displayed text value.
         * 
         * @param line
         *        The text to display.
         */
        public SingleLine(@NotNull String line){
            CheckUtil.notEmpty(line, "Text");
            
            this.text = line;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posX
         *         The horizontal position of the text, relative to the image itself.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
            
            this.posX = posX;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  posY
         *         The vertical position of the text, relative to the image itself.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
        
        @Override
        public SingleLine withTextAlignment(@NotNull TextAlignment textAlignment){
            this.textAlignment = textAlignment.getName();
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  size
         *         The size of the text.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withSize(int size){
            CheckUtil.largerThan(size, 1, "Size");
            
            this.size = size;
            return this;
        }
    
        /**
         * {@inheritDoc}
         *
         * @param  font
         *         The font to use.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withFont(@NotNull String font){
            CheckUtil.notEmpty(font, "Font");
        
            this.font = font;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The Color to use for the Text.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withColor(@NotNull ColorObject color){
            this.color = color;
            return this;
        }
        
        @Override
        public SingleLine withBackgroundColor(@NotNull ColorObject backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  bold
         *         Boolean to set if the text should be bold or not.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine asBold(boolean bold){
            this.bold = bold;
            return this;
        }
        
        @Override
        public SingleLine asItalic(boolean italic){
            this.italic = italic;
            return this;
        }
        
        @Override
        public SingleLine asUnderline(boolean underline){
            this.underline = underline;
            return this;
        }
    
        @Override
        public SingleLine withWeight(int weight){
            this.weight = weight;
            return this;
        }
        
        @Override
        public SingleLine withMaxWidth(int maxWidth){
            CheckUtil.isPositive(maxWidth, "MaxWidth");
            
            this.maxWidth = maxWidth;
            return this;
        }
        
        @Override
        public SingleLine withMaxHeight(int maxHeight){
            CheckUtil.isPositive(maxHeight, "MaxHeight");
            
            this.maxHeight = maxHeight;
            return this;
        }
    
        /**
         * {@inheritDoc}
         * 
         * @param  outline
         *         Boolean to set if the text should have an outline.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withOutline(boolean outline){
            this.outline = outline;
            return this;
        }
    
        @Override
        public SingleLine withOutlineWidth(int outlineWidth){
            CheckUtil.isPositive(outlineWidth, "OutlineWidth");
            
            this.outlineWidth = outlineWidth;
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The color to use for the outline.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public SingleLine withOutlineColor(@NotNull ColorObject color){
            this.color = color;
            return this;
        }
    
        @Override
        public SingleLine withOutlineBlur(int outlineBlur){
            CheckUtil.isPositive(outlineBlur, "OutlineBlur");
            
            this.outlineBlur = outlineBlur;
            return this;
        }
    }
    
    /**
     * Represents multiple lines to add.
     * <br>Each new String entry in the constructor adds a line break in the final image.
     */
    public static class MultiLine extends Text{
        
        private String[] texts;
        @SerializedName("line")
        private double lineSpacing = 1;
        
        /**
         * Creates a new MultiLine instance with each String being set on a new line in the final image.
         * 
         * @param lines
         *        The text lines to add.
         */
        public MultiLine(@NotNull String... lines){
            CheckUtil.noneEmpty("Line array", lines);
            
            this.texts = lines;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  posX
         *         The horizontal position of the text, relative to the image itself.
         *
         * @return The MultiLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withPosX(int posX){
            CheckUtil.isPositive(posX, "PosX");
    
            this.posX = posX;
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  posY
         *         The vertical position of the text, relative to the image itself.
         *
         * @return The MultiLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withPosY(int posY){
            CheckUtil.isPositive(posY, "PosY");
            
            this.posY = posY;
            return this;
        }
    
        @Override
        public MultiLine withTextAlignment(@NotNull TextAlignment textAlignment){
            this.textAlignment = textAlignment.getName();
            return this;
        }
    
        /**
         * {@inheritDoc}
         *
         * @param  size
         *         The size of the text.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withSize(int size){
            CheckUtil.largerThan(size, 1, "Size");
        
            this.size = size;
            return this;
        }
    
        /**
         * {@inheritDoc}
         *
         * @param  font
         *         The font to use.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withFont(@NotNull String font){
            CheckUtil.notEmpty(font, "Font");
        
            this.font = font;
            return this;
        }
    
        /**
         * {@inheritDoc}
         *
         * @param  color
         *         The Color to use for the Text.
         *
         * @return The SingleLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withColor(@NotNull ColorObject color){
            this.color = color;
            return this;
        }
    
        @Override
        public MultiLine withBackgroundColor(@NotNull ColorObject backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  bold
         *         Boolean to set if the text should be bold or not.
         *
         * @return The MultiLine instance. Useful for chaining.
         */
        @Override
        public MultiLine asBold(boolean bold){
            this.bold = bold;
            return this;
        }
    
        @Override
        public MultiLine asItalic(boolean italic){
            this.italic = italic;
            return this;
        }
    
        @Override
        public MultiLine asUnderline(boolean underline){
            this.underline = underline;
            return this;
        }
    
        @Override
        public MultiLine withWeight(int weight){
            this.weight = weight;
            return this;
        }
    
        @Override
        public MultiLine withMaxWidth(int maxWidth){
            CheckUtil.isPositive(maxWidth, "MaxWidth");
        
            this.maxWidth = maxWidth;
            return this;
        }
    
        @Override
        public MultiLine withMaxHeight(int maxHeight){
            CheckUtil.isPositive(maxHeight, "MaxHeight");
        
            this.maxHeight = maxHeight;
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  outline
         *         Boolean to set if the text should have an outline.
         *
         * @return The MultiLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withOutline(boolean outline){
            this.outline = outline;
            return this;
        }
    
        @Override
        public MultiLine withOutlineWidth(int outlineWidth){
            CheckUtil.isPositive(outlineWidth, "OutlineWidth");
        
            this.outlineWidth = outlineWidth;
            return this;
        }
        
        /**
         * {@inheritDoc}
         * 
         * @param  color
         *         The color to use for the outline.
         *
         * @return The MultiLine instance. Useful for chaining.
         */
        @Override
        public MultiLine withOutlineColor(@NotNull ColorObject color){
            this.color = color;
            return this;
        }
    
        @Override
        public MultiLine withOutlineBlur(int outlineBlur){
            CheckUtil.isPositive(outlineBlur, "OutlineBlur");
        
            this.outlineBlur = outlineBlur;
            return this;
        }
        
        public MultiLine withLineSpacing(double lineSpacing){
            CheckUtil.isPositive(lineSpacing, "LineSpacing");
            
            this.lineSpacing = lineSpacing;
            return this;
        }
    }
    
    public enum TextAlignment{
        LEFT("l"),
        MIDDLE("m"),
        RIGHT("r");
    
        private final String name;
    
        TextAlignment(String name){
            this.name = name;
        }
    
        public String getName(){
            return name;
        }
    }
}
