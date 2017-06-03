package net.bozaixing.web.italker.push.bean.card;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

/**
 * 用户注册响应的实体封装类
 * Created by bozaixing on 2017-05-27.
 */
public class UserCard {


    // 用户的ID
    @Expose
    private String id;

    // 用户的手机号码
    @Expose
    private String phone;

    // 用户的名称
    @Expose
    private String name;

    // 用户头像
    @Expose
    private String portrait;

    // 用户描述
    @Expose
    private String description;

    // 性别
    @Expose
    private int sex;

    // 用户关注人的数量（即我关注的人的数量）
    @Expose
    private int following;

    // 用户粉丝的数量（即关注我的人的数量）
    @Expose
    private int follows;

    // 我与当前User的关系状态，是否已经关注了这个人
    @Expose
    private boolean isFollow;

    // 用户信息最后的更新时间
    @Expose
    private LocalDateTime modifyAt = LocalDateTime.now();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public LocalDateTime getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(LocalDateTime modifyAt) {
        this.modifyAt = modifyAt;
    }
}
