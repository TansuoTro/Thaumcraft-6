/*    */ package thaumcraft.proxies;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.DimensionManager;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerProxy
/*    */   extends CommonProxy
/*    */ {
/* 12 */   public void preInit(FMLPreInitializationEvent event) { super.preInit(event); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public World getWorld(int dim) { return DimensionManager.getWorld(dim); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */