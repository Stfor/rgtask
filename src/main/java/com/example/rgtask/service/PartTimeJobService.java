package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.PartTimeJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.PartTimeJobPageVO;
import com.example.rgtask.vo.PartTimeJobVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
public interface PartTimeJobService extends IService<PartTimeJob> {
    int insert(PartTimeJobVO partTimeJobVO);

    IPage<PartTimeJob> findPage(Page<PartTimeJob> page, PartTimeJobPageVO pageVO);
}
