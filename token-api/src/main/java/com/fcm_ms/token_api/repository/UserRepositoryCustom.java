package com.fcm_ms.token_api.repository;

import com.fcm_ms.token_api.entity.User;

public interface UserRepositoryCustom {

  User findByExternalIdOrCreate(Integer externalId);
}
