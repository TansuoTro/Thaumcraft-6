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
/*    */ public class ChampionModFire
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 16 */     if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 17 */       target.func_70015_d(4);
/*    */     }
/* 19 */     return amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 25 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 26 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 27 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 29 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 30 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.03D, 0.0D, 0.9F + boss.field_70170_p.field_73012_v
/*    */         
/* 32 */         .nextFloat() * 0.1F, 1.0F, 1.0F, 0.7F, false, 640, 10, 1, 8 + boss.field_70170_p.field_73012_v
/* 33 */         .nextInt(4), 0, 0.7F + boss.field_70170_p.field_73012_v
/* 34 */         .nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) { GL11.glColor4f(1.0F, 0.75F, 5.0F, 1.0F); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */