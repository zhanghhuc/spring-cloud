package mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import mybatis.po.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MybatisFirst {

	public SqlSession getSession() throws IOException {
		
		String resource = "SqlMapConfig.xml"; //mybatis�����ļ�
		
		//�õ������ļ�����
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//�����Ự����SqlSessionFactory,Ҫ����mybaits�������ļ�����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//ͨ�������õ�SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		return sqlSession;
	}
	
	//����id��ѯ�û�����Ϣ���õ�һ����¼�Ľ��
	@Test
	public void findUserById() throws IOException {
		
		SqlSession sqlSession = getSession();
		
		//ͨ��SqlSession�������ݿ�
		//��һ��������ӳ���ļ���statement��id��= namespace + statement��id
		//�ڶ���������ָ����ӳ���ļ�����ƥ���parameterType���͵Ĳ���
		//selectOne��ʾ��ѯ��һ����¼����ӳ��
		User user = sqlSession.selectOne("test.findUserById", 1);
		
		System.out.println(user);
		
		//�ͷ���Դ����÷���finally�У�����ֻ�ǲ��Գ��򣬾Ͳ�Ū��
		sqlSession.close();
	}
	
	//�����û�����ģ����ѯ�û��б�
	@Test
	public void findUserByName() throws IOException {
		SqlSession sqlSession = getSession();
				
		//selectList��ʾ��ѯ��һ���б�������¼������ӳ��
		List<User> list = sqlSession.selectList("test.findUserByName", "����");
		
		System.out.println(list);
		
		//�ͷ���Դ����÷���finally�У�����ֻ�ǲ��Գ��򣬾Ͳ�Ū��
		sqlSession.close();
	}
	
	//����û���Ϣ
	@Test
	public void insertUser() throws IOException {
		SqlSession sqlSession = getSession();
		
		User user = new User("������", new Date(), "��", "ͬ�ô�ѧ");
		
		sqlSession.insert("test.insertUser", user);
		
		//�ύ����
		sqlSession.commit();
		
		System.out.println(user.getId());
		
		//�ͷ���Դ����÷���finally�У�����ֻ�ǲ��Գ��򣬾Ͳ�Ū��
			sqlSession.close();
	}
	
	//ɾ���û���Ϣ
	@Test
	public void deleteUser() throws IOException {
		SqlSession sqlSession = getSession();
		
		//����id��ɾ���û�
		sqlSession.delete("test.deleteUser", 16);
		
		//�ύ����
		sqlSession.commit();
		
		//�ͷ���Դ����÷���finally�У�����ֻ�ǲ��Գ��򣬾Ͳ�Ū��
		sqlSession.close();
	}
	
	//�����û���Ϣ
	@Test
	public void updateUser() throws IOException {
		SqlSession sqlSession = getSession();
		
		User user = new User("������", new Date(), "��", "ͬ�ô�ѧ");
		user.setId(9);
		
		//�����û�
		sqlSession.update("test.updateUser", user);
		
		//�ύ����
		sqlSession.commit();
		
		//�ͷ���Դ����÷���finally�У�����ֻ�ǲ��Գ��򣬾Ͳ�Ū��
		sqlSession.close();
	}
}
