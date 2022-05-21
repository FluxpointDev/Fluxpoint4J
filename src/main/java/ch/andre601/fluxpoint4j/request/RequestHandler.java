package ch.andre601.fluxpoint4j.request;

import okhttp3.*;

import java.io.IOException;

public class RequestHandler{
    
    private final OkHttpClient CLIENT = new OkHttpClient();
    
    public GeneratedImage getCustomImage(String token, String json){
        return getImage(token, "gen/custom", json);
    }
    
    public GeneratedImage getWelcomeImage(String token, String json){
        return getImage(token, "gen/welcome", json);
    }
    
    private GeneratedImage getImage(String token, String endpoint, String json){
        RequestBody requestBody = RequestBody.create(json, null);
        
        Request request = new Request.Builder()
            .url("https://api.fluxpoint.dev/" + endpoint)
            .addHeader("Authorization", token)
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build();
        
        try(Response response = CLIENT.newCall(request).execute()){
            if(!response.isSuccessful())
                return null;
            
            ResponseBody responseBody = response.body();
            if(responseBody == null)
                return null;
            
            return new GeneratedImage(responseBody.byteStream());
        }catch(IOException ex){
            return null;
        }
    }
}
