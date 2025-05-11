public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;
    private int difficulty; // 1 (easy) to 5 (hard)
    private boolean alreadyAnswered;

    public Question(String questionText, String[] options, int correctAnswerIndex, int difficulty, boolean alreadyAnswered) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.difficulty = difficulty;
        this.alreadyAnswered = alreadyAnswered;
    }

    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public int getDifficulty() { return difficulty; }

    public void setAlreadyAnswered(boolean alreadyAnswered) {
        this.alreadyAnswered = alreadyAnswered;
    }

    public boolean isAlreadyAnswered() { return alreadyAnswered; }
}