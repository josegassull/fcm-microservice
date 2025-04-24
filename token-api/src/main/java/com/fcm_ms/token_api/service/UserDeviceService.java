package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;

import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.entity.UserDevice;

@Service
public class UserDeviceService {

  public UserDevice saveIfNotExists(User user, Device device) {
    return UserDevice.builder()
      .user(user)
      .device(device)
      .build();
  }
}
