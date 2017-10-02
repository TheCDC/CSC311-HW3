import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
public class RDPExample1 {
    private Reader in ;
    private char next;
    private int level = 0;
    public RDPExample1() {
        in = new InputStreamReader(System.in);
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
        T();
        while (next == '+' || next == '-') {
            nextChar();
            T();
        }
        leave('E');
    }
    public void S() {
        enter('S');
        F();
        if (next == '^') {
            nextChar();
            S();
        }
        leave('S');
    }
    public void T() {
        enter('T');
        S();
        while (next == '*' || next == '/') {
            nextChar();
            S();
        }
        leave('T');
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
                System.out.println("Error!");
                System.exit(1);
            }
        } else {
            System.out.println("Error!");
            System.exit(1);
        }
        leave('F');
    }
    public String repeat(String s, int n) {
        if (n <= 0) {
            return " ";
        }
        return new String(new char[n]).replace("\0", s);
    }
    private void enter(char name) {
        level++;
        System.out.print(repeat(" ", level - 1) + " " + name + ": Enter, \t");
        System.out.println("Next == " + next);
    }
    private void leave(char name) {
        level--;
        System.out.print(repeat(" ", level - 1) + " " + name + ": Leave, \t");
        System.out.println("Next == " + next);
    }
    public static void main(String[] args) {
        RDPExample1 ex = new RDPExample1();
        ex.P();
    }
}