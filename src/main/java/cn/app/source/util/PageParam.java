package cn.app.source.util;

import lombok.Data;

/**
 * 分页参数
 * @author kevinsu
 * @date 2020/04/29
 */
@Data
public class PageParam<T> {

    /**
     * 当前页
     */
    private Long page;

    /**
     * 每页记录数
     */
    private Long limit;

    /**
     * 查询条件
     */
    private T query;

}
