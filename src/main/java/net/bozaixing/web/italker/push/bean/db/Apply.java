package net.bozaixing.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 申请消息记录表Model
 * Created by bozaixing on 2017-05-26.
 */

// 实体类
@Entity
// 数据库中对应的表名
@Table(name = "TABLE_APPLY")
public class Apply {

    /**
     * 申请消息的类型
     */
    private static final int TYPE_ADD_USER = 1;     // 申请添加好友
    private static final int TYPE_ADD_GROUP = 2;    // 申请添加群组


    // ID
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false)
    private String id;



    // 描述部分，对我们的申请信息做描述，不允许为空
    // 比如：我想加你为好友。/ 美女能认识下吗？
    @Column(nullable = false)
    private String description;



    // 附件，可以为空
    // 可以附带图片地址，文本信息，其他等
    @Column(columnDefinition = "TEXT")
    private String attach;


    // 当前申请信息的类型，比如加好友，加群等，不能为空
    @Column(nullable = false)
    private int type;


    // 申请信息目标ID，不进行强关联，不建立主外键关系，不能为空
    // type --> TYPE_ADD_USER: 对应的目标id为User.id
    // type --> TYPE_ADD_GROUP:对应的目标id为Group.id;
    @Column(nullable = false)
    private String targetId;


    // 申请消息创建时的时间戳
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();



    // 申请消息更新时的时间戳
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    // 申请人，可以为空（系统消息）
    // 一个人可以发送多条申请消息，多对1
    @ManyToOne
    // 对应数据库表中的字段
    @JoinColumn(name = "applicantId")
    private User applicant;
    // 不允许更新，不允许插入，可以为空
    @Column(updatable = false, insertable = false)
    private String applicantId;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
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

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }


}
