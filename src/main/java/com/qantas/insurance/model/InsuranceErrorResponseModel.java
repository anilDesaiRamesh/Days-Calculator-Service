package com.qantas.insurance.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error"
})
@Getter
@Setter
@Builder
@ToString
public class InsuranceErrorResponseModel {

    @JsonProperty("error")
    public Error error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "code",
            "message"
    })
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Error {

        @JsonProperty("code")
        public Integer code;
        @JsonProperty("message")
        public String message;

    }
}
