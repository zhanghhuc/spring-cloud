package mybatis.mapper;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

import mybatis.po.Items;
import mybatis.po.ItemsExample;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class ItemsMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// ����sqlSessionFactory
		String resource = "SqlMapConfig.xml"; // mybatis�����ļ�

		// �õ������ļ�����
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// �����Ự����SqlSessionFactory,Ҫ����mybaits�������ļ�����
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		Items items = new Items();
		items.setName("�ֻ�");
		items.setPrice(5000f);
		items.setCreatetime(new Date());
		itemsMapper.insert(items);
		sqlSession.commit();
	}

	//�Զ����ѯ
	@Test
	public void testSelectByExample() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("����");
		List<Items> itemsList = itemsMapper.selectByExample(itemsExample);
		System.out.println(itemsList);
	}

	@Test
	public void testSelectByPrimaryKey() {
		ItemsMapper itemsMapper = sqlSessionFactory.openSession().getMapper(ItemsMapper.class);
		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println(items);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		Items items = itemsMapper.selectByPrimaryKey(1);
		items.setPrice(3540f);
		itemsMapper.updateByPrimaryKey(items);
		sqlSession.commit();
	}

}
