package cn.test.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;

public class testlog4j {
	private Logger logger = Logger.getLogger(testlog4j.class);

	@Test
	public void demo1() {
		logger.fatal("致命的错误");
		logger.error("普通错误");
		logger.warn("警告信息");
		logger.info("普通信息");
		logger.debug("调试信息");
		logger.trace("堆栈信息");
	}
}
