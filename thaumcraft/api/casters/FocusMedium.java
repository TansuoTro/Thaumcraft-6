/*    */ package thaumcraft.api.casters;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FocusMedium
/*    */   extends FocusNode
/*    */ {
/*  8 */   public IFocusElement.EnumUnitType getType() { return IFocusElement.EnumUnitType.MEDIUM; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 13 */   public final FocusNode.EnumSupplyType[] mustBeSupplied() { new FocusNode.EnumSupplyType[1][0] = FocusNode.EnumSupplyType.TRAJECTORY; return (this instanceof FocusMediumRoot) ? null : new FocusNode.EnumSupplyType[1]; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET }; }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public boolean hasIntermediary() { return false; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean execute(Trajectory trajectory) { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusMedium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */