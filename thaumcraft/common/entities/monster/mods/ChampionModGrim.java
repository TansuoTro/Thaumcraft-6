/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ 
/*    */ public class ChampionModGrim
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 18 */     if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 19 */       target.func_70690_d(new PotionEffect(MobEffects.field_82731_v, 200));
/*    */     }
/*    */     
/* 22 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 28 */     if (boss.field_70170_p.field_73012_v.nextBoolean())
/* 29 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 30 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 31 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 33 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 34 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, -0.02D, 0.0D, boss.field_70170_p.field_73012_v
/*    */         
/* 36 */         .nextFloat() * 0.2F, boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.8F, false, 640, 10, 1, 8 + boss.field_70170_p.field_73012_v
/* 37 */         .nextInt(4), 0, 0.6F + boss.field_70170_p.field_73012_v
/* 38 */         .nextFloat() * 0.4F, 0.0F, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) { GL11.glColor4f(0.6F, 0.6F, 0.6F, 1.0F); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModGrim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */