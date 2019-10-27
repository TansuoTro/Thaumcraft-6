package thaumcraft.api.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public interface ILabelable {
  boolean applyLabel(EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, ItemStack paramItemStack);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\blocks\ILabelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */