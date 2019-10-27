/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class ChampionModSpined
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 15 */     if (target == null || source.field_76373_n.equalsIgnoreCase("thorns")) return amount; 
/* 16 */     target.func_70097_a(DamageSource.func_92087_a(boss), (1 + boss.field_70170_p.field_73012_v.nextInt(3)));
/* 17 */     target.func_184185_a(SoundEvents.field_187903_gc, 0.5F, 1.0F);
/* 18 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 24 */     if (boss.field_70170_p.field_73012_v.nextBoolean())
/*    */       return; 
/* 26 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 27 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 28 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/* 29 */     int p = 704 + boss.field_70170_p.field_73012_v.nextInt(4) * 3;
/* 30 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 31 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.5F + boss.field_70170_p.field_73012_v
/*    */         
/* 33 */         .nextFloat() * 0.2F, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.7F, false, p, 3, 1, 3, 0, 1.2F + boss.field_70170_p.field_73012_v
/* 34 */         .nextFloat() * 0.3F, 0.0F, 0);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModSpined.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */