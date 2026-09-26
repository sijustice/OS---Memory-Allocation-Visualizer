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
        int numProcess = Main.getPositiveInt(sc, "\nEnter the number of processe(s): ");
        
        for(int p = 1 ; p <= numProcess; p++ ) {
        	int requestSize = Main.getPositiveInt(sc, "\nEnter the size of process" + p + ": ");
        	
        	int index = findBlock(memory, requestSize);
        	
        	if (index == -1) {
				System.out.println("No block found for process P" + p + " (size " + requestSize + ")");
			} else {
				Block block = memory.get(index);
 
				if (block.getSize() == requestSize) {
			
					block.setStatus("allocated");
					block.setProcessId("P" + p);
				} else {
					Block allocated = new Block(block.getStart(), requestSize, "allocated", "P" + p);
					Block remaining = new Block(block.getStart() + requestSize, block.getSize() - requestSize, "free", null);
					memory.set(index, allocated);
					
					memory.add(index + 1, remaining);
				}
 
				System.out.println("Process P" + p + " allocated at index " + index + ": " + memory.get(index));
			}
		}
	}
 
}