import smile.classification.KNN;

import java.util.List;

public class AdaptiveDifficulty {
    private KNN<double[]> knnModel;
    private final List<Question> questions;

    public AdaptiveDifficulty(List<Question> questions) {
        this.questions = questions;
        trainModel();
    }

    private void trainModel() {
        double[][] features = new double[questions.size()][2]; // [score, difficulty]
        int[] labels = new int[questions.size()]; // Difficulty level

        for (int i = 0; i < questions.size(); i++) {
            features[i][0] = i; // Simulated user score
            features[i][1] = questions.get(i).getDifficulty();
            labels[i] = questions.get(i).getDifficulty();
        }

        /**
         * the k parameter is the step to increment difficulty
         * based on correct answer
         * e.g fit(int[][] x, int[] y, int k) :
         *   -(int[][]) → Feature matrix
         *    Each row represents a data point (example).
         *    Each column represents a feature (e.g., score, previous difficulty)
         *    int[][] x = {
         *     {0, 1},  // User with score 0, previous difficulty 1
         *     {2, 2},  // User with score 2, previous difficulty 2
         *     {5, 3},  // User with score 5, previous difficulty 3
         *     {7, 4},  // User with score 7, previous difficulty 4
         *     {10, 5}  // User with score 10, previous difficulty 5
         * };
         *
         *
         *  int[] y = {1, 2, 3, 4, 5}; // Difficulty levels for each user
         *
         *  int k = 3; // Uses 3 nearest neighbors for classification
         *
         *  It takes a dataset of feature vectors and their corresponding labels, then builds a model that can classify new data points based on their similarity to existing ones.
         */
        knnModel = KNN.fit(features, labels, 3);
    }

    public int predictDifficulty(int score) {
        /**
         * the first elem of the input array is score
         * and the second one is question difficulty;
         * predict based on (score, question difficulty
         */
        double[] input = {score, 1};
        return knnModel.predict(input);
    }
}