package com.indigo.restapi.exception;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ErrorResponseDto implements Serializable {
	
	private static final long serialVersionUID = -1918651081331058099L;
	
	@NotNull
	private String message;
	@NotNull
	private String cause;
	@NotNull
	private Date date;
}