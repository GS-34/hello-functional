package kr.co.bgs;

import static kr.co.bgs.Repository.NOW;
import static kr.co.bgs.Repository.ORDERS;
import static kr.co.bgs.Repository.USERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import kr.co.bgs.dto.Order;
import kr.co.bgs.dto.Order.OrderStatus;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class StreamAdvanced {

  @Test
  public void max() {

    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    Optional<Integer> max = integers.stream()
//        .max(Comparator.comparingInt(x -> x));
        .max((x, y) -> x - y);

    System.out.println(max.get());

    Optional<Integer> min = integers.stream()
//        .max(Comparator.comparingInt(x -> x));
        .min((x, y) -> x - y);

    System.out.println(min.get());

  }

  /**
   * 이름순으로 가장 빠른 유저를 추출
   */
  @Test
  public void test() {

    User user = USERS.stream()
        .min(Comparator.comparing(User::getName))
//        .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
        .get();

    System.out.println(user);

  }


  /**
   * 24시간 이내 가입 된 유저들 중 검증되지 않은 유저의 수를 추출
   */
  @Test
  public void test2() {

    long count = USERS.stream()
        .filter(user -> user.getCreateAt().isAfter(NOW.minusHours(24)))
        .filter(user -> !user.isVerified())
        .count();

    System.out.println("count is " + count);
  }

  /**
   * 주문들중 Error 상태이면서 가격이 가장 큰 주문을 출력
   */
  @Test
  public void test3() {

    Order order1 = ORDERS.stream()
        .filter(order -> order.getStatus() == OrderStatus.ERROR)
        .max(Comparator.comparing(Order::getAmount))
//        .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
        .get();

    System.out.println(order1);
  }

  /**
   * allMatch : 모두 참일 경우
   */
  @Test
  public void allMatch() {

    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, -9);

    //모두 양수인가?
    boolean allPositive = integers.stream()
        .allMatch(i -> i > 0);

    System.out.println("allPositive is " + allPositive);

    //모두 음수인가?
    boolean allNegative = integers.stream()
        .allMatch(i -> i < 0);

    System.out.println("allNegative is " + allNegative);


  }

  /**
   * anyMatch : 하나라도 참일 경우
   */
  @Test
  public void anyMatch() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, -9);

    //하나라도 양수인가?
    boolean anyPositive = integers.stream()
        .anyMatch(i -> i > 0);

    System.out.println("anyPositive is " + anyPositive);

    //하나라도 음수인가?
    boolean anyNegative = integers.stream()
        .anyMatch(i -> i < 0);

    System.out.println("allNegative is " + anyNegative);
  }

  /**
   * 유저들 모두 검증 받았는지 체크
   */
  @Test
  public void test4() {

    boolean allVerified = USERS.stream()
        .allMatch(User::isVerified);

    System.out.println("allVerified is " + allVerified);

  }

  @Test
  public void reduce() {

    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, -8);

    //reduce 를 이용한 합산
    Integer sum = integers.stream()
        .reduce((x, y) -> x + y)
        .orElse(Integer.MIN_VALUE);

    System.out.println("sum is " + sum);

    //max 구하기
    Integer max = integers.stream()
        .reduce((x, y) -> x > y ? x : y)
        .get();

    System.out.println("max is " + max);

    //곱하기
    Integer mul = integers.stream()
        .reduce(1, (x, y) -> x * y);

    System.out.println("mull is " + mul);

  }

}
