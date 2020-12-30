package cn.echo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Root {

  private long id;
  private String name;
  private String password;
  private Boolean status;

}
