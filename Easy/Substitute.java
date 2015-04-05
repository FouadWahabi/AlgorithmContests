public class Substitute  {
    public int getValue(String key, String code) {
        String result = "";
        for(int i = 0 ; i< code.length() ; i++) {
            int index = key.indexOf(code.charAt(i));
            if(index != -1) {
                if(index == key.length() - 1) {
                	result += 0;    
                } 
                else
                result += index + 1;
            }
        }
     	return Integer.parseInt(result);   
    }
}
