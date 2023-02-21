package top.ashonecoder.zcstselectcourse.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Data
@ToString
@ConfigurationProperties("select-course")

public class SelectCourseProperties {


private  String selectCourseInterface;
    private  String loginInterface;
private String loginPageInterface;
private String   loginMenuInterface;
private List<String> infoInterfaces;
private  String courseoOptionalListInterface;
    private String[] otherData;
private  String cronExpression;
}
