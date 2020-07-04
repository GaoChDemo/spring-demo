package top.gaoch.service;

public class DemoStaticField {
  private Integer i;
  public Integer getI() {
    return i;
  }

  public static void main(String[] args) {
    new DemoStaticField().getI();
  }
}
