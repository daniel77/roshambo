package com.ciklum.roshambo.model;

import lombok.Builder;
import lombok.Data;

/**
 * Class that represents an error object
 */
@Data
@Builder
public class GameApiError {
  private int status;
  private String message;
}
