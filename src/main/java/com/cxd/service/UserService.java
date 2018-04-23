package com.cxd.service;

import com.cxd.pojo.User;

/**
 * Created by root on 17-7-13.
 */
public interface UserService {

    public User getUserById(String u_id);
    /**
     * 功能说明 :根据id查找
     * @param u_id
     * @return
     */
}
