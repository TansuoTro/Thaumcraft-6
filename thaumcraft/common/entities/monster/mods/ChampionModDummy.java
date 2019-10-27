/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChampionModDummy
/*    */   implements IChampionModifierEffect
/*    */ {
/* 13 */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) { return amount; }
/*    */   
/*    */   public void showFX(EntityLivingBase boss) {}
/*    */   
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */