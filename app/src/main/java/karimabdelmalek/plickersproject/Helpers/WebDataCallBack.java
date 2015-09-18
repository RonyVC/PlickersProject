package karimabdelmalek.plickersproject.Helpers;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;


public interface WebDataCallBack {
	
    /**
     * back in the ui thread, update it
     * @param e
     */
    public void resumeUi(Object result);
    
    /**
     * handle exception in UI thread
     * @param e
     */
    public void handleException(Exception e);
    
    /**
     * call the service here, in a separate thread
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public Object callService() throws IOException, JSONException, URISyntaxException;
    
   
}
