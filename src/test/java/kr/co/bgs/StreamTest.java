package kr.co.bgs;

import static kr.co.bgs.Repository.NOW;
import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.Order.OrderStatus;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class StreamTest {

  /**
   * 검증된 유저의 이메일만 추출
   */
  @Test
  public void test1() {

    List<String> emails = USERS.stream()
        .filter(User::isVerified)
        .map(User::getEmail)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());

    System.out.println(emails);

  }

  /**
   * 에러상태 && 만들어진 24시간 이내의 오더의 유저아아디만 추출
   */
  @Test
  public void test2() {

    List<Long> userIds = ORDERS.stream()
        .filter(order -> order.getStatus() == OrderStatus.ERROR)
        .filter(order -> order.getCreateAt().isAfter(NOW.minusHours(24)))
        .map(Order::getCreatedByUserId)
        .collect(Collectors.toList());

    System.out.println(userIds);
  }


}
