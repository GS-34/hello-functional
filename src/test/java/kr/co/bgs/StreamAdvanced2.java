package kr.co.bgs;

import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.math.BigDecimal;
import java.util.Collection;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.OrderLine;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class StreamAdvanced2 {

  /**
   * 유저들의 친구들의 총합
   */
  @Test
  public void test() {
    Integer totalFriendCount = USERS.stream()
        .map(User::getFriendUserIds)
        .map(Collection::size)
        .reduce(0, (x, y) -> x + y);

    System.out.println("totalFriendCount is " + totalFriendCount);
  }

  /**
   * 모든 주문 가격의 총합
   */
  @Test
  public void test2() {
    BigDecimal totalAmount = ORDERS.stream()
        .map(Order::getOrderLines)
        .flatMap(Collection::stream)
        .map(OrderLine::getAmount)
//        .reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
        .reduce(BigDecimal.ZERO, BigDecimal::add);

  }

}
