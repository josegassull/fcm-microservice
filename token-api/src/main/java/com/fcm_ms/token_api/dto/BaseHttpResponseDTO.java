package com.fcm_ms.token_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseHttpResponseDTO {

  protected HttpStatusDetail httpStatus;

  public HttpStatus _getHttpStatus() {
    return HttpStatus.valueOf(this.httpStatus.getName());
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class HttpStatusDetail {

    private String name;
    private Integer code;
  }
}
