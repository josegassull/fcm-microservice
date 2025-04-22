package com.fcm_ms.token_api.mapper;

import org.mapstruct.Mapper;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.entity.Token;

@Mapper(componentModel = "spring")
public interface TokenMapper {

  Token toEntity(TokenRequest tokenRequest);
}
