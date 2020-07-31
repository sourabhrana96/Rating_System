package com.oyeRickshaw.ratingSystem.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author sourabhrana
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseFailureResponse<T> extends BaseApiResponse<T> {

    private static final long serialVersionUID = 6879050082764235550L;

    public String errorCode;
    public T data;

    public BaseFailureResponse(String errorCode) {
        super(false);
        this.errorCode = errorCode;
    }

    public BaseFailureResponse() {
        super(false);
    }

    public BaseFailureResponse(T data) {
        super(false);
        this.data = data;
    }

}