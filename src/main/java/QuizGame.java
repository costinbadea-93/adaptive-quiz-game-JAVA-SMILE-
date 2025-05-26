import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


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
    AtomicInteger initialXBound = new AtomicInteger(100);

    private static final String scoringTemplate = "Score: %s, KNN-Algorithm prediction level: %s, question difficulty level: %s";

    public QuizGame() {
        questionBank = new QuestionBank();
        adaptiveDifficulty = new AdaptiveDifficulty(questionBank.getQuestions());
        questions = questionBank.getQuestions();

        predictedDifficulty = adaptiveDifficulty.predictDifficulty(score);
//        question = questionBank
//                .getQuestions()
//                .stream()
//                //to review this - it can take a top hard question difficulty :))
//                .filter(q -> q.getDifficulty() >= predictedDifficulty && !q.isAlreadyAnswered())
//                .findFirst().orElseThrow(() -> new RuntimeException("Question not found"));
//        System.out.println(System.getProperty("user.dir"));
        List<Question> newQuestions0 = questionBank
                .getQuestions()
                .stream()
                //to review this - it can take a top hard question difficulty :))
                .filter(q -> q.getDifficulty() == predictedDifficulty && !q.isAlreadyAnswered())
                .toList();

        Random random0 = new Random();
        int number0 = random0.nextInt((newQuestions0.size())); // see explanation below
        question = newQuestions0.get(number0);


        setTitle("Adaptive Quiz Game");
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(7, 1));
        setLayout(null);

        ImageIcon mainIcon = new ImageIcon("planet.jpg");
        Image icon = mainIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(icon);
        JLabel picture = new JLabel(resizedIcon);
        picture.setBounds(700, 100, 100, 100);
        add(picture);


        ImageIcon mainHumanIcon = new ImageIcon("human.png");
        Image humanIcon = mainHumanIcon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedHumanIcon = new ImageIcon(humanIcon);
        humanPicture = new JLabel(resizedHumanIcon);
        humanPicture.setBounds(initialXBound.get(), 100, 100, 100);
        add(humanPicture);


        scoringLabel = new JLabel(String.format(scoringTemplate,score, predictedDifficulty, question.getDifficulty()));
        scoringLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoringLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoringLabel.setBounds(100, 0, getWidth(), 50);
        add(scoringLabel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 30));

        questionLabel.setBounds(100, 250, getWidth(), 50);
        add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        int initialOptionY = 300;
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.BOLD, 20));
            options[i].setBounds(100, initialOptionY, getWidth(), 50);
            initialOptionY += 50;
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {

            checkAnswer();
            currentQuestionIndex++;
//            if (currentQuestionIndex < questions.size()) {
            if (currentQuestionIndex < 10) {
                predictedDifficulty = adaptiveDifficulty.predictDifficulty(score);
                List<Question> newQuestions = questionBank
                        .getQuestions()
                        .stream()
                        //to review this - it can take a top hard question difficulty :))
                        .filter(q -> q.getDifficulty() == predictedDifficulty && !q.isAlreadyAnswered())
                        .toList();

                Random random = new Random();
                int number = random.nextInt((newQuestions.size())); // see explanation below
                question = newQuestions.get(number);

//                question = questionBank
//                        .getQuestions()
//                        .stream()
//                        //to review this - it can take a top hard question difficulty :))
//                        .filter(q -> q.getDifficulty() >= predictedDifficulty && !q.isAlreadyAnswered())
//                        .findFirst().orElseThrow(() -> new RuntimeException("Question not found"));
                loadQuestion();
                scoringLabel.setText(String.format(scoringTemplate,score, predictedDifficulty, question.getDifficulty()));
            } else {
                scoringLabel.setText(String.format(scoringTemplate,score, predictedDifficulty, question.getDifficulty()));
                showResults();
            }
            group.clearSelection();
        });
        nextButton.setBounds(100, 600, getWidth(), 50);
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
                initialXBound.set(initialXBound.get() + 55);
                humanPicture.setBounds(initialXBound.get(), 100, 100, 100);
                score++;
                question.setAlreadyAnswered(true);
                return;
            }
        }
        question.setAlreadyAnswered(true);
    }

    private void showResults() {
        if(score == 10 ){
            JOptionPane.showMessageDialog(this, "Quiz Over! Congratulation you have reach AI planet! Your score: " + score + " !");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(this, "Quiz Over! Try again to reach AI planet! Your score: " + score + " !");
            System.exit(0);
        }
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