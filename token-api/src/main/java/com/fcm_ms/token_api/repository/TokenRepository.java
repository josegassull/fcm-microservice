package com.fcm_ms.token_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByDeviceId(Long deviceId);
}
