package com.asule.springbootreview.service.impl;

import com.asule.springbootreview.bean.Hotel;
import com.asule.springbootreview.mapper.HotelMapper;
import com.asule.springbootreview.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author asule
 * @since 2025-12-01
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

}
