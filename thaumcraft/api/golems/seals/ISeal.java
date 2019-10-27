package thaumcraft.api.golems.seals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.IGolemAPI;
import thaumcraft.api.golems.tasks.Task;

public interface ISeal {
  String getKey();
  
  boolean canPlaceAt(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing);
  
  void tickSeal(World paramWorld, ISealEntity paramISealEntity);
  
  void onTaskStarted(World paramWorld, IGolemAPI paramIGolemAPI, Task paramTask);
  
  boolean onTaskCompletion(World paramWorld, IGolemAPI paramIGolemAPI, Task paramTask);
  
  void onTaskSuspension(World paramWorld, Task paramTask);
  
  boolean canGolemPerformTask(IGolemAPI paramIGolemAPI, Task paramTask);
  
  void readCustomNBT(NBTTagCompound paramNBTTagCompound);
  
  void writeCustomNBT(NBTTagCompound paramNBTTagCompound);
  
  ResourceLocation getSealIcon();
  
  void onRemoval(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing);
  
  Object returnContainer(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, ISealEntity paramISealEntity);
  
  @SideOnly(Side.CLIENT)
  Object returnGui(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, ISealEntity paramISealEntity);
  
  EnumGolemTrait[] getRequiredTags();
  
  EnumGolemTrait[] getForbiddenTags();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\seals\ISeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */