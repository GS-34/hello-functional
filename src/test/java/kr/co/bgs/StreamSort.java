package kr.co.bgs;

import static java.util.stream.Collectors.toList;
import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class StreamSort {

  @Test
  public void sort1() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 6, 7, 8, 6, 4);

    List<Integer> sortedNumbers = numbers.stream()
        .sorted()
        .collect(toList());

    System.out.println(sortedNumbers);

  }

  /**
   * 유저 이름순으로 정렬
   */
  @Test
  public void sort2() {

    List<User> sortedUsers = USERS.stream()
        .sorted(Comparator.comparing(User::getName))
//        .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
        .collect(toList());

    System.out.println(sortedUsers);


  }

  /**
   * 주문리스트 createAt 을 기준으로 가장 먼저 들어온 주문부터 정렬
   */
  @Test
  public void sort3() {

    List<Long> sortedOrders = ORDERS.stream()
        .sorted(Comparator.comparing(Order::getCreateAt))
//        .sorted((o1, o2) -> o1.getCreateAt().compareTo(o2.getCreateAt()))
        .map(Order::getId)
        .collect(toList());

    System.out.println(sortedOrders);
  }

}
