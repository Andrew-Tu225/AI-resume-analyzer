package com.ai.resume_analyzer.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private long id;
    private String username;
    private String email;
}
