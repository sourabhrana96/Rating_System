package com.oyeRickshaw.ratingSystem.response;

import lombok.Data;

/**
 * @author sourabhrana
 */

@Data
public class GenericRatingResponse extends BaseResponse {

    private static final long serialVersionUID = 6879050082764235550L;
    private Float aggregateRating;
    private Integer totalRatingCounts;

}
