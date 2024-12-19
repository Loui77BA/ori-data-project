package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        var context = SpringApplication.run(DemoApplication.class, args);

        // Obter o ambiente da aplicação
        Environment env = context.getEnvironment();

        // Log do perfil ativo
        String activeProfiles = String.join(", ", env.getActiveProfiles());
        logger.info("Aplicação iniciada com sucesso!");

        // Informar o perfil ativo ou usar "default" caso nenhum esteja configurado
        if (activeProfiles.isEmpty()) {
            logger.info("Nenhum perfil ativo configurado. Usando o perfil 'default'.");
        } else {
            logger.info("Perfis ativos: {}", activeProfiles);
        }

        // Log adicional da porta em que o servidor está rodando
        String port = env.getProperty("server.port", "8080");
        logger.info("A aplicação está rodando na porta: {}", port);
    }
}
