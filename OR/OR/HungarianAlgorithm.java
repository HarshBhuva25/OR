import java.util.Arrays;

class HungarianAlgorithm {
    private final int[][] costMatrix;
    private final int rows, cols, dim;
    private final int[] labelByWorker, labelByJob;
    private final int[] minSlackWorkerByJob;
    private final int[] minSlackValueByJob;
    private final int[] matchJobByWorker, matchWorkerByJob;
    private final int[] parentWorkerByCommittedJob;
    private final boolean[] committedWorkers;

    public HungarianAlgorithm(int[][] costMatrix) {
        this.rows = costMatrix.length;
        this.cols = costMatrix[0].length;
        this.dim = Math.max(rows, cols);
        this.costMatrix = new int[dim][dim];

        for (int w = 0; w < dim; w++) {
            if (w < costMatrix.length) {
                if (costMatrix[w].length != cols)
                    throw new IllegalArgumentException("Irregular matrix");
                System.arraycopy(costMatrix[w], 0, this.costMatrix[w], 0, costMatrix[w].length);
            }
        }

        labelByWorker = new int[dim];
        labelByJob = new int[dim];
        minSlackWorkerByJob = new int[dim];
        minSlackValueByJob = new int[dim];
        committedWorkers = new boolean[dim];
        parentWorkerByCommittedJob = new int[dim];
        matchJobByWorker = new int[dim];
        Arrays.fill(matchJobByWorker, -1);
        matchWorkerByJob = new int[dim];
        Arrays.fill(matchWorkerByJob, -1);
    }

    public int[] execute() {
        reduce();
        matchWorkers();

        int w = fetchUnmatchedWorker();
        while (w < dim) {
            initializePhase(w);
            executePhase();
            w = fetchUnmatchedWorker();
        }

        int[] result = Arrays.copyOf(matchJobByWorker, rows);
        return result;
    }

    private void reduce() {
        for (int w = 0; w < dim; w++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < dim; j++) min = Math.min(min, costMatrix[w][j]);
            for (int j = 0; j < dim; j++) costMatrix[w][j] -= min;
        }

        for (int j = 0; j < dim; j++) {
            int min = Integer.MAX_VALUE;
            for (int w = 0; w < dim; w++) min = Math.min(min, costMatrix[w][j]);
            for (int w = 0; w < dim; w++) costMatrix[w][j] -= min;
        }
    }

    private void matchWorkers() {
        for (int w = 0; w < dim; w++) {
            for (int j = 0; j < dim; j++) {
                if (matchJobByWorker[w] == -1 &&
                        matchWorkerByJob[j] == -1 &&
                        costMatrix[w][j] == 0) {
                    match(w, j);
                }
            }
        }
    }

    private void initializePhase(int w) {
        Arrays.fill(committedWorkers, false);
        Arrays.fill(parentWorkerByCommittedJob, -1);
        committedWorkers[w] = true;

        for (int j = 0; j < dim; j++) {
            minSlackValueByJob[j] = costMatrix[w][j] - labelByWorker[w] - labelByJob[j];
            minSlackWorkerByJob[j] = w;
        }
    }

    private void executePhase() {
        while (true) {
            int minSlackValue = Integer.MAX_VALUE;
            int minSlackJob = -1;

            for (int j = 0; j < dim; j++) {
                if (parentWorkerByCommittedJob[j] == -1) {
                    if (minSlackValueByJob[j] < minSlackValue) {
                        minSlackValue = minSlackValueByJob[j];
                        minSlackJob = j;
                    }
                }
            }

            updateLabeling(minSlackValue);

            int worker = matchWorkerByJob[minSlackJob];
            if (worker == -1) {
                augmentPath(minSlackJob);
                return;
            } else {
                committedWorkers[worker] = true;
                for (int j = 0; j < dim; j++) {
                    if (parentWorkerByCommittedJob[j] == -1) {
                        int slack = costMatrix[worker][j] - labelByWorker[worker] - labelByJob[j];
                        if (minSlackValueByJob[j] > slack) {
                            minSlackValueByJob[j] = slack;
                            minSlackWorkerByJob[j] = worker;
                        }
                    }
                }
            }
        }
    }

    private void augmentPath(int job) {
        while (true) {
            int worker = minSlackWorkerByJob[job];
            int nextJob = matchJobByWorker[worker];
            match(worker, job);
            job = nextJob;
            if (job == -1) break;
        }
    }

    private void match(int w, int j) {
        matchJobByWorker[w] = j;
        matchWorkerByJob[j] = w;
    }

    private void updateLabeling(int slack) {
        for (int w = 0; w < dim; w++) {
            if (committedWorkers[w]) labelByWorker[w] += slack;
        }
        for (int j = 0; j < dim; j++) {
            if (parentWorkerByCommittedJob[j] != -1)
                labelByJob[j] -= slack;
            else
                minSlackValueByJob[j] -= slack;
        }
    }

    private int fetchUnmatchedWorker() {
        for (int w = 0; w < dim; w++) {
            if (matchJobByWorker[w] == -1) return w;
        }
        return dim;
    }
}
