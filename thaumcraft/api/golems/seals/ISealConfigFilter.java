package thaumcraft.api.golems.seals;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ISealConfigFilter {
  NonNullList<ItemStack> getInv();
  
  NonNullList<Integer> getSizes();
  
  int getFilterSize();
  
  ItemStack getFilterSlot(int paramInt);
  
  int getFilterSlotSize(int paramInt);
  
  void setFilterSlot(int paramInt, ItemStack paramItemStack);
  
  void setFilterSlotSize(int paramInt1, int paramInt2);
  
  boolean isBlacklist();
  
  void setBlacklist(boolean paramBoolean);
  
  boolean hasStacksizeLimiters();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\seals\ISealConfigFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */