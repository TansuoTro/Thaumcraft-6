/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChampionModMighty
/*    */   implements IChampionModifierEffect
/*    */ {
/* 16 */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount) { return 0.0F; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 22 */     if (boss.field_70170_p.field_73012_v.nextFloat() > 0.3F)
/* 23 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/* 26 */     int p = 704 + boss.field_70170_p.field_73012_v.nextInt(4) * 3;
/* 27 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 28 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.8F + boss.field_70170_p.field_73012_v
/*    */         
/* 30 */         .nextFloat() * 0.2F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.7F, false, p, 3, 1, 4 + boss.field_70170_p.field_73012_v
/* 31 */         .nextInt(3), 0, 1.0F + boss.field_70170_p.field_73012_v.nextFloat() * 0.3F, 0.0F, 0);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModMighty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */