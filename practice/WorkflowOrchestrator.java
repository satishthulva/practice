import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkflowOrchestrator {

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        graph.addTask(new Task("t1"));
        graph.addTask(new Task("t2"));
        graph.addTask(new Task("t3"));

        graph.addDependency("t3", "t2");
        graph.addDependency("t3", "t1");

        try {
            Future f = graph.executeWorkflow();
            f.get();
            System.out.println("Main method completed");
            graph.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Task implements Runnable {
        private final String taskId;

        public Task(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskId() {
            return taskId;
        }

        @Override
        public void run() {
            System.out.println(taskId + " is running at time " + System.currentTimeMillis());
        }
    }

    private static class Graph {
        private final Map<String, List<String>> adjacencyMatrix = new HashMap<>();
        private final Map<String, Task> taskMap = new HashMap<>();

        private final ExecutorService executor = Executors.newCachedThreadPool();
        private final ExecutorService orchestrator = Executors.newCachedThreadPool();

        public void shutdown() {
            executor.shutdown();
            orchestrator.shutdown();
            System.out.println("Graph shutdown");
        }

        public void addTask(Task task) {
            adjacencyMatrix.putIfAbsent(task.getTaskId(), new ArrayList<>());
            taskMap.put(task.getTaskId(), task);
        }

        public void addDependency(String from, String to) {
            // to task has to be completed before from
            adjacencyMatrix.get(from).add(to);
        }

        public Future<Void> executeWorkflow() {
            Future f2 = orchestrator.submit(() -> {
                Map<String, Set<String>> taskToAllDeps = getExecutionOrder(adjacencyMatrix);
                Map<String, Future<Void>> futureMap = new HashMap<>();
                while(futureMap.size() < taskToAllDeps.size()) {
                    for (Map.Entry<String, Set<String>> entry : taskToAllDeps.entrySet()) {
                        String taskId = entry.getKey();
                        if(futureMap.containsKey(taskId)) {
                            continue;
                        }
                        Set<String> allDeps = entry.getValue();
                        if (allDepTasksCompleted(futureMap, allDeps)) {
                            Future f = executor.submit(new Task(taskId));
                            futureMap.put(taskId, f);
                        }
                    }
                }
                System.out.println("Executed the workflow completely");
            });
            return f2;
        }

        private boolean allDepTasksCompleted(Map<String, Future<Void>> futureMap, Set<String> allDeps) {
            for (String dep : allDeps) {
                if (!futureMap.containsKey(dep)) {
                    return false;
                }
                if(!futureMap.get(dep).isDone()) {
                    return false;
                }
            }
            return true;
        }

        private Map<String, Set<String>> getExecutionOrder(Map<String, List<String>> adjacencyMatrix) {
            Map<String, Set<String>> taskToAllDependencies = new HashMap<>();
            Set<String> processedNodes = new HashSet<>();
            for(String keyId : adjacencyMatrix.keySet()) {
                if(adjacencyMatrix.get(keyId).isEmpty()) {
                    taskToAllDependencies.putIfAbsent(keyId, new HashSet<>());
                    processedNodes.add(keyId);
                }
            }

            for(String taskId: adjacencyMatrix.keySet()) {
                processNode(taskId, processedNodes, adjacencyMatrix, taskToAllDependencies);
            }

            return taskToAllDependencies;
        }

        // TODO : handle cycle detection so that we will not go into loops
        private void processNode(String taskId,
                                 Set<String> processedNodes,
                                 Map<String, List<String>> adjacencyMatrix,
                                 Map<String, Set<String>> taskToAllDependencies) {
            if(processedNodes.contains(taskId)) {
                return;
            }
            for(String keyId : adjacencyMatrix.get(taskId)) {
                if(!processedNodes.contains(keyId)) {
                    processNode(keyId, processedNodes, adjacencyMatrix, taskToAllDependencies);
                    processedNodes.add(keyId);
                }
                Set<String> allDeps = taskToAllDependencies.getOrDefault(taskId, new HashSet<>());
                allDeps.addAll(taskToAllDependencies.getOrDefault(keyId, new HashSet<>()));
                allDeps.add(keyId);
                taskToAllDependencies.put(taskId, allDeps);
            }
            processedNodes.add(taskId);
        }
    }

}
