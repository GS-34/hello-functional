package kr.co.bgs;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.Order.OrderStatus;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class CollectorsTest {

  @Test
  public void test() {

    List<Integer> integers = Arrays.asList(1, 2, 2, 3, -3, 6, 8, 9, -10);

    //toList
    List<Integer> list = integers.stream()
        .collect(toList());

    System.out.println(list);

    //toSet
    Set<Integer> set = integers.stream()
        .collect(Collectors.toSet());

    System.out.println(set);

    //mapping to list
    List<Integer> absList = integers.stream()
        .collect(mapping(x -> Math.abs(x), toList()));

    System.out.println(absList);

    //mapping to set
    Set<Integer> absSet = integers.stream()
        .collect(mapping(x -> Math.abs(x), toSet()));

    System.out.println(absSet);

    //reducing
    Integer sum = integers.stream()
        .collect(reducing(0, (x, y) -> x + y));

    System.out.println(sum);

  }

  @Test
  public void test2() {

    List<Integer> integers = Arrays.asList(1, 2, 3, -4, 10);

    Map<Integer, String> map = integers.stream()
        .collect(toMap(identity(), x -> "number is " + x));

    System.out.println(map);

  }

  @Test
  public void test3() {
    Map<Long, User> userIdToUserMap = USERS.stream()
        .collect(toMap(User::getId, identity()));

    System.out.println(userIdToUserMap);
  }

  @Test
  public void test4() {
    Map<Long, OrderStatus> orderIdtoStatusMap = ORDERS.stream()
        .collect(toMap(Order::getId, Order::getStatus));

    System.out.println(orderIdtoStatusMap);
  }


  @Test
  public void test5() {

    List<Integer> integers = Arrays.asList(1, 2, 2, 3, 3, 56, 56, -4, 10, 100, 203, 4012, 1283, 202);

    //1의 자리 숫자가 같은것끼리 그룹핑 된 List
    Map<Integer, List<Integer>> unitDigitListMap = integers.stream()
        .collect(groupingBy(x -> x % 10));

    System.out.println(unitDigitListMap);

    //1의 자리 숫자가 같은것끼리 그룹핑 된 Set
    Map<Integer, Set<Integer>> unitDigitSetMap = integers.stream()
        .collect(groupingBy(x -> x % 10, toSet()));

    System.out.println(unitDigitSetMap);

    //1의 자리 숫자가 같은것끼리 그룹핑 후
    //String 으로 변환 된 리스트
    Map<Integer, List<String>> unitDisitToStringMap = integers.stream()
        .collect(
            groupingBy(
                x -> x % 10,
                mapping(x -> "number is " + x, toList())));

    System.out.println(unitDisitToStringMap.get(2));
  }


  /**
   * status 로 그룹핑된 주문
   */
  @Test
  public void test6() {

    Map<OrderStatus, List<Order>> statusToOrdersMap = ORDERS.stream()
        .collect(groupingBy(Order::getStatus));

    System.out.println(statusToOrdersMap);

  }

  /**
   * status 로 그룹핑 된 주문들의 가격의 총합
   */
  @Test
  public void test7() {

    Map<OrderStatus, BigDecimal> statusToTotalAmount = ORDERS.stream()
        .collect(
            groupingBy(
                Order::getStatus,
                mapping(Order::getAmount,
                    reducing(BigDecimal.ZERO, BigDecimal::add))));

    System.out.println(statusToTotalAmount);

  }

  /**
   * 파티션을 이용한 짝수 홀수 분리
   */
  @Test
  public void test8() {
    List<Integer> integers = Arrays.asList(1, 2, 2, 3, 3, 56, 56, -4, 10, 100, 203, 4012, 1283, 202);

    Map<Boolean, List<Integer>> numberMap = integers.stream()
        .collect(partitioningBy(x -> x % 2 == 0));

    System.out.println("even: " + numberMap.get(true));
    System.out.println("odd : " + numberMap.get(false));
  }

  /**
   * 친구숫자 > 3 인 유저는 "친구들과 놀아" 친구숫자 < 3 인 유저는 "친구들를 더 만들어봐"
   */
  @Test
  public void test9() {

    Map<Boolean, List<User>> userMap = USERS.stream()
        .collect(partitioningBy(user -> user.getFriendUserIds().size() > 3));

    userMap.get(true).stream()
        .map(User::getEmail)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(email -> System.out.println("Sending email 'play with friends', " + email));

    userMap.get(false).stream()
        .map(User::getEmail)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(email -> System.out.println("Sending email 'make more friends', " + email));


  }

  /**
   * 주문 상태별 & 만원 미만의 주문들 그룹핑
   */
  @Test
  public void test10() {
    Map<OrderStatus, Map<Boolean, List<Order>>> collect = ORDERS.stream()
        .collect(
            groupingBy(Order::getStatus,
                partitioningBy(o -> o.getAmount().compareTo(BigDecimal.valueOf(10000)) < 0)));

    System.out.println(collect);
  }
}
