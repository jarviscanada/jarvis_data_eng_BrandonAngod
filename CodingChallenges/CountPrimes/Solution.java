package CountPrimes;

import java.util.ArrayList;

class Solution {
    public int countPrimes(int n) {
        int counter=0;
        boolean isNotPrime = false;
        ArrayList<Integer> filtered = new ArrayList<Integer>();
        //Filter
        //Search for even numbers less than N and filter out
        for(int i=n-1;i>1;i--){
            if(i%2 != 0){
                filtered.add(i);
            }
            else if(i==2){
                counter = counter+1;
            }
        }

        //Calculation
        //Iterates through every single object within the list
        for(int i=0;i<filtered.size();i++){
            //Iterates from 3 - half of the total
            //amount since anything beyond that is repeat
            for(int j=3;j<filtered.get(i)/2;j++){
                if(filtered.get(i)%j == 0){
                    isNotPrime = true;
                    break;
                }
            }
            //If we never tripped the flag add to our counter
            if(!isNotPrime){
                counter++;
            }
            //Reset our flag
            isNotPrime = false;
            //Loop
        }
        return counter;
    }
}