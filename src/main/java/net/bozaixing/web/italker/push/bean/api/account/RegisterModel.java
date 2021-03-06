package net.bozaixing.web.italker.push.bean.api.account;

import com.google.gson.annotations.Expose;

/**
 * 用户注册请求的实体封装类
 * Created by bozaixing on 2017-05-27.
 */
public class RegisterModel {

    // 账户
    @Expose
    private String account;
    // 密码
    @Expose
    private String password;
    // 名称
    @Expose
    private String name;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
