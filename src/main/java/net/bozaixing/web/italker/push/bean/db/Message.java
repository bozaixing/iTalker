package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 消息Model
 * Created by bozaixing on 2017-05-25.
 */


// 实体
@Entity
// 表名
@Table(name = "TABLE_MESSAGE")
public class Message {

    /**
     * 定义消息的类型
     */
    private static final int MESSAGE_TEXT = 1;      // 普通的文本类型
    private static final int MESSAGE_PIC = 2;       // 图片类型
    private static final int MESSAGE_VOICE = 3;     // 语音类型
    private static final int MESSAGE_FILE = 4;      // 文件类型


    // 消息的ID
    @Id
    @PrimaryKeyJoinColumn
    // 主键生成的储存类型为UUID
    // 这里不能自动生成ID，ID由客户端负责生成
    // 为了避免复杂的服务器和客户端的映射关系
    //@GeneratedValue(generator = "uuid")
    // 把UUID的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // 不允许更改，不允许为null
    private String id;


    // 消息的内容，不能为空，指定列的类型为text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    // 消息的附件，可以为空
    @Column
    private String attach;


    // 消息的类型，不能为空
    @Column(nullable = false)
    private int type;


    // 定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();


    // 定义为更新时间戳，在更新时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    // 消息发送者，不能为空
    // 多对1：多条消息对应一个发送者
    // optional = false 不可选，必须存储，一条关注记录一定要有一个“你”
    @ManyToOne(optional = false)
    // 定义的是数据库中的存储字段
    @JoinColumn(name = "senderId")
    private User sender;
    // 对应数据库senderId字段，所以不能为空，不可更新，不可插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String senderId;



    // 消息的接收者，可以为空
    // 对多1：多条消息对应一个接收者
    @ManyToOne
    // 定义的是数据库中的存储字段
    @JoinColumn(name = "receiverId")
    private User receiver;
    // // 对应数据库receiverId字段，所以不可更新，不可插入
    @Column(updatable = false, insertable = false)
    private String receiverId;



    // 一个群可以接收多条消息,群消息的接收者
    // 对多1：多条消息对应一个群组
    @ManyToOne
    // 定义的数据库中的存储字段
    @JoinColumn(name = "groupId")
    private Group group;
    // 不能更新，不能插入
    @Column(updatable = false, insertable = false)
    private String groupId;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
