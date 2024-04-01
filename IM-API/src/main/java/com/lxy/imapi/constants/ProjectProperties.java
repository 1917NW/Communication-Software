package com.lxy.imapi.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProperties {
    private String name;
    private String module;

    public String getPrefix(){
        return name + ":" + module + ":";
    }

}
