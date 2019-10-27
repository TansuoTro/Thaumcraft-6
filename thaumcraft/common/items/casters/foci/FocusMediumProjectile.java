/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.casters.FocusMedium;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.FocusPackage;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ import thaumcraft.common.entities.projectile.EntityFocusProjectile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusMediumProjectile
/*    */   extends FocusMedium
/*    */ {
/* 17 */   public String getResearch() { return "FOCUSPROJECTILE@2"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public String getKey() { return "thaumcraft.PROJECTILE"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getComplexity() {
/* 27 */     int c = 4 + (getSettingValue("speed") - 1) / 2;
/* 28 */     switch (getSettingValue("option")) { case 1:
/* 29 */         c += 3; break;
/* 30 */       case 2: case 3: c += 5; break; }
/*    */     
/* 32 */     return c;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET, FocusNode.EnumSupplyType.TRAJECTORY }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(Trajectory trajectory) {
/* 43 */     float speed = getSettingValue("speed") / 3.0F;
/* 44 */     FocusPackage p = getRemainingPackage();
/* 45 */     if (p.getCaster() != null) {
/* 46 */       EntityFocusProjectile projectile = new EntityFocusProjectile(p, speed, trajectory, getSettingValue("option"));
/* 47 */       return (getPackage().getCaster()).field_70170_p.func_72838_d(projectile);
/*    */     } 
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public boolean hasIntermediary() { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   public NodeSetting[] createSettings() {
/* 59 */     int[] option = { 0, 1, 2, 3 };
/* 60 */     String[] optionDesc = { "focus.common.none", "focus.projectile.bouncy", "focus.projectile.seeking.hostile", "focus.projectile.seeking.friendly" };
/* 61 */     return new NodeSetting[] { new NodeSetting("option", "focus.common.options", new NodeSetting.NodeSettingIntList(option, optionDesc), "FOCUSPROJECTILE"), new NodeSetting("speed", "focus.projectile.speed", new NodeSetting.NodeSettingIntRange(1, 5)) };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public Aspect getAspect() { return Aspect.MOTION; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */