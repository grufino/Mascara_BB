import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class MyTestes {
	
	//Grupo de testes unitários do fluxo FLAG Spaceship
	@Test 
    public void flagTest(){
    	assertEquals(Spaceship.checkFlag("gabriel is M"), "assignment");
    	assertEquals(Spaceship.checkFlag("gabriel marcos Dinheiros is 5 Credits"), "credits");
    	assertEquals(Spaceship.checkFlag("how many Credits is gabriel Dinheiros ?"), "howmany");
    	assertEquals(Spaceship.checkFlag("how much is gabriel Dinheiros ?"), "howmuch");
    	assertEquals(Spaceship.checkFlag("Exemplo Incorreto"), "I have no idea what you are talking about");
    }
	
	//Grupo de testes unitários de buscar de valor ROMANO Converter
	@Test 
    public void getRomanTest(){
		Converter teste = new Converter();
    	assertEquals(teste.getValueByRoman("I"), 1);
    	assertEquals(teste.getValueByRoman("V"), 5);
    	assertEquals(teste.getValueByRoman("X"), 10);
    	assertEquals(teste.getValueByRoman("L"), 50);
    	assertEquals(teste.getValueByRoman("C"), 100);
    	assertEquals(teste.getValueByRoman("D"), 500);
    	assertEquals(teste.getValueByRoman("M"), 1000);
    	    	
    }
	
	//Testes do fluxo como um todo criando arquivo simulado on the Fly
	@Test 
    public void textTest(){
		
		String path = "C:\\Users\\F8068108\\Desktop\\UNICAMP\\teste.txt";
    	StringBuilder input = new StringBuilder();
    	input.append("glob is I\n");
    	input.append("prok is V\n");
    	input.append("pish is X\n");
    	input.append("tegj is L\n");
    	input.append("glob glob Silver is 34 Credits\n");
    	input.append("glob prok Gold is 57800 Credits\n");
    	input.append("pish pish Iron is 3910 Credits\n");
    	input.append("how much is pish tegj glob glob ?\n");
    	input.append("how many Credits is glob prok Silver ?\n");
    	input.append("how many Credits is glob prok Gold ?\n");
    	input.append("how many Credits is glob prok Iron ?");
    			try {
			Files.write(Paths.get(path), input.toString().getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String[] args = {path};
    	args[0]=path;
    	Spaceship.main(args);
    	Path p1 = Paths.get(path);
    	try {
			Files.delete(p1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
	
	
}