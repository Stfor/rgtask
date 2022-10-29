package com.example.rgtask.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by maple on 2018-09-09.
 */
public class MsgCodeUtils {

    public final static int MSG_CODE_SUCCESS = 0;
    public final static int MSG_CODE_UNKNOWN = -10000;
    public final static int MSG_CODE_FORBIDDEN_LOGIN = -10001;
    public final static int MSG_CODE_JWT_TOKEN_ISNULL = -10002;
    public final static int MSG_CODE_JWT_SIGNATURE_EXCEPTION = -10003;
    public final static int MSG_CODE_JWT_MALFORMED = -10004;
    public final static int MSG_CODE_JWT_TOKEN_EXPIRED = -10005;
    public final static int MSG_CODE_JWT_TOKEN_UNSUPPORTED = -10006;
    public final static int MSG_CODE_JWT_ILLEGAL_ARGUMENT = -10007;
    public final static int MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT = -10008;
    public final static int MSG_CODE_JWT_TOKEN_TYPE_MISMATCH = -10009;


    public final static int MSG_CODE_SYSTEM_ROLE_NAME_EXIST = -10010;
    public final static int MSG_CODE_SYSTEM_ROLE_ENNAME_EXIST = -10011;
    public final static int MSG_CODE_SYSTEM_NOT_SUPER_ADMIN_PERMISSION = -10012;


    public final static int MSG_CODE_SYSTEM_DATABASE_KEY_DUPLICATE = -10013;

    public final static int MSG_CODE_JWT_REFRESH_TOKEN_EXPIRED = -10014;
    public final static int MSG_CODE_REFRESH_TOKEN_FREQUENT = -10015;
    public final static int MSG_CODE_ILLEGAL_ARGUMENT = -10016;
    public final static int MSG_CODE_PARAMETER_IS_NULL = -10017;
    public final static int MSG_CODE_ID_IS_NULL = -10018;
    public final static int MSG_CODE_PARENT_NODE_NOT_EXIST = -10019;
    public final static int MSG_CODE_NODE_NOT_EXIST = -10020;
    public final static int MSG_CODE_NOT_PERMISSION = -10021;

    public final static int MSG_CODE_SYSTEM_USER_LOGIN_NAME_EXIST = -10022;
    public final static int MSG_CODE_SYSTEM_OFFICE_NOT_EXIST = -10023;
    public final static int MSG_CODE_DATA_NOT_EXIST = -10024;
    public final static int MSG_CODE_DATA_EXIST = -10025;
    public final static int MSG_CODE_PARENT_NODE_NOT_HIMSELF = -10026;
    public final static int MSG_CODE_FLOWINFO_STATE = -10027;
    public final static int MSG_CODE_FLOWINFO_NOT_FOUNT = -10028;
    public final static int MSC_DATA_DRODATA_ERROR = -10029;
    public final static int MSC_DATA_ADDDATA_ERROR = -10030;
    public final static int MSC_DATA_UPDATADATA_ERROR = -10031;
    //修改关联选择器类型
    public final static int MSC_DATA_UPDATADATA_TYPE_ERROR = -10050;
    public final static int MSG_DATABASE_CONNECT_ERROR = -10032;
    public final static int MSG_TIMING_SET_ERROR = -10033;
    public final static int MSG_TIMING_change_ERROR = -10034;
    public final static int MSG_SAME_NAME_ERROR = -10035;
    public final static int MSG_API_OCCUPY_ERROR = -10051;
    public final static int MSG_CODE_TODAY_DAILY_HEALTH_EXIST = -10036;
    public final static int MSG_CODE_SIMPLE_TOKEN_EXPIRED = -10037;
    public final static int MSG_CODE_RUN_FAIL = -10041;
    public final static int MSG_CODE_SEND_MESSAGE_FAIL = -10042;

    // 人员管理信息管理code码
    //public final static int MSG_CODE_ACCOUNT_INVALID = -10037;
    public final static int MSG_CODE_ACCOUNT_ABANDON = -10038;
    public final static int MSG_CODE_ACCOUNT_ERROR = -10039;

    // 单位管理code码
    public final static int MSG_CODE_UNIT_EMPLOYEE = -10040;

