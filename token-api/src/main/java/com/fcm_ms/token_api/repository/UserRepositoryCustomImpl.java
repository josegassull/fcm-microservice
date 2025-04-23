package com.fcm_ms.token_api.repository;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.entity.User;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

  private final UserRepository userRepository;

  @Override
  public User findByExternalIdOrCreate(Integer externalId) {
    return this.userRepository.findByExternalId(externalId)
      .orElseGet(() -> {return this.userRepository.save(User.builder()
        .externalId(externalId)
        .build()
        );});
  }
}
