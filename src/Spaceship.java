import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spaceship {
	static Converter trader = new Converter();

	public static void main(String[] args) {
        BufferedReader br = null;       
        String currency = "";
        
        try {
            br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            while(line!=null){
                String flag = checkFlag(line); 
                
                if(flag.equals("assignment")){
                    Pattern ptn = Pattern.compile(trader.getRgxAssignment());
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    String name = mcher.group(1).trim();
                    String roman = mcher.group(2).trim();
                    if(!trader.getTransList().containsKey(name)){
                    	trader.getTransList().put(name, roman);
                    }               
                }else if(flag.equals("credits")){
                    Pattern ptn = Pattern.compile(trader.getRgxCredits());
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    
                    currency = mcher.group(4).trim();
                    
                    String[] trans = mcher.group(1).split(" ");
                    int transValue = trader.getTransValue(trans);
                    String curr = mcher.group(2);
                    int credits = Integer.parseInt(mcher.group(3).trim());
                    double value = credits/transValue;
                    trader.getCurryList().put(curr, value);
                }else if(flag.equals("howmany")){
                    Pattern ptn = Pattern.compile(trader.getRgxHowMany());
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    if(!currency.equals(mcher.group(1))){
                        System.out.println(trader.getError());
                    }
                    
                    String[] trans = mcher.group(2).split(" ");
                    int transValue = trader.getTransValue(trans);
                    
                    String curr = mcher.group(3).trim();
                    double value = 0;
                    for(String k:trader.getCurryList().keySet()){
                        if(k.equals(curr)){
                           value = trader.getCurryList().get(k);
                        }
                    }
                    
                    double total = transValue*value;
                    if(total != 0){
                        System.out.println(mcher.group(2)+"is "+(long)total+" "+currency);
                    }
                    
                }else if(flag.equals("howmuch")){
                    Pattern ptn = Pattern.compile(trader.getRgxHowMuch());
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    
                    String[] trans = mcher.group(1).split(" ");
                    int transValue = trader.getTransValue(trans);
                    if(transValue!=0){
                        System.out.println(mcher.group(1)+"is "+transValue);
                    }
                }else{
                    System.out.println(trader.getError());
                }
                
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected static String checkFlag(String s){
        String flag = trader.getError();
        String[] rgxArray = new String[]{trader.getRgxAssignment(),trader.getRgxCredits(),trader.getRgxHowMany(),trader.getRgxHowMuch()};
        for(int i = 0;i<rgxArray.length;i++){
            Pattern ptn = Pattern.compile(rgxArray[i]);
            Matcher mcher = ptn.matcher(s);
            if(mcher.matches()){
                switch(i){
                    case 0:
                        flag = "assignment";
                    break;
                    case 1:
                        flag = "credits";
                    break;
                    case 2:
                        flag = "howmany";
                    break;
                    case 3:
                        flag = "howmuch";
                    break;
                    default:
                        break;
                }
            }
        }     
        return flag;
    }
    
    
    
    
}
