

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Converter {
    private static String Error = "I have no idea what you are talking about";
    private static String rgxAssignment = "^([a-z]+) is ([I|V|X|L|C|D|M])$";
    private static String rgxCredits = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
    private static String rgxHowMany= "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
    private static String rgxHowMuch = "^how much is ((?:\\w+[^0-9] )+)\\?$";
    
    
    protected HashMap<String, String> transList = new HashMap<String, String>();
    protected static HashMap<String, Double> curryList = new HashMap<String, Double>();
    
    List<Integer> doSubstract(List<Integer> numbers)
	{
		int currentElement;
		
		for(int i = 0 ; i < numbers.size() -1; i++)
		{
			currentElement = numbers.get(i);
			if( currentElement < numbers.get(i+1))
			{
				numbers.set(i, -currentElement);
			}
		}
		return numbers;
	}
    
    public int getValueByRoman(String roman){
        switch(roman){
            case "I":
                return Roman.I.getValue();
            case "V":
                return Roman.V.getValue();
            case "X":
                return Roman.X.getValue();
            case "L":
                return Roman.L.getValue();
            case "C":
                return Roman.C.getValue();
            case "D":
                return Roman.D.getValue();
            case "M":
                return Roman.M.getValue();
            default:
                return 0;
        }
    }
    
    public String getError(){
    	return Error;
    }
    
    public String getRgxAssignment(){
    	return rgxAssignment;
    }
    
    public String getRgxCredits(){
    	return rgxCredits;
    }
    
    public String getRgxHowMany(){
    	return rgxHowMany;
    }
    
    public String getRgxHowMuch(){
    	return rgxHowMuch;
    }
    
    public void setCurryList(String curr, double value){
    	curryList.put(curr, value);
    }
    
    public HashMap<String, Double> getCurryList(){
    	return curryList;
    }
    
    public HashMap<String, String> getTransList(){
    	return transList;
    }
    
    public void setCurryList(String name, Double roman){
    	curryList.put(name, roman);
    }
    
    public int getTransValue(String[] trans){
        int value = 0;
        List<String> romans = new ArrayList<String>();
        for(int i=0;i<trans.length;i++){
        	if(!getTransList().keySet().contains(trans[i])){
        		System.out.println("Invalid input detected!");
        		return 0;
        	}
             romans.add(getTransList().get(trans[i]));
        }
        List<Integer> src = new ArrayList<Integer>();
        for(String s : romans){
            src.add(getValueByRoman(s));
        }
        
        List<Integer> newSrc = doSubstract(src);
        for(int i : newSrc){
            value+=i;
        }
        
        return value;
    }
    
}
