package ch.andre601.fluxpoint4j.welcome;

import ch.andre601.fluxpoint4j.CheckUtil;
import ch.andre601.fluxpoint4j.util.ColorObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

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
        
        public Builder withUsername(@NotNull String username){
            CheckUtil.notEmpty(username, "Username");
            
            this.username =  username;
            return this;
        }
        
        public Builder withAvatar(@NotNull String avatar){
            CheckUtil.notEmpty(avatar, "Avatar");
            
            this.avatar = avatar;
            return this;
        }
        
        public Builder withBackgroundColor(@NotNull ColorObject backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }
        
        public Builder withMembersText(@NotNull String membersText){
            CheckUtil.notEmpty(membersText, "MembersText");
            
            this.membersText = membersText;
            return this;
        }
        
        public Builder withIcon(@NotNull String icon){
            CheckUtil.notEmpty(icon, "Icon");
            
            this.icon = icon;
            return this;
        }
        
        public Builder withBanner(@NotNull String banner){
            CheckUtil.notEmpty(banner, "Banner");
            
            this.banner = banner;
            return this;
        }
        
        public Builder withWelcomeColor(@NotNull ColorObject welcomeColor){
            this.welcomeColor = welcomeColor;
            return this;
        }
    
        public Builder withUsernameColor(@NotNull ColorObject usernameColor){
            this.usernameColor = usernameColor;
            return this;
        }
    
        public Builder withMembersColor(@NotNull ColorObject membersColor){
            this.membersColor = membersColor;
            return this;
        }
        
        public WelcomeImage build(){
            CheckUtil.notNullOrEmpty(username, "Username");
            CheckUtil.notNullOrEmpty(avatar, "Avatar");
            CheckUtil.notNull(backgroundColor, "BackgroundColor");
            
            return new WelcomeImage(username, avatar, backgroundColor, membersText, icon, banner, welcomeColor,
                usernameColor, membersColor);
        }
    }
    
}
