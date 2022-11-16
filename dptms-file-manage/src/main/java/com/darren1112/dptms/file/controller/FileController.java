package com.darren1112.dptms.file.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.util.WebUtil;
import com.darren1112.dptms.common.core.validate.handler.ValidateHandler;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.common.enums.FileManageErrorCodeEnum;
import com.darren1112.dptms.file.service.FileInfoService;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.util.SecurityUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        FileInfoDto fileInfoDto = fileInfoService.uploadFile(file, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(fileInfoDto));
    }

    /**
     * 文件下载
     *
     * @param fileId 文件id
     * @return {@link ResponseEntity)
     * @author luyuhao
     * @since 2021/12/10
     */
    @GetMapping("/download")
    @ApiOperation(value = "文件下载")
    public ResponseEntity download(HttpServletRequest request,
                                   @RequestParam(value = "fileId", required = false) Long fileId) {
        // 文件id校验
        ValidateHandler.checkNull(fileId, FileManageErrorCodeEnum.FILE_ID_NOT_NULL);
        // 文件下载
        FileInfoDto fileInfo = fileInfoService.download(fileId);
        try {
            // 文件下载
            return WebUtil.download(fileInfo.getFileName(), fileInfo.getFileBytes(), request);
        } catch (Exception e) {
            // 返回文件下载失败
            return ResponseEntityUtil.internalServerError(JsonResult.buildErrorEnum(FileManageErrorCodeEnum.FILE_DOWNLOAD_ERROR));
        }
    }

    /**
     * 文件预览
     *
     * @param fileId 文件id
     * @return {@link ResponseEntity)
     * @author luyuhao
     * @since 2021/12/10
     */
    @GetMapping("/view")
    @ApiOperation(value = "文件预览")
    public ResponseEntity view(@RequestParam(value = "fileId", required = false) Long fileId) {
        // 文件id校验
        ValidateHandler.checkNull(fileId, FileManageErrorCodeEnum.FILE_ID_NOT_NULL);
        // 文件下载
        FileInfoDto fileInfo = fileInfoService.download(fileId);
        // 文件预览
        return WebUtil.view(fileInfo.getFileName(), fileInfo.getFileBytes());
    }
}
