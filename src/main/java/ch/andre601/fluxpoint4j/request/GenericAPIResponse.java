package ch.andre601.fluxpoint4j.request;

import jdk.internal.jline.internal.Nullable;

/**
 * A generic interface used for the responses of the API.
 * <br>A response can be both successfull or non-successful and this interface is used to have a more generic type returned.
 */
public interface GenericAPIResponse{
    
    /**
     * The returned status code.
     * <br>Anything that isn't a 2xx response should be counted as a failed request.
     * 
     * @return The status code of the API response.
     */
    int getCode();
    
    /**
     * A possible response message. This is usually empty/null value for successful responses as usually only failed
     * ones will have a message in it.
     * 
     * @return Possibly-null or empty message from the API response.
     */
    @Nullable
    String getMessage();
}
