import javax.swing.*;
import java.awt.*;
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
    private JLabel scoringLabel;
    JLabel humanPicture;

    private int predictedDifficulty ;
    private Question question;

    private static final String scoringTemplate = "Score: %s, KNN-Algorithm prediction level: %s, question difficulty level: %s";

    public QuizGame() {
        questionBank = new QuestionBank();
        adaptiveDifficulty = new AdaptiveDifficulty(questionBank.getQuestions());
        questions = questionBank.getQuestions();

        predictedDifficulty = adaptiveDifficulty.predictDifficulty(score);
        question = questionBank
                .getQuestions()
                .stream()
                //to review this - it can take a top hard question difficulty :))
                .filter(q -> q.getDifficulty() >= predictedDifficulty && !q.isAlreadyAnswered())
                .findFirst().orElseThrow(() -> new RuntimeException("Question not found"));


        setTitle("Adaptive Quiz Game");
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

//        ImageIcon mainIcon = new ImageIcon("planet.jpg");
//        Image icon = mainIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        ImageIcon resizedIcon = new ImageIcon(icon);
//        JLabel picture = new JLabel(resizedIcon);
//        add(picture);
//
//
//        ImageIcon mainHumanIcon = new ImageIcon("human.png");
//        Image humanIcon = mainHumanIcon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
//        ImageIcon resizedHumanIcon = new ImageIcon(humanIcon);
//        humanPicture = new JLabel(resizedHumanIcon);
//        add(humanPicture);


        scoringLabel = new JLabel(String.format(scoringTemplate,score, predictedDifficulty, question.getDifficulty()));
        scoringLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoringLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoringLabel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 30));

        add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.BOLD, 20));
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            checkAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                predictedDifficulty = adaptiveDifficulty.predictDifficulty(score);
                question = questionBank
                        .getQuestions()
                        .stream()
                        //to review this - it can take a top hard question difficulty :))
                        .filter(q -> q.getDifficulty() >= predictedDifficulty && !q.isAlreadyAnswered())
                        .findFirst().orElseThrow(() -> new RuntimeException("Question not found"));
                loadQuestion();
                scoringLabel.setText(String.format(scoringTemplate,score, predictedDifficulty, question.getDifficulty()));
            } else {
                showResults();
            }
            group.clearSelection();
        });
        add(nextButton);

        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        System.out.println("Question difficulty: " + question.getDifficulty());
        questionLabel.setText(question.getQuestionText());
        String[] opts = question.getOptions();
        String [] prefixes = {"a)", "b)", "c)", "d)"};
        for (int i = 0; i < opts.length; i++) {
            options[i].setText(prefixes[i] + " " + opts[i]);
            options[i].setSelected(false);
            options[i].setRolloverEnabled(false);
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected() && i == question.getCorrectAnswerIndex()) {
                score++;
                question.setAlreadyAnswered(true);
                return;
            }
        }
        question.setAlreadyAnswered(true);
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