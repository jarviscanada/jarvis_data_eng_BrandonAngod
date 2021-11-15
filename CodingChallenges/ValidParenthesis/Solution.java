package ValidParenthesis;

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        //Calculate each result
        for(int i=0;i<s.length();i++){

            char currentParenthesis = s.charAt(i);
            if(currentParenthesis == '(' || currentParenthesis == '[' || currentParenthesis == '{'){
                stack.push(currentParenthesis);
            }

            else{
                if(stack.isEmpty()){
                    return false;
                }
                char lastOpenParenthesis = stack.pop();
                switch(lastOpenParenthesis){
                    case '(':
                        if(currentParenthesis != ')'){
                            return false;
                        }
                        break;
                    case '[':
                        if(currentParenthesis != ']'){
                            return false;
                        }
                        break;
                    case '{':
                        if(currentParenthesis != '}'){
                            return false;
                        }
                        break;
                }
            }
        }//for
        return stack.isEmpty();
    }
}
