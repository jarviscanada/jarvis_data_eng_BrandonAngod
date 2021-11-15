public class ValidPalimdrone {
    public static String rev(String s,int index){
        return  rev(s,index-1)+ s.charAt(index);
    }

    public static boolean main(String args[]){
        String s = args[0];
        String reverseString = rev(s,s.length());

        for(int i =0; i<=s.length();i++){
            if((s.charAt(i)) != reverseString.charAt(i) ){
                return false;
            }
        }
        return true;
    }
}
