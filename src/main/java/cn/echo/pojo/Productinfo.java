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
public class Productinfo {

  private long id;
  private String name;
  private String description;
  private Date createtime;
  private Double marketprice;
  private Double sellprice;
  private long categoryid;
  private Boolean commend;
  private long sellcount;
  private long productount;
  private long shopid;

}
