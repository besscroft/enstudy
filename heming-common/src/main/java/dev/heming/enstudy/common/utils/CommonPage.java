package dev.heming.enstudy.common.utils;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @Description 分页处理
 * @Author Bess Croft
 * @Date 2023/8/26 17:08
 */
public class CommonPage<T> {

    @Schema(title = "页码", type = "Integer")
    private Integer pageNum;

    @Schema(title = "页大小", type = "Integer")
    private Integer pageSize;

    @Schema(title = "总页数", type = "Integer")
    private Integer totalPage;

    @Schema(title = "数据总数", type = "Long")
    private Long total;

    @Schema(title = "数据列表", type = "List")
    private List<T> list;

    /**
     * 将 PageHelper 分页后的 list 转为分页信息
     * @param list 数据
     * @param <T> 类型
     * @return 分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
