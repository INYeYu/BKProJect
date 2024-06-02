package com.yy.constants;

public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 文章是正常分布状态=="0"
     */
    public static final String STATUS_NORMAL = "0";
    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 根目录为-1
     */
    public  static  final  int ROOT_COMMENT= -1 ;
    /**
     * 评论类型为文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为友链评论
     */
    public static final String LINK_COMMENT = "1" ;
    /**
     * 权限类型，菜单
     */
    public static final Object MENU = "C";
    /**
     * 权限类型，按钮
     */
    public static final Object BUTTON = "F";

    /**
     * 正常状态
     */
    public static final String NORMAL = "0";
    /**
     * 判断为管理员用户
     */
    public static final String IS_ADMAIN = "1";
}