    // 方法找不到
    public final static int MSG_CODE_METHOD_IS_NOT_FOUND = -10042;
    // 非法访问
    public final static int MSG_CODE_ILLEGAL_ACCESS_EXCEPTION = -10043;
    // 调用目标异常
    public final static int MSG_CODE_INVACATION_TARGET_EXCEPTION = -10044;
    // 实例化异常
    public final static int MSG_CODE_INSTANTIATION_EXCEPTION = -10045;

    public final static int MSG_CODE_DATA_ERROR = -10047;


    public final static int MSG_DATA_NOT_ALLOW_DELETE = -11048;
    public final static int MSG_DATA_NOT_ALLOW_OPERAION = -11049;
    public final static int MSG_FILE_INPUT_FAILUER = -11050;
    public final static int MSG_FILE_NOT_EXIST = -11051;
    public final static int MSG_FILE_DELETE_FAILURE = -11052;
    public final static int MSG_RESET_APPROVAL_ERROR = -11053;
    public final static int MSG_NO_PERMISSION_APPROVAL = -11054;
    public final static int MSG_FILE_EXPORT_FAILURE = -11055;
    public final static int MSG_SYSTEM_NOT_OPEN = -11056;
    public final static int MSG_COMPONENT_IS_NOT_REDEAY = -11052;
    public final static int MSG_IO_EXCEPTION = -11053;
    public final static int MSG_FILE_IS_DIRECTORY = -11054;
    public final static int MSG_FILE_ACCESS_ERROR = -11055;
    public final static int MSG_FILE_IS_EXITS = -11056;
    public final static int MSG_FILE_IS_NO_DIRECTORY = -11057;
    public final static int MSG_SETTING_FAIL = -11058;


    public final static int MSG_FILE_IS_FIELD_SHEET_ILLEGAL = -11059;
    public final static int MSG_FILE_IS_FIELD_COLUMNS_NULL = -11060;
    public final static int MSG_FILE_IS_NOT_XLSX = -11061;
    public final static int MSG_FILE_IS_NOT_TXT = -11064;
    public final static int MSG_FILE_IS_NOT_JSON = -11065;
    public final static int MSG_FILE_FILE_LIST_IS_NULL = -11062;
    public final static int MSG_FILE_IS_FIELD_LINE_NO_FIRST = -11063;
    public final static int MSG_COLLECTION_NULL = -12000;
    public final static int MSG_USER_INFO_NULL = -12001;
    public final static int MSG_FILE_IO_EXCEPTION = -12002;
    public final static int MSG_EXCEL_SHEET_INDEX_OUT_OF = -12003;

    public final static int MSG_TABLE_EXIST = -13000;
    public final static int MSG_CREATE_TABLE_FAIL = -13001;
    public final static int MSG_CREATE_SEQUENCE_FAIL = -13002;
    public final static int MSG_SYNC_FAIL = -13003;
    public final static int MSG_TABLE_NO_KEY = -13004;
    public final static int MSG_DATASOURCE_ID_NULL = -13005;
    public final static int MSG_INSERT_FAIL = -13006;
    public final static int MSG_PROCESS_NULL = -13007;
    public final static int MSG_DATAITEM_ADD_FILED_FAIL = -13008;
    public final static int MSG_PHOENIX_TABLE_STRUCT_FAIL = -13009;
    public final static int MSG_PHOENIX_TABLE_FIELD_EXIST = -13010;
    public final static int MSG_DATAITEM_TYPE_NOT_EXIST = -13011;
    public final static int MSG_DATASOURCE_TYPE_ERRO = -13012;
    public final static int MSG_DATAITEM_UPDATE_ERRO = -13013;
    public final static int MSG_DATAITEM_HISTORY = -13014;

