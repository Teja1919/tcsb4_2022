package com.cts.auth.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {
	private String message;
	private LocalDateTime timestamp;

	@JsonInclude(Include.NON_NULL)
	private List<String> fieldErrors;

	public ExceptionDetails(String message2, LocalDateTime now, List<String> singletonList) {
		// TODO Auto-generated constructor stub
	}

	public ExceptionDetails() {
		// TODO Auto-generated constructor stub
	}

	public void setTimestamp(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}

	public void setMessage(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setFieldErrors(List<String> errors) {
		// TODO Auto-generated method stub
		
	}
}
