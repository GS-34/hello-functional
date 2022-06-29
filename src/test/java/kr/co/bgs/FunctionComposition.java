package kr.co.bgs;

import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class FunctionComposition {

  /**
   * compose : 파라미터로 넘어온 함수를 먼저실행 , andThen 은 자신을 먼저 실행
   */
  @Test
  public void test() {

    Function<Integer, Integer> multiplyByTwo = (x) -> x * 2;
    Function<Integer, Integer> addByTen = (x) -> x + 10;

    // 2 + 10 * 2
    System.out.println("compose : " + multiplyByTwo.compose(addByTen).apply(2));

    //2 * 2 + 10
    System.out.println("andThen : " + multiplyByTwo.andThen(addByTen).apply(2));

  }


}
