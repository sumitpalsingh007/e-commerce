package com.helloworldtechconsulting.config;

import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@Configuration
public class EmbeddingConfiguration {

    @Bean
    EmbeddingStore embeddingStore(JdbcTemplate jdbcTemplate) throws SQLException {

        String datasourceUrl = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
        String host = datasourceUrl.split("//")[1].split(":")[0];
        String port = datasourceUrl.split(":")[3].split("/")[0];
        String database = datasourceUrl.split("/")[3].split("\\?")[0];

        return PgVectorEmbeddingStore.builder()
                .host(host)
                .port(Integer.valueOf(port))
                .database(database)
                .user("postgres")
                .password("postgres")
                .table("embeddings")
                .dimension(384)
                .build();
    }
}
