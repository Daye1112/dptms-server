package com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.FastDfsHandlerFactory;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 小文件简单处理器
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public class SmallFileHandler extends AbstractFileHandler {

    public SmallFileHandler(FastDfsHandlerFactory fastDfsHandlerFactory) {
        super(fastDfsHandlerFactory);
    }

    /**
     * 文件上传
     *
     * @param fileStream  文件流
     * @param fileName    文件名
     * @param metaDataSet 元数据信息
     * @return {@link FileDfsInfoDto}
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/1
     */
    @Override
    public List<FileDfsInfoDto> uploadFile(InputStream fileStream, String fileName, Set<MetaData> metaDataSet) throws Exception {
        FileDfsInfoDto result = super.simpleUpload(fileStream, fileName, metaDataSet, 1);
        return CollectionUtil.packToList(result);
    }

    /**
     * 文件下载
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @return {@link FileDfsInfoDto}
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/1
     */
    @Override
    public byte[] download(List<FileDfsInfoDto> fileDfsInfoList) throws Exception {
        FileDfsInfoDto fileDfsInfo = fileDfsInfoList.get(0);
        return this.simpleDownload(fileDfsInfo).getFileBytes();
    }
}
