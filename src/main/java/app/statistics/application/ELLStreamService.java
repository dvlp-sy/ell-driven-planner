package app.statistics.application;

import app.account.model.AccountSignInEvent;
import app.statistics.infra.TaskMementoRepository;
import app.statistics.model.TaskMemento;
import app.statistics.model.enums.TaskType;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ELLStreamService {
    private static final int P = 20;
    private static final int T = 4;
    private static final int D = 5;
    private static final int NUM_THREADS = 4;
    private static final int MAX_BATCH_SIZE = 1_000_000_000;

    private final TaskMementoRepository taskMementoRepository;
    private final ELLStreamProcessor ellStreamProcessor;
    private final BlockingQueue<Long> eventQueue;

    public ELLStreamService(TaskMementoRepository taskMementoRepository) {
        this.taskMementoRepository = taskMementoRepository;
        this.ellStreamProcessor = new ELLStreamProcessor(P, T, D, NUM_THREADS);
        this.eventQueue = new LinkedBlockingQueue<>();
    }

    /**
     * 이벤트를 받아 ELLStreamProcessor에 데이터를 추가하는 메서드
     */
    @Async
    @EventListener(AccountSignInEvent.class)
    public void addEventData(AccountSignInEvent event) {
        eventQueue.offer(event.getAccountId());
    }

    /**
     * 큐에 쌓인 이벤트 데이터를 배치로 처리하여 ELLStreamProcessor에 전달하는 메서드
     */
    public void transferQueueToProcessor(TaskType taskType) {
        if (eventQueue.isEmpty()) {
            return;
        }
        List<Long> batch = new ArrayList<>();
        int elementsDrained = eventQueue.drainTo(batch, MAX_BATCH_SIZE);

        if (elementsDrained > 0) {
            try {
                ellStreamProcessor.processBatchAndMerge(batch);
                TaskMemento taskMemento = ellStreamProcessor.rollWindow(taskType);
                taskMementoRepository.save(taskMemento);
            } catch (Exception e) {
                throw new IllegalStateException("Failed to process batch", e);
            }
        }
    }

}
