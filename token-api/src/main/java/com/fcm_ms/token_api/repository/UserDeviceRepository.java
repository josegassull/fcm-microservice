package com.fcm_ms.token_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcm_ms.token_api.entity.UserDevice;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> { }
