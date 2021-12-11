package com.darren1112.dptms.common.core.util;

import com.darren1112.dptms.common.core.enums.FileTypeInfoEnum;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * web相关工具类
 *
 * @author luyuhao
 * @since 2021/01/31 18:51
 */
public class WebUtil {

    /**
     * 获取用户使用的操作系统
     *
     * @return 操作信息
     * @author luyuhao
     * @since 2021/01/31 19:02
     */
    public static String getOs() {
        String userAgentStr = RequestUtil.getHeaderByName("User-Agent");
        // 解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return operatingSystem.getName();
    }

    /**
     * 获取用户使用的浏览器
     *
     * @return 浏览器+版本
     * @author luyuhao
     * @since 2021/01/31 19:01
     */
    public static String getBrowser() {
        String userAgentStr = RequestUtil.getHeaderByName("User-Agent");
        // 解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        // 获取浏览器对象
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @param fileByte 文件字节流
     * @param request  请求域
     * @return {@link ResponseEntity}
     * @author luyuhao
     * @since 2021/12/11
     */
    public static ResponseEntity<byte[]> download(String fileName, byte[] fileByte, HttpServletRequest request) throws Exception {
        Assert.notNull(fileName, "文件名不能为空");
        fileName = fileNameEncode(request, fileName);
        //设置head
        HttpHeaders headers = new HttpHeaders();
        //文件的属性名
        headers.add("Content-Disposition", "attachment;fileName=" + fileName);
        headers.add("Content-Type", "application/octet-stream;charset=UTF-8");
        return ResponseEntityUtil.ok(headers, fileByte);
    }

    /**
     * 根据请求头对文件名称进行编码
     *
     * @param request  请求域
     * @param fileName 文件名称
     * @return {@link String}
     * @author luyuhao
     * @since 2021/12/11
     */
    private static String fileNameEncode(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String agent = request.getHeader("User-Agent");
        if (agent != null) {
            if (agent.toLowerCase().contains("firefox")) {
                fileName = "=?UTF-8?B?" + new String(Base64.encodeBase64(fileName.getBytes(StandardCharsets.UTF_8))) + "?=";
            } else if (agent.toLowerCase().contains("safari")) {
                fileName = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
            } else if (!agent.toUpperCase().contains("MSIE")
                    && !agent.toUpperCase().contains("TRIDENT")
                    && agent.toLowerCase().contains("chrome")) {
                fileName = "\"" + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1) + "\"";
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                fileName = fileName.replace("%29", "");
                fileName = fileName.replace("+", "%20");
            }
        } else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        return fileName;
    }

    /**
     * 文件预览
     *
     * @param fileName  文件名
     * @param fileBytes 文件流
     * @return {@link ResponseEntity}
     * @author luyuhao
     * @since 2021/12/11
     */
    public static ResponseEntity view(String fileName, byte[] fileBytes) {
        Assert.notNull(fileName, "文件名不能为空");
        String extName = FileUtil.extName(fileName);
        // 匹配文件信息
        FileTypeInfoEnum fileTypeInfoEnum = FileTypeInfoEnum.matchByExtName(extName);
        //设置head
        HttpHeaders headers = new HttpHeaders();
        //文件的属性名
        headers.add("Content-Type", fileTypeInfoEnum.getMediaType());
        return ResponseEntityUtil.ok(headers, fileBytes);
    }
}
