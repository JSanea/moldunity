package web.app.moldunity.util;

import jakarta.annotation.PostConstruct;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MyBatisScriptUtil {
    private final DataSource dataSource;

    @Autowired
    public MyBatisScriptUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void runScripts(List<String> paths) throws Exception {
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.setSendFullScript(false);
        scriptRunner.setStopOnError(true);
        scriptRunner.setDelimiter("$$");
        for(String path : paths){
            scriptRunner.runScript(new java.io.FileReader(path));
        }
    }

    @PostConstruct
    private void runScriptsAtStart() throws Exception {
        String rootPath = "/home/alx/Desktop/Projects/Java/moldunity/src/main/resources/sql/";
        List<String> scripts = List.of(
                rootPath + "create_full_text_index.sql"
        );

        runScripts(scripts);
    }
}













