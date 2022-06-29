package kr.co.bgs;

import static java.util.stream.Collectors.toList;
import static kr.co.bgs.Repository.ORDERS;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.OrderLine;
import org.junit.jupiter.api.Test;

public class StreamFlatMap {

  @Test
  public void test() {

    String[][] cities = new String[][]{
        {"Seoul", "Busan"},
        {"San Francisco", "New York"},
        {"Madrid", "Barcelona"},
    };

    List<String> cities2 = Arrays.stream(cities)
        .flatMap(Stream::of)
        .collect(toList());

    System.out.println(cities2);

  }

  /**
   * 각 주문의 orderLine 을 합산
   */
  @Test
  public void test2() {

    BigDecimal sum = ORDERS.stream()
        .map(Order::getOrderLines)
        .flatMap(List::stream)
        .map(OrderLine::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    System.out.println(sum);


  }

}
