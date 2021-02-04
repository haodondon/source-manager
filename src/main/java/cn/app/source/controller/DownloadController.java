package cn.app.source.controller;


import cn.app.source.service.AccountService;
import cn.app.source.service.DownloadService;
import cn.app.source.util.Result;
import cn.app.source.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haodongdong
 * @since 2020-11-21
 */
@RestController
@RequestMapping("/download")
@Slf4j
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    /**
     * 素材下载
     * @param downloadUrl 下载地址
     */
    @GetMapping("download")
    public Result download(@RequestParam(value = "downloadUrl") String downloadUrl) throws Exception{

        //千图网下载
        if(downloadUrl.contains("58pic")){

            return this.downloadService.downloadPic58(downloadUrl);

        }

        return new Result(ResultCode.FAIL, "请输入正确的素材解析地址");
    }

}

