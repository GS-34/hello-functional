package kr.co.bgs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ParalelStream {

  /**
   * parallelStream 중간작업은 병렬이지만 collect 반환은 정렬됨
   */
  @Test
  public void test() {

    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    List<String> strings = integers.parallelStream()
        .map(n -> {
          System.out.println("[" + Thread.currentThread().getName() + "]map1 Number : " + n);
          return n;
        })
        .map(n -> {
          System.out.println("[" + Thread.currentThread().getName() + "]map2 Number : " + n);
          return n;
        })
        .map(n -> {
          System.out.println("[" + Thread.currentThread().getName() + "]map3 Number : " + n);
          return n;
        })
        .map(n -> {
          System.out.println("[" + Thread.currentThread().getName() + "]map4 Number : " + n);
          return n;
        })
        .map(n -> {
          System.out.println("[" + Thread.currentThread().getName() + "]map5 Number : " + n);
          return "number is " + n;
        })
        .collect(Collectors.toList());

    System.out.println(strings);
  }

}
