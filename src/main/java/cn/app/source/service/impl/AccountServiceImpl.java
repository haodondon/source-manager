package cn.app.source.service.impl;

import cn.app.source.model.AccountDo;
import cn.app.source.mapper.AccountMapper;
import cn.app.source.service.AccountService;
import cn.app.source.util.AppException;
import cn.app.source.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDo> implements AccountService {

    /**
     * 获取可用账号
     * @param type 账号类型 1-千图网 2-摄图 3-包图 4-千库 5-熊猫 6-昵图
     * @return
     * @throws Exception
     */
    @Override
    public List<AccountDo> getValidAccount(int type) throws Exception {

        AccountDo accountDo = new AccountDo();
        accountDo.setAccountType(type);
        accountDo.setAccountUse(BigDecimal.ROUND_DOWN);
        return this.list(new QueryWrapper<>(accountDo));

    }
}
