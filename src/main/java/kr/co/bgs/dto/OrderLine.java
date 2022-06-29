package kr.co.bgs.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderLine {

  private long id;

  private OrderLingType orderLingType;

  private long productId;

  private int quantity;

  private BigDecimal amount;

  public enum OrderLingType {
    PURCHASE, DISCOUNT
  }
}
