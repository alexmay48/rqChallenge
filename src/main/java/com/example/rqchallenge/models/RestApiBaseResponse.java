package com.example.rqchallenge.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class RestApiBaseResponse<T> {

	@SerializedName("status")
	private String status;

	@SerializedName("message")
	private String message;

	@SerializedName("data")
	private T data;

}
