package com.fcm_ms.token_api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.mapper.TokenMapper;
import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.repository.TokenRepository;
import com.fcm_ms.token_api.service.DeviceService;
import com.fcm_ms.token_api.service.UserService;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;

  private final TokenMapper tokenMapper;

  private final DeviceService deviceService;
  private final UserService userService;

  @Transactional
  public String registerToken(TokenRequest tokenRequest) {
    User user = this.userService.findByExternalIdOrCreate(tokenRequest.getUserExternalId());
    Device device = this.deviceService.findOrCreate(Device.of(
      tokenRequest.getDeviceUuid(),
      tokenRequest.getDeviceType()
    ));

    Token token = this.tokenMapper.toEntity(tokenRequest);

    Token savedToken = this.tokenRepository.save(token);

    return "Saved device: " + device.toString();
  }
}
