package com.example.droolsdemo.entity;

import lombok.Data;


public class ErrorMessage {
  private String errorString;

  public ErrorMessage() {
    errorString = "";
  }

  public String getErrorString() {
    return errorString;
  }

  public void setErrorString(String errorString) {
    this.errorString = errorString;
  }
}
