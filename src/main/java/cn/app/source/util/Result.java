package cn.app.source.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 * @author kevinsu
 * @date 2020/04/29
 *  {
 *      success: 是否成功
 *      code: 返回码
 *      messsage: 返回信息
 *      //返回数据
 *      data: {
 *
 *      }
 *  }
 */
@Data
@NoArgsConstructor
public class Result<T> {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     *  返回数据
     */
    private T data;

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }


    public Result(ResultCode code, T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }


    public Result(ResultCode code, String message) {
        this.success = code.success;
        this.code = code.code;
        this.message = message;
    }

    public Result(ResultCode code, T data, String message) {
        this.success = code.success;
        this.code = code.code;
        this.message = message;
        this.data = data;
    }

}