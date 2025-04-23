package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.repository.DeviceRepository;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceRepository deviceRepository;

  @Transactional
  public Device findOrCreate(Device device) {
    return this.deviceRepository.findByUuid(device.getUuid())
      .orElseGet(() -> this.deviceRepository.save(device));
  }
}
