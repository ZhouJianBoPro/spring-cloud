package cn.com.pro.provider.db.service.impl;


import cn.com.pro.provider.db.mapper.TLoanSucceedExtraInfoMapper;
import cn.com.pro.provider.db.model.TLoanSucceedExtraInfo;
import cn.com.pro.provider.db.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/1/22 14:14
 **/
@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TLoanSucceedExtraInfoMapper loanSucceedExtraInfoMapper;

    @Override
    public TLoanSucceedExtraInfo getAll() {
        return loanSucceedExtraInfoMapper.selectByPrimaryKey(2);
    }
}
