/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import thaumcraft.api.casters.FocusModSplit;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ 
/*    */ 
/*    */ public class FocusModSplitTrajectory
/*    */   extends FocusModSplit
/*    */ {
/* 11 */   public String getResearch() { return "FOCUSSPLIT"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public String getKey() { return "thaumcraft.SPLITTRAJECTORY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public int getComplexity() { return 5; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public FocusNode.EnumSupplyType[] mustBeSupplied() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TRAJECTORY }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TRAJECTORY }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Trajectory[] supplyTrajectories() { return getParent().supplyTrajectories(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean execute() { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusModSplitTrajectory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */