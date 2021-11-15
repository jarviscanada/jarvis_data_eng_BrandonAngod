package RotateString;

class Solution {
    public String shift(String s){
        char firstLetter = s.charAt(0);
        return s.substring(1,s.length()) + firstLetter;
    }
    public Boolean test(String s, String goal, int i){
        s=shift(s);
        if(s.equals(goal)){
            return true;
        }
        if(i > s.length()){
            return false;
        }
        return test(s,goal,i+1);
    }
    public boolean rotateString(String s, String goal) {
        return test(s,goal,0);
    }
}