package com.fcm_ms.token_api.controller;

import com.fcm_ms.token_api.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @DeleteMapping("unregister/{deviceUuid}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String deviceUuid) {

        this.deviceService.deleteByUuid(deviceUuid);
        return ResponseEntity.noContent().build(); // 204
    }
}
