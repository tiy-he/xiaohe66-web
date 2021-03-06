package com.xiaohe66.web.base.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 基础实体类，所有的实体类都直接或间接继承该类
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class BasePo implements Serializable, BaseParam {

    @TableId(type = IdType.AUTO)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\""
                + "}";
    }
}
