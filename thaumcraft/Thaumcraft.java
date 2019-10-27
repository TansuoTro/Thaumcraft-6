/*    */ package thaumcraft;
/*    */ 
/*    */ import java.io.File;
/*    */ import net.minecraftforge.common.config.Config;
/*    */ import net.minecraftforge.common.config.ConfigManager;
/*    */ import net.minecraftforge.fluids.FluidRegistry;
/*    */ import net.minecraftforge.fml.client.event.ConfigChangedEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*    */ import net.minecraftforge.fml.common.Mod.Instance;
/*    */ import net.minecraftforge.fml.common.SidedProxy;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLInterModComms;
/*    */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thaumcraft.common.lib.CommandThaumcraft;
/*    */ import thaumcraft.proxies.IProxy;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mod(modid = "thaumcraft", name = "Thaumcraft", version = "6.1.BETA26", dependencies = "required-after:forge@[14.23.5.2768,);required-after:baubles@[1.5.2,)", acceptedMinecraftVersions = "[1.12.2]")
/*    */ public class Thaumcraft
/*    */ {
/*    */   public static final String MODID = "thaumcraft";
/*    */   public static final String MODNAME = "Thaumcraft";
/*    */   public static final String VERSION = "6.1.BETA26";
/*    */   @SidedProxy(clientSide = "thaumcraft.proxies.ClientProxy", serverSide = "thaumcraft.proxies.ServerProxy")
/*    */   public static IProxy proxy;
/*    */   @Instance("thaumcraft")
/*    */   public static Thaumcraft instance;
/*    */   public File modDir;
/* 40 */   public static final Logger log = LogManager.getLogger("thaumcraft".toUpperCase());
/*    */ 
/*    */   
/*    */   @EventHandler
/* 44 */   public void preInit(FMLPreInitializationEvent event) { proxy.preInit(event); }
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/* 49 */   public void init(FMLInitializationEvent event) { proxy.init(event); }
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/* 54 */   public void postInit(FMLPostInitializationEvent event) { proxy.postInit(event); }
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/* 59 */   public void serverLoad(FMLServerStartingEvent event) { event.registerServerCommand(new CommandThaumcraft()); }
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/* 64 */   public void interModComs(FMLInterModComms.IMCEvent event) { proxy.checkInterModComs(event); }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
/* 70 */     if (event.getModID().equals("thaumcraft"))
/*    */     {
/* 72 */       ConfigManager.sync("thaumcraft", Config.Type.INSTANCE);
/*    */     }
/*    */   }
/*    */   
/*    */   static  {
/* 77 */     FluidRegistry.enableUniversalBucket();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\Thaumcraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */