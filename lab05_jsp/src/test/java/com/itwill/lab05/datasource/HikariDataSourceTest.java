package com.itwill.lab05.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceTest {
	private static final Logger log = LoggerFactory.getLogger(HikariDataSourceTest.class);
	
	private final DataSourceUtil util = DataSourceUtil.getInstance();
	
	// JUnit 모듈에서 단위 테스트를 하기 위해서 호출하는 메서드.
	// (1) public void. (2) 아규먼트를 갖지 않음.
	@Test
	public void test() throws SQLException {
		HikariDataSource ds = util.getDataSource();
		Assertions.assertNotNull(ds); // ds가 null이 아니면 단위 테스트 성공.
		log.debug("ds = {}", ds); // System.out.printf("ds = %s", ds) // 15:54:48.360 DEBUG [ll.lab05.datasource.HikariDataSourceTest] ds = HikariDataSource (HikariPool-1)
		
		Connection conn = ds.getConnection(); // 커넥션 풀에서 커넥션 객체를 빌려옴.(Hikari 커넥션. Oracle X)
		Assertions.assertNotNull(conn); // conn이 null이 아니면 단위 테스트 성공.
		log.debug("conn = {}", conn); // 15:57:34.312 DEBUG [ll.lab05.datasource.HikariDataSourceTest] conn = HikariProxyConnection@1205419533 wrapping oracle.jdbc.driver.T4CConnection@7b64240d
		
		conn.close(); // 사용했던 커넥션(연결)을 커넥션 풀에 반환.
		log.debug("CP closed"); // 15:57:34.323 DEBUG [ll.lab05.datasource.HikariDataSourceTest] CP closed
		
//		log.debug("test..."); // 15:39:19.955 DEBUG [ll.lab05.datasource.HikariDataSourceTest] test...
	}

}
