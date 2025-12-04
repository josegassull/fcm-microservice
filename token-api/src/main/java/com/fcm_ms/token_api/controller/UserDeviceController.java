package com.fcm_ms.token_api.controller;

import com.fcm_ms.token_api.service.UserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user-device")
@RequiredArgsConstructor
public class UserDeviceController {
    private final UserDeviceService userDeviceService;
    @DeleteMapping("unregister/user/{userExternalId}/device/{deviceUuid}")
    public ResponseEntity<Void> unassignToken(
            @PathVariable Integer userExternalId,
            @PathVariable String deviceUuid) {

        this.userDeviceService.deleteRelation(userExternalId, deviceUuid);
        return ResponseEntity.noContent().build(); // 204
    }
}