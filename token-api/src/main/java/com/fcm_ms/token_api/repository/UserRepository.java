package com.fcm_ms.token_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fcm_ms.token_api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByExternalId(Integer externalId);
}
