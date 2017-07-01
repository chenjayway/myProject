package com.ctb.model;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Ajax输出模型.
 */
public class JsonResult {

    /** 消息代码, 默认为 1 */
    private int code = 1;

    /** 输出消息 */
    private String message = "操作成功";

    /** 输出消息 */
    private Object results;

    /** 错误信息 */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object errors;

    public JsonResult() {
    }

    public JsonResult(int code) {
        this(code, null);
        if ("1".equals(code)) {
            this.message = "操作成功";
        } else if ("-1".equals(code)) {
            this.message = "操作失败";
        }

    }

    public JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

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

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
