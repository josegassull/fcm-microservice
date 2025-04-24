package com.fcm_ms.token_api.service;

import java.util.Optional;

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

  public void saveIfNotExists(User user, Device device) {
    Optional<UserDevice> existingUD = this.userDeviceRepository.findByUserAndDevice(user, device);

    if (!existingUD.isPresent())
      this.userDeviceRepository.save(UserDevice.of(user, device));
  }
}
