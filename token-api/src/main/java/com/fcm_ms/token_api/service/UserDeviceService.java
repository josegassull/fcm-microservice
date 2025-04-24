package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.entity.UserDevice;
import com.fcm_ms.token_api.repository.UserDeviceRepository;

@Service
@RequiredArgsConstructor
public class UserDeviceService {

  private final UserDeviceRepository userDeviceRepository;

  public UserDevice saveIfNotExists(User user, Device device) {
    return this.userDeviceRepository.findByUserAndDevice(user, device)
      .orElseGet(() -> this.userDeviceRepository.save(
        UserDevice.of(user, device)
      ));
  }
}
