package org.graduate.student.score.manage;

import com.google.gson.Gson;
import org.graduate.role.service.PermissionService;
import org.graduate.role.service.entity.PermissionEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "org.graduate")
@MapperScan(basePackages = "org.graduate.*.repository")
@Configuration
public class Application {

    @Value("${menu.permission.location}")
    public String menuPermissionLocation;

    private final PermissionService permissionService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public Application(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PUT")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600)
                ;
            }
        };
    }

    @PostConstruct
    public void initPermission() {
        File menuPermissionFile = new File(menuPermissionLocation);
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(menuPermissionFile));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                stringBuffer.append(tempStr);
            }
            reader.close();
            Gson gson = new Gson();
            Map<?, ?> json = gson.fromJson(stringBuffer.toString(), Map.class);
            System.out.println(stringBuffer);
            List<?> menuItemData = (List<?>) json.get("menu-item-data");
            List<Map<String, String>> allPermissions = new ArrayList<>();
            menuItemData.forEach(o -> {
                Map<?, ?> menuItem = (Map<?, ?>) o;
                List<Map<String, String>> itemPermissions = getPermissions(menuItem);
                allPermissions.addAll(itemPermissions);
            });
            System.out.println(allPermissions);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private List<Map<String, String>> getPermissions(Map<?, ?> menuItem) {
        List<Map<String, String>> permissions = new ArrayList<>();
        Map<String, String> allPermission = new HashMap<>();
        Map<?, ?> permission = (Map<?, ?>) menuItem.get("permissions");
        String menuPermission = (String) permission.get("menu");
        allPermission.put("name", "menu");
        allPermission.put("permission", menuPermission);
        List<?> operations = (List<?>) permission.get("operation");
        if (operations != null) {
            operations.forEach(o -> {
                Map<?, ?> operationPermissions = (Map<?, ?>) o;
                String operationName = (String) operationPermissions.get("name");
                String operationPermission = (String) operationPermissions.get("permission");
//                allPermission.put(operationName, operationPermission);
                allPermission.put("name", operationName);
                allPermission.put("permission", operationPermission);
            });
        }
        permissions.add(allPermission);
        List<?> childMenu = (List<?>) menuItem.get("child");
        if (childMenu != null) {
            childMenu.forEach(o -> {
                Map<?, ?> childMenuItem = (Map<?, ?>) o;
                List<Map<String, String>> childPermissions = getPermissions(childMenuItem);
                permissions.addAll(childPermissions);
            });
        }
        return permissions;
    }

    private void savePermission(List<Map<String, String>> permissions) {
        System.out.println(permissionService);
        permissions.forEach(o -> {
            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setName(o.get("name"));
            permissionEntity.setName(o.get("permission"));
            permissionService.save(permissionEntity);
        });

    }
}