package cn.com.pro.provider.db.mapper;

import cn.com.pro.provider.db.model.FundNodeRoute;
import cn.com.pro.provider.db.model.FundNodeRouteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundNodeRouteMapper {
    long countByExample(FundNodeRouteExample example);

    int deleteByExample(FundNodeRouteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FundNodeRoute record);

    int insertSelective(FundNodeRoute record);

    List<FundNodeRoute> selectByExample(FundNodeRouteExample example);

    FundNodeRoute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FundNodeRoute record, @Param("example") FundNodeRouteExample example);

    int updateByExample(@Param("record") FundNodeRoute record, @Param("example") FundNodeRouteExample example);

    int updateByPrimaryKeySelective(FundNodeRoute record);

    int updateByPrimaryKey(FundNodeRoute record);
}