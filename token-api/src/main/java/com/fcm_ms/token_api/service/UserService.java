package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public User findByExternalIdOrCreate(Integer externalId) {
    return this.userRepository.findByExternalId(externalId)
      .orElseGet(() -> this.userRepository.save(User.builder()
        .externalId(externalId).build()
      ));
  }
}
