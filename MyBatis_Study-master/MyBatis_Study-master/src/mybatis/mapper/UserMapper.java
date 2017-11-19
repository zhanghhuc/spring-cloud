package mybatis.mapper;

import java.util.List;

import mybatis.po.User;
import mybatis.po.UserQueryVo;
//mapper�ӿڣ��൱��dao�ӿ�
public interface UserMapper {

	//����id��ѯ�û���Ϣ
	public User findUserById(int id) throws Exception;
	//�����û���ģ����ѯ
	public List<User> findUserByName(String name) throws Exception;
	//�û���Ϣ�ۺϲ�ѯ
	public List<User> findUserList(UserQueryVo userQueryVo) throws Exception;
	//����id��ѯ�û���Ϣ��ʹ��resultMap���
	public User findUserByIdResultMap(int id) throws Exception;
	//����û���Ϣ
	public void insertUser(User user) throws Exception;
	//ɾ���û���Ϣ
	public void deleteUser(int id) throws Exception;
	//�����û���Ϣ
	public void updateUser(User user) throws Exception;
}	
