import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class QuizGame extends JFrame {
        private QuestionBank questionBank;
        private AdaptiveDifficulty adaptiveDifficulty;
        private List<Question> questions;
        private int currentQuestionIndex = 0;
        private int score = 0;

        private JLabel questionLabel;
        private JRadioButton[] options;
        private ButtonGroup group;
        private JButton nextButton;

        public QuizGame() {
            questionBank = new QuestionBank();
            adaptiveDifficulty = new AdaptiveDifficulty(questionBank.getQuestions());
            questions = questionBank.getQuestions();

            setTitle("Adaptive Quiz Game");
            setSize(800, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(6, 1));

            questionLabel = new JLabel();
            add(questionLabel);

            options = new JRadioButton[4];
            group = new ButtonGroup();
            for (int i = 0; i < 4; i++) {
                options[i] = new JRadioButton();
                group.add(options[i]);
                add(options[i]);
            }

            nextButton = new JButton("Next");
            nextButton.addActionListener(e -> {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()/2) {
                    loadQuestion();
                } else {
                    showResults();
                }
            });
            add(nextButton);

            loadQuestion();
            setVisible(true);
        }

        private void loadQuestion() {
            int predictedDifficulty = adaptiveDifficulty.predictDifficulty(score);
            Question question = questionBank
                    .getQuestions()
                    .stream()
                    //to review this - it can take a top hard question difficulty :))
                    .filter(q -> q.getDifficulty() >= predictedDifficulty && !q.isAlreadyAnswered())
                    .findFirst().orElseThrow(() -> new RuntimeException("Question not found"));

            System.out.println("Question difficulty: " + question.getDifficulty());
            questionLabel.setText(question.getQuestionText());
            String[] opts = question.getOptions();
            for (int i = 0; i < opts.length; i++) {
                options[i].setText(opts[i]);
                options[i].setSelected(false);
            }
        }

        private void checkAnswer() {
            Question q = questions.get(currentQuestionIndex);
            for (int i = 0; i < options.length; i++) {
                if (options[i].isSelected() && i == q.getCorrectAnswerIndex()) {
                    score++;
                    q.setAlreadyAnswered(true);
                    return;
                }
            }
            q.setAlreadyAnswered(true);
        }

        private void showResults() {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your score: " + score);
            System.exit(0);
        }

        public static void main(String[] args) {
            new QuizGame();
        }

/** Command line runner (without a graphic interface **/


//    public static void main(String[] args) {
//        QuestionBank questionBank = new QuestionBank();
//        int score = 0;
//        String[] responseLabels = {"a", "b", "c", "d"};
//
//        AdaptiveDifficulty adaptiveDifficulty = new AdaptiveDifficulty(questionBank.getQuestions());
//
//        for (int i = 0; i <= questionBank.getQuestions().size()/2 ; i++) {
//            int predictedDifficulty = adaptiveDifficulty.predictDifficulty(score);
//            Question question = questionBank
//                    .getQuestions()
//                    .stream()
//                    //to review this - it can take a top hard question difficulty :))
//                    .filter(q -> q.getDifficulty() >= predictedDifficulty && !q.isAlreadyAnswered())
//                    .findFirst().orElseThrow(() -> new RuntimeException("Question not found"));
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println(question.getQuestionText());
//            for (int j = 0; j < question.getOptions().length; j++) {
//                System.out.println(responseLabels[j] + ") " + question.getOptions()[j]);
//            }
//            System.out.println("Question difficulty: " + question.getDifficulty());
//            System.out.println("Enter your choice: please answer with a, b, c or d");
//            String response = scanner.nextLine();
//            int index;
//            if ((index = Arrays.binarySearch(responseLabels, response)) >= 0) {
//                if (question.getCorrectAnswerIndex() == index) {
//                    System.out.println("Correct!");
//                    score++;
//                } else {
//                    System.out.println("Wrong!");
//                }
//                question.setAlreadyAnswered(true);
//                System.out.println("\nScore: " + score);
//            }
//        }
//
//        System.out.println("\n You ended the quiz with a score of: " + score);
//    }
}