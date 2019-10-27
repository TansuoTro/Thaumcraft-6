/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class ChampionModWarded
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount) {
/* 15 */     if (mob.field_70172_ad <= 0 && mob.field_70173_aa % 25 == 0) {
/* 16 */       int bh = (int)mob.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 17 */       if (mob.func_110139_bj() < bh)
/* 18 */         mob.func_110149_m(mob.func_110139_bj() + 1.0F); 
/*    */     } 
/* 20 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 26 */     if (boss.field_70170_p.field_73012_v.nextBoolean())
/* 27 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 28 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 29 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 31 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 32 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.5F + boss.field_70170_p.field_73012_v
/*    */         
/* 34 */         .nextFloat() * 0.1F, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.6F, true, 69, 4, 1, 4 + boss.field_70170_p.field_73012_v
/* 35 */         .nextInt(4), 0, 0.8F + boss.field_70170_p.field_73012_v
/* 36 */         .nextFloat() * 0.3F, 0.0F, 0);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModWarded.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */