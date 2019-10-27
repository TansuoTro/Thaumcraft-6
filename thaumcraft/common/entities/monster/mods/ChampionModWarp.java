/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class ChampionModWarp
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 17 */     if (boss.field_70170_p.field_73012_v.nextFloat() < 0.33F && target instanceof EntityPlayer) {
/* 18 */       ThaumcraftCapabilities.getWarp((EntityPlayer)target).add(IPlayerWarp.EnumWarpType.TEMPORARY, 1 + boss.field_70170_p.field_73012_v.nextInt(3));
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
/* 32 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.8F + boss.field_70170_p.field_73012_v
/*    */         
/* 34 */         .nextFloat() * 0.2F, 0.0F, 0.9F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.7F, true, 264, 8, 1, 10 + boss.field_70170_p.field_73012_v
/* 35 */         .nextInt(4), 0, 0.6F + boss.field_70170_p.field_73012_v
/* 36 */         .nextFloat() * 0.4F, 0.0F, 0);
/*    */   }
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModWarp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */