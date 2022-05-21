package ch.andre601.fluxpoint4j.welcome;

import ch.andre601.fluxpoint4j.CheckUtil;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class WelcomeImage{
    
    private String username;
    private String avatar;
    @SerializedName("background")
    private String backgroundColor;
    
    @SerializedName("members")
    private String membersText;
    private String icon;
    private String banner;
    @SerializedName("color_welcome")
    private String welcomeColor;
    @SerializedName("color_username")
    private String usernameColor;
    @SerializedName("color_members")
    private String membersColor;
    
    private WelcomeImage(String username, String avatar, String backgroundColor, String membersText, String icon,
                         String banner, String welcomeColor, String usernameColor, String membersColor){
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
        private String backgroundColor = null;
        
        private String membersText = null;
        private String icon = null;
        private String banner = null;
        private String welcomeColor = null;
        private String usernameColor = null;
        private String membersColor = null;
        
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
        
        public Builder withBackgroundColor(@NotNull Color bgColor){
            this.backgroundColor = String.format("%d,%d,%d", bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
            return this;
        }
        
        public Builder withBackgroundColor(@NotNull String bgColor){
            this.backgroundColor = bgColor;
            return this;
        }
        
        public Builder withBackgroundColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            
            this.backgroundColor = String.format("%d,%d,%d", r, g, b);
            return this;
        }
        
        public Builder withBackgroundColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
            
            this.backgroundColor = String.format("%d,%d,%d,%d", r, g, b, a);
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
        
        public Builder withWelcomeColor(@NotNull Color welcomeColor){
            this.welcomeColor = String.format("%d,%d,%d", welcomeColor.getRed(), welcomeColor.getGreen(), welcomeColor.getBlue());
            return this;
        }
        
        public Builder withWelcomeColor(@NotNull String welcomeColor){
            CheckUtil.notEmpty(welcomeColor, "WelcomeColor");
            
            this.welcomeColor = welcomeColor;
            return this;
        }
        
        public Builder withWelcomeColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
    
            this.welcomeColor = String.format("%d,%d,%d", r, g, b);
            return this;
        }
        
        public Builder withWelcomeColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
    
            this.welcomeColor = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
    
        public Builder withUsernameColor(@NotNull Color usernameColor){
            this.usernameColor = String.format("%d,%d,%d", usernameColor.getRed(), usernameColor.getGreen(), usernameColor.getBlue());
            return this;
        }
    
        public Builder withUsernameColor(@NotNull String usernameColor){
            CheckUtil.notEmpty(usernameColor, "WelcomeColor");
        
            this.usernameColor = usernameColor;
            return this;
        }
    
        public Builder withUsernameColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
        
            this.usernameColor = String.format("%d,%d,%d", r, g, b);
            return this;
        }
    
        public Builder withUsernameColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
        
            this.usernameColor = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
    
        public Builder withMembersColor(@NotNull Color membersColor){
            this.membersColor = String.format("%d,%d,%d", membersColor.getRed(), membersColor.getGreen(), membersColor.getBlue());
            return this;
        }
    
        public Builder withMembersColor(@NotNull String membersColor){
            CheckUtil.notEmpty(membersColor, "WelcomeColor");
        
            this.membersColor = membersColor;
            return this;
        }
    
        public Builder withMembersColor(int r, int g, int b){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
        
            this.membersColor = String.format("%d,%d,%d", r, g, b);
            return this;
        }
    
        public Builder withMembersColor(int r, int g, int b, int a){
            CheckUtil.inRange(r, 0, 255, "Red value");
            CheckUtil.inRange(g, 0, 255, "Green value");
            CheckUtil.inRange(b, 0, 255, "Blue value");
            CheckUtil.inRange(a, 0, 255, "Alpha value");
        
            this.membersColor = String.format("%d,%d,%d,%d", r, g, b, a);
            return this;
        }
        
        public WelcomeImage build(){
            CheckUtil.notNull(username, "Username");
            CheckUtil.notNull(avatar, "Avatar");
            CheckUtil.notNull(backgroundColor, "BackgroundColor");
            
            return new WelcomeImage(username, avatar, backgroundColor, membersText, icon, banner, welcomeColor,
                usernameColor, membersColor);
        }
    }
    
}
