package thaumcraft.api.casters;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICaster {
  float getConsumptionModifier(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, boolean paramBoolean);
  
  boolean consumeVis(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, float paramFloat, boolean paramBoolean1, boolean paramBoolean2);
  
  Item getFocus(ItemStack paramItemStack);
  
  ItemStack getFocusStack(ItemStack paramItemStack);
  
  void setFocus(ItemStack paramItemStack1, ItemStack paramItemStack2);
  
  ItemStack getPickedBlock(ItemStack paramItemStack);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\ICaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */