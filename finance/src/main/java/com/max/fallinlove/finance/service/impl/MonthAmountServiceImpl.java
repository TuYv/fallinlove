package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.base.utils.DateUtil;
import com.max.fallinlove.finance.dto.MonthDailyCountDTO;
import com.max.fallinlove.finance.dto.MonthDetailDTO;
import com.max.fallinlove.finance.dto.TagDetailDTO;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.mapper.MonthAmountDetailMapper;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.service.IMonthAmountService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
@Service
public class MonthAmountServiceImpl extends ServiceImpl<MonthAmountMapper, MonthAmount> implements IMonthAmountService {

    @Resource
    private MonthAmountMapper monthAmountMapper;
    @Resource private MonthAmountDetailMapper monthAmountDetailMapper;
    @Resource private MonthAmountDetailServiceImpl monthAmountDetailService;
    @Resource
    private MonthAmountRepository monthAmountRepository;

    @Override
    public List<MonthAmount> getAllMonthAmountByAccountId(int accountId) {
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        return monthAmountMapper.selectList(queryWrapper);
    }

    @Override
    public MonthAmount getByTime(Integer id, String year, String month) {
        //从数据库中查询
        MonthAmount monthAmount = monthAmountRepository.getByTime(id, year, month);
        if (Objects.isNull(monthAmount)) {
            //todo 可以设置成月初自动生成当月账单
            monthAmount = defaultMonthAmount(id, year, month);
        }
        return monthAmount;
    }

    @Override
    public MonthDetailDTO getMonthDetail(Integer accountId, String year, String month) {
        MonthDetailDTO monthDetailDTO = new MonthDetailDTO();
        MonthAmount monthAmount = this.getByTime(accountId, year, month);
        if (Objects.isNull(monthAmount) || Objects.isNull(monthAmount.getId())) {
            return monthDetailDTO;
        }
        monthDetailDTO.setIncome(monthAmount.getIncome());
        monthDetailDTO.setSpend(monthAmount.getSpend());

        List<TagDetailDTO> tagDetailList = new ArrayList<>();
        List<MonthAmountDetail> detailList = monthAmountDetailService.getMonthAmountDetailList(monthAmount.getId());
        Map<String, List<MonthAmountDetail>> tagAmountMap = detailList.stream().collect(Collectors.groupingBy(MonthAmountDetail::getTagName));
        for (Entry<String, List<MonthAmountDetail>> tagAmountEntry : tagAmountMap.entrySet()) {
            TagDetailDTO tagDetail = new TagDetailDTO();
            tagDetail.setTagName(tagAmountEntry.getKey());
            tagDetail.setAmountType(tagAmountEntry.getValue().get(0).getAmountType());
            tagDetail.setAmount(tagAmountEntry.getValue().stream().map(MonthAmountDetail::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
            tagDetailList.add(tagDetail);
        }
        monthDetailDTO.setTagDetailDTOList(tagDetailList);

        return monthDetailDTO;
    }

    @Override
    public List<MonthDailyCountDTO> getMonthDailyCount(Integer accountId, String year, String month) {
        MonthAmount monthAmount = this.getByTime(accountId, year, month);
        List<MonthAmountDetail> detailList = monthAmountDetailService.getMonthAmountDetailList(monthAmount.getId());
        Integer monthDay = DateUtil.getMonthDay(Integer.valueOf(monthAmount.getYear()), Integer.valueOf(monthAmount.getMonth()));
        List<MonthDailyCountDTO> monthDailyList = MonthDailyCountDTO.buildMonthDayCount(monthDay);
        Optional.ofNullable(detailList).orElse(new ArrayList<>()).forEach(monthAmountDetail -> {
            int day = monthAmountDetail.getTime().getDayOfMonth();
            monthDailyList.get(day).add();
        });

        return monthDailyList;
    }

    /**
     * 设置默认月账单
     * @param id
     * @param year
     * @param month
     * @return
     */
    private MonthAmount defaultMonthAmount(Integer id, String year, String month) {
        MonthAmount monthAmount = new MonthAmount();
        monthAmount.setMonth(month);
        monthAmount.setAccountId(id);
        monthAmount.setYear(year);
        monthAmount.setSpend(BigDecimal.ZERO);
        monthAmount.setIncome(BigDecimal.ZERO);

        return monthAmount;
    }

}
