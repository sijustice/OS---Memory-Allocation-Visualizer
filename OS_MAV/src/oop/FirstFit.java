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
	
	public int fragmentation(Block block, int size) {
		
		return block.getSize() - size;
	}
	
	
	public int compaction(List<Block> memory) {
		
		int totalExcess = 0;
		int currentStart= 0;
		
		for(Block block : memory) {
			if(!block.isFree()) {
				int excess = block.getSize() - block.getUsedSize();
				
				if(excess > 0) {
					totalExcess += excess;
				}
				
				
			}
			else{
				totalExcess += block.getSize();
						
			}
			
			block.setSize(block.getUsedSize());
			
			block.setStart(currentStart);

	        currentStart += block.getSize();
			
		}
		
		memory.removeIf(Block::isFree);
		
		 if (totalExcess > 0) {

		        Block newFreeBlock = new Block(
		            currentStart,
		            totalExcess,
		            "free",
		            null
		        );

		        memory.add(newFreeBlock);
		 }
		
		System.out.println(totalExcess);
		
		return 0;
		
	}
	
	public boolean allocate(List<Block> memory, String processId, int size) {
		int index = findBlock(memory, size);
	    if (index == -1) {
	        return false;
	    }
	    Block block = memory.get(index); //makes gets the index
	    block.setStatus("allocated"); //status if allocated or not
	    block.setProcessId(processId); //gives the job a label
	    block.setUsedSize(size); //ade tinitingnan kung ilan yung used na kb
	    return true;
	}
 
}