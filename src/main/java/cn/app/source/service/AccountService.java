package cn.app.source.service;

import cn.app.source.model.AccountDo;
import cn.app.source.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haodongdong
 * @since 2020-11-21
 */
public interface AccountService extends IService<AccountDo> {


    /**
     * 获取可用账号
     * @param type 账号类型 1-千图网 2-摄图 3-包图 4-千库 5-熊猫 6-昵图
     * @return
     * @throws Exception
     */
    List<AccountDo> getValidAccount(int type) throws Exception;

}
