/*    */ package thaumcraft.common.golems.parts;
/*    */ 
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.GolemLeg;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class GolemLegLevitator
/*    */   implements GolemLeg.ILegFunction
/*    */ {
/*    */   public void onUpdateTick(IGolemAPI golem) {
/* 11 */     if ((golem.getGolemWorld()).field_72995_K && (!(golem.getGolemEntity()).field_70122_E || (golem.getGolemEntity()).field_70173_aa % 5 == 0))
/* 12 */       FXDispatcher.INSTANCE.drawGolemFlyParticles(
/* 13 */           (golem.getGolemEntity()).field_70165_t, (golem.getGolemEntity()).field_70163_u + 0.1D, (golem.getGolemEntity()).field_70161_v, 
/* 14 */           (golem.getGolemWorld()).field_73012_v.nextGaussian() / 100.0D, -0.1D, (golem.getGolemWorld()).field_73012_v.nextGaussian() / 100.0D); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\parts\GolemLegLevitator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */