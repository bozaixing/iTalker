package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 群组成员Model
 * Created by bozaixing on 2017-05-25.
 */


// 实体
@Entity
// 数据库中表名
@Table(name = "TABLE_GROUP_MEMBER")
public class GroupMember {

    /**
     * 定义群组消息通知的类型
     */
    private static final int NOTIFY_LEVEL_INVALID = -1;     // 不接收群消息
    private static final int NOTIFY_LEVEL_NONE = 0;         // 默认通知级别
    private static final int NOTIFY_LEVEL_CLOSE = 1;        // 接收消息不提示


    /**
     * 定义群成员权限的类型
     */
    private static final int PERMISSIONS_TYPE_NONE = 0;         // 普通成员，默认权限
    private static final int PERMISSIONS_TYPE_ADMIN = 1;        // 管理员权限
    private static final int PERMISSIONS_TYPE_CREATOR = 100;    // 创建者



    // ID
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false)
    private String id;


    // 别名，可以为空
    @Column
    private String alias;


    // 消息的通知级别，不能为空，默认情况下为默认的通知级别
    @Column(nullable = false)
    private int notifyLevel = NOTIFY_LEVEL_NONE;


    // 成员的权限类型，不能为空，默认为普通成员
    @Column(nullable = false)
    private int permissionsType = PERMISSIONS_TYPE_NONE;


    // 创建时的时间戳，在创建时就已经写入，不能为空
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();


    // 更新时的时间戳，在更新时就已经写入，不能为空
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();



    // 成员信息对应的用户信息
    // 多对1
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
    // 不能为空，不能更新，不能插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String userId;



    // 成员信息对应的群信息
    // 多对1
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    private Group group;
    // 不能为空，不能更新，不能插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String groupId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getNotifyLevel() {
        return notifyLevel;
    }

    public void setNotifyLevel(int notifyLevel) {
        this.notifyLevel = notifyLevel;
    }

    public int getPermissionsType() {
        return permissionsType;
    }

    public void setPermissionsType(int permissionsType) {
        this.permissionsType = permissionsType;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
