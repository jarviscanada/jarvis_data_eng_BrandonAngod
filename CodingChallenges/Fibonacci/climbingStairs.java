package Fibonacci;
class ClimbSolution {
    public int climbStairs(int n) {
        int y = 2;
        int x = 1;
        if(n<=2){
            return n;
        }
        for(int i = 3; i < n; i++){
            y = y + x;
            x = y-x;
        }
        y=y+x;
        return y;
    }
}