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

package cn.lhfei.auth.server.rest.model;

import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class RestJsonResponse<T> extends JsonResponse<T> {
    private static final String DEFAULT_SUCCESS_MSG = "SUCCESS";

    public RestJsonResponse(javax.ws.rs.core.Response.Status status) {
        super(status);
        this.success = status.equals(Status.OK);
    }

    public RestJsonResponse(javax.ws.rs.core.Response.Status status, String message) {
        super(status, message);
        this.success = status.equals(Status.OK);
    }

    public RestJsonResponse(javax.ws.rs.core.Response.Status status, T body) {
        super(status, body);
        this.success = status.equals(Status.OK);
    }

    public RestJsonResponse(javax.ws.rs.core.Response.Status status, String message, T body) {
        super(status, message, body);
        this.success = status.equals(Status.OK);
    }

    public RestJsonResponse(javax.ws.rs.core.Response.Status status, Integer total, T body) {
        super(status, DEFAULT_SUCCESS_MSG, body);
        this.total = total;
        this.success = status.equals(Status.OK);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty(value = "success")
    private boolean success;
    
    private int total;
}
