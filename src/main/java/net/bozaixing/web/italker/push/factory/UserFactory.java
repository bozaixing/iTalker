package net.bozaixing.web.italker.push.factory;

import net.bozaixing.web.italker.push.utils.HibernateHelper;
import net.bozaixing.web.italker.push.utils.TextUtil;
import org.hibernate.Session;
import net.bozaixing.web.italker.push.bean.db.User;

/**
 * 用户业务逻辑的封装类
 * Created by bozaixing on 2017-06-02.
 */
public class UserFactory {

    /**
     * 用户注册
     * 注册的操作需要写入数据库，并返回数据库中的User信息
     * @param account   账号（手机号码）
     * @param password  密码
     * @param name      用户名
     * @return          User
     */
    public static User register(String account, String password, String name){
        // 账号去除首尾空格
        account = account.trim();
        // 密码进行加密处理
        password = encodePassword(password);

        // 创建一个User对象
        User user = new User();

        user.setPhone(account);
        user.setPassword(password);
        user.setName(name);

        // 进行数据库的操作

        // 首先创建一个与数据库操作的会话
        Session session = HibernateHelper.session();


        // 开启一个进行数据库操作的事务
        session.beginTransaction();

        try {
            // 保存User数据
            session.save(user);
            // 提交我们的事务
            session.getTransaction().commit();

            return user;

        }catch (Exception e){
            // 提交事务失败时需要进行回滚事务
            session.getTransaction().rollback();

            return null;
        }
    }


    /**
     * 通过手机号码查询用户信息
     * @param phone
     * @return
     */
    public static User findByPhone(String phone){

        if (phone == null || phone.equals("")){
            return null;
        }
        User user = HibernateHelper.query(new HibernateHelper.OnQueryCallbak<User>() {

            @Override
            public User onQuery(Session session) {
                // 进行查询
                return  (User) session.createQuery("from User where phone=:phone")
                        .setParameter("phone", phone)
                        .uniqueResult();
            }
        });

        if (user != null){
            return user;
        }

        return null;
    }



    /**
     * 实现密码加密的方法
     * @param password
     * @return
     */
    private static String encodePassword(String password){
        // 去除首尾空格
        password = password.trim();

        // 进行MD5非对称加密
        password = TextUtil.getMD5(password);

        // 在进行一次对称的Base64加密
        return TextUtil.encodeBase64(password);
    }



}
