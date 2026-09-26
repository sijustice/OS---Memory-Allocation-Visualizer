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
	
	public boolean allocate(List<Block> memory, String processId, int size) {
	    int index = findBlock(memory, size);
	    if (index == -1) {
	        return false;
	    }
	    Block block = memory.get(index);
	    if (block.getSize() == size) {
	        block.setStatus("allocated");
	        block.setProcessId(processId);
	    } else {
	        Block allocated = new Block(block.getStart(), size, "allocated", processId);
	        Block remaining = new Block(block.getStart() + size, block.getSize() - size, "free", null);
	        memory.set(index, allocated);
	        memory.add(index + 1, remaining);
	    }
	    return true;
	}
 
}