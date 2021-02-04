package cn.app.source.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author haodongdong
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
public class AccountDo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId
      private String id;

    /**
     * 第三方账号
     */
    private String accountName;

    /**
     * 第三方密码
     */
    private String accountPassWord;

    /**
     * 1-千图网 2-摄图 3-包图 4-千库 5-熊猫 6-昵图
     */
    private Integer accountType;

    /**
     * 第三方token
     */
    private String accountToken;

    /**
     * 最后一次登录时间
     */
    private Date accountLastLoginTime;

    /**
     * 创建时间
     */
    private Date accountCreateTime;

    /**
     * 错误信息
     */
    private String accountErrorInfo;

    /**
     * 1-可用 2-停用
     */
    private Integer accountUse;


}
