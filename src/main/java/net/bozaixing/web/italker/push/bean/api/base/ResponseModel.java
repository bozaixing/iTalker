package net.bozaixing.web.italker.push.bean.api.base;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * Created by bozaixing on 2017-06-03.
 */
public class ResponseModel<T> implements Serializable {

    // 成功
    public static final int SUCCEED = 1000;

    // 未知的错误
    public static final int ERROR_UNKNOWN = 0;




    // 没有找到用户信息
    public static final int ERROR_NOT_FOUND_USER = 4041;

    // 没有找到群信息
    public static final int ERROR_NOT_FOUND_GROUP = 4042;

    // 没有找到群成员的信息
    public static final int ERROR_NOT_FOUND_GROUP_MEMBER = 4043;



    // 创建用户失败
    public static final int ERROR_CREATE_USER = 3001;

    // 创建群组失败
    public static final int ERROR_CREATE_GROUP = 3002;

    // 创建群成员失败
    public static final int ERROR_CREATE_GROUP_MEMBER = 3003;



    // 请求参数错误
    public static final int ERROR_PARAMETER = 4001;

    // 请求参数错误-账号已存在
    public static final int ERROR_PARAMETER_EXIST_ACCOUNT = 4002;

    // 请求参数错误-用户名称已存在
    public static final int ERROR_PARAMETER_EXIST_NAME = 4003;



    // 服务器错误
    public static final int ERROR_SERVER = 5001;



    // 账户Token错误，需要重新登录
    public static final int ERROR_ACCOUNT_TOKEN = 2001;

    // 账户登录失败
    public static final int ERROR_ACCOUNT_LOGIN = 2002;

    // 账户注册失败
    public static final int ERROR_ACCOUNT_REGISTER = 2003;

    // 没有权限操作
    public static final int ERROR_ACCOUNT_NO_PERMISSION = 2004;



    // 返回码
    @Expose
    private int code;
    // 当前状态描述的信息
    @Expose
    private String message;
    // 服务器返回的时间点
    @Expose
    private LocalDateTime time = LocalDateTime.now();
    // 返回的结果（对象、数据），这里定义为泛型
    @Expose
    private T result;



    // 构造方法
    public ResponseModel(){
        // 设置默认返回的结果为成功
        this.code = 1000;
        this.message = "ok";
    }

    public ResponseModel(T result){
        this.result = result;
    }

    public ResponseModel(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ResponseModel(int code, String message, T result){
        this.code = code;
        this.message = message;
        this.result = result;
    }


    // get()/set()方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }



    // 返回信息方法的封装

    // 成功
    public static ResponseModel buildOk(){

        return new ResponseModel();
    }


    // 成功，返回结果
    public static <T> ResponseModel<T> bulidOk(T result){

        return new ResponseModel<T>(result);
    }


    // 参数错误
    public static <T> ResponseModel<T> buildParameterError(){
        return new ResponseModel<T>(ERROR_PARAMETER, "Parameter Error.");
    }

    // 参数错误-账号已经存在
    public static <T> ResponseModel<T> buildParameterExistAccountError(){
        return new ResponseModel<T>(ERROR_PARAMETER_EXIST_ACCOUNT, "Already have this account.");
    }

    // 参数错误-用户名称已经存在
    public static <T> ResponseModel<T> buildParameterExistNameError(){
        return new ResponseModel<T>(ERROR_PARAMETER_EXIST_NAME, "Already have this name.");
    }

    // 参数错误-用户名称已经存在
    public static <T> ResponseModel<T> buildServerError(){
        return new ResponseModel<T>(ERROR_SERVER, "Service Error.");
    }

    // 没有找到用户信息
    public static <T> ResponseModel<T> buildNotFoundUserError(String message) {
        return new ResponseModel<T>(ERROR_NOT_FOUND_USER, message != null ? message : "Not Found User.");
    }

    // 没有找到群组信息
    public static <T> ResponseModel<T> buildNotFoundGroupError(String message) {
        return new ResponseModel<T>(ERROR_NOT_FOUND_GROUP, message != null ? message : "Not Found Group.");
    }

    // 没有找到群组成员信息
    public static <T> ResponseModel<T> buildNotFoundGroupMemberError(String message) {
        return new ResponseModel<T>(ERROR_NOT_FOUND_GROUP_MEMBER, message != null ? message : "Not Found GroupMember.");
    }

    // 账户Token错误，需要重新登录
    public static <T> ResponseModel<T> buildAccountTokenError() {
        return new ResponseModel<T>(ERROR_ACCOUNT_TOKEN, "Account Error; you need login.");
    }

    // 账户登录错误
    public static <T> ResponseModel<T> buildAccountLoginError() {
        return new ResponseModel<T>(ERROR_ACCOUNT_LOGIN, "Account or password error.");
    }

    // 账户注册错误
    public static <T> ResponseModel<T> buildAccountRegisterError() {
        return new ResponseModel<T>(ERROR_ACCOUNT_REGISTER, "Have this account.");
    }

    // 没有权限操作
    public static <T> ResponseModel<T> buildNoPermissionError() {
        return new ResponseModel<T>(ERROR_ACCOUNT_NO_PERMISSION, "You do not have permission to operate.");
    }

    // 创建用户失败
    public static <T> ResponseModel<T> buildCreateUserError() {
        return new ResponseModel<T>(ERROR_CREATE_USER, "Create user failed.");
    }

    // 创建群失败
    public static <T> ResponseModel<T> buildCreateGroupError() {
        return new ResponseModel<T>(ERROR_CREATE_GROUP, "Create group failed.");
    }

    // 创建群成员失败
    public static <T> ResponseModel<T> buildCreateGroupMemberError() {
        return new ResponseModel<T>(ERROR_CREATE_GROUP_MEMBER, "Create group member failed.");
    }



}
