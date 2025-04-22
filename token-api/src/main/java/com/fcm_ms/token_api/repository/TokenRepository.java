package com.fcm_ms.token_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcm_ms.token_api.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> { }
