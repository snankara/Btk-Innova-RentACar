package com.btkAkademi.rentACar.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T> {

	public SuccessDataResult(T data, String message, long totalCount) {
		super(data, true ,message, totalCount);
	}
	
	public SuccessDataResult(T data, long totalCount) {
		super(data,true, totalCount);
	}
	
	public SuccessDataResult(String message, long totalCount) {
		super(null, true ,message, totalCount);
	}
	
	public SuccessDataResult(T data, String message) {
		super(data, true, message);
	}

	public SuccessDataResult(T data) {
		super(data, true);
	}
	
	public SuccessDataResult(long totalCount) {
		super(null, true, totalCount);
	}
}
