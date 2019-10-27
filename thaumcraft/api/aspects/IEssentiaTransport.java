package thaumcraft.api.aspects;

import net.minecraft.util.EnumFacing;

public interface IEssentiaTransport {
  boolean isConnectable(EnumFacing paramEnumFacing);
  
  boolean canInputFrom(EnumFacing paramEnumFacing);
  
  boolean canOutputTo(EnumFacing paramEnumFacing);
  
  void setSuction(Aspect paramAspect, int paramInt);
  
  Aspect getSuctionType(EnumFacing paramEnumFacing);
  
  int getSuctionAmount(EnumFacing paramEnumFacing);
  
  int takeEssentia(Aspect paramAspect, int paramInt, EnumFacing paramEnumFacing);
  
  int addEssentia(Aspect paramAspect, int paramInt, EnumFacing paramEnumFacing);
  
  Aspect getEssentiaType(EnumFacing paramEnumFacing);
  
  int getEssentiaAmount(EnumFacing paramEnumFacing);
  
  int getMinimumSuction();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\IEssentiaTransport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */