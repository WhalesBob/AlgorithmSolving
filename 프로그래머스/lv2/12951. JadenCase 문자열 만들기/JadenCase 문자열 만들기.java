import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        StringBuilder builder = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s," ",true);

        while(st.hasMoreTokens()){

            String get = st.nextToken().toLowerCase();

            if(get.equals(" ")){
                builder.append(get);
                continue;
            }

            String firstLetter = get.substring(0,1);
            
            try{
                Integer.parseInt(firstLetter);
                builder.append(get);
            }catch(NumberFormatException e){
                String upperCase = firstLetter.toUpperCase();
                builder.append(upperCase);
                builder.append(get.substring(1));
            }
        }
        return builder.toString();
    }
}