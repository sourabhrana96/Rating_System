package com.oyeRickshaw.ratingSystem.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sourabhrana
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BaseApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -857198674294884017L;

    private boolean success;

    private String message;

    public BaseApiResponse(boolean success) {
        this.success = success;
    }

}
