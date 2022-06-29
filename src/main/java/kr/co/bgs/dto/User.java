package kr.co.bgs.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class User {

  private long id;

  private String name;

  private Optional<String> email;

  private boolean verified;

  private List<Integer> friendUserIds;

  private LocalDateTime createAt;

}
