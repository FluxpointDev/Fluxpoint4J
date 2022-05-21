package ch.andre601.fluxpoint4j.test;

import ch.andre601.fluxpoint4j.Fluxpoint4J;
import ch.andre601.fluxpoint4j.image.CustomImage;
import ch.andre601.fluxpoint4j.image.format.Image;
import ch.andre601.fluxpoint4j.image.format.Text;
import ch.andre601.fluxpoint4j.request.GeneratedImage;
import ch.andre601.fluxpoint4j.welcome.WelcomeImage;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class CustomImageTest{
    
    private final Gson GSON = new Gson();
    
    @Test
    public void createCustomImage(){
        Fluxpoint4J fluxpoint4J = new Fluxpoint4J();
        fluxpoint4J.setToken(""); // TODO: Add a way to use ENV for token?
        
        GeneratedImage result = fluxpoint4J.getCustomImage(getCustomImage());
        
        if(result == null){
            System.out.println("Received null GeneratedImage instance");
            return;
        }
    
        System.out.println("InputStream: " + result.getAsInputStream());
        System.out.println("BufferedImage: " + result.getAsBufferedImage());
    }
    
    @Test
    public void printJSON(){
        System.out.println("Print JSON output: START");
        
        CustomImage image = getCustomImage();
        
        System.out.println("Custom Image:");
        System.out.println(GSON.toJson(image));
    
        System.out.println("========================================");
    
        System.out.println("Welcome Image:");
        System.out.println(GSON.toJson(getWelcomeImage()));
    
        System.out.println("Print JSON output: END");
    }
    
    private CustomImage getCustomImage(){
        Image.Rectangle base = new Image.Rectangle()
            .withColor(Color.GREEN)
            .withWidth(1920)
            .withHeight(1080);
    
        Image.Circle circle = new Image.Circle()
            .withColor(Color.YELLOW)
            .withRadius(100)
            .withPosX(10)
            .withPosY(10);
    
        Text.SingleLine text = new Text.SingleLine("Test")
            .withColor(Color.BLACK)
            .withSize(20)
            .asBold(true)
            .withPosX(10)
            .withPosY(20);
    
        return CustomImage.Builder.createBase(base)
            .addImage(circle)
            .addText(text)
            .build();
    }
    
    private WelcomeImage getWelcomeImage(){
        return new WelcomeImage.Builder()
            .withUsername("Someone")
            .withAvatar("https://purrbot.site/assets/img/api/unknown.png")
            .withBackgroundColor("#000000")
            .withWelcomeColor(Color.YELLOW)
            .build();
    }
}
