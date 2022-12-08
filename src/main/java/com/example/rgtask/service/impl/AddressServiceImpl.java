package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Address;
import com.example.rgtask.mapper.AddressMapper;
import com.example.rgtask.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.vo.AddressPageVO;
import com.example.rgtask.vo.AddressVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    private AddressMapper addressMapper;
    @Autowired
    private void setAddressMapper(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }

    @Override
    public Boolean insert(AddressVO addressVO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressVO,address);
        address.setCreatTime(LocalDateTime.now());
        return addressMapper.insert(address) > 0;
    }

    @Override
    public Boolean updateAllById(AddressVO addressVO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressVO,address);
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.updateById(address) > 0;
    }

    @Override
    public IPage<Address> findPage(AddressPageVO pageVO) {
        Page<Address> page = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        QueryWrapper<Address> wrapper = new QueryWrapper<>();

        return addressMapper.selectPage(page,wrapper);
    }
}
