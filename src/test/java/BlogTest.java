import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;


@Slf4j
public class BlogTest {

    @Test
    public void test() {
        log.info("info测试");
        log.info("info测试");
        log.debug("debug测试");
        log.error("error测试");
        log.warn("warn测试");
    }
}
