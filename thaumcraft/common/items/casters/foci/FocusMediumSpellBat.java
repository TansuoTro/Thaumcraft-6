/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.casters.FocusMedium;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ import thaumcraft.common.entities.monster.EntitySpellBat;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusMediumSpellBat
/*    */   extends FocusMedium
/*    */ {
/* 15 */   public String getResearch() { return "FOCUSSPELLBAT"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getKey() { return "thaumcraft.SPELLBAT"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public Aspect getAspect() { return Aspect.BEAST; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public int getComplexity() { return 8; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(Trajectory trajectory) {
/* 40 */     EntitySpellBat bat = new EntitySpellBat(getRemainingPackage(), (getSettingValue("target") == 1));
/* 41 */     bat.func_70107_b(trajectory.source.field_72450_a, trajectory.source.field_72448_b, trajectory.source.field_72449_c);
/* 42 */     return (getPackage().getCaster()).field_70170_p.func_72838_d(bat);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public boolean hasIntermediary() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public float getPowerMultiplier() { return 0.33F; }
/*    */ 
/*    */ 
/*    */   
/*    */   public NodeSetting[] createSettings() {
/* 57 */     int[] friend = { 0, 1 };
/* 58 */     String[] friendDesc = { "focus.common.enemy", "focus.common.friend" };
/* 59 */     return new NodeSetting[] { new NodeSetting("target", "focus.common.target", new NodeSetting.NodeSettingIntList(friend, friendDesc)) };
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumSpellBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */