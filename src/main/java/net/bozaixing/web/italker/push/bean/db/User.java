package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户个人信息的Model
 * Created by bozaixing on 2017-05-23.
 */
// 实体类
@Entity
// 表名
@Table(name = "TABLE_USER")
public class User {

    // 用户的ID, 主键
    @Id
    @PrimaryKeyJoinColumn
    // 主键生成的储存类型为UUID
    @GeneratedValue(generator = "uuid")
    // 把UUID的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // 不允许更改，不允许为null
    @Column(updatable = false, nullable = false)
    private String id;


    // 用户名，不能为空，长度为128，用户名不能重复，具有唯一性
    @Column(nullable = false, length = 128, unique = true)
    private String name;


    // 电话，不能为空，长度为62，用户名不能重复，具有唯一性
    @Column(nullable = false, length = 62, unique = true)
    private String phone;


    // 密码，不能为空
    @Column(nullable = false)
    private String password;


    // 头像，允许为空
    @Column
    private String portrait;


    // 描述，允许为空
    @Column
    private String description;


    // 性别，有初始值，不能为空
    @Column(nullable = false)
    private int sex = 0;


    // token 可以拉取用户信息，所以token必须唯一
    @Column(unique = true)
    private String token;


    // 用于推送的设备ID
    @Column
    private String pushId;


    // 定义为创建时间戳，在创建时就已经写入，不能为空
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();


    // 定义为更新时间戳，在更新时就已经写入，不能为空
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    // 最后一次接收到消息的时间，可以为空
    @Column(nullable = true)
    private LocalDateTime lastReceivedAt = LocalDateTime.now();



    // 我关注人的列表方法
    // 对应的数据库表字段为 TABLE_USER_FOLLOW.originId
    @JoinColumn(name = "originId")
    // 定义为懒加载，默认加载User信息的时候不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    // 1对多，一个用户可以关注很多人
    // // cascade = CascadeType.ALL，联级级别为ALL，代表所有的更新，删除等操作都将进行关系更新
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> following = new HashSet<>();


    // 关注我的人的列表方法
    // 对应的数据库表字段为 TABLE_USER_FOLLOW.targetId
    @JoinColumn(name = "targetId")
    // 定义为懒加载，默认加载User信息的时候不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    // 1对多，一个用户可以被很多人关注，每一次被关注都是一个记录
    // cascade = CascadeType.ALL，联级级别为ALL，代表所有的更新，删除等操作都将进行关系更新
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> followers = new HashSet<>();



    // 我创建的所有群
    // 对应的数据库表字段为 TABLE_GROUP.ownerId
    @JoinColumn(name = "ownerId")
    // 懒加载集合方式为尽可能的不加载具体的数据，
    // 当访问groups.size()时仅仅查询数量，不加载具体的Group信息
    // 只有当遍历集合的时候才加载具体的数据
    @LazyCollection(LazyCollectionOption.EXTRA)
    // fetch = FetchType.LAZY，懒加载，加载用户信息时不加载定义的这个集合
    // cascade = CascadeType.ALL，联级级别为ALL，代表所有的更新，删除等操作都将进行关系更新
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getLastReceivedAt() {
        return lastReceivedAt;
    }

    public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
        this.lastReceivedAt = lastReceivedAt;
    }

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    }

    public Set<UserFollow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserFollow> followers) {
        this.followers = followers;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
