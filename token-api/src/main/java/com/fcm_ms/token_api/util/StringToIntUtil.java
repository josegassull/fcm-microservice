package com.fcm_ms.token_api.util;

public class StringToIntUtil {

  public static boolean isValidInteger(String str) {
    try {
      if (str.trim().isEmpty())
        return false;
      Integer.parseInt(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
