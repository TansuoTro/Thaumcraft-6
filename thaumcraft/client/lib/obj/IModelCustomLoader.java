package thaumcraft.client.lib.obj;

import net.minecraft.util.ResourceLocation;

public interface IModelCustomLoader {
  String getType();
  
  String[] getSuffixes();
  
  IModelCustom loadInstance(ResourceLocation paramResourceLocation) throws WavefrontObject.ModelFormatException;
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\IModelCustomLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */