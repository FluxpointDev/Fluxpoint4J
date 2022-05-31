package ch.andre601.fluxpoint4j.request;

import org.jetbrains.annotations.Nullable;

public class FailedAPIResponse implements GenericAPIResponse{
    
    private int code;
    private String message;
    
    // No-Args constructor for Gson
    public FailedAPIResponse(){}
    
    public FailedAPIResponse(String message){
        this.code = -1;
        this.message = message;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return Non-200 Response code from the API.
     */
    @Override
    public int getCode(){
        return code;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return Message explaining why the request failed.
     */
    @Override
    @Nullable
    public String getMessage(){
        return message;
    }
}
