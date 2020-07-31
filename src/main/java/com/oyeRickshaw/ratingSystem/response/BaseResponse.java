package com.oyeRickshaw.ratingSystem.response;

import lombok.Data;

/**
 * @author sourabhrana
 */

@Data
public class BaseResponse {

    private static final long serialVersionUID = 5102768053933583035L;

    private String message;

    private boolean success;
}
