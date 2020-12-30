package cn.echo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Shop {

  private long id;
  private String name;
  private String address;
  private Boolean status;

}
