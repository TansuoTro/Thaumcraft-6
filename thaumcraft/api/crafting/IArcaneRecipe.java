package thaumcraft.api.crafting;

import net.minecraft.item.crafting.IRecipe;
import thaumcraft.api.aspects.AspectList;

public interface IArcaneRecipe extends IRecipe {
  int getVis();
  
  String getResearch();
  
  AspectList getCrystals();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\IArcaneRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */