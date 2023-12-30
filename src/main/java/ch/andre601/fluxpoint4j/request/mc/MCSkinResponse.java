package ch.andre601.fluxpoint4j.request.mc;

import ch.andre601.fluxpoint4j.request.GenericAPIResponse;
import org.jetbrains.annotations.Nullable;

public class MCSkinResponse implements GenericAPIResponse{
    
    private boolean accountFound = false;
    
    private String uuid = null;
    private String name = null;
    
    private String headUrl = null;
    private String cubeUrl = null;
    private String bodyUrl = null;
    private String fullUrl = null;
    
    @Override
    public int getCode(){
        return -1;
    }
    
    @Override
    public @Nullable String getMessage(){
        return null;
    }
    
    public boolean isAccountFound(){
        return accountFound;
    }
    
    public String getUuid(){
        return uuid;
    }
    
    public String getName(){
        return name;
    }
    
    public String getHeadUrl(){
        return headUrl;
    }
    
    public String getCubeUrl(){
        return cubeUrl;
    }
    
    public String getBodyUrl(){
        return bodyUrl;
    }
    
    public String getFullUrl(){
        return fullUrl;
    }
}
