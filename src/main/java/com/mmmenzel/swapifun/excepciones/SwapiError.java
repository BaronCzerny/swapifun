package com.mmmenzel.swapifun.excepciones;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;


@Data
public class SwapiError {
	   private HttpStatusCode statusCode;
	   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	   private String timestamp;
	   private String message;
	   private String debugMessage;

	   private SwapiError() {
		   timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	   }

	   public SwapiError(HttpStatusCode statusCode) {
	       this();
	       this.statusCode = statusCode;
	   }

	   public SwapiError(HttpStatusCode statusCode, String message) {
	       this();
	       this.statusCode = statusCode;
	       this.message = message;
	   }

	   public SwapiError(HttpStatusCode statusCode, Throwable e) {
	       this();
	       this.statusCode = statusCode;
	       this.message = "Unexpected error";
	       this.debugMessage = e.getLocalizedMessage();
	   }

	   public SwapiError(HttpStatusCode statusCode, String message, Throwable e) {
	       this();
	       this.statusCode = statusCode;
	       this.message = message;
	       this.debugMessage = e.getLocalizedMessage();
	   }
	}