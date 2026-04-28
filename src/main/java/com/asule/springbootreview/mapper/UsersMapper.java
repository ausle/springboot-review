package com.asule.springbootreview.mapper;

import com.asule.springbootreview.bean.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Users record);
//
//    int insertSelective(Users record);
//
    Users selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Users record);
//
//    int updateByPrimaryKey(Users record);
}