package cn.com.pro.provider.db.mapper;

import cn.com.pro.provider.db.model.TLoanSucceedExtraInfo;
import cn.com.pro.provider.db.model.TLoanSucceedExtraInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLoanSucceedExtraInfoMapper {
    long countByExample(TLoanSucceedExtraInfoExample example);

    int deleteByExample(TLoanSucceedExtraInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLoanSucceedExtraInfo record);

    int insertSelective(TLoanSucceedExtraInfo record);

    List<TLoanSucceedExtraInfo> selectByExample(TLoanSucceedExtraInfoExample example);

    TLoanSucceedExtraInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLoanSucceedExtraInfo record, @Param("example") TLoanSucceedExtraInfoExample example);

    int updateByExample(@Param("record") TLoanSucceedExtraInfo record, @Param("example") TLoanSucceedExtraInfoExample example);

    int updateByPrimaryKeySelective(TLoanSucceedExtraInfo record);

    int updateByPrimaryKey(TLoanSucceedExtraInfo record);
}