package kr.co.bgs.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Order {

  private long id;

  private LocalDateTime createAt;

  private long createdByUserId;

  private OrderStatus status;

  private BigDecimal amount;

  private List<OrderLine> orderLines;

  public enum OrderStatus {
    CREATED,
    PROGRESS,
    ERROR,
    PROCESSED
  }

}
