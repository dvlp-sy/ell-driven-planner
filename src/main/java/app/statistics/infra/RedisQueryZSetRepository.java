//package app.statistics.infra;
//
//import app.statistics.model.TaskMemento;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Repository
//public class RedisQueryZSetRepository implements TaskMementoZSetRepository {
//    private static final String ZSET_KEY = "task:timestamp_index";
//
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final RedisTemplate<String, TaskMemento> taskRedisTemplate;
//
//
//    public RedisQueryZSetRepository(@Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate,
//                                    @Qualifier("taskZSetTemplate") RedisTemplate<String, TaskMemento> taskRedisTemplate) {
//        this.redisTemplate = redisTemplate;
//        this.taskRedisTemplate = taskRedisTemplate;
//    }
//
//    private String getTaskKey(String taskId) {
//        return taskId;
//    }
//
//    /**
//     * Task 저장 시, ZSet 인덱스도 함께 업데이트합니다.
//     * 이 코드는 CrudRepository.save() 호출 후 실행되도록 AOP 등으로 처리하거나,
//     * 또는 CrudRepository를 사용하지 않고 이 TaskRepositoryImpl에서 save() 로직을 직접 구현해야 합니다.
//     * 여기서는 편의상 save 로직을 생략하고, findByTimestampAscending만 구현합니다.
//     * * (실제 환경에서는 @EventListener 등을 사용하여 Task가 저장될 때마다 이 ZADD를 호출해야 합니다.)
//     */
//
//    @Override
//    public List<TaskMemento> findByTimestampAsc(long offset, long limit) {
//        Set<Object> taskIds = redisTemplate.opsForZSet()
//                .range(ZSET_KEY, offset, offset + limit - 1);
//
//        if (taskIds == null || taskIds.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        List<String> taskKeys = taskIds.stream()
//                .map(id -> getTaskKey(id.toString()))
//                .toList();
//
//        List<TaskMemento> tasks = new ArrayList<>();
//        for (String key : taskKeys) {
//            TaskMemento task = taskRedisTemplate.opsForValue().get(key);
//            if (task != null) {
//                tasks.add(task);
//            }
//        }
//        return tasks;
//    }
//}
