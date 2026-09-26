package oop;
import java.util.*;

//git push origin master (ayaw gumana commit ko eh, paste ko lang here)

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		List<Block> memory = new ArrayList<>();
		char ch = '\0';
		
		while(ch != 'X' && ch != 'x') {
			System.out.println("Select your choice \n"
					+ "(A) Initialize the memory block count and size\n"
					+ "(B) Choose which allocation algorithm to use\n"
					+ "(C) Print the output\n"
					+ "(X) Exit\n"
					); //PLACE HOLDER MUNA TO SEE IF IT WORKS terminal base kwkwkw
			ch = sc.next().charAt(0);
			switch(ch) {
			case 'A': case 'a':
				
				memory = createMemory(sc, memory); break;
				
			case 'B': case 'b':
				
				allocateMemory(sc, memory); break;
				
			case 'C': case 'c':
				break;
			case 'X': case 'x':
				System.out.println("Exiting program...");
			    System.exit(0);
			default:
				System.out.println("\nINVALID CHOICE!\n");
				break;
			}
			
		}			
	}
	
	//Make sures na lagi greater than 1 yung input
	public static int getPositiveInt(Scanner sc, String message) {
		while (true) {
	        System.out.print(message);

	        if (sc.hasNextInt()) {
	            int value = sc.nextInt();

	            if (value > 0) {
	                return value;
	            }

	            System.out.println("Please enter a number greater than 0!");

	        } else {
	            System.out.println("Please enter a valid integer!");
	            sc.next();
	        }
	    }
	}
	//Dito gagawa ng memory blocks
	public static List<Block> createMemory(Scanner sc, List<Block> memory){
		
		int numBlocks = getPositiveInt(sc, "\nEnter number of memory blocks: \n");

		int start = 0;
		
		for (int i = 0; i < numBlocks; i++) {
			System.out.print("Memory Size " + (i + 1) + ": " );
			int size = sc.nextInt();
			
			memory.add(new Block(start, size, "free", null));
			start += size;
		}
		return memory;
	}
	
	public static void allocateMemory(Scanner sc, List<Block> memory) {
		System.out.println("\nChoose Allocation Algorithm: ");
	    System.out.println("1. First Fit");
	    System.out.println("2. Best Fit");
	    
		int ch = getPositiveInt(sc, "\nEnter choice: ");

		if(ch == 1) {
			FirstFit firstFit = new FirstFit();
		    firstFit.allocate(sc, memory);
		} else if (ch == 2) {
			//MARL DITO MO LAGAY CODE MO HAHHAHAHA
		} else {
			System.out.println("Invalid choice!");
		}
	}
}
