package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public User getFromTokenRequest(TokenRequest tokenRequest) {
    Integer externalId = tokenRequest.getUserExternalId();

    return this.userRepository.findByExternalId(externalId)
      .orElseGet(() -> this.userRepository.save(User.of(externalId)));
  }
}
