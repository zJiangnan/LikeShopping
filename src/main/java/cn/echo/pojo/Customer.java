package cn.echo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Customer {

  private long id;
  private String username;
  private String password;
  private String realname;
  private String address;
  private String email;
  private String mobile;

}
