/*     */ package thaumcraft.client.lib.events;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.casters.ICaster;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.items.casters.ItemFocus;
/*     */ import thaumcraft.common.items.casters.ItemFocusPouch;
/*     */ import thaumcraft.common.lib.events.KeyHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class WandRenderingHandler
/*     */ {
/*  42 */   static float radialHudScale = 0.0F;
/*  43 */   TreeMap<String, Integer> foci = new TreeMap();
/*  44 */   HashMap<String, ItemStack> fociItem = new HashMap();
/*  45 */   HashMap<String, Boolean> fociHover = new HashMap();
/*  46 */   HashMap<String, Float> fociScale = new HashMap();
/*  47 */   long lastTime = 0L;
/*     */   boolean lastState = false;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleFociRadial(Minecraft mc, long time, RenderGameOverlayEvent event) {
/*  52 */     if (KeyHandler.radialActive || radialHudScale > 0.0F) {
/*  53 */       if (KeyHandler.radialActive) {
/*  54 */         if (mc.field_71462_r != null) {
/*  55 */           KeyHandler.radialActive = false;
/*  56 */           KeyHandler.radialLock = true;
/*  57 */           mc.func_71381_h();
/*  58 */           mc.func_71364_i();
/*     */           return;
/*     */         } 
/*  61 */         if (radialHudScale == 0.0F) {
/*  62 */           getFociInfo(mc);
/*  63 */           if (this.foci.size() > 0 && 
/*  64 */             mc.field_71415_G)
/*     */           {
/*  66 */             mc.field_71415_G = false;
/*  67 */             mc.field_71417_B.func_74373_b();
/*     */           }
/*     */         
/*     */         }
/*     */       
/*  72 */       } else if (mc.field_71462_r == null && 
/*  73 */         this.lastState) {
/*     */         
/*  75 */         if (Display.isActive())
/*     */         {
/*  77 */           if (!mc.field_71415_G) {
/*     */             
/*  79 */             mc.field_71415_G = true;
/*  80 */             mc.field_71417_B.func_74372_a();
/*     */           } 
/*     */         }
/*  83 */         this.lastState = false;
/*     */       } 
/*     */ 
/*     */       
/*  87 */       renderFocusRadialHUD(event.getResolution().func_78327_c(), event.getResolution().func_78324_d(), time, event.getPartialTicks());
/*     */       
/*  89 */       if (time > this.lastTime) {
/*  90 */         for (String key : this.fociHover.keySet()) {
/*  91 */           if (((Boolean)this.fociHover.get(key)).booleanValue()) {
/*  92 */             if (!KeyHandler.radialActive && !KeyHandler.radialLock) {
/*  93 */               PacketHandler.INSTANCE.sendToServer(new PacketFocusChangeToServer(key));
/*  94 */               KeyHandler.radialLock = true;
/*     */             } 
/*  96 */             if (((Float)this.fociScale.get(key)).floatValue() < 1.3F)
/*  97 */               this.fociScale.put(key, Float.valueOf(Math.min(1.3F, ((Float)this.fociScale.get(key)).floatValue() + getRadialChange(time, this.lastTime, 150L)))); 
/*     */             continue;
/*     */           } 
/* 100 */           if (((Float)this.fociScale.get(key)).floatValue() > 1.0F) {
/* 101 */             this.fociScale.put(key, Float.valueOf(Math.max(1.0F, ((Float)this.fociScale.get(key)).floatValue() - getRadialChange(time, this.lastTime, 250L))));
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 106 */         if (!KeyHandler.radialActive)
/* 107 */         { radialHudScale -= getRadialChange(time, this.lastTime, 150L); }
/*     */         
/* 109 */         else if (KeyHandler.radialActive && radialHudScale < 1.0F) { radialHudScale += getRadialChange(time, this.lastTime, 150L); }
/*     */         
/* 111 */         if (radialHudScale > 1.0F) radialHudScale = 1.0F; 
/* 112 */         if (radialHudScale < 0.0F) {
/* 113 */           radialHudScale = 0.0F;
/* 114 */           KeyHandler.radialLock = false;
/*     */         } 
/*     */         
/* 117 */         this.lastState = KeyHandler.radialActive;
/*     */       } 
/*     */     } 
/* 120 */     this.lastTime = time;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 125 */   private float getRadialChange(long time, long lasttime, long total) { return (float)(time - lasttime) / (float)total; }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void getFociInfo(Minecraft mc) {
/* 131 */     this.foci.clear();
/* 132 */     this.fociItem.clear();
/* 133 */     this.fociHover.clear();
/* 134 */     this.fociScale.clear();
/*     */     
/* 136 */     int pouchcount = 0;
/* 137 */     ItemStack item = null;
/*     */     
/* 139 */     IInventory baubles = BaublesApi.getBaubles(mc.field_71439_g);
/* 140 */     for (int a = 0; a < baubles.func_70302_i_(); a++) {
/* 141 */       if (baubles.func_70301_a(a) != null && !baubles.func_70301_a(a).func_190926_b() && baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch) {
/* 142 */         pouchcount++;
/* 143 */         item = baubles.func_70301_a(a);
/* 144 */         NonNullList<ItemStack> inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 145 */         for (int q = 0; q < inv.size(); q++) {
/* 146 */           item = (ItemStack)inv.get(q);
/* 147 */           if (item.func_77973_b() instanceof ItemFocus) {
/* 148 */             String sh = ((ItemFocus)item.func_77973_b()).getSortingHelper(item);
/* 149 */             if (sh != null) {
/* 150 */               this.foci.put(sh, Integer.valueOf(q + pouchcount * 1000));
/* 151 */               this.fociItem.put(sh, item.func_77946_l());
/* 152 */               this.fociScale.put(sh, Float.valueOf(1.0F));
/* 153 */               this.fociHover.put(sh, Boolean.valueOf(false));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     for (int a = 0; a < 36; a++) {
/* 160 */       item = (ItemStack)mc.field_71439_g.field_71071_by.field_70462_a.get(a);
/* 161 */       if (item.func_77973_b() instanceof ItemFocus) {
/* 162 */         String sh = ((ItemFocus)item.func_77973_b()).getSortingHelper(item);
/* 163 */         if (sh == null)
/* 164 */           continue;  this.foci.put(sh, Integer.valueOf(a));
/* 165 */         this.fociItem.put(sh, item.func_77946_l());
/* 166 */         this.fociScale.put(sh, Float.valueOf(1.0F));
/* 167 */         this.fociHover.put(sh, Boolean.valueOf(false));
/*     */       } 
/* 169 */       if (item.func_77973_b() instanceof ItemFocusPouch) {
/* 170 */         pouchcount++;
/* 171 */         NonNullList<ItemStack> inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 172 */         for (int q = 0; q < inv.size(); q++) {
/* 173 */           item = (ItemStack)inv.get(q);
/* 174 */           if (item.func_77973_b() instanceof ItemFocus) {
/* 175 */             String sh = ((ItemFocus)item.func_77973_b()).getSortingHelper(item);
/* 176 */             if (sh != null) {
/* 177 */               this.foci.put(sh, Integer.valueOf(q + pouchcount * 1000));
/* 178 */               this.fociItem.put(sh, item.func_77946_l());
/* 179 */               this.fociScale.put(sh, Float.valueOf(1.0F));
/* 180 */               this.fociHover.put(sh, Boolean.valueOf(false));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       continue;
/*     */     } 
/*     */   }
/* 188 */   final ResourceLocation R1 = new ResourceLocation("thaumcraft", "textures/misc/radial.png");
/* 189 */   final ResourceLocation R2 = new ResourceLocation("thaumcraft", "textures/misc/radial2.png");
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void renderFocusRadialHUD(double sw, double sh, long time, float partialTicks) {
/* 193 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 195 */     ItemStack s = mc.field_71439_g.func_184614_ca();
/* 196 */     if (!(s.func_77973_b() instanceof ICaster)) {
/* 197 */       s = mc.field_71439_g.func_184592_cb();
/*     */     }
/* 199 */     if (!(s.func_77973_b() instanceof ICaster))
/*     */       return; 
/* 201 */     ICaster wand = (ICaster)s.func_77973_b();
/* 202 */     ItemFocus focus = (ItemFocus)wand.getFocus(s);
/*     */     
/* 204 */     int i = (int)(Mouse.getEventX() * sw / mc.field_71443_c);
/* 205 */     int j = (int)(sh - Mouse.getEventY() * sh / mc.field_71440_d - 1.0D);
/* 206 */     int k = Mouse.getEventButton();
/*     */     
/* 208 */     if (this.fociItem.size() == 0)
/*     */       return; 
/* 210 */     GL11.glPushMatrix();
/* 211 */     GL11.glClear(256);
/* 212 */     GL11.glMatrixMode(5889);
/* 213 */     GL11.glLoadIdentity();
/* 214 */     GL11.glOrtho(0.0D, sw, sh, 0.0D, 1000.0D, 3000.0D);
/* 215 */     GL11.glMatrixMode(5888);
/* 216 */     GL11.glLoadIdentity();
/* 217 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 218 */     GL11.glDisable(2929);
/* 219 */     GL11.glDepthMask(false);
/*     */     
/* 221 */     GL11.glPushMatrix();
/*     */     
/* 223 */     GL11.glTranslated((int)(sw / 2.0D), (int)(sh / 2.0D), 0.0D);
/*     */     
/* 225 */     ItemStack tt = null;
/*     */     
/* 227 */     float width = 16.0F + this.fociItem.size() * 2.5F;
/*     */     
/* 229 */     mc.field_71446_o.func_110577_a(this.R1);
/* 230 */     GL11.glPushMatrix();
/* 231 */     GL11.glRotatef(partialTicks + (mc.field_71439_g.field_70173_aa % 720) / 2.0F, 0.0F, 0.0F, 1.0F);
/* 232 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 233 */     GL11.glEnable(3042);
/* 234 */     GL11.glBlendFunc(770, 771);
/* 235 */     UtilsFX.renderQuadCentered(1, 1, 0, width * 2.75F * radialHudScale, 0.5F, 0.5F, 0.5F, 200, 771, 0.5F);
/*     */     
/* 237 */     GL11.glDisable(3042);
/* 238 */     GL11.glAlphaFunc(516, 0.1F);
/* 239 */     GL11.glPopMatrix();
/*     */     
/* 241 */     mc.field_71446_o.func_110577_a(this.R2);
/* 242 */     GL11.glPushMatrix();
/* 243 */     GL11.glRotatef(-(partialTicks + (mc.field_71439_g.field_70173_aa % 720) / 2.0F), 0.0F, 0.0F, 1.0F);
/* 244 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 245 */     GL11.glEnable(3042);
/* 246 */     GL11.glBlendFunc(770, 771);
/* 247 */     UtilsFX.renderQuadCentered(1, 1, 0, width * 2.55F * radialHudScale, 0.5F, 0.5F, 0.5F, 200, 771, 0.5F);
/*     */     
/* 249 */     GL11.glDisable(3042);
/* 250 */     GL11.glAlphaFunc(516, 0.1F);
/* 251 */     GL11.glPopMatrix();
/*     */     
/* 253 */     if (focus != null) {
/* 254 */       ItemStack item = wand.getFocusStack(s).func_77946_l();
/* 255 */       UtilsFX.renderItemInGUI(-8, -8, 100, item);
/*     */       
/* 257 */       int mx = (int)(i - sw / 2.0D);
/* 258 */       int my = (int)(j - sh / 2.0D);
/*     */       
/* 260 */       if (mx >= -10 && mx <= 10 && my >= -10 && my <= 10) {
/* 261 */         tt = item;
/*     */       }
/*     */     } 
/*     */     
/* 265 */     GL11.glScaled(radialHudScale, radialHudScale, radialHudScale);
/*     */     
/* 267 */     float currentRot = -90.0F * radialHudScale;
/* 268 */     float pieSlice = 360.0F / this.fociItem.size();
/* 269 */     String key = (String)this.foci.firstKey();
/*     */     
/* 271 */     for (int a = 0; a < this.fociItem.size(); a++) {
/*     */       
/* 273 */       double xx = (MathHelper.func_76134_b(currentRot / 180.0F * 3.1415927F) * width);
/* 274 */       double yy = (MathHelper.func_76126_a(currentRot / 180.0F * 3.1415927F) * width);
/* 275 */       currentRot += pieSlice;
/*     */       
/* 277 */       GL11.glPushMatrix();
/* 278 */       GL11.glTranslated((int)xx, (int)yy, 100.0D);
/*     */       
/* 280 */       GL11.glScalef(((Float)this.fociScale.get(key)).floatValue(), ((Float)this.fociScale.get(key)).floatValue(), ((Float)this.fociScale.get(key)).floatValue());
/* 281 */       GL11.glEnable(32826);
/* 282 */       ItemStack item = ((ItemStack)this.fociItem.get(key)).func_77946_l();
/* 283 */       UtilsFX.renderItemInGUI(-8, -8, 100, item);
/* 284 */       GL11.glDisable(32826);
/* 285 */       GL11.glPopMatrix();
/*     */       
/* 287 */       if (!KeyHandler.radialLock && KeyHandler.radialActive) {
/* 288 */         int mx = (int)(i - sw / 2.0D - xx);
/* 289 */         int my = (int)(j - sh / 2.0D - yy);
/*     */         
/* 291 */         if (mx >= -10 && mx <= 10 && my >= -10 && my <= 10) {
/* 292 */           this.fociHover.put(key, Boolean.valueOf(true));
/*     */           
/* 294 */           tt = (ItemStack)this.fociItem.get(key);
/*     */           
/* 296 */           if (k == 0) {
/* 297 */             KeyHandler.radialActive = false;
/* 298 */             KeyHandler.radialLock = true;
/* 299 */             PacketHandler.INSTANCE.sendToServer(new PacketFocusChangeToServer(key));
/*     */             break;
/*     */           } 
/*     */         } else {
/* 303 */           this.fociHover.put(key, Boolean.valueOf(false));
/*     */         } 
/*     */       } 
/*     */       
/* 307 */       key = (String)this.foci.higherKey(key);
/*     */     } 
/*     */     
/* 310 */     GL11.glPopMatrix();
/*     */     
/* 312 */     if (tt != null) {
/* 313 */       UtilsFX.drawCustomTooltip(mc.field_71462_r, mc.field_71466_p, tt
/* 314 */           .func_82840_a(mc.field_71439_g, (Minecraft.func_71410_x()).field_71474_y.field_82882_x ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL), -4, 20, 11);
/*     */     }
/* 316 */     GL11.glDepthMask(true);
/* 317 */     GL11.glEnable(2929);
/* 318 */     GL11.glDisable(3042);
/* 319 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */     
/* 322 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/* 326 */   int lastArcHash = 0;
/* 327 */   ArrayList<BlockPos> architectBlocks = new ArrayList();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean handleArchitectOverlay(ItemStack stack, EntityPlayer player, float partialTicks, int playerticks, RayTraceResult target) {
/* 331 */     if (target == null) return false; 
/* 332 */     Minecraft mc = Minecraft.func_71410_x();
/* 333 */     IArchitect af = (IArchitect)stack.func_77973_b();
/*     */     
/* 335 */     String h = target.func_178782_a().func_177958_n() + "" + target.func_178782_a().func_177956_o() + "" + target.func_178782_a().func_177952_p() + "" + target.field_178784_b + "" + (playerticks / 5);
/* 336 */     int hc = h.hashCode();
/* 337 */     if (hc != this.lastArcHash) {
/*     */       
/* 339 */       this.lastArcHash = hc;
/* 340 */       this.bmCache.clear();
/* 341 */       this.architectBlocks = af.getArchitectBlocks(stack, mc.field_71441_e, target.func_178782_a(), target.field_178784_b, player);
/*     */     } 
/*     */     
/* 344 */     if (this.architectBlocks == null || this.architectBlocks.size() == 0) return false;
/*     */     
/* 346 */     drawArchitectAxis(target.func_178782_a(), partialTicks, af
/* 347 */         .showAxis(stack, mc.field_71441_e, player, target.field_178784_b, IArchitect.EnumAxis.X), af
/* 348 */         .showAxis(stack, mc.field_71441_e, player, target.field_178784_b, IArchitect.EnumAxis.Y), af
/* 349 */         .showAxis(stack, mc.field_71441_e, player, target.field_178784_b, IArchitect.EnumAxis.Z));
/*     */     
/* 351 */     for (BlockPos cc : this.architectBlocks) {
/* 352 */       drawOverlayBlock(cc, playerticks, mc, partialTicks);
/*     */     }
/*     */     
/* 355 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 356 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isConnectedBlock(World world, BlockPos pos) {
/* 360 */     if (this.architectBlocks.contains(pos)) {
/* 361 */       return true;
/*     */     }
/* 363 */     return false;
/*     */   }
/*     */   
/* 366 */   HashMap<BlockPos, boolean[]> bmCache = new HashMap();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private boolean[] getConnectedSides(World world, BlockPos pos) {
/* 370 */     if (this.bmCache.containsKey(pos)) return (boolean[])this.bmCache.get(pos); 
/* 371 */     boolean[] bitMatrix = new boolean[8];
/*     */     
/* 373 */     bitMatrix[0] = (!isConnectedBlock(world, pos.func_177982_a(-1, 0, 0)) && 
/* 374 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, -1)) && 
/* 375 */       !isConnectedBlock(world, pos.func_177982_a(0, 1, 0)));
/* 376 */     bitMatrix[1] = (!isConnectedBlock(world, pos.func_177982_a(1, 0, 0)) && 
/* 377 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, -1)) && 
/* 378 */       !isConnectedBlock(world, pos.func_177982_a(0, 1, 0)));
/* 379 */     bitMatrix[2] = (!isConnectedBlock(world, pos.func_177982_a(-1, 0, 0)) && 
/* 380 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, 1)) && 
/* 381 */       !isConnectedBlock(world, pos.func_177982_a(0, 1, 0)));
/* 382 */     bitMatrix[3] = (!isConnectedBlock(world, pos.func_177982_a(1, 0, 0)) && 
/* 383 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, 1)) && 
/* 384 */       !isConnectedBlock(world, pos.func_177982_a(0, 1, 0)));
/*     */     
/* 386 */     bitMatrix[4] = (!isConnectedBlock(world, pos.func_177982_a(-1, 0, 0)) && 
/* 387 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, -1)) && 
/* 388 */       !isConnectedBlock(world, pos.func_177982_a(0, -1, 0)));
/* 389 */     bitMatrix[5] = (!isConnectedBlock(world, pos.func_177982_a(1, 0, 0)) && 
/* 390 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, -1)) && 
/* 391 */       !isConnectedBlock(world, pos.func_177982_a(0, -1, 0)));
/* 392 */     bitMatrix[6] = (!isConnectedBlock(world, pos.func_177982_a(-1, 0, 0)) && 
/* 393 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, 1)) && 
/* 394 */       !isConnectedBlock(world, pos.func_177982_a(0, -1, 0)));
/* 395 */     bitMatrix[7] = (!isConnectedBlock(world, pos.func_177982_a(1, 0, 0)) && 
/* 396 */       !isConnectedBlock(world, pos.func_177982_a(0, 0, 1)) && 
/* 397 */       !isConnectedBlock(world, pos.func_177982_a(0, -1, 0)));
/*     */     
/* 399 */     this.bmCache.put(pos, bitMatrix);
/* 400 */     return bitMatrix;
/*     */   }
/*     */   
/* 403 */   final ResourceLocation CFRAME = new ResourceLocation("thaumcraft", "textures/misc/frame_corner.png");
/* 404 */   final ResourceLocation SFRAME = new ResourceLocation("thaumcraft", "textures/misc/frame_side.png");
/*     */   
/* 406 */   int[][] mos = { { 4, 5, 6, 7 }, { 0, 1, 2, 3 }, { 0, 1, 4, 5 }, { 2, 3, 6, 7 }, { 0, 2, 4, 6 }, { 1, 3, 5, 7 } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 411 */   int[][] rotmat = { { 0, 90, 270, 180 }, { 270, 180, 0, 90 }, { 180, 90, 270, 0 }, { 0, 270, 90, 180 }, { 270, 180, 0, 90 }, { 180, 270, 90, 0 } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void drawOverlayBlock(BlockPos pos, int ticks, Minecraft mc, float partialTicks) {
/* 418 */     boolean[] bitMatrix = getConnectedSides(mc.field_71441_e, pos);
/*     */     
/* 420 */     GL11.glPushMatrix();
/* 421 */     GlStateManager.func_179112_b(770, 771);
/* 422 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 423 */     GL11.glDepthMask(false);
/* 424 */     GL11.glDisable(2929);
/* 425 */     GL11.glDisable(2884);
/*     */     
/* 427 */     EntityPlayer player = (EntityPlayer)mc.func_175606_aa();
/* 428 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 429 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 430 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 432 */     GL11.glTranslated(-iPX + pos.func_177958_n() + 0.5D, -iPY + pos.func_177956_o() + 0.5D, -iPZ + pos.func_177952_p() + 0.5D);
/*     */ 
/*     */ 
/*     */     
/* 436 */     for (EnumFacing face : EnumFacing.values()) {
/* 437 */       if (!isConnectedBlock(mc.field_71441_e, pos.func_177972_a(face))) {
/* 438 */         GL11.glPushMatrix();
/* 439 */         GL11.glRotatef(90.0F, -face.func_96559_d(), face.func_82601_c(), -face.func_82599_e());
/* 440 */         if (face.func_82599_e() < 0) {
/* 441 */           GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */         } else {
/* 443 */           GL11.glTranslated(0.0D, 0.0D, 0.5D);
/*     */         } 
/* 445 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*     */         
/* 447 */         GL11.glPushMatrix();
/* 448 */         UtilsFX.renderQuadCentered(this.SFRAME, 1, 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, 200, 1, 0.1F);
/* 449 */         GL11.glPopMatrix();
/*     */         
/* 451 */         for (int a = 0; a < 4; a++) {
/* 452 */           if (bitMatrix[this.mos[face.ordinal()][a]]) {
/* 453 */             GL11.glPushMatrix();
/* 454 */             GL11.glRotatef(this.rotmat[face.ordinal()][a], 0.0F, 0.0F, 1.0F);
/* 455 */             UtilsFX.renderQuadCentered(this.CFRAME, 1, 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, 200, 1, 0.66F);
/* 456 */             GL11.glPopMatrix();
/*     */           } 
/*     */         } 
/* 459 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 465 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 467 */     GL11.glEnable(2884);
/* 468 */     GL11.glEnable(2929);
/* 469 */     GL11.glDepthMask(true);
/* 470 */     GlStateManager.func_179084_k();
/* 471 */     GlStateManager.func_179092_a(516, 0.1F);
/* 472 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/* 475 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/misc/architect_arrows.png");
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void drawArchitectAxis(BlockPos pos, float partialTicks, boolean dx, boolean dy, boolean dz) {
/* 478 */     if (!dx && !dy && !dz)
/* 479 */       return;  EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().func_175606_aa();
/* 480 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 481 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 482 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 484 */     float r = MathHelper.func_76126_a(player.field_70173_aa / 4.0F + pos.func_177958_n()) * 0.2F + 0.3F;
/* 485 */     float g = MathHelper.func_76126_a(player.field_70173_aa / 3.0F + pos.func_177956_o()) * 0.2F + 0.3F;
/* 486 */     float b = MathHelper.func_76126_a(player.field_70173_aa / 2.0F + pos.func_177952_p()) * 0.2F + 0.8F;
/* 487 */     GL11.glPushMatrix();
/* 488 */     GL11.glDepthMask(false);
/* 489 */     GL11.glDisable(2929);
/* 490 */     GL11.glDisable(2884);
/* 491 */     GL11.glEnable(3042);
/* 492 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 494 */     GL11.glTranslated(-iPX + pos.func_177958_n() + 0.5D, -iPY + pos.func_177956_o() + 0.5D, -iPZ + pos.func_177952_p() + 0.5D);
/*     */     
/* 496 */     GL11.glPushMatrix();
/* 497 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
/* 498 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 499 */     if (dz) {
/* 500 */       GL11.glPushMatrix();
/* 501 */       UtilsFX.renderQuadCentered(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 502 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 503 */       UtilsFX.renderQuadCentered(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 504 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 507 */     if (dx) {
/* 508 */       GL11.glPushMatrix();
/* 509 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 510 */       UtilsFX.renderQuadCentered(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 511 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 512 */       UtilsFX.renderQuadCentered(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 513 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 516 */     if (dy) {
/* 517 */       GL11.glPushMatrix();
/* 518 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 519 */       UtilsFX.renderQuadCentered(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 520 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 521 */       UtilsFX.renderQuadCentered(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 522 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 525 */     GL11.glPopMatrix();
/* 526 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 528 */     GL11.glDisable(3042);
/* 529 */     GL11.glEnable(2884);
/* 530 */     GL11.glEnable(2929);
/* 531 */     GL11.glDepthMask(true);
/* 532 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\events\WandRenderingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */