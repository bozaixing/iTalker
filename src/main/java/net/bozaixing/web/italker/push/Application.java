package net.bozaixing.web.italker.push;


import net.bozaixing.web.italker.push.provider.GsonProvider;
import net.bozaixing.web.italker.push.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

/*
 * Descr:   服务器程序运行入口类
 * Author:  bozaixing.
 * Date:    2017-05-17.
 * Email:   654152983@qq.com.
 */

public class Application extends ResourceConfig {


    /**
     * 构造函数
     */
    public Application(){

        // 注册包名
        packages(AccountService.class.getPackage().getName());

        // 注册json解析器
//        register(JacksonJsonProvider.class);
        // 替换json解析器为Gson
        register(GsonProvider.class);

        // 注册打印日志
        register(Logger.class);

    }
}


