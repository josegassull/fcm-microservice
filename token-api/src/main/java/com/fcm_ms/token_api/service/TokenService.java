package com.fcm_ms.token_api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.dto.TokenResponse;
import com.fcm_ms.token_api.mapper.TokenMapper;
import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;

  private final TokenMapper tokenMapper;

  private final DeviceService deviceService;
  private final UserService userService;
  private final UserDeviceService userDeviceService;

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public Boolean registerToken(TokenRequest tokenRequest) {
    User   user   = this.userService.getFromTokenRequest(tokenRequest);
    Device device = this.deviceService.getFromTokenRequest(tokenRequest);

    this.userDeviceService.saveIfNotExists(user, device);

    Optional<Token> existingToken = this.tokenRepository.findByDeviceId(device.getId());

    Boolean isNew = !existingToken.isPresent();
    Token toSaveToken;
    if (!isNew) {
      toSaveToken = existingToken.get();
      toSaveToken.setToken(tokenRequest.getToken()); /* will trigger Token's @PreUpdate */
    } else {
      toSaveToken = this.tokenMapper.toEntity(tokenRequest);
      toSaveToken.setDevice(device);
    }

    Token savedToken = this.tokenRepository.save(toSaveToken);

    if (isNew) {
      this.entityManager.flush();
      this.entityManager.refresh(savedToken); /* useful if @PreUpdate has triggered */
    }

    return isNew;
  }

  @Transactional
  public String getTokenResponse(TokenRequest tokenRequest, Boolean isNew) {
    Device device = this.deviceService.findByUuid(tokenRequest.getDeviceUuid()).get();

    return TokenResponse.of(
        isNew,
        device
        ).toString();
  }
}
