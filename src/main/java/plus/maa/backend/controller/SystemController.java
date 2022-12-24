package plus.maa.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.maa.backend.common.bo.EmailBusinessObject;
import plus.maa.backend.controller.response.MaaResult;
import plus.maa.backend.service.EmailService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AnselYuki
 */
@Data
@Tag(name = "System", description = "系统接口")
@RequestMapping("")
@RestController
@RequiredArgsConstructor
public class SystemController {
    @Value("${maa-copilot.info.version}")
    private String version;
    @Value("${maa-copilot.info.description}")
    private String description;

    private final EmailService emailService;


    @GetMapping("/")
    public MaaResult<String> test() {
        return MaaResult.success("Maa Copilot Server is Running", null);
    }

    @GetMapping("version")
    public MaaResult<Map<String, String>> version() {
        Map<String, String> map = new HashMap<>();
        map.put("time", LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
        map.put("version", version);
        return MaaResult.success(map);
    }

    @GetMapping("test/email")
    public MaaResult<String> testEmail() {
        new EmailBusinessObject().TestEmail();
        return MaaResult.success("发送成功");
    }
}