    //参数不能为空
    public final static int MSG_PARAM_NOT_NULL = -13015;
    public final static int MSG_CREATE_DATABASE_ERR = -13016;
    public final static int MSG_DORP_DATABASE_ERR = -13017;
    public final static int MSG_DATABASE_IS_EXIST = -13018;
    public final static int MSG_MODIFY_TASK_ERR = -13019;
    public final static int MSG_NAME_REPEATED = -13020;
    public final static int MSG_DATABASE_NOT_EXIST = -13021;
    public final static int MSG_DISCOVER_SCHEME_NOT_EXIST = -13022;
    public final static int MSG_DISCOVER_RULE_NOT_EXIST = -13023;
    public final static int MSG_DATABASE_TABLE_NULL = -13024;
    public final static int MSG_FILE_IS_FIELD_COLUMNS_ILLEGAL = -13025;
    public final static int MSG_INIT_FTP_ERR = -13026;
    public final static int MSG_INIT_FILE_READ_ERR = -13027;
    public final static int MSG_SELECT_ERR = -13028;
    public final static int MSG_FILE_IS_NOT_CSV = -13029;
    public final static int MSG_DATAHORIZON_ERR = -13030;
    public final static int MSG_COLUMNHORIZON_ERR = -13031;
    public final static int MSG_HBASE_ERR = -14032;
    public final static int MSG_TASK_STOP = -14033;
    public final static int MSG_PHOENIX_ERR = -14034;
    public final static int MSG_HUB_VIEW_ADD_ERR = -14035;
    public final static int MSG_OPERATION_LOG_HISTORY_ERROR = -14036;
    public final static int MSG_OPERATION_LOG_REALTIME_ERROR = -14037;
    public final static int MSG_DATASOURCE_CONNECT_ERROR = -14038;

    /**
     * 授权验证码
     */
    public static final int MSG_AUTHORIZATION_ERROR = -15001;
    /**
     * 超出密码有效期
     */
    public static final int MSG_EXPIRE_DATE_ERROR = -15002;

    /**
     * 密码强度验证
     */
    public static final int PASSWORD_STRENGTH_ERROR = -16001;
    /**
     * 保存全局属性值
     */
    protected static Map<Integer, String> map = Maps.newHashMap();

