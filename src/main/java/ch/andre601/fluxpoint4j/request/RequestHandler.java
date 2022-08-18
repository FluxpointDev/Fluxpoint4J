package ch.andre601.fluxpoint4j.request;

import ch.andre601.fluxpoint4j.image.CustomImage;
import ch.andre601.fluxpoint4j.util.ColorObject;
import ch.andre601.fluxpoint4j.util.ColorObjectSerializer;
import ch.andre601.fluxpoint4j.welcome.WelcomeImage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;

public class RequestHandler{
    
    public static final String BASE_URL = "https://api.fluxpoint.dev";
    
    private final OkHttpClient CLIENT = new OkHttpClient();
    private final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(ColorObject.class, new ColorObjectSerializer())
        .create();
    
    public GenericAPIResponse getCustomImage(String token, CustomImage image){
        return getImage(token, "/gen/custom", GSON.toJson(image));
    }
    
    public GenericAPIResponse getWelcomeImage(String token, WelcomeImage image){
        return getImage(token, "/gen/welcome", GSON.toJson(image));
    }
    
    public GenericAPIResponse getMcServerResponse(String token, String server, int port){
        Request request = new Request.Builder()
            .url(String.format("%s/mc/ping?host=%s&port=%d", BASE_URL, server, port))
            .addHeader("Authorization", token)
            .build();
        
        try(Response response = CLIENT.newCall(request).execute()){
            ResponseBody responseBody = response.body();
            if(responseBody == null)
                return new FailedAPIResponse("API returned a null/invalid Body!");
            
            if(!response.isSuccessful())
                return GSON.fromJson(responseBody.string(), FailedAPIResponse.class);
            
            return GSON.fromJson(responseBody.string(), MCServerPingResponse.class);
        }catch(IOException ex){
            return new FailedAPIResponse("Encountered IOException: " + ex.getMessage());
        }
    }
    
    private GenericAPIResponse getImage(String token, String endpoint, String json){
        RequestBody requestBody = RequestBody.create(json, null);
        
        Request request = new Request.Builder()
            .url(BASE_URL + endpoint)
            .addHeader("Authorization", token)
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build();
        
        try(Response response = CLIENT.newCall(request).execute()){
            ResponseBody responseBody = response.body();
            if(responseBody == null)
                return new FailedAPIResponse("API returned a null/invalid Body!");
            
            if(!response.isSuccessful())
                return GSON.fromJson(responseBody.string(), FailedAPIResponse.class);
            
            return new GeneratedImage(responseBody.byteStream());
        }catch(IOException ex){
            return new FailedAPIResponse("Encountered IOException: " + ex.getMessage());
        }
    }
}
