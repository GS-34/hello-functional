package kr.co.bgs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.Order.OrderStatus;
import kr.co.bgs.dto.OrderLine;
import kr.co.bgs.dto.OrderLine.OrderLingType;
import kr.co.bgs.dto.User;

public class Repository {

  public static final LocalDateTime NOW = LocalDateTime.now();

  public static final User USER1 = User.builder()
      .id(101)
      .name("Alice")
      .verified(false)
      .email(Optional.of("alice@fastcampus.co.kr"))
      .friendUserIds(Arrays.asList(101, 102, 103, 104))
      .createAt(NOW.minusHours(10))
      .build();

  public static final User USER2 = User.builder()
      .id(102)
      .name("Bob")
      .verified(true)
      .email(Optional.of("bob@fastcampus.co.kr"))
      .friendUserIds(Arrays.asList(101, 105))
      .createAt(NOW.minusHours(25))
      .build();

  public static final User USER3 = User.builder()
      .id(103)
      .name("Charlie")
      .verified(true)
      .email(Optional.of("charlie@fastcampus.co.kr"))
      .friendUserIds(Arrays.asList(101, 105, 106, 107, 108))
      .createAt(NOW.minusHours(10))
      .build();

  public static final User USER4 = User.builder()
      .id(104)
      .name("David")
      .verified(true)
      .email(Optional.of("David@fastcampus.co.kr"))
      .friendUserIds(Arrays.asList(101))
      .createAt(NOW.minusHours(15))
      .build();

  public static final List<User> USERS = Arrays.asList(USER1, USER2, USER3, USER4);


  public static final Order ORDER1 = Order.builder()
      .id(1001)
      .status(OrderStatus.CREATED)
      .createdByUserId(1)
      .amount(BigDecimal.valueOf(1000))
      .createAt(NOW.minusHours(4))
      .orderLines(
          Arrays.asList(
              OrderLine.builder()
                  .id(10001)
                  .orderLingType(OrderLingType.PURCHASE)
                  .amount(BigDecimal.valueOf(5000))
                  .build(),
              OrderLine.builder()
                  .id(10002)
                  .orderLingType(OrderLingType.PURCHASE)
                  .amount(BigDecimal.valueOf(4000))
                  .build()
          )
      )
      .build();

  public static final Order ORDER2 = Order.builder()
      .id(1002)
      .status(OrderStatus.ERROR)
      .createdByUserId(2)
      .amount(BigDecimal.valueOf(3000))
      .createAt(NOW.minusHours(10))
      .orderLines(
          Arrays.asList(
              OrderLine.builder()
                  .id(10003)
                  .orderLingType(OrderLingType.PURCHASE)
                  .amount(BigDecimal.valueOf(5000))
                  .build(),
              OrderLine.builder()
                  .id(10004)
                  .orderLingType(OrderLingType.DISCOUNT)
                  .amount(BigDecimal.valueOf(-2000))
                  .build()
          )
      )
      .build();

  public static final Order ORDER3 = Order.builder()
      .id(1003)
      .status(OrderStatus.CREATED)
      .createdByUserId(101)
      .amount(BigDecimal.valueOf(2000))
      .createAt(NOW.minusHours(24))
      .orderLines(
          Arrays.asList(
              OrderLine.builder()
                  .id(10001)
                  .orderLingType(OrderLingType.PURCHASE)
                  .amount(BigDecimal.valueOf(10000))
                  .build(),
              OrderLine.builder()
                  .id(10002)
                  .orderLingType(OrderLingType.DISCOUNT)
                  .amount(BigDecimal.valueOf(-4000))
                  .build()
          )
      )
      .build();

  public static final Order ORDER4 = Order.builder()
      .id(1004)
      .status(OrderStatus.ERROR)
      .createdByUserId(102)
      .amount(BigDecimal.valueOf(5000))
      .createAt(NOW.minusHours(26))
      .orderLines(
          Arrays.asList(
              OrderLine.builder()
                  .id(10001)
                  .orderLingType(OrderLingType.PURCHASE)
                  .amount(BigDecimal.valueOf(15000))
                  .build()
          )
      )
      .build();

  public static final Order ORDER5 = Order.builder()
      .id(1005)
      .status(OrderStatus.CREATED)
      .createdByUserId(102)
      .amount(BigDecimal.valueOf(10000))
      .createAt(NOW.minusHours(36))
      .orderLines(
          Arrays.asList(
              OrderLine.builder()
                  .id(10001)
                  .orderLingType(OrderLingType.PURCHASE)
                  .amount(BigDecimal.valueOf(9900))
                  .build()
          )
      )
      .build();

  public static final List<Order> ORDERS = Arrays.asList(ORDER1, ORDER2, ORDER3, ORDER4, ORDER5);

}
