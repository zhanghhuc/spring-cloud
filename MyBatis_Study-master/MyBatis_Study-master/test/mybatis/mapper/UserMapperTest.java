package mybatis.mapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mybatis.mapper.UserMapper;
import mybatis.po.User;
import mybatis.po.UserQueryVo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception {
		//����sqlSessionFactory
		String resource = "SqlMapConfig.xml"; //mybatis�����ļ�
	
		//�õ������ļ�����
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//�����Ự����SqlSessionFactory,Ҫ����mybaits�������ļ�����
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	
	@Test
	public void testFindUserById() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByName() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findUserByName("������");
		sqlSession.close();
		System.out.println(list);
	}
	
	@Test
	public void testFindUserList() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//������װ�������ò�ѯ����
		UserQueryVo userQueryVo = new UserQueryVo();
		User user = new User();
		//��������ʹ�ö�̬sql�����������ĳ��ֵ����������ƴ����sql��
		user.setSex("��");
		user.setUsername("������");
		//������id
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(12);
		ids.add(17);
		userQueryVo.setIds(ids);
		userQueryVo.setUser(user);
		
		//����userMapper�ķ���
		List<User> list = userMapper.findUserList(userQueryVo);
		System.out.println(list);
	}
	
	@Test
	public void testFindUserByIdResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = userMapper.findUserByIdResultMap(1);
		System.out.println(user);
	}

}