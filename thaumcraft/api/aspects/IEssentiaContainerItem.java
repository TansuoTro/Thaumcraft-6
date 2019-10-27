package thaumcraft.api.aspects;

import net.minecraft.item.ItemStack;

public interface IEssentiaContainerItem {
  AspectList getAspects(ItemStack paramItemStack);
  
  void setAspects(ItemStack paramItemStack, AspectList paramAspectList);
  
  boolean ignoreContainedAspects();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\IEssentiaContainerItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */