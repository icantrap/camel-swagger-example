package com.icantrap.examples.camel_swagger.beans;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A dummy bean ...")
public class Dummy {
  private int number;
  private String text;

  public Dummy(int requiredNumber) {
    this.number = requiredNumber;
  }

  @ApiModelProperty(value="an identifier", required = true)
  public int getNumber() {
    return number;
  }

  @ApiModelProperty(value="some text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
