package thaumcraft.common.entities.monster.mods;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IChampionModifierEffect {
  float performEffect(EntityLivingBase paramEntityLivingBase1, EntityLivingBase paramEntityLivingBase2, DamageSource paramDamageSource, float paramFloat);
  
  @SideOnly(Side.CLIENT)
  void showFX(EntityLivingBase paramEntityLivingBase);
  
  void preRender(EntityLivingBase paramEntityLivingBase, RenderLivingBase paramRenderLivingBase);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\IChampionModifierEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */