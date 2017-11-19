package mybatis.dao;

import java.util.List;

import mybatis.po.User;

public interface UserDao {
	
	//����id��ѯ�û���Ϣ
	public User findUserById(int id) throws Exception;
	//�����û���ģ����ѯ
	public List<User> findUserByName(String name) throws Exception;
	//����û���Ϣ
	public void insertUser(User user) throws Exception;
	//ɾ���û���Ϣ
	public void deleteUser(int id) throws Exception;
	//�����û���Ϣ
	public void updateUser(User user) throws Exception;
}	
