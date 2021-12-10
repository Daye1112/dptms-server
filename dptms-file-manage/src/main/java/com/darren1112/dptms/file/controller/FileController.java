package com.darren1112.dptms.file.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.handler.ValidateHandler;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.common.enums.FileManageErrorCodeEnum;
import com.darren1112.dptms.file.service.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件Controller
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Slf4j
@Api(tags = "文件管理")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 文件上传
     *
     * @param file 文件信息
     * @return {@link JsonResult)
     * @author luyuhao
     * @since 2021/12/5
     */
    @PostMapping("/uploadFile")
    @ApiOperation(value = "文件上传")
    @Log(value = "文件上传", logLevel = LogLevel.INFO, businessType = BusinessType.INSERT)
    public ResponseEntity<JsonResult> uploadFile(MultipartFile file) {
        ValidateHandler.checkNull(file, FileManageErrorCodeEnum.FILE_NOT_NULL);
        ActiveUser activeUser = DptmsSecurityUtil.get();
        FileInfoDto fileInfoDto = fileInfoService.uploadFile(file, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(fileInfoDto));
    }

    // /**
    //  * 文件下载
    //  *
    //  * @param fileId 文件id
    //  * @return {@link ResponseEntity)
    //  * @author luyuhao
    //  * @since 2021/12/10
    //  */
    // @PostMapping("/download")
    // @ApiOperation(value = "文件上传")
    // @Log(value = "文件上传", logLevel = LogLevel.INFO, businessType = BusinessType.INSERT)
    // public ResponseEntity download(@RequestParam(value = "fileId", required = false) Long fileId){
    //     ValidateHandler.checkNull(fileId, FileManageErrorCodeEnum.FILE_ID_NOT_NULL);
    //     byte[] bytes = fileInfoService.download(fileId);
    // }
}
