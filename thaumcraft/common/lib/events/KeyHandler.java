/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
/*     */ import thaumcraft.common.lib.network.misc.PacketItemKeyToServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber({Side.CLIENT})
/*     */ public class KeyHandler
/*     */ {
/*  25 */   public static KeyBinding keyF = new KeyBinding("Change Caster Focus", 33, "key.categories.thaumcraft");
/*  26 */   public static KeyBinding keyG = new KeyBinding("Misc Caster Toggle", 34, "key.categories.thaumcraft");
/*     */   private static boolean keyPressedF = false;
/*     */   private boolean keyPressedH;
/*     */   private static boolean keyPressedG = false;
/*     */   public static boolean radialActive = false;
/*     */   public static boolean radialLock = false;
/*  32 */   public static long lastPressF = 0L;
/*  33 */   public static long lastPressH = 0L;
/*  34 */   public static long lastPressG = 0L;
/*     */   public KeyHandler() {
/*     */     this.keyPressedH = false;
/*  37 */     ClientRegistry.registerKeyBinding(keyF);
/*  38 */     ClientRegistry.registerKeyBinding(keyG);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void playerTick(TickEvent.PlayerTickEvent event) {
/*  45 */     if (event.side == Side.SERVER) {
/*     */       return;
/*     */     }
/*  48 */     if (event.phase == TickEvent.Phase.START) {
/*     */ 
/*     */       
/*  51 */       if (keyF.func_151470_d()) {
/*  52 */         if ((FMLClientHandler.instance().getClient()).field_71415_G) {
/*  53 */           EntityPlayer player = event.player;
/*  54 */           if (player != null) {
/*     */             
/*  56 */             if (!keyPressedF) {
/*  57 */               lastPressF = System.currentTimeMillis();
/*  58 */               radialLock = false;
/*     */             } 
/*  60 */             if (!radialLock && ((player.func_184614_ca() != null && player
/*  61 */               .func_184614_ca().func_77973_b() instanceof thaumcraft.api.casters.ICaster) || (player
/*  62 */               .func_184592_cb() != null && player
/*  63 */               .func_184592_cb().func_77973_b() instanceof thaumcraft.api.casters.ICaster))) {
/*     */               
/*  65 */               if (player.func_70093_af())
/*  66 */               { PacketHandler.INSTANCE.sendToServer(new PacketFocusChangeToServer("REMOVE")); }
/*     */               else
/*  68 */               { radialActive = true; } 
/*  69 */             } else if (player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() instanceof thaumcraft.common.golems.ItemGolemBell && !keyPressedF) {
/*     */               
/*  71 */               PacketHandler.INSTANCE.sendToServer(new PacketItemKeyToServer(0));
/*     */             } 
/*     */           } 
/*  74 */           keyPressedF = true;
/*     */         } 
/*     */       } else {
/*  77 */         radialActive = false;
/*  78 */         if (keyPressedF) {
/*  79 */           lastPressF = System.currentTimeMillis();
/*     */         }
/*  81 */         keyPressedF = false;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       if (keyG.func_151470_d()) {
/*     */         
/* 115 */         if ((FMLClientHandler.instance().getClient()).field_71415_G) {
/* 116 */           EntityPlayer player = event.player;
/* 117 */           if (player != null && 
/* 118 */             !keyPressedG) {
/* 119 */             lastPressG = System.currentTimeMillis();
/* 120 */             PacketHandler.INSTANCE
/* 121 */               .sendToServer(new PacketItemKeyToServer(1, 
/* 122 */                   Keyboard.isKeyDown(29) ? 1 : (
/* 123 */                   Keyboard.isKeyDown(42) ? 2 : 0)));
/*     */           } 
/*     */           
/* 126 */           keyPressedG = true;
/*     */         } 
/*     */       } else {
/* 129 */         if (keyPressedG) {
/* 130 */           lastPressG = System.currentTimeMillis();
/*     */         }
/* 132 */         keyPressedG = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\KeyHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */