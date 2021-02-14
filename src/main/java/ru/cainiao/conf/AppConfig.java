package ru.cainiao.conf;

import liquibase.integration.spring.SpringLiquibase;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author A.Tretyakov.
 * @since 13.02.2021
 */
@Configuration
public class AppConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db.changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    public Settings settings() {
        return new Settings().withRenderNameStyle(RenderNameStyle.AS_IS);
    }

}
