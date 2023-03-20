package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName:User
 * PackageName:com.leo.domain
 * Description:
 * @Date 2023/1/3 15:58
 * @Author  leo
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {
    public User(String userName, String password, String userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */

    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1工人，3监督员）
     */
    private String userType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;

    private String region;

    public User(long id, String userName, String userType) {
        this.setUserType(userType);
        this.setId(id);
        this.setUserName(userName);
    }

    public User(String userName, String passwd, String region, String userType) {
        this.userName = userName;
        this.password = passwd;
        this.region = region;
        this.userType = userType;
    }
}
