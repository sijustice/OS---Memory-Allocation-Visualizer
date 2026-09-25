package oop;
import java.util.*;

//git push origin master (ayaw gumana commit ko eh, paste ko lang here)

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
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
				
				//input number of memory blocks
				System.out.print("Enter number of memory blocks: ");
				int numBlocks = 0;
				if (sc.hasNextInt()) {
					numBlocks = sc.nextInt();
					if(numBlocks > 0) {
					} else {
						System.out.println("Please enter a number greater than 0!");
					}
				} else {
					System.out.println("Please enter a valid number!");
				}
				
				//input memory size
				List<Block> memory = new ArrayList<>();
				int start = 0;
				for (int i = 0; i < numBlocks; i++) {
					System.out.print("Memory Size " + (i + 1) + ": " );
					int size = sc.nextInt();
					memory.add(new Block(start, size, "free", null));
					start += size;
				}
				
				break;
			case 'B': case 'b':
				System.out.println("Select your choice \n"
						+ "(A) First Fit\n"
						+ "(B) Best Fit\n"
						+ "(X) Back\n"
						);
				
				switch(ch) {
				case 'A' : case 'a':
					
					break;
				case 'B' : case 'b':
					// Enter the code for Best Fit here bro
					break;
				case 'X' : case 'x':
					break;
				default:
					System.out.println("\nINVALID CHOICE!\n");
					break;
				}
				break;
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
}
