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
public class BaseSuccessResponse<T> extends BaseApiResponse<T> {

    private static final long serialVersionUID = 6879050082764235550L;

    public final T data;

    public BaseSuccessResponse(T data) {
        super(true);
        this.data = data;
    }
}