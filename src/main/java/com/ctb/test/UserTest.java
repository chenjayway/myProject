package com.ctb.test;

import com.ctb.model.UserInfo;
import com.ctb.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ctb on 2017/6/26.
 */
public class UserTest {

    @Autowired
    private UserService userSerivce;

    @Test
    public void testBind() {
        UserInfo user = new UserInfo();

        user.setUserOpenId("oXMGUjlDaY_8C1mkH4arXMpSw6rQ");
        user.setUserMobile("15510763135");
        boolean b = userSerivce.bingUser(user);
        System.out.println(b);
    }
}
