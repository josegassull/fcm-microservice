package com.fcm_ms.token_api.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeviceTypeConverter implements AttributeConverter<DeviceType, String> {

  @Override
  public String convertToDatabaseColumn(DeviceType deviceType) {
    if (deviceType == null)
      return null;
    return deviceType.name();
  }

  @Override
  public DeviceType convertToEntityAttribute(String dbData) {
    if (dbData == null)
      return null;
    return DeviceType.valueOf(dbData);
  }
}
