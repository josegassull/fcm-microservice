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

    if (!this.hasUser(deviceUuid)) {
      this.deviceService.deleteById(device.getId());
    }
  }

  public boolean hasUser(String deviceUuid) {
    Device device = this.deviceService.findByUuid(deviceUuid)
            .orElseThrow(() -> new IllegalArgumentException("Device does not exist"));

    return this.userDeviceRepository.existsByDevice(device);
  }
}
