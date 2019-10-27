/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import thaumcraft.api.casters.FocusMod;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusModScatter
/*    */   extends FocusMod
/*    */ {
/* 17 */   public String getResearch() { return "FOCUSSCATTER"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public String getKey() { return "thaumcraft.SCATTER"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public int getComplexity() { return (int)Math.max(2.0F, 2.0F * (getSettingValue("forks") - getSettingValue("cone") / 45.0F)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NodeSetting[] createSettings() {
/* 33 */     int[] angles = { 10, 30, 60, 90, 180, 270, 360 };
/* 34 */     String[] anglesDesc = { "10", "30", "60", "90", "180", "270", "360" };
/*    */     
/* 36 */     return new NodeSetting[] { new NodeSetting("forks", "focus.scatter.forks", new NodeSetting.NodeSettingIntRange(2, 10)), new NodeSetting("cone", "focus.scatter.cone", new NodeSetting.NodeSettingIntList(angles, anglesDesc)) };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public FocusNode.EnumSupplyType[] mustBeSupplied() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TRAJECTORY }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TRAJECTORY }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Trajectory[] supplyTrajectories() {
/* 54 */     if (getParent() == null) return new Trajectory[0]; 
/* 55 */     ArrayList<Trajectory> tT = new ArrayList<Trajectory>();
/*    */     
/* 57 */     int forks = getSettingValue("forks");
/* 58 */     int angle = getSettingValue("cone");
/*    */     
/* 60 */     if (getParent().supplyTrajectories() != null) {
/* 61 */       for (Trajectory sT : getParent().supplyTrajectories()) {
/* 62 */         for (int a = 0; a < forks; a++) {
/* 63 */           Vec3d sV = sT.source;
/* 64 */           Vec3d dV = sT.direction;
/* 65 */           dV = dV.func_72432_b();
/*    */           
/* 67 */           dV = dV.func_72441_c(
/* 68 */               (getPackage()).world.field_73012_v.nextGaussian() * 0.007499999832361937D * angle, 
/* 69 */               (getPackage()).world.field_73012_v.nextGaussian() * 0.007499999832361937D * angle, 
/* 70 */               (getPackage()).world.field_73012_v.nextGaussian() * 0.007499999832361937D * angle);
/*    */           
/* 72 */           tT.add(new Trajectory(sV, dV.func_72432_b()));
/*    */         } 
/*    */       } 
/*    */     }
/* 76 */     return (Trajectory[])tT.toArray(new Trajectory[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public float getPowerMultiplier() { return 1.0F / getSettingValue("forks") / 2.0F; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 86 */   public boolean execute() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 91 */   public boolean isExclusive() { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusModScatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */