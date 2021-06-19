package com.zohancs.microservices;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLogginFilter extends ZuulFilter {
	
	private Logger LOGGER = LoggerFactory.getLogger(ZuulLogginFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		LOGGER.info("request -> {} request uri -> {}",request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre"; //it can be "post","error","pre"
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
