package com.fcm_ms.token_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcm_ms.token_api.entity.Device;
import com.fcm_ms.token_api.entity.User;
import com.fcm_ms.token_api.entity.UserDevice;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

  Optional<UserDevice> findByUserAndDevice(User user, Device device);
}
