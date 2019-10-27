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
/*    */ public class ChampionModBold
/*    */   implements IChampionModifierEffect
/*    */ {
/* 16 */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount) { return 0.0F; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 22 */     if (boss.field_70170_p.field_73012_v.nextBoolean())
/* 23 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O / 3.0F;
/*    */     
/* 27 */     FXDispatcher.INSTANCE.spark(
/* 28 */         (boss.func_174813_aQ()).field_72340_a + w, 
/* 29 */         (boss.func_174813_aQ()).field_72338_b + h, 
/* 30 */         (boss.func_174813_aQ()).field_72339_c + d, 0.2F, 0.3F - boss.field_70170_p.field_73012_v
/* 31 */         .nextFloat() * 0.1F, 0.0F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 1.0F);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModBold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */