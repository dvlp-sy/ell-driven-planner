package app.statistics.presentation;

import app.statistics.application.ELLStreamService;
import app.statistics.model.enums.TaskType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StatisticsBatchController {
    private final ELLStreamService ellStreamService;

    @Scheduled(cron = "0 0/1 * * * *") // 1분마다 실행
    public void process() {
        log.info("");
        ellStreamService.transferQueueToProcessor(TaskType.ACCOUNT_ID_SIGNED_IN);
        log.info("");
    }
}
