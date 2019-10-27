package thaumcraft.api.research;

import net.minecraft.entity.player.EntityPlayer;

public interface IScanThing {
  boolean checkThing(EntityPlayer paramEntityPlayer, Object paramObject);
  
  String getResearchKey(EntityPlayer paramEntityPlayer, Object paramObject);
  
  default void onSuccess(EntityPlayer player, Object object) {}
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\IScanThing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */