package kr.co.bgs;

import static kr.co.bgs.Repository.USER1;

import java.util.Optional;
import kr.co.bgs.dto.User;
import org.junit.jupiter.api.Test;

public class OptionalTest {

  @Test
  public void constructor() {

    //null 이 아닌 옵셔널
    Optional.of(1);

    //null 을 담는 옵셔널
    Optional.empty();

    //null 일수도 있고 아닐 수도 있는 옵셔널
    Optional.ofNullable(null);
  }

  @Test
  public void test() {

    Optional<String> maybeEmail = Optional.of("some@email.com");

    String email = maybeEmail.get();
    System.out.println("maybeEmail is " + email);

    Optional<String> maybeEmail2 = Optional.empty();

    if (maybeEmail2.isPresent()) {
      System.out.println("maybeEmail2 is " + maybeEmail2.get());
    } else {
      System.out.println("maybeEmail2 is null");
    }

    Optional<String> nullEmail = Optional.ofNullable(null);

    String defaultEmail = "default@email.com";
    String email3 = nullEmail.orElse(defaultEmail);
    System.out.println("nullEmail is " + email3);

    String email4 = maybeEmail2.orElseGet(() -> defaultEmail);

    System.out.println("nullEmail is " + email4);

//    String email5 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
  }

  @Test
  public void test2() {
    Optional<User> user1 = Optional.ofNullable(maybeGetUser(true));

    //user 가 null 아 아니라면
    //이름을 추출해서 출력한다.
    user1
        .map(User::getName)
        .ifPresent(System.out::println);

  }

  @Test
  public void test3() {
    Optional<User> user = Optional.ofNullable(maybeGetUser(false));

    String nameIs = user
        .map(User::getName)
        .map(name -> "User name is " + name)
        .orElse("User name is empty");

    System.out.println(nameIs);
  }

  private static User maybeGetUser(boolean returnUser) {
    if (returnUser) {
      return USER1;
    } else {
      return null;
    }

  }

}
