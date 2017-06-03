package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 推送消息历史记录Model
 * Created by bozaixing on 2017-05-26.
 */
// 实体类
@Entity
// 数据库对应的表名
@Table(name = "TABLE_PUSH_HISTORY")
public class PushHistory {

    // 历史消息ID
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false)
    private String id;

    // 推送的具体实体储存的都是json类型的字符串
    // columnDefinition = "BLOB"：指定列的储存类型为BLOB
    // BLOB是大字段，是比TEXT更多的一个大字段类型
    // @Lob:注解属性将被持久化为 Blog 或 Clob 类型
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private String entity;


    // 定义实体的类型
    @Column(nullable = false)
    private int entityType;


    // 推送消息的接收者
    // 一个接收者可以接收到很多条消息，多对1
    // optional = false：接收者具有唯一性，不能为空
    // fetch = FetchType.EAGER：加载一条推送消息的时候急加载用户信息
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // 数据库表对应的字段名
    @JoinColumn(name = "receiverId")
    private User receiver;
    // 不能为空，不能更改，不能插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String receiverId;


    // 推送消息的发送者
    // 一个消息发送者可以发送很多条消息，多对1
    // 消息发送者可以为空，因为可能是系统发送的消息
    //  fetch = FetchType.EAGER：加载一条推送消息的时候急加载用户信息
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // 数据库表中对应的字段名
    @JoinColumn(name = "senderId")
    private User sender;
    // 不允许更新，不允许插入，但是可以为空
    @Column(updatable = false, insertable = false)
    private String senderId;



    // 接收当前状态下的设备ID，可以为空
    // 对应TABLE_USER数据库表中的User.pushId字段
    @Column
    private String receiverPushId;




    // 创建时的时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();




    // 更新时的时间戳，在更新时写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();



    // 消息送达的时间戳，消息实际送达的时间，可以为空
    @Column
    private LocalDateTime arrivalAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverPushId() {
        return receiverPushId;
    }

    public void setReceiverPushId(String receiverPushId) {
        this.receiverPushId = receiverPushId;
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

    public LocalDateTime getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(LocalDateTime arrivalAt) {
        this.arrivalAt = arrivalAt;
    }
}
