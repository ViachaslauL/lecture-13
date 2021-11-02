package by.itacademy.javaenterprise.lepnikau.app;

import by.itacademy.javaenterprise.lepnikau.app.config.SchoolDiaryConfig;
import by.itacademy.javaenterprise.lepnikau.app.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.app.dao.implement.MarkDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SchoolDiaryConfig.class);

        MarkDAO markDAO = context.getBean(MarkDAOImpl.class);


        getAllPageByPageTest(markDAO, 5, 0);
    }

    private static void getAllPageByPageTest(MarkDAO dao, int limit, int offset) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            List<Mark> allPageByPage = dao.getAllPageByPage(limit, offset);
            if (!allPageByPage.isEmpty()) {
                for (Mark m : allPageByPage) {
                    sBuilder.append("\n").append(m);
                }
                sBuilder.append("\n--Page--");
                offset += limit;
            } else {
                break;
            }
        }
        log.info(sBuilder.toString());
    }
}