    static {
        map.put(MSG_CODE_SUCCESS, "请求成功.");
        map.put(MSG_CODE_UNKNOWN, "未知错误.");
        map.put(MSG_CODE_FORBIDDEN_LOGIN, "该帐号禁止登录.");
        map.put(MSG_CODE_JWT_TOKEN_ISNULL, "access token为空.");
        map.put(MSG_CODE_JWT_SIGNATURE_EXCEPTION, "token签名异常或者token过期.");
        map.put(MSG_CODE_JWT_MALFORMED, "token格式错误.");
        map.put(MSG_CODE_JWT_TOKEN_EXPIRED, "token过期.");
        map.put(MSG_CODE_JWT_TOKEN_UNSUPPORTED, "不支持该token.");
        map.put(MSG_CODE_JWT_ILLEGAL_ARGUMENT, "token参数错误异常.");
        map.put(MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT, "账号或者密码错误.");
        map.put(MSG_CODE_JWT_TOKEN_TYPE_MISMATCH, "token类型错误.");
        map.put(MSG_CODE_SYSTEM_ROLE_NAME_EXIST, "角色名称已经存在.");
        map.put(MSG_CODE_SYSTEM_ROLE_ENNAME_EXIST, "角色英文名称已经存在.");
        map.put(MSG_CODE_SYSTEM_NOT_SUPER_ADMIN_PERMISSION, "越权操作，需超级管理员权限.");
        map.put(MSG_CODE_SYSTEM_DATABASE_KEY_DUPLICATE, "数据库主键重复.");
        map.put(MSG_CODE_JWT_REFRESH_TOKEN_EXPIRED, "refresh token过期.");
        map.put(MSG_CODE_REFRESH_TOKEN_FREQUENT, "refresh token刷新频率太高，请稍后再试.");
        map.put(MSG_CODE_ILLEGAL_ARGUMENT, "非法参数异常:");
        map.put(MSG_CODE_PARAMETER_IS_NULL, "参数为空.");
        map.put(MSG_CODE_ID_IS_NULL, "参数ID为空.");
        map.put(MSG_CODE_PARENT_NODE_NOT_EXIST, "父级节点不存在.");
        map.put(MSG_CODE_NODE_NOT_EXIST, "节点不存在.");
        map.put(MSG_CODE_NOT_PERMISSION, "无权限.");
        map.put(MSG_CODE_SYSTEM_USER_LOGIN_NAME_EXIST, "登录名已经存在.");
        map.put(MSG_CODE_SYSTEM_OFFICE_NOT_EXIST, "机构不存在.");
        map.put(MSG_CODE_DATA_NOT_EXIST, "数据不存在：");
        map.put(MSG_CODE_DATA_EXIST, "数据存在：");
        map.put(MSG_CODE_PARENT_NODE_NOT_HIMSELF, "父级节点不能是自己");
        map.put(MSC_DATA_DRODATA_ERROR, "删除失败");
        map.put(MSC_DATA_ADDDATA_ERROR, "新增失败");
        map.put(MSC_DATA_UPDATADATA_ERROR, "修改失败");
        map.put(MSC_DATA_UPDATADATA_TYPE_ERROR, "修改失败,暂不支持修改选择器类型");
        map.put(MSG_DATABASE_CONNECT_ERROR, "数据源连接失败");
        map.put(MSG_TIMING_SET_ERROR, "时间调度设置失败");
        map.put(MSG_TIMING_change_ERROR, "该表非事务表，不允许更新数据");
        map.put(MSG_SAME_NAME_ERROR, "名称重复，请检查参数是否设置合理");
        map.put(MSG_CODE_FLOWINFO_STATE, "流程状态不存在");
        map.put(MSG_CODE_FLOWINFO_NOT_FOUNT, "未找到当前运行流程信息");
        map.put(MSG_API_OCCUPY_ERROR, "当前API被占用，暂时无法进行编辑");
        map.put(MSG_CODE_TODAY_DAILY_HEALTH_EXIST, "今日健康已经提交.");
        map.put(MSG_CODE_SIMPLE_TOKEN_EXPIRED, "基础token过期.");
        // map.put(MSG_CODE_ACCOUNT_INVALID, "该账户无效，请联系公司管理员.");
        map.put(MSG_CODE_ACCOUNT_ABANDON, "该账号被禁止入园，请联系公司管理员");
        map.put(MSG_CODE_ACCOUNT_ERROR, "该账户存在异常，请联系公司管理员");
        map.put(MSG_CODE_UNIT_EMPLOYEE, "该单位下存在员工，禁止删除");
        map.put(MSG_CODE_ILLEGAL_ACCESS_EXCEPTION, "非法访问异常");
        map.put(MSG_CODE_INVACATION_TARGET_EXCEPTION, "调用目标异常");
        map.put(MSG_CODE_INSTANTIATION_EXCEPTION, "实例化异常");
        map.put(MSG_CODE_METHOD_IS_NOT_FOUND, "方法不存在");
        map.put(MSG_CODE_SEND_MESSAGE_FAIL, "消息推送失败");
        //
        map.put(MSG_DATA_NOT_ALLOW_DELETE, "数据不允许删除");
        map.put(MSG_DATA_NOT_ALLOW_OPERAION, "数据禁止操作");
        map.put(MSG_FILE_INPUT_FAILUER, "文件上传失败");
        map.put(MSG_FILE_NOT_EXIST, "文件不存在");
        map.put(MSG_FILE_DELETE_FAILURE, "文件删除失败");
        map.put(MSG_RESET_APPROVAL_ERROR, "重置审批流程失败,项目不在征集库");
        map.put(MSG_NO_PERMISSION_APPROVAL, "您没有权限操作该项目");
        map.put(MSG_FILE_EXPORT_FAILURE, "文件导出失败");
        map.put(MSG_SYSTEM_NOT_OPEN, "项目申报模块未开放");
        map.put(MSG_COMPONENT_IS_NOT_REDEAY, "分布式组件未准备，请稍后再试");
        map.put(MSG_IO_EXCEPTION, "磁盘IO异常");
        map.put(MSG_FILE_IS_DIRECTORY, "该文件为目录");
        map.put(MSG_FILE_ACCESS_ERROR, "文件访问越界");
        map.put(MSG_FILE_IS_EXITS, "文件已存在");
        map.put(MSG_FILE_IS_NO_DIRECTORY, "该路径不为目录路径");
        map.put(MSG_SETTING_FAIL, "数仓配额出错");
        map.put(MSG_FILE_IS_FIELD_LINE_NO_FIRST, "字段行不为首行");
        map.put(MSG_FILE_IS_FIELD_SHEET_ILLEGAL, "非法sheet索引");
        map.put(MSG_FILE_IS_FIELD_COLUMNS_NULL, "字段为空");
        map.put(MSG_FILE_IS_NOT_XLSX, "文件不为excel类型");
        map.put(MSG_FILE_IS_NOT_TXT, "文件不为TXT类型");
        map.put(MSG_FILE_IS_NOT_JSON, "文件不为JSON类型");
        map.put(MSG_FILE_IS_NOT_CSV, "文件不为Csv类型");
        map.put(MSG_FILE_FILE_LIST_IS_NULL, "文件路径集合为空");

        map.put(MSG_COLLECTION_NULL, "集合为空");
        map.put(MSG_USER_INFO_NULL, "获取用户信息失败");
        map.put(MSG_FILE_IO_EXCEPTION, "文件IO异常");
        map.put(MSG_EXCEL_SHEET_INDEX_OUT_OF, "excel文件sheet索引越界");
        map.put(MSG_FILE_IS_FIELD_COLUMNS_ILLEGAL, "非法字段索引");

        map.put(MSG_TABLE_EXIST, "表存在");
        map.put(MSG_CREATE_TABLE_FAIL, "创建表失败");
        map.put(MSG_CREATE_SEQUENCE_FAIL, "创建序列失败");
        map.put(MSG_SYNC_FAIL, "导入进程已经存在");
        map.put(MSG_TABLE_NO_KEY, "主键不存在");
        map.put(MSG_DATASOURCE_ID_NULL, "数据源id不存在");
        map.put(MSG_INSERT_FAIL, "插入数据失败");
        map.put(MSG_PROCESS_NULL, "无进度任务");
        map.put(MSG_DATAITEM_ADD_FILED_FAIL, "数据项表结构失败");
        map.put(MSG_PHOENIX_TABLE_STRUCT_FAIL, "获取数仓表结构失败");
        map.put(MSG_DATAITEM_TYPE_NOT_EXIST, "数据项类别不存在");
        map.put(MSG_DATASOURCE_TYPE_ERRO, "数据源类别异常");
        map.put(MSG_DATAITEM_UPDATE_ERRO, "数据项升级失败,请强制升级");
        map.put(MSG_DATAITEM_HISTORY, "该数据项为历史版本，不可升级");

        map.put(MSG_PARAM_NOT_NULL, "%s参数不能为空！");
        map.put(MSG_CREATE_DATABASE_ERR, "创建数据库失败");
        map.put(MSG_DORP_DATABASE_ERR, "删除数据库失败");
        map.put(MSG_DATABASE_IS_EXIST, "数据库已存在");
        map.put(MSG_MODIFY_TASK_ERR, "修改任务失败");
        map.put(MSG_NAME_REPEATED, "名称重复");
        map.put(MSG_DATABASE_NOT_EXIST, "数据库名称不存在");
        map.put(MSG_DISCOVER_SCHEME_NOT_EXIST, "发现方案不存在");
        map.put(MSG_DISCOVER_RULE_NOT_EXIST, "发现规则为空");
        map.put(MSG_DATABASE_TABLE_NULL, "该库下没有表");
        map.put(MSG_INIT_FTP_ERR, "初始化ftp失败");
        map.put(MSG_INIT_FILE_READ_ERR, "文件读取错误,请检查文件是否正常");
        map.put(MSG_SELECT_ERR, "查询异常，请检查查询信息是否正确");
        map.put(MSG_DATAHORIZON_ERR, "文件数据行索引异常");
        map.put(MSG_COLUMNHORIZON_ERR, "文件字段行索引异常");
        map.put(MSG_HBASE_ERR, "Hbase连接失败");
        map.put(MSG_TASK_STOP, "任务暂停中，无法修改");
        map.put(MSG_PHOENIX_ERR, "Phoenix连接失败");
        map.put(MSG_HUB_VIEW_ADD_ERR, "添加数仓视图异常");
        map.put(MSG_OPERATION_LOG_HISTORY_ERROR, "查询历史操作记录异常");
        map.put(MSG_OPERATION_LOG_REALTIME_ERROR, "查询实时操作记录异常");

        // 授权验证码
        map.put(MSG_AUTHORIZATION_ERROR, "授权验证失败");
        map.put(MSG_EXPIRE_DATE_ERROR,"超出密码有效期");
        map.put(PASSWORD_STRENGTH_ERROR,"密码强度过于简单，强制修改密码");
        map.put(MSG_DATASOURCE_CONNECT_ERROR,"数据源连接异常");
    }

    public static String getErrMsg(int msgCode) {
        return map.get(msgCode);
    }
}
