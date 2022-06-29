package kr.co.bgs;

import static java.util.stream.Collectors.toList;
import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.util.Arrays;
import java.util.List;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class StreamMapTest {

  /**
   * X 2 된 int list 만들
   */
  @Test
  public void map1() {

    List<Integer> numberList = Arrays.asList(1, 2, 3, 5, 6, 7, 8);
    List<Integer> numberListX2 = numberList.stream()
        .map(x -> x * 2)
        .collect(toList());

    System.out.println(numberListX2);

    numberList.stream()
        .map(x -> "Number is " + x)
        .forEach(System.out::println);

  }

  /**
   * 유저 목록에서 이메일 리스트 추츌
   */
  @Test
  public void map2() {

    List<String> emails = USERS.stream()
        .map(User::getEmail)
        .map(o -> o.orElse("empty"))
        .collect(toList());

    System.out.println(emails);
  }


  /**
   * 주문 리스트에서 주문자 아이디 리스트 추출
   */
  @Test
  public void map3() {

    List<Long> createdUserIds = ORDERS.stream()
        .map(Order::getCreatedByUserId)
        .collect(toList());

    System.out.println(createdUserIds);


  }

}
