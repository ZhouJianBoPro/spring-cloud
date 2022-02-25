package cn.com.pro.provider.service.selector;

import cn.com.pro.provider.enums.FundEnum;
import cn.com.pro.provider.service.FundCreditService;
import cn.com.pro.provider.service.FundPreCreditService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author zhoujianbo
 * @Title: 资金方服务选择处理类
 * @Description:
 * @date Created in 2022/2/25 16:16
 **/
@Component
public class FundServiceSelectorHandler implements ApplicationContextAware {

    private Map<FundEnum, FundCreditService> creditServiceMap;

    private Map<FundEnum, FundPreCreditService> preCreditServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Collection<FundCreditService> creditServices = applicationContext.getBeansOfType(FundCreditService.class).values();
        this.creditServiceMap = creditServices.stream().collect(Collectors.toMap(FundCreditService::getFund, Function.identity()));

        Collection<FundPreCreditService> preCreditServices = applicationContext.getBeansOfType(FundPreCreditService.class).values();
        this.preCreditServiceMap = preCreditServices.stream().collect(Collectors.toMap(FundPreCreditService::getFund, Function.identity()));
    }

    /**
     * 获取指定资方授信bean
     * @param fundId
     * @return
     */
    public FundCreditService getCreditBean(String fundId) {
        return creditServiceMap.get(FundEnum.getByCode(fundId));
    }

    /**
     * 获取指定资方预授信bean
     * @param fundId
     * @return
     */
    public FundPreCreditService getPreCreditBean(String fundId) {
        return preCreditServiceMap.get(FundEnum.getByCode(fundId));
    }


}
