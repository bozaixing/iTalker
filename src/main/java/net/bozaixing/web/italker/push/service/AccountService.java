package net.bozaixing.web.italker.push.service;


import net.bozaixing.web.italker.push.bean.api.account.RegisterModel;
import net.bozaixing.web.italker.push.bean.card.UserCard;
import net.bozaixing.web.italker.push.bean.db.User;
import net.bozaixing.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


// 127.0.0.1/api/account/......
@Path("/account")
public class AccountService {


    /**
     * 用户注册
     * @return
     */
    @POST
    @Path("/register")
    // 请求数据为json格式
    @Consumes(MediaType.APPLICATION_JSON)
    // 返回数据也为json格式
    @Produces(MediaType.APPLICATION_JSON)
    public UserCard register(RegisterModel registerModel){

        // 查询操作
        User user = UserFactory.findByPhone(registerModel.getAccount());

        if (user != null){
            UserCard userCard = new UserCard();
            userCard.setName("这个手机号码已经注册过");

            return userCard;
        }

        // 进行注册操作
        user = UserFactory.register(registerModel.getAccount(),
                registerModel.getPassword(),
                registerModel.getName());

        if (user != null){
            UserCard userCard = new UserCard();
            userCard.setPhone(registerModel.getAccount());
            userCard.setName(registerModel.getName());
            userCard.setSex(1);
            userCard.setFollow(true);
            return userCard;
        }

        return null;
    }

}
