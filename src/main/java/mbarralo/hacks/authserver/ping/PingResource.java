package mbarralo.hacks.authserver.ping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Ping resource.
 */
@RestController
public class PingResource {


    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;


    /**
     * Hello map.
     *
     * @return the map
     */
    @GetMapping
    @RequestMapping("ping")
    public Map<String, String> hello() {
        Map<String, String> state = new HashMap<>();
        state.put("app", appName);
        state.put("version", appVersion);
        state.put("date", LocalDateTime.now().toString());
        return state;
    }


}
