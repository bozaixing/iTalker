package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 群组信息的Model
 * Created by bozaixing on 2017-05-25.
 */


// 实体
@Entity
// 群组
@Table(name = "TABLE_GROUP")
public class Group {

    // 群组ID,这是一个主键
    @Id
    @PrimaryKeyJoinColumn
    // 主键生成的储存类型为UUID
    @GeneratedValue(generator = "uuid")
    // 把UUID的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // 不允许更改，不能为空
    @Column(updatable = false, nullable = false)
    private String id;


    // 群组名称，不能为空
    @Column(nullable = false)
    private String name;


    // 群组简介描述，不能为空
    @Column(nullable = false)
    private String description;

    // 群组图片，不能为空
    @Column(nullable = false)
    private String picture;

    // 群组创建的时间戳，不能为空
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();


    // 群组更新的时间戳，不能为空
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    // 群组的创建者
    // optional = false表示必须要有创建者
    // fetch = FetchType.EAGER，获取创建者信息的加载方式为急加载，意味着加载群组信息的时候就必须加载创建者的信息
    // cascade = CascadeType.ALL，联级级别为ALL，代表所有的更新，删除等操作都将进行关系更新
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // 定义关联的数据库表的字段名为ownerId，对应的是User.id
    @JoinColumn(name = "ownerId")
    private User owner;
    // 不能为空，不能更新，不能插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String ownerId;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
