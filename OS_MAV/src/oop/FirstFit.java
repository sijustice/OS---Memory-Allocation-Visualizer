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

}
