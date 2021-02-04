package cn.app.source.service.impl;

import cn.app.source.mapper.AccountMapper;
import cn.app.source.model.AccountDo;
import cn.app.source.remote.Pic58Download;
import cn.app.source.service.AccountService;
import cn.app.source.service.DownloadService;
import cn.app.source.util.BaseUtil;
import cn.app.source.util.Result;
import cn.app.source.util.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haodongdong
 * @since 2020-11-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Pic58Download pic58Download;

    //千图网下载积分
    private Integer DOWNLOAD_QTW_INTEGRAL = 400;

    /**
     * 千图网下载
     * @param downloadUrl 下载地址
     * @return
     * @throws Exception
     */
    @Override
    public Result downloadPic58(String downloadUrl) throws Exception {

        //校验当前用户积分是否充足
        this.checkUserIntegral(DOWNLOAD_QTW_INTEGRAL);

        //获取当前可用账号
        List<AccountDo> accounts = this.accountService.getValidAccount(BigDecimal.ROUND_DOWN);

        //校验当前下载是否需要滑动验证,并且校验账号token是否可用
        downloadUrl = pic58Download.checkAccountValid(accounts, downloadUrl);

        return new Result(ResultCode.SUCCESS,downloadUrl);

    }

    /**
     * 校验用户积分是否充足
     * @param integral 积分
     * @throws Exception
     */
    private void checkUserIntegral(Integer integral) throws Exception{

        //获取用户ID
        String userId = BaseUtil.getCurrentUserId();

    }

}
