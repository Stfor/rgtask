package com.example.rgtask.mapper;

import com.example.rgtask.pojo.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
@Mapper
public interface GroupMapper extends BaseMapper<Organization> {

}
