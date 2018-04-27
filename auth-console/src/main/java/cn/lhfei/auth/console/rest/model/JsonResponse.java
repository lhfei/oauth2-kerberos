/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.auth.console.rest.model;

import javax.ws.rs.core.Response.ResponseBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
public class JsonResponse<T> {
	@JsonProperty(value = "status")
	private javax.ws.rs.core.Response.Status status;
	
	@JsonProperty(value = "message")
	private String message;
	
	@JsonProperty(value = "body")
	private T body;

	public JsonResponse(javax.ws.rs.core.Response.Status status) {
		this.status = status;
		this.message = null;
		this.body = null;

	}

	public JsonResponse(javax.ws.rs.core.Response.Status status, String message) {
		this.status = status;
		this.message = message;
		this.body = null;
	}

	public JsonResponse(javax.ws.rs.core.Response.Status status, T body) {
		this.status = status;
		this.message = null;
		this.body = body;
	}

	public JsonResponse(javax.ws.rs.core.Response.Status status, String message, T body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public javax.ws.rs.core.Response build() {
		ResponseBuilder r = javax.ws.rs.core.Response.status(status).entity(this.toString());
		return r.build();
	}
}
