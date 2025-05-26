import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private final List<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("Number of primitive data types in Java are?", new String[]{"6(int,long,float,double,char,boolean)", "7(int,long,float,double,char,boolean,byte)", "8(int,long,float,double,char,boolean,byte,short)", "9(int,long,float,double,char,boolean,byte,short,times)"}, 2, 1, false));
        questions.add(new Question("Which component of Java is responsible for running the compiled Java bytecode?", new String[]{"JDK", "JVM", "JRE", "JIT"}, 1, 2, false));
        questions.add(new Question("What is the purpose of the PATH environment variable in Java?", new String[]{"To locate Java libraries", "To store Java bytecode", "To Locate the exe files (.exe)", "To optimize Java code"}, 2, 2, false));
        questions.add(new Question("Which feature of Java makes it possible to run a Java program on different platforms?", new String[]{"Object-Oriented", "Platform-Independent", "Syntax", "Memory Management"}, 1, 2, false));
        questions.add(new Question("What does 'BufferedReader' in Java provide that 'Scanner' does not?", new String[]{"Faster input reading", "Input from file only", "Different data types", "Graphical interface"}, 0, 3, false));
        questions.add(new Question("Which exception is thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted?", new String[]{"IOException", "SQLException", "ArithmeticException", "InterruptedException"}, 3, 5, false));
        questions.add(new Question("What is the difference between 'while' and 'do-while' loops in Java?", new String[]{"Syntax only", "Execution speed", "The 'do-while' loop executes at least once", "No difference"}, 2, 4, false));
        questions.add(new Question("Which keyword is used to exit a loop prematurely in Java?", new String[]{"break", "continue", "exit", "stop"}, 0, 4, false));
        questions.add(new Question("Which one of the following is true for Java", new String[]{"Java is object-oriented and interpreted", "Java is efficient and faster than C", "Java is the choice of everyone.", "Java is not robust."}, 0, 5, false));
        questions.add(new Question("What is the range of the short data type in Java?", new String[]{"-32768 to 32767", ".-2147483648 to 2147483647", "-128 to 127", "0 to 65535"}, 2, 5, false));
        questions.add(new Question("In Java, how should class names be written?", new String[]{"camelCase", "snake_case", "PascalCase", "kebab-case"}, 2, 4, false));
        questions.add(new Question("Which java version the newest LTS(long term support)?", new String[]{"13", "15", "17", "21"}, 3, 3, false));
        questions.add(new Question("Which class is commonly used for simple keyboard input in Java?", new String[]{"Scanner", "InputStream", "BufferedReader", "FileReader"}, 0, 2, false));
        questions.add(new Question("Which keyword is used to prevent a class from being subclassed in Java?", new String[]{"static", "abstract", "final", "sealed"}, 2, 1, false));
        questions.add(new Question("Which of the following is not a Java access modifier?", new String[]{"public", "private", "protected", "package"}, 3, 1, false));
        questions.add(new Question("Which of the following is not an OOPS concept in Java?", new String[]{"Polymorphism", "Inheritance", "Compilation", "Encapsulation"}, 2, 2, false));
        questions.add(new Question("Which of the below is not a Java Profiler?", new String[]{"JProfiler", "Eclipse Profiler", "JVM", "JConsole"}, 2, 4, false));
        questions.add(new Question("Which of these packages contains the exception Stack Overflow in Java?", new String[]{"java.io", "java.system", "java.lang", " java.util"}, 2, 5, false));
        questions.add(new Question("What is Truncation in Java?", new String[]{"Floating-point value assigned to a Floating type", "Floating-point value assigned to an integer type", "Integer value assigned to floating type", "Integer value assigned to integer type"}, 1, 3, false));
        questions.add(new Question("Which one of the following is not a Java feature?", new String[]{"Object-oriented", "Dynamic and Extensible", "Portable", "Use of pointers"}, 3, 1, false));
        questions.add(new Question("Which component is used to compile, debug and execute the java programs?", new String[]{"JDK", "JIT", "JRE", "JVM"}, 0, 2, false));
        questions.add(new Question("Who invented Java Programming?", new String[]{"James Gosling", "Dennis Ritchie", "Guido van Rossum", "Bjarne Stroustrup"}, 0, 5, false));
        questions.add(new Question("What is the extension of java code files?", new String[]{".js", ".txt", ".class", ".java"}, 3, 1, false));
        questions.add(new Question("Which environment variable is used to set the java path?", new String[]{"MAVEN_Path", "JavaPATH", "JAVA_HOME", "JAVA"}, 2, 2, false));
        questions.add(new Question("Which of the following keywords is used to define an abstract class?", new String[]{"final", "static", "abstract", "virtual"}, 2, 1, false));
        questions.add(new Question("What is the purpose of the super keyword?", new String[]{"To access private members of a superclass", "To define an abstract method", "To reference the current class instance", "To call the parent class constructor"}, 3, 3, false));
        questions.add(new Question("Which of the following is true about the Object class in Java?", new String[]{"Object class cannot be subclassed", "All classes inherit from Object", "Object class provides no methods", "Object class has no constructors"}, 1, 1, false));
        questions.add(new Question("In Java, what is the visibility of a protected attribute?", new String[]{"Accessible only within its own package", "Accessible only within subclasses", "Accessible within its own package and subclasses", "Accessible from anywhere"}, 2, 4, false));
        questions.add(new Question("What is the visibility of private attributes in Java?", new String[]{"Accessible from anywhere", "Accessible only within the same class", "Accessible from subclasses", "Accessible within its own package"}, 1, 2, false));
        questions.add(new Question("What is the purpose of the instanceof operator in Java?", new String[]{"To check the type of an object", "To create an instance of a class", "To cast an object to a different type", "To compare two objects"}, 0, 2, false));
    }

    public List<Question> getQuestions() {
        return questions;
    }
}