package com.example.holaserver.Config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.runner.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableEncryptableProperties
@Slf4j
public class JasyptConfig {
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() throws IOException {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(getJasyptEncryptorPassword());
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor;
    }

    private String getJasyptEncryptorPassword() throws IOException {
//        Resource resource = new InputStreamResource(getClass().getResourceAsStream("secret/rds-dev-secret-key.txt"));

        InputStream inputStream = new ClassPathResource("secret/rds-dev-secret-key.txt").getInputStream();
        File file = File.createTempFile("rds-dev-secret-key",".txt");
        try{
            FileUtils.copyInputStreamToFile(inputStream, file);
            System.out.println("T: " + FileUtils.readFileToString(file, StandardCharsets.UTF_8));
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e){
            throw new RuntimeException("Not found Jasypt password file.");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}