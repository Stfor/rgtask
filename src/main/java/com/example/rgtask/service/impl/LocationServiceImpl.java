package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.mapper.UserMapper;
import com.example.rgtask.pojo.Comments;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Location;
import com.example.rgtask.mapper.LocationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.LocationService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.LocationPageVO;
import com.example.rgtask.vo.LocationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements LocationService {

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public int insert(LocationVO locationVO) {
        Location location = new Location();
        BeanUtils.copyProperties(locationVO,location);
        location.setId(UUID.randomUUID().toString());
        location.setCreateDate(LocalDateTime.now());
        location.setUserid(UserUtils.getPrincipal());
        if (locationMapper.insert(location)>0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public IPage<Location> findPage(LocationPageVO pageVO) {
        Page<Location> page = new Page<Location>(pageVO.getPageNo(),pageVO.getPageSize());

        QueryWrapper<Location> wrapper = new QueryWrapper<>();
// 根据id查询
        if (StringUtils.isNotEmpty(pageVO.getId())){
            wrapper.eq("id", pageVO.getId());
        }
// 用户id查询
        if (StringUtils.isNotEmpty(pageVO.getUserid())){
            wrapper.eq("userId",pageVO.getUserid());
        }
        return locationMapper.selectPage(page,wrapper);
    }

    @Override
    public List<Location> findLocation(String id) {
        QueryWrapper<Location> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return locationMapper.selectList(wrapper);
    }

    @Override
    public Boolean compareLocation(LocationVO locationVO) {
        QueryWrapper<Location> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",locationVO.getUserid());
        Location location = locationMapper.selectOne(wrapper);

        double stdLongtitude = location.getLongitude();
        double stdLatitude = location.getLatitude();

        double longitude = locationVO.getLongitude();
        double latitude = locationVO.getLatitude();
        //经度转弧度
        double longtitudeUser= Math.toRadians(longitude);
        double longtitudeStd = Math.toRadians(stdLongtitude);
        //纬度转弧度
        double latitudeUser = Math.toRadians(latitude);
        double latitudeStd = Math.toRadians(stdLatitude);
        //经度之差
        double longtitudeCut = longtitudeUser - longtitudeStd;
        //纬度之差
        double latitudeCut = latitudeUser - latitudeStd;
        //距离
        double middis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latitudeCut / 2), 2) +
                Math.cos(latitudeUser) * Math.cos(latitudeStd) * Math.pow(Math.sin(longtitudeCut / 2), 2)));
        //地球半径6378137
        double distance = middis*6378137;

        return distance<=10;
    }
}
