package thaumcraft.api.golems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public interface IGolemAPI {
  EntityLivingBase getGolemEntity();
  
  IGolemProperties getProperties();
  
  void setProperties(IGolemProperties paramIGolemProperties);
  
  World getGolemWorld();
  
  ItemStack holdItem(ItemStack paramItemStack);
  
  ItemStack dropItem(ItemStack paramItemStack);
  
  boolean canCarry(ItemStack paramItemStack, boolean paramBoolean);
  
  int canCarryAmount(ItemStack paramItemStack);
  
  boolean isCarrying(ItemStack paramItemStack);
  
  NonNullList<ItemStack> getCarrying();
  
  void addRankXp(int paramInt);
  
  byte getGolemColor();
  
  void swingArm();
  
  boolean isInCombat();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\IGolemAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */