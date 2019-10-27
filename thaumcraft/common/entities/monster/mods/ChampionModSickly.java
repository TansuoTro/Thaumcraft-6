/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class ChampionModSickly
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 16 */     if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 17 */       target.func_70690_d(new PotionEffect(MobEffects.field_76438_s, 500));
/*    */     }
/* 19 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 25 */     if (boss.field_70170_p.field_73012_v.nextBoolean())
/* 26 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 27 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 28 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 30 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 31 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, -0.02D, 0.0D, 0.2F, 0.6F + boss.field_70170_p.field_73012_v
/*    */         
/* 33 */         .nextFloat() * 0.1F, 0.2F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.5F, false, 1, 4, 2, 5 + boss.field_70170_p.field_73012_v
/* 34 */         .nextInt(4), 0, 0.9F + boss.field_70170_p.field_73012_v
/* 35 */         .nextFloat() * 0.3F, 0.0F, 0);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModSickly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */