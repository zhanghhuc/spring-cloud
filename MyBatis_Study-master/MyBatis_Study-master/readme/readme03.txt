3. mapper����������dao

����Ա��Ҫ��дmapper.xmlӳ���ļ�
����Ա��Ҫ��дmapper�ӿ�(�൱��dao�ӿ�)����Ҫ��ѭһЩ�����淶������mybatis�����Զ�����mapper�ӿ�ʵ����Ĵ������

˼·��(�����淶)
1. ��mapper.xml�У�ʹnamespace����mapper�ӿڵĵ�ַ����ȫ�޶�����
2. mapper.java�ӿ��еķ�������mapper.xml��statement��idһ��
3. mapper.java�ӿ��з���������������ͺ�mapper.xml��statement��parameterTypeָ��������һ��
4. mapper.java�ӿ��з�������ֵ���ͺ�mapper.xml��statement��resultTypeָ��������һ��

���ϵĹ淶��Ҫ�Ƕ������±���Щ�������ͳһ����
User user = sqlSession.selectOne("test.findUserById, id);
sqlSession.insert("test.insertUser", user);

��������ȫ�������ļ�SqlMapConfig.xml�����øոյ�UserMapper.xml
<mappers>
		<mapper resource="sqlmap/User.xml" />
		<mapper resource="mapper/UserMapper.xml" />
</mappers>

���mapper�������ص���pojo���󣨷Ǽ��϶��󣩣���������ڲ�ͨ��selectOne����ѯ���ݿ�
���mapper��������һ���Ǽ��϶��󣬴�������ڲ�ͨ��selectList����ѯ���ݿ�
