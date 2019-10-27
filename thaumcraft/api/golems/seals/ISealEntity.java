package thaumcraft.api.golems.seals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ISealEntity {
  void tickSealEntity(World paramWorld);
  
  ISeal getSeal();
  
  SealPos getSealPos();
  
  byte getPriority();
  
  void setPriority(byte paramByte);
  
  void readNBT(NBTTagCompound paramNBTTagCompound);
  
  NBTTagCompound writeNBT();
  
  void syncToClient(World paramWorld);
  
  BlockPos getArea();
  
  void setArea(BlockPos paramBlockPos);
  
  boolean isLocked();
  
  void setLocked(boolean paramBoolean);
  
  boolean isRedstoneSensitive();
  
  void setRedstoneSensitive(boolean paramBoolean);
  
  String getOwner();
  
  void setOwner(String paramString);
  
  byte getColor();
  
  void setColor(byte paramByte);
  
  boolean isStoppedByRedstone(World paramWorld);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\seals\ISealEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */