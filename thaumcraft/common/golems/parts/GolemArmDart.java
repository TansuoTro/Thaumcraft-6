/*    */ package thaumcraft.common.golems.parts;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IRangedAttackMob;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIAttackRanged;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.GolemArm;
/*    */ import thaumcraft.common.entities.projectile.EntityGolemDart;
/*    */ import thaumcraft.common.golems.ai.AIArrowAttack;
/*    */ 
/*    */ 
/*    */ public class GolemArmDart
/*    */   implements GolemArm.IArmFunction
/*    */ {
/*    */   public void onMeleeAttack(IGolemAPI golem, Entity target) {}
/*    */   
/*    */   public void onRangedAttack(IGolemAPI golem, EntityLivingBase target, float range) {
/* 21 */     EntityGolemDart entityarrow = new EntityGolemDart(golem.getGolemWorld(), golem.getGolemEntity());
/* 22 */     float dmg = (float)golem.getGolemEntity().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() / 3.0F;
/* 23 */     entityarrow.func_70239_b((dmg + range) + (golem.getGolemWorld()).field_73012_v.nextGaussian() * 0.25D);
/* 24 */     double d0 = target.field_70165_t - (golem.getGolemEntity()).field_70165_t;
/* 25 */     double d1 = (target.func_174813_aQ()).field_72338_b + target.func_70047_e() + (range * range) - entityarrow.field_70163_u;
/* 26 */     double d2 = target.field_70161_v - (golem.getGolemEntity()).field_70161_v;
/* 27 */     entityarrow.func_70186_c(d0, d1, d2, 1.6F, 3.0F);
/*    */     
/* 29 */     golem.getGolemWorld().func_72838_d(entityarrow);
/* 30 */     golem.getGolemEntity().func_184185_a(SoundEvents.field_187737_v, 1.0F, 1.0F / ((golem.getGolemWorld()).field_73012_v.nextFloat() * 0.4F + 0.8F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public EntityAIAttackRanged getRangedAttackAI(IRangedAttackMob golem) { return new AIArrowAttack(golem, 1.0D, 20, 25, 16.0F); }
/*    */   
/*    */   public void onUpdateTick(IGolemAPI golem) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\parts\GolemArmDart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */