package com.efe13.tdt.util;

import java.util.Collections;
import java.util.List;

public class JSONOutputWrapper<T> {

	public static final boolean RESULT_SUCCESS = true;
	public static final boolean RESULT_FAILED = false;
	
	private T object;
	private List<T> collection = Collections.emptyList();
	private boolean success;
	private String message;
	
	public JSONOutputWrapper() {
	}
	
	public JSONOutputWrapper(T object, boolean success) {
		this( object, Collections.emptyList(), success, "" );
	}
	
	public JSONOutputWrapper(List<T> collection, boolean success) {
		this( null, collection, success, "" );
	}
	
	public JSONOutputWrapper(String message, boolean success) {
		this( null, Collections.emptyList(), success, message );
	}
	
	public JSONOutputWrapper(T object, List<T> collection, boolean success, String message) {
		this.success = success;
		this.message = message;
		this.object = object;
		this.collection = collection;
	}

	public boolean isResultSuccess() {
		return success;
	}
	
	public void setResultSuccess(boolean success) {
		this.success = success;
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
	
}
