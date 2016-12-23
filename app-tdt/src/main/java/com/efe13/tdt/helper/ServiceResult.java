package com.efe13.tdt.helper;

import java.util.Collections;
import java.util.List;

import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.enums.StatusResultService;

public class ServiceResult<T> {
	
	private T object;
	private List<T> collection = Collections.emptyList();
	private StatusResultService statusResult;
	private String message;
	private QueryHelper queryHelper;
	
	public ServiceResult() {
	}
	
	public ServiceResult(T object, StatusResultService success) {
		this( object, Collections.emptyList(), success, "" );
	}
	
	public ServiceResult(List<T> collection, StatusResultService success) {
		this( null, collection, success, "" );
	}
	
	public ServiceResult(String message, StatusResultService success) {
		this( null, Collections.emptyList(), success, message );
	}
	
	public ServiceResult(T object, List<T> collection, StatusResultService success, String message) {
		this.statusResult = success;
		this.message = message;
		this.object = object;
		this.collection = collection;
	}

	public StatusResultService getStatusResult() {
		return statusResult;
	}
	
	public void setStatusResult( StatusResultService success) {
		this.statusResult = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public List<T> getCollection() {
		return collection;
	}
	
	public void setCollection(List<T> collection) {
		this.collection = collection;
	}

	public QueryHelper getQueryHelper() {
		return queryHelper;
	}

	public void setQueryHelper(QueryHelper queryHelper) {
		this.queryHelper = queryHelper;
	}
}