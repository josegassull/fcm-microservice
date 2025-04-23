package com.fcm_ms.token_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcm_ms.token_api.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

  Optional<Device> findByUuid(String uuid);
}
