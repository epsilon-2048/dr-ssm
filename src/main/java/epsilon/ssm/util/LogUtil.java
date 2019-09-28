package epsilon.ssm.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private Logger logger;

    public LogUtil(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void logException(Throwable e) {
        StringBuffer sb = new StringBuffer("# * # * # * # * # * # * # * # * # * # * [LogUtils]\n");
        try {
            StackTraceElement s = e.getStackTrace()[0];
            sb.append("\n报错的文件是：\n\t" + s.getFileName());
            sb.append("\n报错方法是：\n\t" + s.getMethodName());
            sb.append("\n报错的行是：\n\t" + s.getLineNumber());
            sb.append("\n报错信息是：\n\t" + e.getMessage());
        } catch (Exception e2) {
            sb.append("\n【日志后续处理出错】");
        }
        logger.error(sb.toString());
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void error(String msg) {
        logger.error(msg);
    }

}
