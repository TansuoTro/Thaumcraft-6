package thaumcraft.api.casters;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICasterTriggerManager {
  boolean performTrigger(World paramWorld, ItemStack paramItemStack, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, int paramInt);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\ICasterTriggerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */