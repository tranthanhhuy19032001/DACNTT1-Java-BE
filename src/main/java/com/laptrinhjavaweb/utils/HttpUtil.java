package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil(String value) {
		 this.value = value;
	}
	
	//Map string(json) to model
	public <T> T toModel(Class<T> tClass) {
		try {
			//ObjectMapper convert string <-> json
			//Convert json -> string so insert into db
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Conver json to String
	public static HttpUtil of (BufferedReader br) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}

}
