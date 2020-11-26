package cn.app.source.service;

import cn.app.source.model.AccountDo;
import cn.app.source.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haodongdong
 * @since 2020-11-21
 */
public interface DownloadService{

    /**
     * 千图网下载
     * @param downloadUrl   下载地址
     * @return
     * @throws Exception
     */
    Result downloadPic58(String downloadUrl) throws Exception;
}
