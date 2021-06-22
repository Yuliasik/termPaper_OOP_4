package repository.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.controller.StudentController;
import repository.dao.StudentDao;
import repository.dao.StudentDaoImp;
import repository.mapper.StudentRowMapper;

import javax.sql.DataSource;

@Configuration
@PropertySource("/application.properties")
public class Config {
    @Value("${db.driver}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(20);

        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public StudentController studentController(StudentDao studentDao){
        return new StudentController(studentDao);
    }

    @Bean
    public StudentRowMapper studentRowMapper(){
        return new StudentRowMapper();
    }

    @Bean
    public StudentDao studentDao(JdbcTemplate jdbcTemplate, StudentRowMapper studentRowMapper){
        return new StudentDaoImp(jdbcTemplate, studentRowMapper);
    }
}
