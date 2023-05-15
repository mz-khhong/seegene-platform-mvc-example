package com.seegene.mvnpoc.support.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StatusCode {
    Y("Y"),
    N("N");

    private final String isBlacklist;

    StatusCode(String isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public String getStatusCode() {
        return this.isBlacklist;
    }

    public static StatusCode getCodeValidate(String statusCode){
        switch (statusCode){
            case "Y":
                return StatusCode.Y;
        }
        return StatusCode.N;
    }
}
