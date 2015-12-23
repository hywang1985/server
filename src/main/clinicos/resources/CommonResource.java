package app.ws.clinicos.resources;

import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.model.Errors;
import app.ws.clinicos.model.Errors.Error;
import app.ws.clinicos.model.HasLinks;
import app.ws.clinicos.model.Links;
import app.ws.clinicos.model.Links.Link;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Arjun Golabhanvi
 */
public class CommonResource {

    @Context
    protected HttpHeaders headers;

    @Context
    protected Request request;    
    
    @Context
    protected UriInfo uriInfo;

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    
    /**
     * Gets the base URI of the web service in absolute form.
     * @return the URI.
     */
    public URI getBaseUri() 
    {
	return uriInfo.getBaseUri();
    }

    protected static void setPrivateCacheHeaders(ResponseBuilder builder, EntityTag eTag, int maxAge) {
        if (eTag != null) {
            builder.tag(eTag);
        }
        CacheControl cc = new CacheControl();
        cc.setMaxAge(maxAge);
        cc.setPrivate(true);
        builder.cacheControl(cc);
        Date expiryDate = new Date(System.currentTimeMillis() + maxAge);
        builder.expires(expiryDate);
    }

    protected Response handleException(CustomException exception, Response.Status status) {
        Response.ResponseBuilder builder = Response.status(status);
        builder.entity(reflectErrors(exception));
        return builder.build();
    }

    private Errors reflectErrors(CustomException exception) {
        Errors errors = new Errors();
        List<Error> errorList = new ArrayList<Error>();
        Error error = new Error();
        error.setMessage(exception.getErrMsg());
        error.setType(exception.getType());
        errorList.add(error);
        errors.setErrors(errorList);
        return errors;
    }
    
    /**
     * Adds a link to a representation instance.
     * @param container the instance
     * @param href the target of the link
     * @param rel the name of the link relation
     * @param type the media type of the target, or null if it is not known
     */
    protected void addLink(HasLinks container, String href, String rel, String method)
    {
	// create the link
	Link link = new Link();
	link.setHref(href);
	link.setRel(rel);
	
	if (method != null)
	    link.setMethod(method);
	
	// create a links container, if necessary
	Links links = container.getLinks();
	
	if (links == null) {
	    links = new Links();
	    container.setLinks(links);
	}
	
	// create a link list, if necessary
	List<Link> linkList = links.getLinks();
	
	if (linkList == null) {
	    linkList = new ArrayList<Link>();
	    links.setLinks(linkList);
	}
	
	// add the link to the list
	linkList.add(link);
    }
    
    protected URI createUri(URI baseUri, String relativePath,
	    Map<String,String> queryParams)
    {
	UriBuilder builder = UriBuilder.fromUri(baseUri);
	builder.replacePath(baseUri.getPath() + relativePath);
	if(queryParams != null) {
	    for(String key : queryParams.keySet()) {
		builder.queryParam(key, queryParams.get(key));
	    }
	}
	return builder.build();
    }
    
    protected String getResponseMediaType(){
        if(headers.getMediaType() != null)
            return headers.getMediaType().toString();
        else 
            return headers.getHeaderString(HttpHeaders.ACCEPT); 
    }
}
