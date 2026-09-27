package oop;

import java.util.*;

public class Deallocation {

	public static boolean deallocate(List<Block> memory, int blockIndex) {
		if(blockIndex < 0 || blockIndex >= memory.size()) {
			return false;
		}

		Block block = memory.get(blockIndex);

		if(block.isFree()) {
			return false;
		}

		System.out.println("Deallocating: " + block.getProcessId());

		block.setStatus("free");
		block.setProcessId(null);
		block.setUsedSize(0);

		return true;
	}
}

// ito ilalagay sa dealloc part ng GUI class
//private void onDeallocateClicked() {
//    int selectedRow = blockTable.getSelectedRow();
//
//    if (selectedRow == -1) {
//        log("Please select a block from the table to deallocate.");
//        return;
//    }
//
//    Boolean success = null;
//
//    try {
//        // kuha block data
//        Block block = memory.get(selectedRow);
//
//        // check kung free yung block
//        if (block.isFree()) {
//            success = false;
//            log("Selected block is already free.");
//        } else {
//            // dealloc
//            success = Deallocation.deallocate(memory, selectedRow);
//
//            if (success) {
//                refreshDisplay();
//                log("Deallocated Block " + (selectedRow + 1));
//                log("Block is now free.");
//            }
//        }
//
//       
//        if (success != null && !success) {
//            log("Unable to deallocate Block " + (selectedRow + 1) + ".");
//        }
//        // if nag error dealloc
//    } catch (Exception e) {
//        System.out.println("DEBUG - caught exception, message: " + e.getMessage());
//        log("An error occurred during deallocation.");
//    }
//}