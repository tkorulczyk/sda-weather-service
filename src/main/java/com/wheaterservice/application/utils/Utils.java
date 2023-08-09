package com.wheaterservice.application.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created on 27.07.2023
 *
 * @author Tomasz Korulczyk
 */

public class Utils {

  public static String arrowDrawer(float windDir) {
    String arrow = null;
    if (windDir >= 337.5 && windDir <= 22.5) {
      arrow = "↑";
    }
    if (windDir >= 67.5 && windDir <= 112.5) {
      arrow = "→";
    }
    if (windDir >= 157.5 && windDir <= 202.5) {
      arrow = "↓";
    }
    if (windDir >= 247.5 && windDir <= 292.5) {
      arrow = "←";
    }
    if (windDir > 22.5 && windDir < 67.5) {
      arrow = "↗";
    }
    if (windDir > 112.5 && windDir < 157.5) {
      arrow = "↘";
    }
    if (windDir > 202.5 && windDir < 247.5) {
      arrow = "↙";
    }
    if (windDir > 292.5 && windDir < 337.5) {
      arrow = "↖";
    }
    return arrow;
  }

  public static String reverseDateFormat(String date) {
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate localDate = LocalDate.parse(date, inputFormatter);

    return localDate.format(outputFormatter);
  }

}
