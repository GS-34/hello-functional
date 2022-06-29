package kr.co.bgs;

import static kr.co.bgs.Repository.USERS;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import kr.co.bgs.dto.SimpleUser;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

/**
 * 메서드 레퍼런스
 */
public class MethodReference {

  @Test
  public void staticMethod() {

    Function<String, Integer> str2Int = Integer::parseInt;

    String str = "100";

    int num = str2Int.apply(str);

    System.out.println("num is " + num);

  }

  @Test
  public void instanceMethod() {

    String str = "hello";

    Predicate<String> equalsToHello = str::equals;

    System.out.println(equalsToHello.test("hello"));

    System.out.println(equalsToHello.test("world"));

  }

  /**
   * 파라미터로 넘어온 인스턴스의 함수 레퍼런스
   */
  @Test
  public void instanceMethod2() {
    printUserField(USERS, User::getId);
    printUserField(USERS, User::getName);
  }

  private static void printUserField(List<User> users, Function<User, Object> getter) {
    users.stream()
        .forEach(user -> System.out.println(getter.apply(user)));
  }


  @Test
  public void newMethod() {
    BiFunction<Long, String, SimpleUser> userCreator = SimpleUser::new;

    SimpleUser user1 = userCreator.apply(1L, "aaa");
    System.out.println(user1);
  }

}
