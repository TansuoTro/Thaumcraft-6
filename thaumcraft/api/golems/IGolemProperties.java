package thaumcraft.api.golems;

import java.util.Set;
import net.minecraft.item.ItemStack;
import thaumcraft.api.golems.parts.GolemAddon;
import thaumcraft.api.golems.parts.GolemArm;
import thaumcraft.api.golems.parts.GolemHead;
import thaumcraft.api.golems.parts.GolemLeg;
import thaumcraft.api.golems.parts.GolemMaterial;

public interface IGolemProperties {
  Set<EnumGolemTrait> getTraits();
  
  boolean hasTrait(EnumGolemTrait paramEnumGolemTrait);
  
  long toLong();
  
  ItemStack[] generateComponents();
  
  void setMaterial(GolemMaterial paramGolemMaterial);
  
  GolemMaterial getMaterial();
  
  void setHead(GolemHead paramGolemHead);
  
  GolemHead getHead();
  
  void setArms(GolemArm paramGolemArm);
  
  GolemArm getArms();
  
  void setLegs(GolemLeg paramGolemLeg);
  
  GolemLeg getLegs();
  
  void setAddon(GolemAddon paramGolemAddon);
  
  GolemAddon getAddon();
  
  void setRank(int paramInt);
  
  int getRank();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\IGolemProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */