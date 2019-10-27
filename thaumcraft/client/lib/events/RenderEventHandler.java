/*      */ package thaumcraft.client.lib.events;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.LinkedBlockingQueue;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.inventory.GuiContainer;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.shader.ShaderGroup;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.EnumDyeColor;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.tileentity.TileEntityNote;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.RayTraceResult;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*      */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*      */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*      */ import net.minecraftforge.client.event.RenderLivingEvent;
/*      */ import net.minecraftforge.client.event.RenderTooltipEvent;
/*      */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*      */ import net.minecraftforge.client.event.TextureStitchEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingEvent;
/*      */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*      */ import net.minecraftforge.fml.client.FMLClientHandler;
/*      */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*      */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*      */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.ThaumcraftApiHelper;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectHelper;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.aspects.IAspectContainer;
/*      */ import thaumcraft.api.golems.seals.ISealEntity;
/*      */ import thaumcraft.api.golems.seals.SealPos;
/*      */ import thaumcraft.api.items.IArchitect;
/*      */ import thaumcraft.api.items.IGogglesDisplayExtended;
/*      */ import thaumcraft.client.fx.FXDispatcher;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*      */ import thaumcraft.common.config.ModConfig;
/*      */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*      */ import thaumcraft.common.golems.seals.SealEntity;
/*      */ import thaumcraft.common.golems.seals.SealHandler;
/*      */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*      */ import thaumcraft.common.lib.events.EssentiaHandler;
/*      */ import thaumcraft.common.lib.network.PacketHandler;
/*      */ import thaumcraft.common.lib.network.misc.PacketNote;
/*      */ import thaumcraft.common.lib.utils.EntityUtils;
/*      */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*      */ import thaumcraft.common.tiles.devices.TileRedstoneRelay;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @EventBusSubscriber({Side.CLIENT})
/*      */ public class RenderEventHandler
/*      */ {
/*   89 */   public static RenderEventHandler INSTANCE = new RenderEventHandler();
/*      */   @SideOnly(Side.CLIENT)
/*   91 */   public static HudHandler hudHandler = new HudHandler();
/*      */   @SideOnly(Side.CLIENT)
/*   93 */   public static WandRenderingHandler wandHandler = new WandRenderingHandler();
/*      */   @SideOnly(Side.CLIENT)
/*   95 */   static ShaderHandler shaderhandler = new ShaderHandler();
/*   96 */   public static List blockTags = new ArrayList();
/*   97 */   public static float tagscale = 0.0F;
/*   98 */   public static int tickCount = 0;
/*      */   static boolean checkedDate = false;
/*  100 */   private Random random = new Random();
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void playerTick(TickEvent.PlayerTickEvent event) {
/*  107 */     Minecraft mc = Minecraft.func_71410_x();
/*      */     
/*  109 */     if (event.side == Side.SERVER || event.player.func_145782_y() != mc.field_71439_g.func_145782_y())
/*      */       return; 
/*  111 */     if (event.phase == TickEvent.Phase.START)
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */         
/*  117 */         shaderhandler.checkShaders(event, mc);
/*      */         
/*  119 */         if (ShaderHandler.warpVignette > 0) {
/*  120 */           ShaderHandler.warpVignette--;
/*  121 */           targetBrightness = 0.0F;
/*      */         } else {
/*  123 */           targetBrightness = 1.0F;
/*      */         } 
/*      */         
/*  126 */         if (fogFiddled) {
/*  127 */           if (fogDuration < 100) {
/*  128 */             fogTarget = 0.1F * fogDuration / 100.0F;
/*  129 */           } else if (fogTarget < 0.1F) {
/*  130 */             fogTarget += 0.001F;
/*      */           } 
/*  132 */           fogDuration--;
/*  133 */           if (fogDuration < 0)
/*  134 */             fogFiddled = false; 
/*      */         } 
/*  136 */       } catch (Exception exception) {} 
/*      */   }
/*      */   
/*      */   public static class ChargeEntry {
/*      */     public long time;
/*      */     public long tickTime;
/*      */     
/*      */     public ChargeEntry(long time, ItemStack item, float charge) {
/*  144 */       this.charge = 0.0F;
/*  145 */       this.diff = 0;
/*      */       
/*  147 */       this.time = time;
/*  148 */       this.item = item;
/*  149 */       this.charge = charge;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ItemStack item;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     float charge;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte diff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void clientWorldTick(TickEvent.ClientTickEvent event) {
/*  241 */     if (event.side == Side.SERVER)
/*  242 */       return;  Minecraft mc = FMLClientHandler.instance().getClient();
/*  243 */     WorldClient worldClient = mc.field_71441_e;
/*  244 */     if (event.phase == TickEvent.Phase.START) {
/*      */       
/*  246 */       tickCount++;
/*  247 */       for (String fxk : (String[])EssentiaHandler.sourceFX.keySet().toArray(new String[0])) {
/*  248 */         EssentiaHandler.EssentiaSourceFX fx = (EssentiaHandler.EssentiaSourceFX)EssentiaHandler.sourceFX.get(fxk);
/*  249 */         if (worldClient != null) {
/*  250 */           int mod = 0;
/*  251 */           TileEntity tile = worldClient.func_175625_s(fx.start);
/*  252 */           if (tile != null && tile instanceof thaumcraft.common.tiles.crafting.TileInfusionMatrix)
/*  253 */             mod = -1; 
/*  254 */           FXDispatcher.INSTANCE.essentiaTrailFx(fx.end, fx.start.func_177981_b(mod), tickCount, fx.color, 0.1F, fx.ext);
/*  255 */           EssentiaHandler.sourceFX.remove(fxk);
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  260 */       LinkedBlockingQueue<HudHandler.KnowledgeGainTracker> temp = new LinkedBlockingQueue<HudHandler.KnowledgeGainTracker>();
/*  261 */       if (hudHandler.knowledgeGainTrackers.isEmpty()) {
/*  262 */         if (hudHandler.kgFade > 0.0F) {
/*  263 */           hudHandler.kgFade--;
/*      */         }
/*      */       } else {
/*  266 */         hudHandler.kgFade += 10.0F;
/*  267 */         if (hudHandler.kgFade > 40.0F) hudHandler.kgFade = 40.0F; 
/*  268 */         while (!hudHandler.knowledgeGainTrackers.isEmpty()) {
/*  269 */           HudHandler.KnowledgeGainTracker current = (HudHandler.KnowledgeGainTracker)hudHandler.knowledgeGainTrackers.poll();
/*  270 */           if (current != null && 
/*  271 */             current.progress > 0) {
/*  272 */             current.progress--;
/*  273 */             temp.offer(current);
/*      */           } 
/*      */         } 
/*      */         
/*  277 */         while (!temp.isEmpty()) {
/*  278 */           hudHandler.knowledgeGainTrackers.offer(temp.poll());
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  283 */       if (mc.field_71441_e != null && !checkedDate) {
/*  284 */         checkedDate = true;
/*  285 */         Calendar calendar = mc.field_71441_e.func_83015_S();
/*  286 */         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
/*  287 */           ModConfig.isHalloween = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void renderTick(TickEvent.RenderTickEvent event) {
/*  297 */     if (event.phase == TickEvent.Phase.START) {
/*  298 */       UtilsFX.sysPartialTicks = event.renderTickTime;
/*      */     } else {
/*  300 */       Minecraft mc = FMLClientHandler.instance().getClient();
/*      */       
/*  302 */       if (Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer) {
/*  303 */         EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().func_175606_aa();
/*  304 */         long time = System.currentTimeMillis();
/*      */         
/*  306 */         if (player != null) {
/*  307 */           hudHandler.renderHuds(mc, event.renderTickTime, player, time);
/*      */         }
/*      */         
/*  310 */         if ((player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() instanceof IArchitect) || (player.func_184592_cb() != null && player.func_184592_cb().func_77973_b() instanceof IArchitect)) {
/*      */           
/*  312 */           ItemStack stack = (player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() instanceof IArchitect) ? player.func_184614_ca() : player.func_184592_cb();
/*  313 */           if (!((IArchitect)stack.func_77973_b()).useBlockHighlight(stack)) {
/*  314 */             RayTraceResult target2 = ((IArchitect)stack.func_77973_b()).getArchitectMOP(stack, player.field_70170_p, player);
/*  315 */             if (target2 != null) {
/*  316 */               wandHandler.handleArchitectOverlay(stack, player, event.renderTickTime, player.field_70173_aa, target2);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void tooltipEvent(ItemTooltipEvent event) {
/*  329 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*  330 */     GuiScreen gui = mc.field_71462_r;
/*  331 */     if (gui instanceof GuiContainer) if (gui.func_146272_n() != ModConfig.CONFIG_GRAPHICS.showTags && 
/*  332 */         !Mouse.isGrabbed() && event.getItemStack() != null) {
/*  333 */         AspectList tags = ThaumcraftCraftingManager.getObjectTags(event.getItemStack());
/*  334 */         int index = 0;
/*  335 */         if (tags != null && tags.size() > 0)
/*  336 */           for (Aspect tag : tags.getAspects()) {
/*  337 */             if (tag != null)
/*  338 */               index++; 
/*      */           }  
/*  340 */         int width = index * 18;
/*  341 */         if (width > 0) {
/*  342 */           double sw = mc.field_71466_p.func_78256_a(" ");
/*  343 */           int t = MathHelper.func_76143_f(width / sw);
/*  344 */           int l = MathHelper.func_76143_f(18.0D / mc.field_71466_p.field_78288_b);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  349 */           for (int a = 0; a < l; a++) {
/*  350 */             event.getToolTip().add("                                                                                                                                            "
/*  351 */                 .substring(0, Math.min(120, t)));
/*      */           }
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void tooltipEvent(RenderTooltipEvent.PostBackground event) {
/*  360 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*  361 */     GuiScreen gui = mc.field_71462_r;
/*  362 */     if (gui instanceof GuiContainer) if (gui.func_146272_n() != ModConfig.CONFIG_GRAPHICS.showTags && !Mouse.isGrabbed()) {
/*  363 */         int bot = event.getHeight();
/*  364 */         if (!event.getLines().isEmpty()) {
/*  365 */           for (int a = event.getLines().size() - 1; a >= 0; a--) {
/*  366 */             if (event.getLines().get(a) != null && !((String)event.getLines().get(a)).contains("    ")) {
/*  367 */               bot -= 10;
/*      */             }
/*  369 */             else if (a > 0 && event.getLines().get(a - true) != null && ((String)event.getLines().get(a - 1)).contains("    ")) {
/*  370 */               hudHandler.renderAspectsInGui((GuiContainer)gui, mc.field_71439_g, event.getStack(), bot, event
/*  371 */                   .getX(), event.getY());
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void renderOverlay(RenderGameOverlayEvent event) {
/*  386 */     Minecraft mc = Minecraft.func_71410_x();
/*  387 */     long time = System.nanoTime() / 1000000L;
/*  388 */     if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
/*  389 */       wandHandler.handleFociRadial(mc, time, event);
/*      */     }
/*      */     
/*  392 */     if (event.getType() == RenderGameOverlayEvent.ElementType.PORTAL) {
/*  393 */       renderVignette(targetBrightness, event.getResolution().func_78327_c(), event.getResolution().func_78324_d());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void renderShaders(RenderGameOverlayEvent.Pre event) {
/*  402 */     if (!ModConfig.CONFIG_GRAPHICS.disableShaders && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
/*  403 */       Minecraft mc = Minecraft.func_71410_x();
/*  404 */       if (OpenGlHelper.field_148824_g && shaderGroups.size() > 0) {
/*      */ 
/*      */         
/*  407 */         updateShaderFrameBuffers(mc);
/*  408 */         GL11.glMatrixMode(5890);
/*  409 */         GL11.glLoadIdentity();
/*  410 */         for (ShaderGroup sg : shaderGroups.values()) {
/*  411 */           GL11.glPushMatrix();
/*      */           try {
/*  413 */             sg.func_148018_a(event.getPartialTicks());
/*  414 */           } catch (Exception exception) {}
/*  415 */           GL11.glPopMatrix();
/*      */         } 
/*  417 */         mc.func_147110_a().func_147610_a(true);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean resetShaders = false;
/*  423 */   private static int oldDisplayWidth = 0;
/*  424 */   private static int oldDisplayHeight = 0;
/*      */   private static void updateShaderFrameBuffers(Minecraft mc) {
/*  426 */     if (resetShaders || mc.field_71443_c != oldDisplayWidth || oldDisplayHeight != mc.field_71440_d) {
/*  427 */       for (ShaderGroup sg : shaderGroups.values()) {
/*  428 */         sg.func_148026_a(mc.field_71443_c, mc.field_71440_d);
/*      */       }
/*  430 */       oldDisplayWidth = mc.field_71443_c;
/*  431 */       oldDisplayHeight = mc.field_71440_d;
/*  432 */       resetShaders = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void blockHighlight(DrawBlockHighlightEvent event) {
/*  442 */     int ticks = (event.getPlayer()).field_70173_aa;
/*  443 */     RayTraceResult target = event.getTarget();
/*      */     
/*  445 */     if (blockTags.size() > 0) {
/*  446 */       int x = ((Integer)blockTags.get(0)).intValue();
/*  447 */       int y = ((Integer)blockTags.get(1)).intValue();
/*  448 */       int z = ((Integer)blockTags.get(2)).intValue();
/*  449 */       AspectList ot = (AspectList)blockTags.get(3);
/*  450 */       EnumFacing dir = EnumFacing.field_82609_l[((Integer)blockTags.get(4)).intValue()];
/*  451 */       if (x == target.func_178782_a().func_177958_n() && y == target.func_178782_a().func_177956_o() && z == target.func_178782_a().func_177952_p()) {
/*  452 */         if (tagscale < 0.5F) tagscale += 0.031F - tagscale / 10.0F; 
/*  453 */         drawTagsOnContainer((target
/*  454 */             .func_178782_a().func_177958_n() + dir.func_82601_c() / 2.0F), (target
/*  455 */             .func_178782_a().func_177956_o() + dir.func_96559_d() / 2.0F), (target
/*  456 */             .func_178782_a().func_177952_p() + dir.func_82599_e() / 2.0F), ot, 220, dir, event
/*  457 */             .getPartialTicks());
/*      */       } 
/*      */     } 
/*      */     
/*  461 */     if (target != null && target.func_178782_a() != null) {
/*      */       
/*  463 */       TileEntity te = (event.getPlayer()).field_70170_p.func_175625_s(target.func_178782_a());
/*  464 */       if (te != null && te instanceof TileRedstoneRelay) {
/*  465 */         RayTraceResult hit = RayTracer.retraceBlock((event.getPlayer()).field_70170_p, event.getPlayer(), target.func_178782_a());
/*  466 */         if (hit != null) {
/*  467 */           if (hit.subHit == 0) {
/*  468 */             drawTextInAir(target.func_178782_a().func_177958_n(), target.func_178782_a().func_177956_o() + 0.3D, target.func_178782_a().func_177952_p(), event.getPartialTicks(), "Out: " + ((TileRedstoneRelay)te)
/*  469 */                 .getOut());
/*      */           }
/*  471 */           else if (hit.subHit == 1) {
/*  472 */             drawTextInAir(target.func_178782_a().func_177958_n(), target.func_178782_a().func_177956_o() + 0.3D, target.func_178782_a().func_177952_p(), event.getPartialTicks(), "In: " + ((TileRedstoneRelay)te)
/*  473 */                 .getIn());
/*      */           } 
/*      */         }
/*      */       } 
/*  477 */       if (EntityUtils.hasGoggles(event.getPlayer())) {
/*      */         
/*  479 */         float to = 0.0F;
/*  480 */         if (te instanceof IGogglesDisplayExtended) {
/*  481 */           GL11.glDisable(2929);
/*  482 */           Vec3d v = ((IGogglesDisplayExtended)te).getIGogglesTextOffset();
/*  483 */           String[] sa = ((IGogglesDisplayExtended)te).getIGogglesText();
/*  484 */           for (String s : sa) {
/*  485 */             drawTextInAir(target
/*  486 */                 .func_178782_a().func_177958_n() + v.field_72450_a, target
/*  487 */                 .func_178782_a().func_177956_o() + v.field_72448_b - ((to - sa.length / 2.0F) / 5.5F), target
/*  488 */                 .func_178782_a().func_177952_p() + v.field_72449_c, event.getPartialTicks(), s);
/*  489 */             to++;
/*      */           } 
/*  491 */           GL11.glEnable(2929);
/*      */         } else {
/*  493 */           Block b = (event.getPlayer()).field_70170_p.func_180495_p(target.func_178782_a()).func_177230_c();
/*  494 */           if (b instanceof IGogglesDisplayExtended) {
/*  495 */             GL11.glDisable(2929);
/*  496 */             Vec3d v = ((IGogglesDisplayExtended)b).getIGogglesTextOffset();
/*  497 */             String[] sa = ((IGogglesDisplayExtended)b).getIGogglesText();
/*  498 */             for (String s : sa) {
/*  499 */               drawTextInAir(target
/*  500 */                   .func_178782_a().func_177958_n() + v.field_72450_a, target
/*  501 */                   .func_178782_a().func_177956_o() + v.field_72448_b + ((to - sa.length / 2.0F) / 5.5F), target
/*  502 */                   .func_178782_a().func_177952_p() + v.field_72449_c, event.getPartialTicks(), s);
/*  503 */               to++;
/*      */             } 
/*  505 */             GL11.glEnable(2929);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  510 */         boolean spaceAbove = (event.getPlayer()).field_70170_p.func_175623_d(target.func_178782_a().func_177984_a());
/*  511 */         if (te != null) {
/*  512 */           int note = -1;
/*  513 */           if (te instanceof TileEntityNote) {
/*  514 */             note = ((TileEntityNote)te).field_145879_a;
/*      */           }
/*  516 */           else if (te instanceof TileArcaneEar) {
/*  517 */             note = ((TileArcaneEar)te).note;
/*      */           }
/*  519 */           else if (te instanceof IAspectContainer && ((IAspectContainer)te).getAspects() != null && ((IAspectContainer)te)
/*  520 */             .getAspects().size() > 0) {
/*  521 */             float shift = 0.0F;
/*      */             
/*  523 */             if (tagscale < 0.3F) tagscale += 0.031F - tagscale / 10.0F; 
/*  524 */             drawTagsOnContainer(target.func_178782_a().func_177958_n(), (target.func_178782_a().func_177956_o() + (spaceAbove ? 0.4F : 0.0F) + shift), target.func_178782_a().func_177952_p(), ((IAspectContainer)te)
/*  525 */                 .getAspects(), 220, spaceAbove ? EnumFacing.UP : 
/*  526 */                 (event.getTarget()).field_178784_b, event.getPartialTicks());
/*      */           } 
/*      */           
/*  529 */           if (note >= 0) {
/*  530 */             if (ticks % 5 == 0)
/*  531 */               PacketHandler.INSTANCE.sendToServer(new PacketNote(target
/*  532 */                     .func_178782_a().func_177958_n(), target.func_178782_a().func_177956_o(), target.func_178782_a().func_177952_p(), 
/*  533 */                     (event.getPlayer()).field_70170_p.field_73011_w.getDimension())); 
/*  534 */             drawTextInAir(target.func_178782_a().func_177958_n(), (target.func_178782_a().func_177956_o() + 1), target.func_178782_a().func_177952_p(), event.getPartialTicks(), "Note: " + note);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  540 */     if (target.field_72313_a == RayTraceResult.Type.BLOCK && ((event
/*  541 */       .getPlayer().func_184614_ca() != null && event.getPlayer().func_184614_ca().func_77973_b() instanceof IArchitect) || (event
/*  542 */       .getPlayer().func_184592_cb() != null && event.getPlayer().func_184592_cb().func_77973_b() instanceof IArchitect))) {
/*      */       
/*  544 */       ItemStack stack = (event.getPlayer().func_184614_ca() != null && event.getPlayer().func_184614_ca().func_77973_b() instanceof IArchitect) ? event.getPlayer().func_184614_ca() : event.getPlayer().func_184592_cb();
/*  545 */       if (((IArchitect)stack.func_77973_b()).useBlockHighlight(stack)) {
/*  546 */         RayTraceResult target2 = ((IArchitect)stack.func_77973_b()).getArchitectMOP(stack, (event.getPlayer()).field_70170_p, event.getPlayer());
/*  547 */         if (target2 != null && wandHandler.handleArchitectOverlay(stack, event.getPlayer(), event.getPartialTicks(), (event.getPlayer()).field_70173_aa, target2)) {
/*  548 */           event.setCanceled(true);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void renderLast(RenderWorldLastEvent event) {
/*  561 */     if (tagscale > 0.0F) tagscale -= 0.005F; 
/*  562 */     float partialTicks = event.getPartialTicks();
/*  563 */     Minecraft mc = Minecraft.func_71410_x();
/*      */     
/*  565 */     if (Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer) {
/*  566 */       EntityPlayer player = (EntityPlayer)mc.func_175606_aa();
/*      */       
/*  568 */       if (player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() instanceof thaumcraft.api.golems.ISealDisplayer) {
/*  569 */         drawSeals(partialTicks, player);
/*      */       }
/*  571 */       else if (player.func_184592_cb() != null && player.func_184592_cb().func_77973_b() instanceof thaumcraft.api.golems.ISealDisplayer) {
/*  572 */         drawSeals(partialTicks, player);
/*      */       } 
/*      */       
/*  575 */       if (player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() instanceof IArchitect) {
/*      */         
/*  577 */         RayTraceResult target = ((IArchitect)player.func_184614_ca().func_77973_b()).getArchitectMOP(player.func_184614_ca(), player.field_70170_p, player);
/*  578 */         wandHandler.handleArchitectOverlay(player.func_184614_ca(), player, partialTicks, player.field_70173_aa, target);
/*      */       }
/*  580 */       else if (player.func_184592_cb() != null && player.func_184592_cb().func_77973_b() instanceof IArchitect) {
/*      */         
/*  582 */         RayTraceResult target = ((IArchitect)player.func_184592_cb().func_77973_b()).getArchitectMOP(player.func_184592_cb(), player.field_70170_p, player);
/*  583 */         wandHandler.handleArchitectOverlay(player.func_184592_cb(), player, partialTicks, player.field_70173_aa, target);
/*      */       } 
/*      */       
/*  586 */       if (thaumTarget != null) {
/*  587 */         AspectList ot = AspectHelper.getEntityAspects(thaumTarget);
/*  588 */         if (ot != null && !ot.aspects.isEmpty()) {
/*  589 */           if (tagscale < 0.5F) tagscale += 0.031F - tagscale / 10.0F; 
/*  590 */           double iPX = thaumTarget.field_70169_q + (thaumTarget.field_70165_t - thaumTarget.field_70169_q) * partialTicks;
/*  591 */           double iPY = thaumTarget.field_70167_r + (thaumTarget.field_70163_u - thaumTarget.field_70167_r) * partialTicks;
/*  592 */           double iPZ = thaumTarget.field_70166_s + (thaumTarget.field_70161_v - thaumTarget.field_70166_s) * partialTicks;
/*  593 */           drawTagsOnContainer(iPX, iPY + thaumTarget.field_70131_O, iPZ, ot, 220, null, event
/*      */               
/*  595 */               .getPartialTicks());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*  601 */   public static Entity thaumTarget = null;
/*      */   
/*      */   private static void drawSeals(float partialTicks, EntityPlayer player) {
/*  604 */     ConcurrentHashMap<SealPos, SealEntity> seals = (ConcurrentHashMap)SealHandler.sealEntities.get(Integer.valueOf(player.field_70170_p.field_73011_w.getDimension()));
/*  605 */     if (seals != null && seals.size() > 0) {
/*  606 */       GL11.glPushMatrix();
/*      */       
/*  608 */       if (player.func_70093_af()) GL11.glDisable(2929);
/*      */       
/*  610 */       GL11.glEnable(3042);
/*  611 */       GL11.glBlendFunc(770, 771);
/*      */       
/*  613 */       GL11.glDisable(2884);
/*      */ 
/*      */       
/*  616 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  617 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  618 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*  619 */       GL11.glTranslated(-iPX, -iPY, -iPZ);
/*      */       
/*  621 */       for (ISealEntity seal : seals.values()) {
/*  622 */         double dis = player.func_174831_c((seal.getSealPos()).pos);
/*  623 */         if (dis <= 256.0D) {
/*  624 */           float alpha = 1.0F - (float)(dis / 256.0D);
/*  625 */           boolean ia = false;
/*  626 */           if (seal.isStoppedByRedstone(player.field_70170_p)) {
/*  627 */             ia = true;
/*  628 */             if (player.field_70170_p.field_73012_v.nextFloat() < partialTicks / 12.0F) {
/*  629 */               FXDispatcher.INSTANCE.spark((
/*  630 */                   (seal.getSealPos()).pos.func_177958_n() + 0.5F + (seal.getSealPos()).face.func_82601_c() * 0.66F), (
/*  631 */                   (seal.getSealPos()).pos.func_177956_o() + 0.5F + (seal.getSealPos()).face.func_96559_d() * 0.66F), (
/*  632 */                   (seal.getSealPos()).pos.func_177952_p() + 0.5F + (seal.getSealPos()).face.func_82599_e() * 0.66F), 2.0F, 0.8F - player.field_70170_p.field_73012_v
/*  633 */                   .nextFloat() * 0.2F, 0.0F, 0.0F, 1.0F);
/*  634 */               ia = false;
/*      */             } 
/*      */           } 
/*      */           
/*  638 */           renderSeal((seal.getSealPos()).pos.func_177958_n(), (seal.getSealPos()).pos.func_177956_o(), (seal.getSealPos()).pos.func_177952_p(), alpha, 
/*  639 */               (seal.getSealPos()).face, seal.getSeal().getSealIcon(), ia);
/*      */           
/*  641 */           drawSealArea(player, seal, alpha, partialTicks);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  646 */       GL11.glDisable(3042);
/*  647 */       GL11.glEnable(2884);
/*      */       
/*  649 */       if (player.func_70093_af()) GL11.glEnable(2929);
/*      */       
/*  651 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */   
/*  655 */   static final ResourceLocation CFRAME = new ResourceLocation("thaumcraft", "textures/misc/frame_corner.png");
/*  656 */   static final ResourceLocation MIDDLE = new ResourceLocation("thaumcraft", "textures/misc/seal_area.png");
/*      */ 
/*      */ 
/*      */   
/*      */   private static void drawSealArea(EntityPlayer player, ISealEntity seal, float alpha, float partialTicks) {
/*  661 */     GL11.glPushMatrix();
/*      */     
/*  663 */     float r = 0.0F;
/*  664 */     float g = 0.0F;
/*  665 */     float b = 0.0F;
/*      */     
/*  667 */     if (seal.getColor() > 0) {
/*  668 */       Color c = new Color(EnumDyeColor.func_176764_b(seal.getColor() - 1).func_193350_e());
/*  669 */       r = c.getRed() / 255.0F;
/*  670 */       g = c.getGreen() / 255.0F;
/*  671 */       b = c.getBlue() / 255.0F;
/*      */     } else {
/*  673 */       r = 0.7F + MathHelper.func_76126_a((player.field_70173_aa + partialTicks + (seal.getSealPos()).pos.func_177958_n()) / 4.0F) * 0.1F;
/*  674 */       g = 0.7F + MathHelper.func_76126_a((player.field_70173_aa + partialTicks + (seal.getSealPos()).pos.func_177956_o()) / 5.0F) * 0.1F;
/*  675 */       b = 0.7F + MathHelper.func_76126_a((player.field_70173_aa + partialTicks + (seal.getSealPos()).pos.func_177952_p()) / 6.0F) * 0.1F;
/*      */     } 
/*      */     
/*  678 */     GL11.glPushMatrix();
/*  679 */     GL11.glTranslated((seal.getSealPos()).pos.func_177958_n() + 0.5D, (seal.getSealPos()).pos.func_177956_o() + 0.5D, (seal.getSealPos()).pos.func_177952_p() + 0.5D);
/*  680 */     GL11.glRotatef(90.0F, -(seal.getSealPos()).face.func_96559_d(), (seal.getSealPos()).face.func_82601_c(), -(seal.getSealPos()).face.func_82599_e());
/*  681 */     if ((seal.getSealPos()).face.func_82599_e() < 0) {
/*  682 */       GL11.glTranslated(0.0D, 0.0D, -0.5099999904632568D);
/*      */     } else {
/*  684 */       GL11.glTranslated(0.0D, 0.0D, 0.5099999904632568D);
/*      */     } 
/*  686 */     GL11.glRotatef((player.field_70173_aa % 360) + partialTicks, 0.0F, 0.0F, 1.0F);
/*  687 */     UtilsFX.renderQuadCentered(MIDDLE, 0.9F, r, g, b, 200, 771, alpha * 0.8F);
/*  688 */     GL11.glPopMatrix();
/*      */     
/*  690 */     if (seal.getSeal() instanceof thaumcraft.api.golems.seals.ISealConfigArea) {
/*  691 */       GL11.glDepthMask(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  703 */       AxisAlignedBB area = (new AxisAlignedBB((seal.getSealPos()).pos.func_177958_n(), (seal.getSealPos()).pos.func_177956_o(), (seal.getSealPos()).pos.func_177952_p(), ((seal.getSealPos()).pos.func_177958_n() + 1), ((seal.getSealPos()).pos.func_177956_o() + 1), ((seal.getSealPos()).pos.func_177952_p() + 1))).func_72317_d((seal.getSealPos()).face.func_82601_c(), (seal.getSealPos()).face.func_96559_d(), (seal.getSealPos()).face.func_82599_e()).func_72321_a(((seal.getSealPos()).face.func_82601_c() != 0) ? ((seal.getArea().func_177958_n() - 1) * (seal.getSealPos()).face.func_82601_c()) : 0.0D, ((seal.getSealPos()).face.func_96559_d() != 0) ? ((seal.getArea().func_177956_o() - 1) * (seal.getSealPos()).face.func_96559_d()) : 0.0D, ((seal.getSealPos()).face.func_82599_e() != 0) ? ((seal.getArea().func_177952_p() - 1) * (seal.getSealPos()).face.func_82599_e()) : 0.0D).func_72314_b(
/*  704 */           ((seal.getSealPos()).face.func_82601_c() == 0) ? (seal.getArea().func_177958_n() - 1) : 0.0D, 
/*  705 */           ((seal.getSealPos()).face.func_96559_d() == 0) ? (seal.getArea().func_177956_o() - 1) : 0.0D, 
/*  706 */           ((seal.getSealPos()).face.func_82599_e() == 0) ? (seal.getArea().func_177952_p() - 1) : 0.0D);
/*      */       
/*  708 */       double[][] locs = { { area.field_72340_a, area.field_72338_b, area.field_72339_c }, { area.field_72340_a, area.field_72337_e - 1.0D, area.field_72339_c }, { area.field_72336_d - 1.0D, area.field_72338_b, area.field_72339_c }, { area.field_72336_d - 1.0D, area.field_72337_e - 1.0D, area.field_72339_c }, { area.field_72336_d - 1.0D, area.field_72338_b, area.field_72334_f - 1.0D }, { area.field_72336_d - 1.0D, area.field_72337_e - 1.0D, area.field_72334_f - 1.0D }, { area.field_72340_a, area.field_72338_b, area.field_72334_f - 1.0D }, { area.field_72340_a, area.field_72337_e - 1.0D, area.field_72334_f - 1.0D } };
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  713 */       int q = 0;
/*  714 */       for (double[] loc : locs) {
/*  715 */         GL11.glPushMatrix();
/*  716 */         GL11.glTranslated(loc[0] + 0.5D, loc[1] + 0.5D, loc[2] + 0.5D);
/*  717 */         int w = 0;
/*  718 */         for (EnumFacing face : rotfaces[q]) {
/*  719 */           GL11.glPushMatrix();
/*  720 */           GL11.glRotatef(90.0F, -face.func_96559_d(), face.func_82601_c(), -face.func_82599_e());
/*  721 */           if (face.func_82599_e() < 0) {
/*  722 */             GL11.glTranslated(0.0D, 0.0D, -0.49000000953674316D);
/*      */           } else {
/*  724 */             GL11.glTranslated(0.0D, 0.0D, 0.49000000953674316D);
/*      */           } 
/*  726 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*  727 */           GL11.glRotatef(rotmat[q][w], 0.0F, 0.0F, 1.0F);
/*  728 */           UtilsFX.renderQuadCentered(CFRAME, 1.0F, r, g, b, 200, 771, alpha * 0.7F);
/*  729 */           GL11.glPopMatrix();
/*  730 */           w++;
/*      */         } 
/*  732 */         GL11.glPopMatrix();
/*  733 */         q++;
/*      */       } 
/*  735 */       GL11.glDepthMask(true);
/*      */     } 
/*      */     
/*  738 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*  741 */   static EnumFacing[][] rotfaces = { { EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.WEST }, { EnumFacing.UP, EnumFacing.NORTH, EnumFacing.WEST }, { EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.EAST }, { EnumFacing.UP, EnumFacing.NORTH, EnumFacing.EAST }, { EnumFacing.DOWN, EnumFacing.SOUTH, EnumFacing.EAST }, { EnumFacing.UP, EnumFacing.SOUTH, EnumFacing.EAST }, { EnumFacing.DOWN, EnumFacing.SOUTH, EnumFacing.WEST }, { EnumFacing.UP, EnumFacing.SOUTH, EnumFacing.WEST } };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  746 */   static int[][] rotmat = { { 0, 270, 0 }, { 270, 180, 270 }, { 90, 0, 90 }, { 180, 90, 180 }, { 180, 180, 0 }, { 90, 270, 270 }, { 270, 90, 90 }, { 0, 0, 180 } };
/*      */ 
/*      */ 
/*      */   
/*      */   static void renderSeal(int x, int y, int z, float alpha, EnumFacing face, ResourceLocation resourceLocation, boolean ia) {
/*  751 */     GL11.glPushMatrix();
/*  752 */     GL11.glColor4f(ia ? 0.5F : 1.0F, ia ? 0.5F : 1.0F, ia ? 0.5F : 1.0F, alpha);
/*  753 */     translateSeal(x, y, z, face.ordinal(), -0.05F);
/*  754 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  755 */     UtilsFX.renderItemIn2D(resourceLocation.toString(), Minecraft.func_71410_x().func_175606_aa().func_70093_af() ? 0.0F : 0.1F);
/*  756 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  757 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private static void translateSeal(float x, float y, float z, int orientation, float off) {
/*  761 */     if (orientation == 1) {
/*      */       
/*  763 */       GL11.glTranslatef(x + 0.25F, y + 1.0F, z + 0.75F);
/*  764 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  765 */     } else if (orientation == 0) {
/*      */       
/*  767 */       GL11.glTranslatef(x + 0.25F, y, z + 0.25F);
/*  768 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  769 */     } else if (orientation == 3) {
/*      */       
/*  771 */       GL11.glTranslatef(x + 0.25F, y + 0.25F, z + 1.0F);
/*  772 */     } else if (orientation == 2) {
/*      */       
/*  774 */       GL11.glTranslatef(x + 0.75F, y + 0.25F, z);
/*  775 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  776 */     } else if (orientation == 5) {
/*      */       
/*  778 */       GL11.glTranslatef(x + 1.0F, y + 0.25F, z + 0.75F);
/*  779 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  780 */     } else if (orientation == 4) {
/*      */       
/*  782 */       GL11.glTranslatef(x, y + 0.25F, z + 0.25F);
/*  783 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*      */     } 
/*  785 */     GL11.glTranslatef(0.0F, 0.0F, -off);
/*      */   }
/*      */ 
/*      */   
/*  789 */   public static HashMap<Integer, ShaderGroup> shaderGroups = new HashMap();
/*      */ 
/*      */   
/*      */   public static boolean fogFiddled = false;
/*      */   
/*  794 */   public static float fogTarget = 0.0F;
/*  795 */   public static int fogDuration = 0;
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void fogDensityEvent(EntityViewRenderEvent.RenderFogEvent event) {
/*  800 */     if (fogFiddled && fogTarget > 0.0F) {
/*  801 */       GL11.glFogi(2917, 2048);
/*  802 */       GL11.glFogf(2914, fogTarget);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public static void livingTick(LivingEvent.LivingUpdateEvent event) {
/*  822 */     if ((event.getEntity()).field_70170_p.field_72995_K && event.getEntity() instanceof EntityCreature && !(event.getEntity()).field_70128_L) {
/*  823 */       EntityCreature mob = (EntityCreature)event.getEntity();
/*  824 */       if (mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD) != null) {
/*  825 */         Integer t = Integer.valueOf((int)mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e());
/*  826 */         if (t != null && t.intValue() >= 0 && t.intValue() < ChampionModifier.mods.length) {
/*  827 */           (ChampionModifier.mods[t.intValue()]).effect.showFX(mob);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public static void renderLivingPre(RenderLivingEvent.Pre event) {
/*  837 */     if ((event.getEntity()).field_70170_p.field_72995_K && event.getEntity() instanceof EntityCreature && !(event.getEntity()).field_70128_L) {
/*  838 */       EntityCreature mob = (EntityCreature)event.getEntity();
/*  839 */       if (mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD) != null) {
/*  840 */         Integer t = Integer.valueOf((int)mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e());
/*  841 */         if (t != null && t.intValue() >= 0 && t.intValue() < ChampionModifier.mods.length) {
/*  842 */           (ChampionModifier.mods[t.intValue()]).effect.preRender(mob, event.getRenderer());
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawTagsOnContainer(double x, double y, double z, AspectList tags, int bright, EnumFacing dir, float partialTicks) {
/*  851 */     if (Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer && tags != null && tags.size() > 0) {
/*  852 */       int fox = 0;
/*  853 */       int foy = 0;
/*  854 */       int foz = 0;
/*  855 */       if (dir != null) {
/*  856 */         fox = dir.func_82601_c();
/*  857 */         foy = dir.func_96559_d();
/*  858 */         foz = dir.func_82599_e();
/*      */       } else {
/*  860 */         x -= 0.5D;
/*  861 */         z -= 0.5D;
/*      */       } 
/*  863 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().func_175606_aa();
/*  864 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  865 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  866 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*      */       
/*  868 */       int rowsize = 5;
/*  869 */       int current = 0;
/*  870 */       float shifty = 0.0F;
/*  871 */       int left = tags.size();
/*      */       
/*  873 */       for (Aspect tag : tags.getAspects()) {
/*  874 */         int div = Math.min(left, rowsize);
/*      */         
/*  876 */         if (current >= rowsize) {
/*  877 */           current = 0;
/*  878 */           shifty -= tagscale * 1.05F;
/*  879 */           left -= rowsize;
/*  880 */           if (left < rowsize) {
/*  881 */             div = left % rowsize;
/*      */           }
/*      */         } 
/*      */         
/*  885 */         float shift = (current - div / 2.0F + 0.5F) * tagscale * 4.0F;
/*  886 */         shift *= tagscale;
/*      */         
/*  888 */         Color color = new Color(tag.getColor());
/*      */         
/*  890 */         GL11.glPushMatrix();
/*      */         
/*  892 */         GL11.glDisable(2929);
/*      */ 
/*      */ 
/*      */         
/*  896 */         GL11.glTranslated(-iPX + x + 0.5D + (tagscale * 2.0F * fox), -iPY + y - shifty + 0.5D + (tagscale * 2.0F * foy), -iPZ + z + 0.5D + (tagscale * 2.0F * foz));
/*      */         
/*  898 */         float xd = (float)(iPX - x + 0.5D);
/*  899 */         float zd = (float)(iPZ - z + 0.5D);
/*  900 */         float rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / Math.PI);
/*      */         
/*  902 */         GL11.glRotatef(rotYaw + 180.0F, 0.0F, 1.0F, 0.0F);
/*      */         
/*  904 */         GL11.glTranslated(shift, 0.0D, 0.0D);
/*  905 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  906 */         GL11.glScalef(tagscale, tagscale, tagscale);
/*      */         
/*  908 */         UtilsFX.renderQuadCentered(tag.getImage(), 1.0F, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, bright, 771, 0.75F);
/*      */         
/*  910 */         if (tags.getAmount(tag) >= 0) {
/*  911 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  912 */           String am = "" + tags.getAmount(tag);
/*  913 */           GL11.glScalef(0.04F, 0.04F, 0.04F);
/*  914 */           GL11.glTranslated(0.0D, 6.0D, -0.1D);
/*  915 */           int sw = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(am);
/*  916 */           GL11.glEnable(3042);
/*  917 */           GL11.glBlendFunc(770, 771);
/*  918 */           (Minecraft.func_71410_x()).field_71466_p.func_78276_b(am, 14 - sw, 1, 1118481);
/*  919 */           GL11.glTranslated(0.0D, 0.0D, -0.1D);
/*  920 */           (Minecraft.func_71410_x()).field_71466_p.func_78276_b(am, 13 - sw, 0, 16777215);
/*      */         } 
/*  922 */         GL11.glEnable(2929);
/*  923 */         GL11.glPopMatrix();
/*  924 */         current++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawTextInAir(double x, double y, double z, float partialTicks, String text) {
/*  930 */     if (Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer) {
/*  931 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().func_175606_aa();
/*  932 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  933 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  934 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*  935 */       GL11.glPushMatrix();
/*  936 */       GL11.glTranslated(-iPX + x + 0.5D, -iPY + y + 0.5D, -iPZ + z + 0.5D);
/*  937 */       float xd = (float)(iPX - x + 0.5D);
/*  938 */       float zd = (float)(iPZ - z + 0.5D);
/*  939 */       float rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / Math.PI);
/*  940 */       GL11.glRotatef(rotYaw + 180.0F, 0.0F, 1.0F, 0.0F);
/*  941 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  942 */       GL11.glScalef(0.0125F, 0.0125F, 0.0125F);
/*  943 */       int sw = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(text);
/*  944 */       GL11.glEnable(3042);
/*  945 */       GL11.glBlendFunc(770, 771);
/*  946 */       (Minecraft.func_71410_x()).field_71466_p.func_175065_a(text, (1 - sw / 2), 1.0F, 16777215, true);
/*      */ 
/*      */ 
/*      */       
/*  950 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1353 */   public static float prevVignetteBrightness = 0.0F;
/* 1354 */   public static float targetBrightness = 1.0F;
/* 1355 */   protected static final ResourceLocation vignetteTexPath = new ResourceLocation("thaumcraft", "textures/misc/vignette.png");
/*      */ 
/*      */   
/*      */   protected static void renderVignette(float brightness, double sw, double sh) {
/* 1359 */     int k = (int)sw;
/* 1360 */     int l = (int)sh;
/*      */     
/* 1362 */     brightness = 1.0F - brightness;
/* 1363 */     prevVignetteBrightness = (float)(prevVignetteBrightness + (brightness - prevVignetteBrightness) * 0.01D);
/*      */     
/* 1365 */     if (prevVignetteBrightness > 0.0F) {
/* 1366 */       float b = prevVignetteBrightness * (1.0F + MathHelper.func_76126_a(
/* 1367 */           (Minecraft.func_71410_x()).field_71439_g.field_70173_aa / 2.0F) * 0.1F);
/* 1368 */       GL11.glPushMatrix();
/* 1369 */       GL11.glClear(256);
/* 1370 */       GL11.glMatrixMode(5889);
/* 1371 */       GL11.glLoadIdentity();
/* 1372 */       GL11.glOrtho(0.0D, sw, sh, 0.0D, 1000.0D, 3000.0D);
/*      */       
/* 1374 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(vignetteTexPath);
/*      */       
/* 1376 */       GL11.glMatrixMode(5888);
/* 1377 */       GL11.glLoadIdentity();
/* 1378 */       GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 1379 */       GL11.glDisable(2929);
/* 1380 */       GL11.glDepthMask(false);
/* 1381 */       OpenGlHelper.func_148821_a(0, 769, 1, 0);
/* 1382 */       GL11.glColor4f(b, b, b, 1.0F);
/*      */       
/* 1384 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 1385 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1386 */       tessellator.func_178180_c().func_181662_b(0.0D, l, -90.0D).func_187315_a(0.0D, 1.0D).func_181675_d();
/* 1387 */       tessellator.func_178180_c().func_181662_b(k, l, -90.0D).func_187315_a(1.0D, 1.0D).func_181675_d();
/* 1388 */       tessellator.func_178180_c().func_181662_b(k, 0.0D, -90.0D).func_187315_a(1.0D, 0.0D).func_181675_d();
/* 1389 */       tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, -90.0D).func_187315_a(0.0D, 0.0D).func_181675_d();
/* 1390 */       tessellator.func_78381_a();
/* 1391 */       GL11.glDepthMask(true);
/* 1392 */       GL11.glEnable(2929);
/* 1393 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1394 */       OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*      */       
/* 1396 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   @SubscribeEvent
/*      */   public static void textureStitchEventPre(TextureStitchEvent.Pre event) {
/* 1404 */     event.getMap().func_174942_a(new ResourceLocation("thaumcraft", "research/quill"));
/* 1405 */     event.getMap().func_174942_a(new ResourceLocation("thaumcraft", "blocks/crystal"));
/* 1406 */     event.getMap().func_174942_a(new ResourceLocation("thaumcraft", "blocks/taint_growth_1"));
/* 1407 */     event.getMap().func_174942_a(new ResourceLocation("thaumcraft", "blocks/taint_growth_2"));
/* 1408 */     event.getMap().func_174942_a(new ResourceLocation("thaumcraft", "blocks/taint_growth_3"));
/* 1409 */     event.getMap().func_174942_a(new ResourceLocation("thaumcraft", "blocks/taint_growth_4"));
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\events\RenderEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */