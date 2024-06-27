package com.urubu.web.endpoint;

import java.util.Map;
import java.util.Objects;

import com.urubu.core.auth.LoginOrigin;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import jakarta.ws.rs.core.UriInfo;

public abstract class AbstractController {

	protected void addValidStringFilter(String fieldName, UriInfo uriInfo, Map<String, Object> searchCriteria){
		String field = uriInfo.getQueryParameters().getFirst(fieldName);
		if (StringUtils.isNotBlank(field)){
			searchCriteria.put(fieldName, field);
		}
	}
	protected void addValidNumberFilter(String fieldName, UriInfo uriInfo, Map<String, Object> searchCriteria){
		Integer field = NumberUtils.toInt(uriInfo.getQueryParameters().getFirst(fieldName));
		if (field != null && field > 0){
			searchCriteria.put(fieldName, field);
		}
	}
	protected void addValidLongFilter(String fieldName, UriInfo uriInfo, Map<String, Object> searchCriteria) {
		Long field = NumberUtils.toLong(uriInfo.getQueryParameters().getFirst(fieldName));

		if (field != null && field > 0){
			searchCriteria.put(fieldName, field);
		}
	}
	
	protected void addValidBooleanFilter(String fieldName, UriInfo uriInfo, Map<String, Object> searchCriteria) {
		String field = uriInfo.getQueryParameters().getFirst(fieldName);
		if (StringUtils.isNotBlank(field)){
			searchCriteria.put(fieldName, BooleanUtils.toBoolean(field));
		}
	}

	protected void addValidFloatFilter(String fieldName, UriInfo uriInfo, Map<String, Object> searchCriteria) {
		Float field = NumberUtils.toFloat(uriInfo.getQueryParameters().getFirst(fieldName));
		if (field != null && field > 0.0F){
			searchCriteria.put(fieldName, field);
		}
	}

	protected LoginOrigin buildOrigin(HttpServletRequest httpServletRequest) {
		LoginOrigin origin = new LoginOrigin();
		origin.setIp(extractUserIp(httpServletRequest));
		origin.setDevice(httpServletRequest.getHeader("User-Agent"));
		String remoteHost = httpServletRequest.getRemoteHost();
		origin.setHost(Objects.nonNull(remoteHost) ? remoteHost : "Não Informado");
		return origin;
	}

	private static String extractUserIp(HttpServletRequest httpServletRequest) {
		String ipChain = httpServletRequest.getHeader("X-Forwarded-For");
		String userIp = null;
		if (StringUtils.isNotBlank(ipChain)) {
			userIp = ipChain.split(",")[0];
		} else {
			userIp = httpServletRequest.getRemoteAddr();
		}
		return userIp;
	}

}
