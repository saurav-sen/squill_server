package com.pack.pack.ml.rest.api;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.pack.pack.common.util.JSONUtil;
import com.pack.pack.model.web.dto.BookmarkDTO;
import com.pack.pack.services.exception.PackPackException;
import com.pack.pack.services.ext.text.summerize.WebDocumentParser;
import com.pack.pack.services.registry.ServiceRegistry;
import com.squill.feed.web.model.JRssFeed;

/**
 * 
 * @author Saurav
 *
 */
@Singleton
@Provider
@Path("/bookmark")
public class BookmarkResource {

	/*private static final Logger LOG = LoggerFactory
			.getLogger(BookmarkResource.class);*/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JRssFeed processArticleLink(String json) throws PackPackException {
		BookmarkDTO dto = JSONUtil.deserialize(json, BookmarkDTO.class, true);
		WebDocumentParser parser = ServiceRegistry.INSTANCE
				.findService(WebDocumentParser.class);
		return parser.setUrl(dto.getHyperlink()).parse();
	}
}
