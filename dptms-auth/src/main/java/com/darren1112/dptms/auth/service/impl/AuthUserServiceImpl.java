package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.repository.AuthUserRepository;
import com.darren1112.dptms.auth.service.AuthUserService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.util.Md5Util;
import com.darren1112.dptms.common.core.validate.handler.ValidateHandler;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserEntity;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @since 2020/07/23 02:43
 */
@Service
@CacheConfig(cacheNames = "authUser", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class AuthUserServiceImpl extends BaseService implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @since 20/07/23 02:58
     */
    @Override
    @Cacheable
    public AuthUserDto getByUsername(String username) {
        return authUserRepository.getBaseMapper().getByUsername(username);
    }

    /**
     * 更新登录时间
     *
     * @param id 用户id
     * @author luyuhao
     * @since 20/12/09 01:02
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void updateLoginTime(Long id) {
        authUserRepository.getBaseMapper().updateLastLoginTime(id);
    }

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 20/11/30 23:12
     */
    @Override
    @Cacheable
    public AuthUserDto getById(Long id) {
        return authUserRepository.getBaseMapper().getById(id);
    }

    /**
     * 插入用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(AuthUserEntity entity) {
        validRepeat(entity, false);
        authUserRepository.getBaseMapper().insert(entity);
        return entity.getId();
    }

    /**
     * 验证code和url是否重复
     *
     * @param entity   验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 2020/12/12 11:06
     */
    private void validRepeat(AuthUserEntity entity, boolean isUpdate) {
        AuthUserDto param = new AuthUserDto();
        param.setId(entity.getId());
        param.setUsername(entity.getUsername());
        param.setIsUpdate(isUpdate);
        Long count = authUserRepository.getBaseMapper().countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(AuthErrorCodeEnum.USER_NOT_REPEAT);
        }
    }

    /**
     * 分页查询用户
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @Cacheable
    public PageBean<AuthUserDto> listPage(PageParam pageParam, AuthUserDto dto) {
        List<AuthUserDto> list = authUserRepository.getBaseMapper().listPage(pageParam, dto);
        Long count = authUserRepository.getBaseMapper().listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(AuthUserEntity entity) {
        validRepeat(entity, true);
        return authUserRepository.getBaseMapper().update(entity);
    }

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        authUserRepository.getBaseMapper().deleteById(id, updater);
    }

    /**
     * 更新用户锁定状态
     *
     * @param entity 更新状态
     * @author luyuhao
     * @since 2021/01/14 00:19
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void updateLock(AuthUserEntity entity) {
        authUserRepository.getBaseMapper().updateLock(entity);
    }

    /**
     * 更新密码
     *
     * @param dto 用户信息
     * @author luyuhao
     * @since 2021/11/24
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void updatePassword(AuthUserDto dto) {
        // 查询旧用户信息
        AuthUserDto userDto = authUserRepository.getBaseMapper().getById(dto.getId());
        // 判断用户是否存在
        ValidateHandler.checkNull(userDto, AuthErrorCodeEnum.USER_NOT_EXIST);
        // 加密输入的旧密码
        String oldEncPassword = Md5Util.encrypt(dto.getOldPassword(), userDto.getSalt());
        // 验证输入的旧密码与当前密码是否相同
        ValidateHandler.checkParameter(!oldEncPassword.equals(userDto.getPassword()), AuthErrorCodeEnum.USER_PASSWORD_ERROR);
        // 新密码加密
        String newEncPassword = Md5Util.encrypt(dto.getNewPassword(), userDto.getSalt());
        dto.setNewPassword(newEncPassword);
        authUserRepository.getBaseMapper().updatePassword(dto);
    }
}
