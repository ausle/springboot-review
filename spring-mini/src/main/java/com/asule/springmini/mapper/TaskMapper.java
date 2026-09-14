package com.asule.springmini.mapper;

import com.asule.springmini.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 任务表，发送MQ
 * @create 2024-04-03 15:57
 */
@Mapper
public interface TaskMapper {

    List<Task> queryNoSendMessageTaskList();

    Task queryTaskByMsg(@Param("msgId") String msgId);


    List<Task> getTasks(@Param("userId")String userId);

    List<Task> getTasksOrder(@Param("userId")String userId,@Param("orderBy")String orderBy);
}
