package web.app.moldunity.util;


public class ThreadPoolAndHikariPoolSize {
    public static Integer getPoolSize(){
        return Runtime.getRuntime().availableProcessors();
    }

    public static Integer getMaxPoolSize(){
        return getPoolSize() * 2;
    }
}




