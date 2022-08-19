package ch.andre601.fluxpoint4j.welcome;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.util.ColorObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

/**
 * Class used to generate the JSON to get a welcome image from the Fluxpoint API using
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#getWelcomeImage(WelcomeImage) getWelcomeImage} or
 * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#queueWelcomeImage(WelcomeImage) queueWelcomeImage}.
 */
public class WelcomeImage{
    
    private String username;
    private String avatar;
    @SerializedName("background")
    private ColorObject backgroundColor;
    
    @SerializedName("members")
    private String membersText;
    private String icon;
    private String banner;
    @SerializedName("color_welcome")
    private ColorObject welcomeColor;
    @SerializedName("color_username")
    private ColorObject usernameColor;
    @SerializedName("color_members")
    private ColorObject membersColor;
    
    private WelcomeImage(String username, String avatar, ColorObject backgroundColor, String membersText, String icon,
                         String banner, ColorObject welcomeColor, ColorObject usernameColor, ColorObject membersColor){
        this.username = username;
        this.avatar = avatar;
        this.backgroundColor = backgroundColor;
        this.membersText = membersText;
        this.icon = icon;
        this.banner = banner;
        this.welcomeColor = welcomeColor;
        this.usernameColor = usernameColor;
        this.membersColor = membersColor;
    }
    
    /**
     * Builder class to create a new {@link WelcomeImage WelcomeImage instance} to use in the
     * {@link ch.andre601.fluxpoint4j.Fluxpoint4J Fluxpoint4J class}.
     */
    public static class Builder{
        private String username = null;
        private String avatar = null;
        private ColorObject backgroundColor = null;
        
        private String membersText = null;
        private String icon = null;
        private String banner = null;
        private ColorObject welcomeColor = null;
        private ColorObject usernameColor = null;
        private ColorObject membersColor = null;
        
        public Builder(){}
    
        /**
         * Sets the username to display on the image.
         * <br>This option is required to be used!
         * 
         * @param  username
         *         The username to display.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withUsername(@NotNull String username){
            CheckUtil.notEmpty(username, "Username");
            
            this.username =  username;
            return this;
        }
    
        /**
         * Sets the avatar to display on the image. The String needs to be a valid URL.
         * <br>This option is required to be used!
         * 
         * @param  avatar
         *         The URL of the avatar to display.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withAvatar(@NotNull String avatar){
            CheckUtil.notEmpty(avatar, "Avatar");
            
            this.avatar = avatar;
            return this;
        }
    
        /**
         * Sets the background color to use.
         * <br>This option is required to be used!
         * 
         * <p>Please see the {@link ColorObject ColorObject class} for more info on what formats are supported.
         * 
         * @param  backgroundColor
         *         The {@link ColorObject ColorObject} to use for the background.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withBackgroundColor(@NotNull ColorObject backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }
    
        /**
         * Set the text that should be displayed under the username.
         * <br>You can provide an empty String to hide the text.
         * 
         * @param  membersText
         *         The text to display
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withMembersText(@NotNull String membersText){
            this.membersText = membersText;
            return this;
        }
    
        /**
         * Sets the icon to display on the right side of the image. The value can be an available icon on the Fluxpoint API
         * or a URL to a custom one.
         * <br>You can provide an empty String to hide the icon.
         * 
         * @param  icon
         *         The icon to use.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withIcon(@NotNull String icon){
            this.icon = icon;
            return this;
        }
    
        /**
         * Sets the banner to display. The value can be an available banner on the Fluxpoint API or a URL to a custom one.
         * <br>You can provide an empty String to hide the icon.
         * 
         * @param  banner
         *         The banner to use.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withBanner(@NotNull String banner){
            this.banner = banner;
            return this;
        }
    
        /**
         * Sets the color for the "Welcome" text on the image.
         *
         * <p>Please see the {@link ColorObject ColorObject class} for more info on what formats are supported.
         * 
         * @param  welcomeColor
         *         The {@link ColorObject ColorObject} to use for the "Welcome" text.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withWelcomeColor(@NotNull ColorObject welcomeColor){
            this.welcomeColor = welcomeColor;
            return this;
        }
    
        /**
         * Sets the color for the username.
         *
         * <p>Please see the {@link ColorObject ColorObject class} for more info on what formats are supported.
         * 
         * @param  usernameColor
         *         The {@link ColorObject ColorObject} to use for the username.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withUsernameColor(@NotNull ColorObject usernameColor){
            this.usernameColor = usernameColor;
            return this;
        }
    
        /**
         * Sets the color for the {@link #withMembersText(String) members text}.
         * 
         * <p>Please see the {@link ColorObject ColorObject class} for more info on what formats are supported.
         * 
         * @param  membersColor
         *         The {@link ColorObject ColorObject} to use for the username.
         * 
         * @return This Builder instance. Useful for chaining.
         */
        public Builder withMembersColor(@NotNull ColorObject membersColor){
            this.membersColor = membersColor;
            return this;
        }
    
        /**
         * Creates a new, usable {@link WelcomeImage WelcomeImage instance} for the 
         * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#getWelcomeImage(WelcomeImage) getWelcomeImage} and
         * {@link ch.andre601.fluxpoint4j.Fluxpoint4J#queueWelcomeImage(WelcomeImage) queueWelcomeImage} methods.
         *
         * <p>An {@link java.lang.IllegalArgumentException IllegalArgumentException} may be thrown in the following cases:
         * <ul>
         *     <li>{@link #withUsername(String) Username} is null or empty.</li>
         *     <li>{@link #withAvatar(String) Avatar} is null or empty.</li>
         *     <li>{@link #withBackgroundColor(ColorObject) Background color} is null.</li>
         * </ul>
         * 
         * @return A new {@link WelcomeImage WelcomeImage instance} from this Builder.
         */
        public WelcomeImage build(){
            CheckUtil.notNullOrEmpty(username, "Username");
            CheckUtil.notNullOrEmpty(avatar, "Avatar");
            CheckUtil.notNull(backgroundColor, "BackgroundColor");
            
            return new WelcomeImage(username, avatar, backgroundColor, membersText, icon, banner, welcomeColor,
                usernameColor, membersColor);
        }
    }
    
}
