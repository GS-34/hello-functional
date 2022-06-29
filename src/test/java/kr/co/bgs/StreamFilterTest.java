package kr.co.bgs;

import static java.util.stream.Collectors.toList;
import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import kr.co.bgs.dto.Order.OrderStatus;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class StreamFilterTest {

  @Test
  public void streamConstruct() {

    Stream<String> nameStream = Stream.of("alice", "bob", "charlie");

    List<String> nameList = nameStream.collect(toList());

    String[] strArray = new String[]{"a", "b", "c"};
    Stream<String> strStream = Arrays.stream(strArray);
  }

  /**
   * 문제1) 양수만 출력
   */
  @Test
  public void filter1() {
    Stream<Integer> integerStream = Stream.of(1, 2, 3, -5, -10);

    integerStream
        .filter(x -> x > 0)
        .forEach(System.out::println);


  }

  /**
   * 문제2) 검증 된 유저 이 출력
   */
  @Test
  public void filter2() {

    USERS.stream()
        .filter(User::isVerified)
        .forEach(user -> System.out.println(user.getName()));
  }

  /**
   * 에러상태의 주문만 출력
   */
  @Test
  public void filter3() {

    ORDERS.stream()
        .filter(order -> order.getStatus() == OrderStatus.ERROR)
        .forEach(order -> System.out.println(order.getId()));

  }

}
