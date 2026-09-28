package oop;

import java.util.*;

public class BestFit implements AllocationStrat {

	@Override
	public int findBlock(List<Block> memory, int size) {
		int bestIndex = -1;

		for(int i = 0; i < memory.size(); i++) {
			Block currentBlock = memory.get(i);
			System.out.println("Checking index: " + i + " " + currentBlock);

			if(currentBlock.isFree() && currentBlock.getSize() >= size) {
				if(bestIndex == -1 || currentBlock.getSize() < memory.get(bestIndex).getSize()) {
					bestIndex = i;
				}
			}
		}

		if(bestIndex == -1) {
			System.out.println("No block is found for size " + size);
		} else {
			System.out.println("Best Fit selected index: " + bestIndex);
		}

		return bestIndex;
	}

	public int fragmentation(Block block, int size) {
		return block.getSize() - size;
	}

	public int compaction(List<Block> memory) {
		return 0;
	}

	public boolean allocate(List<Block> memory, String processId, int size) {
		int index = findBlock(memory, size);

		if(index == -1) {
			return false;
		}

		Block block = memory.get(index);
		block.setStatus("allocated");
		block.setProcessId(processId);
		block.setUsedSize(size);

		return true;
	}
}

//ito code na ilalagay sa onAllocateClicked sa else part ko
//BestFit bestFit = new BestFit();
//int index = bestFit.findBlock(memory, size);
//if (index != -1) {
//    // get block data
//    Block block = memory.get(index);
//
//    int fragmentation = bestFit.fragmentation(block, size);
//
//    totalFrag += fragmentation;
//
//    // allocates the job
//    success = bestFit.allocate(memory, jobName, size);
//
//    if (success) {
//        refreshDisplay();
//        log("Allocated " + jobName + " (" + size + " KB) using " + selectedStrategy);
//        if (fragmentation > 0) {
//            log("Fragmentation: " + fragmentation + " KB");
//            log("Total Fragmentation: " + totalFrag + " KB");
//        } else {
//            log("Fragmentation: none");
//        }
//        log("Action triggered: Allocate " + jobName + " (" + rawSize + " KB) using " + selectedStrategy);
//    }
//} else {
//    success = false;