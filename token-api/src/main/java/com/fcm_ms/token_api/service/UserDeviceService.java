package com.fcm_ms.token_api.service;

import java.util.Optional;

import jakarta.transaction.Transactional;
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
  private final UserService userService;
  private final DeviceService deviceService;

  public void saveIfNotExists(User user, Device device) {
    Optional<UserDevice> existingUD = this.userDeviceRepository.findByUserAndDevice(user, device);

    if (!existingUD.isPresent())
      this.userDeviceRepository.save(UserDevice.of(user, device));
  }

  @Transactional
  public void deleteRelation(Integer userExternalId, String deviceUuid) {
    User user = this.userService.findByExternalIdOrThrow(userExternalId);

    Device device = this.deviceService.findByUuid(deviceUuid)
            .orElseThrow(() -> new IllegalArgumentException("Device does not exist"));

    this.userDeviceRepository.findByUserAndDevice(user, device)
            .ifPresent(ud -> this.userDeviceRepository.delete(ud));
    //TODO: preguntar si al no quedar mas usuarios en el dispositvio se debe borrar solo el token o tambien el dispositivo?
    //TODO: según esto, manejar a continuación lo que deba pasar:
    if(device.get)
  }
}
