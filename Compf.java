//Стековый компилятор формул.
public class Compf extends Stack {
    boolean b;
    //Типы символов (скобки, знаки операций, иное).
    protected final static int SYM_LEFT = 0,
            SYM_RIGHT = 1,
            SYM_OPER = 2,
            SYM_OTHER = 3;

    private int symType(char c) {
        switch (c) {
            case '(':
                return SYM_LEFT;
            case ')':
                return SYM_RIGHT;
            case '+':
            case '-':
            case '*':
            case '/':
                return SYM_OPER;
            default:
                return symOther(c);
        }
    }

    private void processSymbol(char c) {
        switch (symType(c)) {
            case SYM_LEFT:
                push(c);
                break;
            case SYM_RIGHT:
                processSuspendedSymbols(c);
                pop();
                break;
            case SYM_OPER:
                processSuspendedSymbols(c);
                push(c);
                break;
            case SYM_OTHER:
                nextOther(c);
                break;
        }
    }

    private void processSuspendedSymbols(char c) {
        while (precedes(top(), c))
            nextOper(pop());
    }

    private int priority(char c) {
        return c == '+' || c == '-' ? 1 : 2;
    }

    private boolean precedes(char a, char b) {
        if (symType(a) == SYM_LEFT) return false;
        if (symType(b) == SYM_RIGHT) return true;

        return priority(a) >= priority(b);
    }

    protected int symOther(char c) {
        if (!(Character.toString(c).matches("([0-9]|[a-z])+"))) {
            System.out.println("Недопустимый символ: " + c);
            System.exit(0);
        }

        return SYM_OTHER;
    }

    protected void nextOper(char c) {
        if (!(Character.toString(c).matches("([0-9]|[a-z])+"))) {
            System.out.print(c + " ");
        }
    }

    protected void nextOther(char c) {
        nextOper(c);
    }

    public void compile(char[] str) {
        String str_1 = "";
        processSymbol('(');
        for (int i = 0; i < str.length; i++) {
            if (Character.toString(str[i]).matches("([0-9]|[a-z])+")) {
                processSymbol(str[i]);
                str_1 += str[i];
                if (i == str.length-1){
                    System.out.print(str_1+" ");
                }
            }
            else {
                System.out.print(str_1+" ");
                str_1 = "";
                processSymbol(str[i]);
            }
        }
        processSymbol(')');
        System.out.print("\n");
    }
}