package com.offcn.reptile.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 15:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_new")
public class New implements Serializable {

    private static final long serialVersionUID = -6957361951748382519L;
    /**
     * 新闻编号
     */
    @Id
    private String id;

    private String catory;
    /**
     * 新闻的标题
     */
    private String title;
    /**
     * 新闻的介绍
     */
    private String intro;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 新闻图片
     */

    private List<String> imgs;

}
