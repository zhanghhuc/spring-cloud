package mybatis.mapper;

import java.util.List;

import mybatis.po.Orders;
import mybatis.po.OrdersCustom;
import mybatis.po.User;

public interface UserMapperOrders {
	
	//��ѯ������������ѯ�û���Ϣ
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	//��ѯ������������ѯ�û���Ϣ,ʹ��resultMap
	public List<OrdersCustom> findOrdersUserResultMap() throws Exception;
	
	//��ѯ�����������û�����������ϸ
	public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;
	
	//��ѯ�û�������Ʒ��Ϣ
	public List<User> findUserAndItemsResultMap() throws Exception;

	//��ѯ�����������û���ѯ���û���ѯ�õ����ӳټ���
	public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
