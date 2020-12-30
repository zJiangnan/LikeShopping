package cn.echo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Orderitem {

  private long id;
  private long productid;
  private String productname;
  private Double productprice;
  private long amount;
  private long orderid;

}
