package cn.app.source.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author haodongdong
 * @since 2020-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("download_detail")
public class DownloadDetailDo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 下载时间
     */
    private LocalDateTime downloadTime;

    /**
     * 素材名称
     */
    private String downloadName;

    /**
     * 素材下载地址
     */
    private String downloadUrl;

    /**
     * 1-下载成功 2-下载失败
     */
    private Integer downloadStatus;


}
