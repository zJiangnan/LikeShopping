package cn.echo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Shoppingcar {

  private long id;
  private long productid;
  private long customerid;
  private Double productprice;
  private long productcount;
  private String productname;
}
