package mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import mybatis.po.Orders;
import mybatis.po.OrdersCustom;
import mybatis.po.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserMapperOrdersTest {

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
	public void testUserMapperOrders() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<OrdersCustom> list = userMapperOrders.findOrdersUser();
		System.out.println(list);
	}
	
	@Test
	public void testUserMapperOrdersResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<OrdersCustom> list = userMapperOrders.findOrdersUserResultMap();
		System.out.println(list);
	}
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<Orders> list = userMapperOrders.findOrdersAndOrderDetailResultMap();
		System.out.println(list);
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<User> list = userMapperOrders.findUserAndItemsResultMap();
		System.out.println(list);
	}
	
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		//��ѯ����������
		List<Orders> list = userMapperOrders.findOrdersUserLazyLoading();
		
		//�����ϱߵĶ����б�
		for(Orders orders : list) {
			//ִ��getUser()ȥ��ѯ�û���Ϣ������ʵ�ְ������
			User user = orders.getUser();
			System.out.println(user);
		}
	}
	
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();//�����������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//�±߲�ѯʹ��һ��SqlSession
		//��һ�η������󣬲�ѯidΪ1���û�
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		
//		���sqlSessionȥִ��commit������ִ�в��롢���¡�ɾ���������SqlSession�е�һ�����棬��������Ŀ��Ϊ���û����д洢�������µ���Ϣ�����������
		
		//����user1����Ϣ
		user1.setUsername("�����û�22");
		userMapper.updateUser(user1);
		//ִ��commit����ȥ��ջ���
		sqlSession.commit();
		
		//�ڶ��η������󣬲�ѯidΪ1���û�
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
		
	}
	
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		// �����������
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		// ��һ�η������󣬲�ѯidΪ1���û�
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		
		//����ִ�йرղ�������sqlsession�е�����д��������������
		sqlSession1.close();
		
		//sqlSession3������ջ���ģ����Ҫ���Զ������棬��Ҫ�Ѹò���ע�͵�
		//ʹ��sqlSession3ִ��commit()����
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user  = userMapper3.findUserById(1);
		user.setUsername("������");
		userMapper3.updateUser(user);
		//ִ���ύ�����UserMapper�±ߵĶ�������
		sqlSession3.commit();
		sqlSession3.close();
		
		

		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		// �ڶ��η������󣬲�ѯidΪ1���û�
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);

		sqlSession2.close();

	}


}
