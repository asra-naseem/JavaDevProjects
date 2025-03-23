package com.springboot.project.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	private String resourceName ;
	private String field;
	private long fieldValue;
	public ResourceNotFoundException(String resourceName, String field, long fieldValue) {
		super(String.format("%s not found with this %s: %s", resourceName,field,fieldValue));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldValue = fieldValue;
	}
	
	
	

}
