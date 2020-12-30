package cn.echo.utli;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * ClassName: PageUtil
 * Description:
 * date: 2020/12/18 16:32
 *
 * @author wuyafeng
 * @version 1.0   softeem.com
 */
@Setter
@Getter
@Data
@ToString
public class PageUtil<T> {
//    默认的数据--默认值为0
    private int code;
//    数据信息
    private String msg;
//    数据总条数
    private int count;
//    一页显示行数  默认10行
    private int limit=5;
//    存放数据
    private List<T> data;
}
