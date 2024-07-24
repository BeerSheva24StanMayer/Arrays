package run.util;

import java.util.function.Predicate;

public class CharacterRule{
    public boolean flag;
    public Predicate<Character> predicate;
    public String errorMessage;
    private boolean flagOut;

    public CharacterRule(boolean flag, Predicate<Character> predicate, String errorMessage) {
        this.flag = flag;
        this.predicate = predicate;
        this.errorMessage = errorMessage;
    }
    public boolean ifTrue(char character) {
        // String outMessage = null;
        if(flag) {
            flagOut = !predicate.test(character);
        }
        else if(!flag) {
            // outMessage = errorMessage;
            flagOut = predicate.test(character);
        }
        
        return flagOut;
    }
    }

