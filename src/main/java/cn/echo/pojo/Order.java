package cn.echo.pojo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Data
public class Order {

  private long id;
  private String name;
  private String address;
  private String mobile;
  private Double totalprice;
  private Date createtime;
  private String paymentway;
  private String state;
  private long cid;

}
