package app.statistics.simulator;

import app.statistics.application.ELLStreamProcessor;
import app.statistics.model.enums.TaskType;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RealTimeELLSimulation {
    public static void main(String[] args) throws Exception {
        // 1. 설정 파라미터
        int p = 20;
        int t = 4;
        int d = 5;
        int numThreads = 4;

        // 2. 프로세서 초기화
        ELLStreamProcessor processor = new ELLStreamProcessor(p, t, d, numThreads);
        Random random = new Random();

        System.out.println("--- 🟢 1분 윈도우 기반 ELL 스트림 시뮬레이션 시작 ---");

        // 3. 윈도우 1 처리 (여러 개의 배치가 유입)
        System.out.println("\n--- ⏳ WINDOW 1 (0:00 - 1:00) 데이터 유입 시작 ---");
        int window1Events = 0;

        for (int i = 0; i < 3; i++) {
            int batchSize = 3000 + random.nextInt(2000);
            List<Long> incomingHashes = random.longs(batchSize, 0, Long.MAX_VALUE).boxed()
                    .collect(Collectors.toList());
            processor.processBatchAndMerge(incomingHashes);
            window1Events += batchSize;

            System.out.printf("   [BATCH %d] 처리 완료. 현재 윈도우 이벤트 수: %d, 추정된 활성 사용자 수: %.2f\n",
                    i + 1, window1Events, processor.getDistinctCount());
        }

        // 4. 윈도우 1 종료 시뮬레이션 (rollWindow 호출)
        System.out.println("\n--- 🛑 1분 경과: WINDOW 롤링 ---");
        processor.rollWindow(TaskType.ACCOUNT_ID_SIGNED_IN);

        // 5. 윈도우 2 처리 (새로운 스케치에 데이터 유입)
        System.out.println("\n--- ⏳ WINDOW 2 (1:00 - 2:00) 데이터 유입 시작 ---");
        int window2Events = 0;

        for (int i = 0; i < 2; i++) {
            int batchSize = 4000 + random.nextInt(1000);
            List<Long> incomingHashes = random.longs(batchSize, 0, Long.MAX_VALUE).boxed().collect(Collectors.toList());
            processor.processBatchAndMerge(incomingHashes);
            window2Events += batchSize;

            System.out.printf("   [BATCH %d] 처리 완료. 현재 윈도우 이벤트 수: %d, 추정된 활성 사용자 수: %.2f\n",
                    i + 1, window2Events, processor.getDistinctCount());
        }

        // 6. 윈도우 2 종료 시뮬레이션
        System.out.println("\n--- 🛑 2분 경과: WINDOW 롤링 ---");
        processor.rollWindow(TaskType.ACCOUNT_ID_SIGNED_IN);

        System.out.println("==============================================");
        processor.shutdown();
        System.out.println("--- 🔴 시뮬레이션 종료 ---");
    }
}