package com.btkAkademi.rentACar.core.utilities.results;

public class DataResult<T> extends Result {

	private long totalCount;
	private T data;
	public DataResult(T data, boolean success, String message, long totalCount) {
		super(success, message);
		this.totalCount = totalCount;
		this.data = data;
	}
	
	public DataResult(T data, boolean success, long totalCount) {
		super(success);
		this.totalCount = totalCount;
		this.data = data;
	}
	
	public DataResult(T data, boolean success, String message) {
		super(success, message);
		this.data = data;
	}
	
	public DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public T getData() {
		return this.data;
	}
}