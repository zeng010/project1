package com.tjhd.project1.firstproject.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-16 12:33
 * 封装返回数据结果给前段
 */
@Data
public class Result implements Serializable {
    /**
     * code: 返回的状态码：200 成功，非200 失败
     * msg: 返回的信息
     * data: 返回的数据
     */
    private int code;
    private String msg;
    private Object data;

    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static Result succ(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
