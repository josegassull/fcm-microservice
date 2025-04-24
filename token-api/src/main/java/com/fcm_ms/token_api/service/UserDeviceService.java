package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;

import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.entity.Device;

@Service
public class UserDeviceService {

  public void saveIfNotExists(User user, Device device) {
    System.out.println("[DEBUG] [stf] user device relationship");
    System.out.println(user.toString());
    System.out.println(device.toString());
  }
}
