package kr.co.bgs;

import static kr.co.bgs.Repository.ORDERS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.bgs.dto.Order;
import org.junit.jupiter.api.Test;

public class StreamDistinct {

  /**
   * 중복제거하려는 객체가 equals 가 구현 안돼있으면 작동안함.
   */
  @Test
  public void test() {
    List<Integer> numbers = Arrays.asList(1, 5, 3, 6, 9, 0, 7, 1, 1, 2, 3, 43);

    List<Integer> distinctNumbers = numbers.stream()
        .distinct()
        .collect(Collectors.toList());

    System.out.println(distinctNumbers);
  }

  /**
   * 1. 주문 리스트에서 유저 아이디를 추출 2. 중복을 제거 3. 정렬
   */
  @Test
  public void test2() {

    List<Long> distinctUserIds = ORDERS.stream()
        .map(Order::getId)
        .distinct()
        .sorted()
        .collect(Collectors.toList());

    System.out.println(distinctUserIds);
  }

}
