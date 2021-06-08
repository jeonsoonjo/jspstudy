package mybatis.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 역할 : SqlSessionFactory를 빌드해서 DAO에 넘겨준다

public class DBService {
	
	// singleton
	private static DBService instance = new DBService();
	private SqlSessionFactory factory;
	// SqlsessionFactory 빌드
	private DBService() {
		try {
			String resource = "mybatis/config/sqlmap-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// DBService.getInstance().getFactory()
	public static DBService getInstance() {
		if(instance == null) {
			instance = new DBService();
		}
		return instance;
	}
	public SqlSessionFactory getFactory() {
		return factory;
	}
	
}


