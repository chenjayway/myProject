package com.ctb.service;

import com.ctb.exception.AppPayException;
import com.ctb.exception.AppPayExceptionKeys;
import com.ctb.mapper.UserInfoMapper;
import com.ctb.mapper.UserMapper;
import com.ctb.model.User;
import com.ctb.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lms on 2017/6/26.
 */

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserMapper userMapper;
    public boolean bingUser(UserInfo userInfo) throws AppPayException {
        int count = userInfoMapper.getUser(userInfo.getUserOpenId(), userInfo.getUserMobile());
        if (count > 0) {
            throw new AppPayException(AppPayExceptionKeys.USER_BIND_FINISHED_CODE,
                    AppPayExceptionKeys.USER_BIND_FINISHED_MSG);
        }

        userInfo.setBindStatus(1);
        int insert = userInfoMapper.insert(userInfo);
        return insert > 0;
    }

    public User getUserInfo() {
        return userMapper.findUserInfo();
    }
}
