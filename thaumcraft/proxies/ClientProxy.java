/*    */ package thaumcraft.proxies;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.model.ModelLoader;
/*    */ import net.minecraftforge.client.model.obj.OBJLoader;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ import thaumcraft.client.ColorHandler;
/*    */ import thaumcraft.client.lib.ender.ShaderHelper;
/*    */ import thaumcraft.common.lib.events.KeyHandler;
/*    */ 
/*    */ public class ClientProxy
/*    */   extends CommonProxy
/*    */ {
/* 22 */   ProxyEntities proxyEntities = new ProxyEntities();
/* 23 */   ProxyTESR proxyTESR = new ProxyTESR();
/*    */ 
/*    */ 
/*    */   
/*    */   public void preInit(FMLPreInitializationEvent event) {
/* 28 */     super.preInit(event);
/* 29 */     OBJLoader.INSTANCE.addDomain("thaumcraft".toLowerCase());
/*    */     
/* 31 */     ShaderHelper.initShaders();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(FMLInitializationEvent event) {
/* 37 */     super.init(event);
/*    */     
/* 39 */     ColorHandler.registerColourHandlers();
/*    */     
/* 41 */     registerKeyBindings();
/*    */ 
/*    */     
/* 44 */     this.proxyEntities.setupEntityRenderers();
/*    */ 
/*    */     
/* 47 */     this.proxyTESR.setupTESR();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void postInit(FMLPostInitializationEvent event) { super.postInit(event); }
/*    */ 
/*    */ 
/*    */   
/* 58 */   KeyHandler kh = new KeyHandler();
/*    */ 
/*    */   
/* 61 */   public void registerKeyBindings() { MinecraftForge.EVENT_BUS.register(this.kh); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public World getClientWorld() { return (FMLClientHandler.instance().getClient()).field_71441_e; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 72 */   public World getWorld(int dim) { return getClientWorld(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   public boolean getSingleplayer() { return Minecraft.func_71410_x().func_71356_B(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   public boolean isShiftKeyDown() { return GuiScreen.func_146272_n(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOtherBlockRenderers() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 92 */   public void registerModel(ItemBlock itemBlock) { ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */