import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
public class RDPExample1 {
    private Reader in ;
    private char next;
    public RDPExample1() { in = new InputStreamReader(System.in);
    }
    // getNextChar: fetches next char
    public char getNextChar() {
        char ch = ' ';
        try {
            ch = (char) in .read();
        } catch (IOException e) {
            System.out.println("Exception reading character");
        }
        return ch;
    }
    private void nextChar() {
        while (Character.isWhitespace(next = getNextChar()));
    }

    private void P() {
        nextChar();
        E();
        if (next != '$') {
            System.out.println("Error!");
            System.exit(1);
        } else System.out.println("Successful parse");
    }
    private void E() {
        enter('E');
        F();
        while (next == '+' || next == '-') {
            nextChar();
            F();
        }
        leave('E');
    }
    private void F() {
        enter('F');
        if (Character.isLetter(next)) {
            nextChar();
        } else if (next == '(') {
            nextChar();
            E();
            if (next == ')')
                nextChar();
            else {
                System.out
                    .println("Error!");
                System.exit(1);
            }
        } else {
            System.out.println("Error!");
            System.exit(1);
        }
        leave('F');
    }
    private void enter(char name) {
        System.out.print(name + ": Enter, \t");
        System.out.println("Next == " + next);
    }
    private void leave(char name) {
        System.out.print(name + ": Leave, \t");
        System.out.println("Next == " + next);
    }
    public static void main(String[] args) {
        RDPExample1 ex = new RDPExample1();
        ex.P();
    }
}