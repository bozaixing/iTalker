package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户关系的Model,用于用户直接进行好友关系的实现
 * Created by bozaixing on 2017-05-23.
 */
@Entity
@Table(name = "TABLE_USER_FOLLOW")
public class UserFollow {

    // 主键，不能为空，不允许更改
    @Id
    @PrimaryKeyJoinColumn
    // UUID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;


    // 定义一个发起人，你关注某人，这里就是你
    // 多对1  -> 你可以关注很多人，你的每一次关注都是一条记录
    // 你可以创建很多个关注的信息，所以是多对1；
    // optional = false 不可选，必须存储，一条关注记录一定要有一个“你”
    @ManyToOne(optional = false)
    // 定义关联的表字段名为originId，对应的是User.id
    // 定义的是数据库中的存储字段
    @JoinColumn(name = "originId")
    private User origin;
    // 把这个列提取到我们的Model中，不允许为空，不允许更新和插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String originId;


    // 定义关注的目标，你关注的人
    // 也是多对1  -> 你可以被很多人关注，你的每一次被关注都是一条记录
    @ManyToOne(optional = false)
    // 定义关联的表字段名为targetId，对应的是User.id
    @JoinColumn(name = "targetId")
    private User target;
    // 把这个列提取到我们的Model中，不允许为空，不允许更新和插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String targetId;


    // 定义一个别名，也就是对target的备注,别名可以为空
    @Column
    private String alias;


    // 定义为创建时间戳，在创建时就已经写入，不能为空
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();


    // 定义为更新时间戳，在更新时就已经写入，不能为空
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
}
