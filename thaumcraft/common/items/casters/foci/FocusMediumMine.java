/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.casters.FocusMedium;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ import thaumcraft.common.entities.projectile.EntityFocusMine;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusMediumMine
/*    */   extends FocusMedium
/*    */ {
/* 16 */   public String getResearch() { return "FOCUSMINE"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public String getKey() { return "thaumcraft.MINE"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public int getComplexity() { return 4; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public Aspect getAspect() { return Aspect.TRAP; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET, FocusNode.EnumSupplyType.TRAJECTORY }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(Trajectory trajectory) {
/* 41 */     EntityFocusMine projectile = new EntityFocusMine(getRemainingPackage(), trajectory, (getSettingValue("target") == 1));
/* 42 */     return (getPackage().getCaster()).field_70170_p.func_72838_d(projectile);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public boolean hasIntermediary() { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   public NodeSetting[] createSettings() {
/* 52 */     int[] friend = { 0, 1 };
/* 53 */     String[] friendDesc = { "focus.common.enemy", "focus.common.friend" };
/* 54 */     return new NodeSetting[] { new NodeSetting("target", "focus.common.target", new NodeSetting.NodeSettingIntList(friend, friendDesc)) };
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumMine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */