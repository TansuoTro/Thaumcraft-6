/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.casters.FocusMedium;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ import thaumcraft.common.entities.projectile.EntityFocusCloud;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusMediumCloud
/*    */   extends FocusMedium
/*    */ {
/* 15 */   public String getResearch() { return "FOCUSCLOUD"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getKey() { return "thaumcraft.CLOUD"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public Aspect getAspect() { return Aspect.ALCHEMY; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public int getComplexity() { return 4 + getSettingValue("radius") * 2 + getSettingValue("duration") / 5; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(Trajectory trajectory) {
/* 40 */     EntityFocusCloud cloud = new EntityFocusCloud(getRemainingPackage(), trajectory, getSettingValue("radius"), getSettingValue("duration"));
/* 41 */     return (getPackage().getCaster()).field_70170_p.func_72838_d(cloud);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public boolean hasIntermediary() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("radius", "focus.common.radius", new NodeSetting.NodeSettingIntRange(1, 3)), new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(5, 30)) }; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public float getPowerMultiplier() { return 0.5F; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */