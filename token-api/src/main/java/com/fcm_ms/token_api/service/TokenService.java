package com.fcm_ms.token_api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.mapper.TokenMapper;
import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.repository.TokenRepository;
import com.fcm_ms.token_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;
  private final UserRepository userRepository;

  private final TokenMapper tokenMapper;

  public String registerToken(TokenRequest tokenRequest) {
    /* TODO save user and device relationship */
    Optional<User> user = this.userRepository.findByExternalId(
      tokenRequest.getUserExternalId()
    );

    Token token = this.tokenMapper.toEntity(tokenRequest);

    Token savedToken = this.tokenRepository.save(token);

    // return "Hello from TokenService!! user: " + user.toString();

    if (user.isPresent())
      return "User exists!" + user.get().toString();
    else
      return "User with " + tokenRequest.getUserExternalId() + " does not exist";
  }
}
