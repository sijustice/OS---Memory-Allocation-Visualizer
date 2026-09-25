package oop;


import java.util.*;

public class FirstFit implements AllocationStrat {

	@Override
	public int findBlock(List<Block> memory, int size) {
		for(int i = 0; i < memory.size(); i++) {
			Block currentBlock = memory.get(i);
			System.out.println("Checking index: " +i+ "" + currentBlock);
			if(currentBlock.isFree() && currentBlock.getSize() >= size) {
				return i;
			}
		}
		System.out.println("No block is found for size " +size);
		return -1;
	}
	
	public void allocate(Scanner sc, List<Block> memory) {
        int requestSize = Main.getPositiveInt(sc, "\nEnter size of process to allocate: ");
        
        int index = findBlock(memory, requestSize);
        
        if (index == -1) {
        	
            System.out.println("No block found for size " + requestSize);

        } else {
            System.out.println("Found a fit at index " + index +": " + memory.get(index));
        }
    }

}
