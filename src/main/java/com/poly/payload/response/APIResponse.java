package com.poly.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse {
    String message="success";
    boolean success =true;
    Object data =null;

    public APIResponse(String message) {
        this.message = message;
    }
    public APIResponse(String message, Boolean success) {
        this.success=success;
        this.message = message;
    }

}
