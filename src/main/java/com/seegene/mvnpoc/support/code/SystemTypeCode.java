package com.seegene.mvnpoc.support.code;

/**
 * 시스템 구분 코드
 */
public enum SystemTypeCode implements DescriptionCode {

  SYSTY000("시스템구분");

  private final String description;

  SystemTypeCode(String description) {
    this.description = description;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

}
