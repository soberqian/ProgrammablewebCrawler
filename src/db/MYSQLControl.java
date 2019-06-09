package db;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.ApiBasicInfoModel;
import model.DetailInfoModel;

public class MYSQLControl {
	static final Log logger = LogFactory.getLog(MYSQLControl.class);
	//�������ݿ��ַ�����������ݿ�
	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/programmableweb");
	static QueryRunner qr = new QueryRunner(ds);
	//��һ�෽��
	public static void executeUpdate(String sql){
		try {
			qr.update(sql);
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	//����SQL��ѯ�������
	public static Object getScalaBySQL ( String sql ){

		ResultSetHandler<Object> h = new ScalarHandler<Object>(1);
		Object obj = null;
		try {
			obj = qr.query(sql, h);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;

	}
	//����SQL��ѯ������
	public static <T> List<T> getListInfoBySQL (String sql, Class<T> type ){
		List<T> list = null;
		try {
			list = qr.query(sql,new BeanListHandler<T>(type));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//��ѯһ��
	public static List<Object> getListOneBySQL (String sql,String id){
		List<Object> list=null;

		try {
			list = (List<Object>) qr.query(sql, new ColumnListHandler(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//�������ݿ����������Ҫ�Ż�
	public static int insertBasicInfo ( List<ApiBasicInfoModel> list ) {

		Object[][] params = new Object[list.size()][3];
		int c = 0;	//success number of update
		int[] sum;
		for ( int i = 0; i < list.size(); i++ ){
			params[i][0] = list.get(i).getApi_name();
			params[i][1] = list.get(i).getUrl();
			params[i][2] = list.get(i).getCategory();
		}

		QueryRunner qr = new QueryRunner(ds);
		try {
			sum = qr.batch("INSERT INTO basicinfo_cloud(api_name,url,category) VALUES (?,?,?)", params);
			//			qr.batch("UPDATE alluser SET is_crawler = 1 WHERE user_id = ?",params1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("����������");

		return c;

	}
	//�������ݿ����������Ҫ�Ż�
	public static int insertDetailInfo ( List<DetailInfoModel> list ) {

		Object[][] params = new Object[list.size()][3];
		Object[][] params1 = new Object[list.size()][1];
		int c = 0;	//success number of update
		int[] sum;
		for ( int i = 0; i < list.size(); i++ ){
			params1[i][0] = list.get(i).getApi_name();
			params[i][0] = list.get(i).getApi_name();
			params[i][1] = list.get(i).getTags();
			params[i][2] = list.get(i).getContent();
		}
		QueryRunner qr = new QueryRunner(ds);
		try {
			sum = qr.batch("INSERT INTO detailinfo_cloud(api_name,tags,content) VALUES (?,?,?)", params);
			qr.batch("UPDATE basicinfo_cloud SET tag = 1 WHERE api_name = ?",params1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("�������"+list.size()+"��");

		return c;

	}

}
