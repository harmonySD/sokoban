package sokoban.project;
import java.awt.EventQueue;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc= new Scanner(System.in);
	System.out.println("Choisissez votre nom :");
	String nom=sc.next();
	EventQueue.invokeLater( () -> {
		new GameVue(nom);
	});
    }
}
