/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ 
/*    */ public class ChampionModVampire
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 16 */     boss.func_70691_i(Math.max(2.0F, amount / 2.0F));
/* 17 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 23 */     if (boss.field_70170_p.field_73012_v.nextFloat() > 0.2F)
/* 24 */       return;  float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 26 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 28 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 29 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.9F + boss.field_70170_p.field_73012_v
/*    */         
/* 31 */         .nextFloat() * 0.1F, 0.0F, 0.0F, 0.9F, false, 579, 4, 1, 4 + boss.field_70170_p.field_73012_v
/* 32 */         .nextInt(4), 0, 0.5F + boss.field_70170_p.field_73012_v
/* 33 */         .nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) { GL11.glColor4f(1.0F, 0.7F, 0.7F, 1.0F); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModVampire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */