/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class ChampionModArmored
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount) {
/* 12 */     if (!source.func_76363_c()) {
/* 13 */       float f1 = amount * 19.0F;
/* 14 */       amount = f1 / 25.0F;
/*    */     } 
/* 16 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void showFX(EntityLivingBase boss) {
/* 21 */     if (boss.field_70170_p.field_73012_v.nextInt(4) != 0)
/* 22 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 23 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 26 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 27 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.9F, 0.9F, 0.9F + boss.field_70170_p.field_73012_v
/*    */         
/* 29 */         .nextFloat() * 0.1F, 0.7F, false, 448, 9, 1, 5 + boss.field_70170_p.field_73012_v
/* 30 */         .nextInt(4), 0, 0.6F + boss.field_70170_p.field_73012_v
/* 31 */         .nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModArmored.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */