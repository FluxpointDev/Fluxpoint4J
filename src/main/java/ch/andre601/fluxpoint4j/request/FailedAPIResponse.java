package ch.andre601.fluxpoint4j.request;

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
    public String getMessage(){
        return message;
    }
}
