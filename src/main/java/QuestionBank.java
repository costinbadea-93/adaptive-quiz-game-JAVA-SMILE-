import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private final List<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("Number of primitive data types in Java are?", new String[]{"6", "7", "8", "9"}, 2, 1, false));
        questions.add(new Question("Which component of Java is responsible for running the compiled Java bytecode?", new String[]{"JDK", "JVM", "JRE", "JIT"}, 1, 2, false));
        questions.add(new Question("What is the purpose of the PATH environment variable in Java?", new String[]{"To locate Java libraries", "To store Java bytecode", "To Locate the exe files (.exe)", "To optimize Java code"}, 2, 2, false));
        questions.add(new Question("Which feature of Java makes it possible to run a Java program on different platforms?", new String[]{"Object-Oriented", "Platform-Independent", "Syntax", "Memory Management"}, 1, 2, false));
        questions.add(new Question("What does 'BufferedReader' in Java provide that 'Scanner' does not?", new String[]{"Faster input reading", "Input from file only", "Different data types", "Graphical interface"}, 1, 3, false));
        questions.add(new Question("Which exception is thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted?", new String[]{"IOException", "SQLException", "ArithmeticException", "InterruptedException"}, 3, 5, false));
        questions.add(new Question("What is the difference between 'while' and 'do-while' loops in Java?", new String[]{"Syntax only", "Execution speed", "The 'do-while' loop executes at least once", "No difference"}, 2, 4, false));
        questions.add(new Question("Which keyword is used to exit a loop prematurely in Java?", new String[]{"break", "continue", "exit", "stop"}, 0, 4, false));
        questions.add(new Question("Which one of the following is true for Java", new String[]{"Java is object-oriented and interpreted", "Java is efficient and faster than C", "Java is the choice of everyone.", "Java is not robust."}, 0, 5, false));
        questions.add(new Question("What is the range of the short data type in Java?", new String[]{"-32768 to 32767", ".-2147483648 to 2147483647", "-128 to 127", "0 to 65535"}, 2, 5, false));
    }

    public List<Question> getQuestions() {
        return questions;
    }
}