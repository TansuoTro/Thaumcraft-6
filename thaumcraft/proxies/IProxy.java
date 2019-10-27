package thaumcraft.proxies;

import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
  void preInit(FMLPreInitializationEvent paramFMLPreInitializationEvent);
  
  void init(FMLInitializationEvent paramFMLInitializationEvent);
  
  void postInit(FMLPostInitializationEvent paramFMLPostInitializationEvent);
  
  World getClientWorld();
  
  boolean getSingleplayer();
  
  World getWorld(int paramInt);
  
  boolean isShiftKeyDown();
  
  void registerModel(ItemBlock paramItemBlock);
  
  void checkInterModComs(FMLInterModComms.IMCEvent paramIMCEvent);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\IProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */