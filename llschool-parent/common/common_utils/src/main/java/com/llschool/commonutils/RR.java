package com.llschool.commonutils;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class RR {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //构造方法私有
    private RR(){}

    //成功静态方法
    public static RR ok(){
        RR r = new RR();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    //失败静态方法
    public static RR error(){
        RR r = new RR();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    public RR success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public RR message(String message){
        this.setMessage(message);
        return this;
    }

    public RR code(Integer code){
        this.setCode(code);
        return this;
    }

    public RR data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public RR data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
