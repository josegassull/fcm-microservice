package com.fcm_ms.token_api.mapper;

import org.mapstruct.Mapper;

import com.fcm_ms.token_api.dto.TokenRequestDTO;
import com.fcm_ms.token_api.entity.Token;

@Mapper(componentModel = "spring")
public interface TokenMapper {

  Token toEntity(TokenRequestDTO tokenRequest);
}
