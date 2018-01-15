package cn.hzhy.oa.dao;

import java.util.List;

import cn.hzhy.oa.entity.Role;

/**
 * 角色信息的持久化操作接口
 * @author Administrator
 *
 */
public interface RoleDao {
	/**
	 * 根据ID查询角色信息
	 * @param roleId
	 * @return
	 */
	public Role getById(int roleId);
	/**
	 * 获取所有角色信息
	 * @return
	 */
	public List<Role> getAll();
}
