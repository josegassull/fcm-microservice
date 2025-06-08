package com.fcm_ms.token_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByDeviceId(Long deviceId);

  @Query(value = """
    SELECT f.* AS token
    FROM fcm_token f
    JOIN device d ON f.device_id = d.id
    JOIN user_device ud ON ud.device_id = d.id
    JOIN registered_user ru ON ru.id = ud.registered_user_id
    WHERE ru.external_id = :externalId""",
    nativeQuery = true)
  List<Token> findTokensByUserExternalId(@Param("externalId") Integer externalId);
}
