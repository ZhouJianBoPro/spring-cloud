package cn.com.pro.provider.db.mapper;

import cn.com.pro.provider.db.model.MessageConsume;
import cn.com.pro.provider.db.model.MessageConsumeExample;
import cn.com.pro.provider.db.model.MessageConsumeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageConsumeMapper {
    long countByExample(MessageConsumeExample example);

    int deleteByExample(MessageConsumeExample example);

    int deleteByPrimaryKey(MessageConsumeKey key);

    int insert(MessageConsume record);

    int insertSelective(MessageConsume record);

    List<MessageConsume> selectByExampleWithBLOBs(MessageConsumeExample example);

    List<MessageConsume> selectByExample(MessageConsumeExample example);

    MessageConsume selectByPrimaryKey(MessageConsumeKey key);

    int updateByExampleSelective(@Param("record") MessageConsume record, @Param("example") MessageConsumeExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageConsume record, @Param("example") MessageConsumeExample example);

    int updateByExample(@Param("record") MessageConsume record, @Param("example") MessageConsumeExample example);

    int updateByPrimaryKeySelective(MessageConsume record);

    int updateByPrimaryKeyWithBLOBs(MessageConsume record);

    int updateByPrimaryKey(MessageConsume record);
}