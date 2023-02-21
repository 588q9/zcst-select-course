package top.ashonecoder.zcstselectcourse.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@ToString
@ConfigurationProperties("user")
@Component
public class UserProperties {
    private String studentNumber;
    private String password;
    private List<Map<String,String>> courseList;

}
