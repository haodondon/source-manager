package cn.app.source.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页对象
 * @author kevinsu
 * @date 2020/04/29
 */
@Data
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long total;

    private Collection<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
     */
    public PageData(Collection<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public PageData(){
        super();
    }
}