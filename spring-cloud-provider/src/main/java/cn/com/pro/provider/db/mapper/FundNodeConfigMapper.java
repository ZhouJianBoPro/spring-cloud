package cn.com.pro.provider.db.mapper;

import cn.com.pro.provider.db.model.FundNodeConfig;
import cn.com.pro.provider.db.model.FundNodeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundNodeConfigMapper {
    long countByExample(FundNodeConfigExample example);

    int deleteByExample(FundNodeConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FundNodeConfig record);

    int insertSelective(FundNodeConfig record);

    List<FundNodeConfig> selectByExample(FundNodeConfigExample example);

    FundNodeConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FundNodeConfig record, @Param("example") FundNodeConfigExample example);

    int updateByExample(@Param("record") FundNodeConfig record, @Param("example") FundNodeConfigExample example);

    int updateByPrimaryKeySelective(FundNodeConfig record);

    int updateByPrimaryKey(FundNodeConfig record);
}