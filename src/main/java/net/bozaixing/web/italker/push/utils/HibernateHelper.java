package net.bozaixing.web.italker.push.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate进行数据库操作的辅助类
 * Created by qiujuer
 * on 2017/2/17.
 */
public class HibernateHelper {

    // 全局SessionFactory
    private static SessionFactory sessionFactory;

    static {
        // 静态初始化sessionFactory
        init();
    }

    private static void init() {
        // 从hibernate.cfg.xml文件初始化
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            // build 一个sessionFactory
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // 错误则打印输出，并销毁
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * 获取全局的SessionFactory
     * @return SessionFactory
     */
    public static SessionFactory sessionFactory() {
        return sessionFactory;
    }

    /**
     * 从SessionFactory中得到一个Session会话
     * @return Session
     */
    public static Session session() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 关闭sessionFactory
     */
    public static void closeFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


    /**
     * 没有返回对象的查询方法（仅仅只是查询）
     */
    public static void query(OnQueryCallback onQueryCallback){
        // 重开一个对数据库操作的Session
        Session session = sessionFactory.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        try {

            // 调用传递进来的接口，并调用接口的方法把Session对象传递进去
            if (onQueryCallback != null) {

                onQueryCallback.onQuery(session);
                // 提交事务
                transaction.commit();
            }
        }catch (Exception exception){

            exception.printStackTrace();

            try {
                // 出现异常则回滚提交的事务
                transaction.rollback();

            }catch (RuntimeException runtimeException){
                runtimeException.printStackTrace();
            }

        }finally {
            // 无论成功与失败都需要关闭Session
            session.close();
        }

    }

    /**
     * 有返回对象的查询方法
     * @param onQueryCallbak
     * @param <T>   泛型对象
     * @return
     */
    public static <T> T query(OnQueryCallbak<T> onQueryCallbak){

        // 重开一个对数据库操作的Session
        Session session = sessionFactory.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 创建返回对象
        T t = null;
        try {
            // 调用传递进来的接口，并调用接口的方法把Session对象传递进去
            if (onQueryCallbak != null) {

                t = onQueryCallbak.onQuery(session);
                // 提交事务
                transaction.commit();
            }
        }catch (Exception exception){

            exception.printStackTrace();
            try {
                // 出现异常则回滚提交的事务
                transaction.rollback();

            }catch (RuntimeException runtimeException){
                runtimeException.printStackTrace();
            }

        }finally {
            // 无论成功与失败都需要关闭Session
            session.close();
        }

        return t;
    }


    /**
     * 没有返回对象的查询回调监听
     */
    public interface OnQueryCallback{

        void onQuery(Session session);
    }

    /**
     * 有返回对象的查询回调监听
     * @param <T>   泛型对象
     */
    public interface OnQueryCallbak<T> {

        T onQuery(Session session);
    }




}
