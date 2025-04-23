package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.enums.DeviceType;
import com.fcm_ms.token_api.repository.DeviceRepository;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceRepository deviceRepository;

  @Transactional
  public Device getFromTokenRequest(TokenRequest tokenRequest) {
    Device device = Device.builder()
      .type(DeviceType.Android)
      .uuid(tokenRequest.getDeviceUuid())
      .build();

    return this.deviceRepository.findByUuid(device.getUuid())
      .orElseGet(() -> this.deviceRepository.save(device));
  }
}
