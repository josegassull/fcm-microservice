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
    User   user   = this.userService.getFromTokenRequest(tokenRequest);
    Device device = this.deviceService.getFromTokenRequest(tokenRequest);

    Token token = this.tokenMapper.toEntity(tokenRequest);
    token.setDevice(device);

    Token savedToken = this.tokenRepository.save(token);

    return "User: " + user.toString() + " | Device: " + device.toString()
      + " | Token: " + token.toString() + " | SavedToken: " + savedToken.toString();
  }
}
