/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import thaumcraft.api.casters.FocusModSplit;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusModSplitTarget
/*    */   extends FocusModSplit
/*    */ {
/* 12 */   public String getResearch() { return "FOCUSSPLIT"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public String getKey() { return "thaumcraft.SPLITTARGET"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public int getComplexity() { return 4; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public FocusNode.EnumSupplyType[] mustBeSupplied() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public RayTraceResult[] supplyTargets() { return getParent().supplyTargets(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public boolean execute() { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusModSplitTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */