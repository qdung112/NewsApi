package com.news.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseResponse<T> {

    @JsonProperty("message")
    private String message;
    /*@JsonProperty("status")
    private int status;*/
    @JsonProperty("data")
    private List<T> data = new ArrayList<>();

}
