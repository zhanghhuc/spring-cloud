4. SqlMapConfig.xml�ļ��е�����

SqlMapConfig.xml��mybatis��ȫ�����õ��ļ������õ��������£�

1. properties(����)
2. settings(ȫ�ֲ���)
3. typeAliases(���ͱ���)
4. typeHandlers(���ʹ�����)
5. objectFactory(���󹤳�)
6. plugins(���)
7. environments(�����������Զ���)
	environment(���������Զ���)
		transactionManager(�������)
		dataSource(����Դ)
8. mappers(ӳ����)

����ϸ�ڣ� 
1. properties����
���󣺽����ݿ����Ӳ�����������db.properties�ļ��У�ֻ��Ҫ��SqlMapConfig.xml�м���db.properties������ֵ
��SqlMapConfig.xml�оͲ���Ҫ�����ݿ����Ӳ�������Ӳ�����ˡ�
SqlMapConfig.xml�м��������ļ���
	<properties resource="db.properties"></properties>
	��properties�ڲ������Զ�������ֵ��<property name="" value=""/>
		��property�ж��������Ҳ���Ա�evironments�������أ�����漰����mybatis�������Ե�˳���ˣ�
		1) ��propertiesԪ�����ڶ�������ȱ���ȡ
		2) Ȼ���ȡpropertiesԪ����resource����url���ص����ԣ����Ḳ���Ѷ�ȡ��ͬ������
		3) ����ȡparameterType���ݵ����ԣ����Ḳ���Ѷ���ͬ������
		���飺
		��Ҫ��propertiesԪ����������κ�����ֵ��ֻ������ֵ������properties�ļ���
		��properties�ļ��ж����������Ҫ��һ���������ԣ���xxx.xxx
		
2. settingsȫ�ֲ�������
mybatis���������ʱ���Ե���һЩ���в��������磺�����������桢�����ӳټ��ء�
��Щȫ�ֲ��������û�Ӱ��mybatis��������Ϊ����Ҫ��ʱ�������ã�����Ҫ��ʱ����������

3. typeAliases���ͱ���(�ص�)
��mapper.xml�ж����˺ܶ��statement��statement��ҪparameterType����ָ��������������͡�
��ҪresultType��ָ�������������͡�
�����ָ������ʱ��������ȫ·������ʱ���ܳ�����������п������������parameterType��resultTypeָ�������Ͷ���һЩ������
��mapper.xml��ͨ�����������壬���㿪��
mybatis��Ĭ��֧�ֵ�һЩ������һ��������Ͷ��б�������java.lang.Integer�ı���Ϊint
�������pojo����Ҫ�����Զ��������
	<!-- �����Ķ��� -->
	<typeAliases>
		<!-- ��Ե��������Ķ��� 
			type�����͵�·���� alias������
		-->		
		<typeAlias type="mybatis.po.User" alias="user"/>
	</typeAliases>
	Ȼ�������������������Ϊmybatis.po.Userʱ���Ϳ�����user��������
	
	<!-- ������������(�������)
		ָ��һ��������mybatis���Զ���ɨ�����po�࣬�Զ����������������������������ĸ��д��Сд�����ԣ�
	-->
	<package name="mybatis.po"/>
	
4. typeHandlers���ʹ�����
��mybatis����ͨ��typeHandlers���jdbc���ͺ�java���͵�ת����mybatisĬ��֧�ֵ����ʹ���������������ʹ����
һ�㲻��Ҫ�Զ���

5. objectFactory(���󹤳�)
6. plugins(���)
������һ�����ǲ���

7. environments(�����������Զ���)
	environment(���������Զ���)
		transactionManager(�������)
		dataSource(����Դ)
	���Ŀǰ����mybatis������ģ�������Spring���Ϻ󣬻������
	
8. mappers(ӳ������)
ͨ��resource�������ص���ӳ���ļ�
	<mappers>
		<mapper resource="sqlmap/User.xml" />
		<mapper resource="mapper/UserMapper.xml" />
	</mappers>
ͨ��url�������ص���ӳ���ļ�������ʹ��url�Ļ��Ǿ���·����Ӳ���е�·��
ͨ��mapper�ӿ�������
	<!-- ͨ��mapper�ӿڼ���ӳ���ļ� 
			��ѭһЩ�淶����Ҫ��mapper�ӿ�������mapper.xmlӳ���ļ����Ʊ���һ�£�����һ��Ŀ¼��
			����淶��ǰ���ǣ�ʹ�õ���mapper����ķ���
	-->
	���������<mapper resource="mapper/UserMapper.xml" />�Ϳ��Ըĳ�
	<mapper class="mybatis.mapper.UserMapper"/>
	����Ҫ��config.mapper�е�UserMapper.xml�ϵ���UserMapper.javaһ��Ŀ¼����ͬ��
	
	
	Ҳ�����������أ��Ƽ�ʹ�ã�
	<!-- ��������mapper
			ָ��mapper�ӿڵİ�����mybatis�Զ�ɨ����������е�mapper�ӿڽ��м���
			��ѭһЩ�淶����Ҫ��mapper�ӿ�������mapper.xmlӳ���ļ����Ʊ���һ�£�����һ��Ŀ¼��
			����淶��ǰ���ǣ�ʹ�õ���mapper����ķ���
	 -->
	<package name="mybatis.mapper"/>
	����mybatis.mapper���µ�����mapper.xml�����ļ������Լ�����
	