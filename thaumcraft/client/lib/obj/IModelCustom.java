package thaumcraft.client.lib.obj;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelCustom {
  String getType();
  
  @SideOnly(Side.CLIENT)
  void renderAll();
  
  @SideOnly(Side.CLIENT)
  void renderOnly(String... paramVarArgs);
  
  @SideOnly(Side.CLIENT)
  void renderPart(String paramString);
  
  @SideOnly(Side.CLIENT)
  void renderAllExcept(String... paramVarArgs);
  
  @SideOnly(Side.CLIENT)
  String[] getPartNames();
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\IModelCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */