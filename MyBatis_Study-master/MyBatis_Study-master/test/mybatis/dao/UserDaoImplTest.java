package mybatis.dao;
import java.io.InputStream;

import mybatis.dao.UserDao;
import mybatis.dao.UserDaoImpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
	
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
		//����UserDao�Ķ���
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		System.out.println(userDao.findUserById(1));
	}
}
