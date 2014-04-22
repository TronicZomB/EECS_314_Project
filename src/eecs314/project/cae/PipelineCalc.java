package eecs314.project.cae;

/**
 * 
 * @author James Marsh
 *
 */
public class PipelineCalc {
	
	public static float basicPipe(float stageLength, int stageCount, int instructCount)
	{
		return (stageLength * (instructCount - 1)) + fillTime(stageLength, stageCount);
	} 
	//Time it takes to finish executing the first instruction
	//After this point, new instructions will finish every (stagelength)
	public static float fillTime(float stageLength, int stageCount)
	{
		return stageLength * stageCount;
	}
	
	public static float singleCycle (float cycleLength, int instructCount)
	{
		return cycleLength * instructCount;
	}
	
	public static float throughCompare (float singCycleLength, float avgStall, float stageLength, int stageCount, int instructCount)
	{
		return (basicPipe (stageLength, stageCount, instructCount) +totalWaste(stageLength, avgStall)*instructCount)/singleCycle(singCycleLength,instructCount);
	}
	
	public static float throughCompare(float stageLength, float avgStall, int stageCount, int instructCount)
	{
		return throughCompare(fillTime(stageLength, stageCount), avgStall, stageLength, stageCount, instructCount);
	}
	
	public static float totalWaste(float stageLength, float avgStall)
	{
		return stageLength * avgStall;
	}

}
