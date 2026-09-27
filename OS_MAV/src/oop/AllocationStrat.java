package oop;
import java.util.*;

public interface AllocationStrat{
	
	int findBlock(List<Block> memory, int size);
	
	int fragmentation(Block block, int size);
	
	
	
	int compaction(List<Block> memory);
}
