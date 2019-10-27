/*     */ package thaumcraft.client.fx;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.crash.ICrashReportDetail;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber({Side.CLIENT})
/*     */ public class ParticleEngine
/*     */ {
/*  42 */   public static final ResourceLocation particleTexture = new ResourceLocation("thaumcraft", "textures/misc/particles.png");
/*     */   
/*     */   protected World world;
/*     */   
/*  46 */   private static HashMap<Integer, ArrayList<Particle>>[] particles = { new HashMap(), new HashMap(), new HashMap(), new HashMap(), new HashMap(), new HashMap() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   private static ArrayList<ParticleDelay> particlesDelayed = new ArrayList();
/*     */   
/*     */   private static class ParticleDelay
/*     */   {
/*     */     Particle particle;
/*     */     int dim;
/*     */     
/*     */     public ParticleDelay(Particle particle, int dim, int delay) {
/*  63 */       this.dim = dim;
/*  64 */       this.particle = particle;
/*  65 */       this.delay = delay;
/*     */     }
/*     */     
/*     */     int level;
/*     */     int delay; }
/*  70 */   private Random rand = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void renderTick(TickEvent.RenderTickEvent event) {
/*  75 */     if ((Minecraft.func_71410_x()).field_71441_e == null)
/*  76 */       return;  if (event.phase == TickEvent.Phase.END) {
/*  77 */       float frame = event.renderTickTime;
/*  78 */       EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*  79 */       TextureManager renderer = (Minecraft.func_71410_x()).field_71446_o;
/*     */       
/*  81 */       int dim = (Minecraft.func_71410_x()).field_71441_e.field_73011_w.getDimension();
/*     */ 
/*     */       
/*  84 */       GL11.glPushMatrix();
/*  85 */       ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  86 */       GL11.glClear(256);
/*  87 */       GL11.glMatrixMode(5889);
/*  88 */       GL11.glLoadIdentity();
/*  89 */       GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/*  90 */       GL11.glMatrixMode(5888);
/*  91 */       GL11.glLoadIdentity();
/*  92 */       GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*  93 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  95 */       GlStateManager.func_179147_l();
/*  96 */       GL11.glEnable(3042);
/*  97 */       GL11.glAlphaFunc(516, 0.003921569F);
/*  98 */       renderer.func_110577_a(particleTexture);
/*  99 */       GlStateManager.func_179132_a(false);
/*     */       
/* 101 */       for (int layer = 5; layer >= 4; layer--) {
/* 102 */         if (particles[layer].containsKey(Integer.valueOf(dim))) {
/* 103 */           ArrayList<Particle> parts = (ArrayList)particles[layer].get(Integer.valueOf(dim));
/* 104 */           if (parts.size() != 0) {
/*     */             
/* 106 */             switch (layer) {
/*     */               case 4:
/* 108 */                 GlStateManager.func_179112_b(770, 1);
/*     */                 break;
/*     */               case 5:
/* 111 */                 GlStateManager.func_179112_b(770, 771);
/*     */                 break;
/*     */             } 
/*     */             
/* 115 */             Tessellator tessellator = Tessellator.func_178181_a();
/* 116 */             BufferBuilder buffer = tessellator.func_178180_c();
/*     */             
/* 118 */             for (int j = 0; j < parts.size(); j++) {
/* 119 */               final Particle Particle = (Particle)parts.get(j);
/* 120 */               if (Particle != null)
/*     */                 
/*     */                 try {
/* 123 */                   Particle.func_180434_a(buffer, entityPlayerSP, frame, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 124 */                 } catch (Throwable throwable) {
/* 125 */                   CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering Particle");
/* 126 */                   CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
/* 127 */                   crashreportcategory.func_189529_a("Particle", new ICrashReportDetail<String>()
/*     */                       {
/*     */                         public String call() {
/* 130 */                           return Particle.toString();
/*     */                         }
/*     */                       });
/* 133 */                   crashreportcategory.func_189529_a("Particle Type", new ICrashReportDetail<String>()
/*     */                       {
/*     */                         public String call() {
/* 136 */                           return "ENTITY_PARTICLE_TEXTURE";
/*     */                         }
/*     */                       });
/* 139 */                   throw new ReportedException(crashreport);
/*     */                 }  
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 145 */       GlStateManager.func_179132_a(true);
/* 146 */       GlStateManager.func_179112_b(770, 771);
/* 147 */       GlStateManager.func_179084_k();
/* 148 */       GlStateManager.func_179092_a(516, 0.1F);
/*     */       
/* 150 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onRenderWorldLast(RenderWorldLastEvent event) {
/* 160 */     float frame = event.getPartialTicks();
/* 161 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 162 */     TextureManager renderer = (Minecraft.func_71410_x()).field_71446_o;
/* 163 */     int dim = (Minecraft.func_71410_x()).field_71441_e.field_73011_w.getDimension();
/*     */     
/* 165 */     GL11.glPushMatrix();
/*     */     
/* 167 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 168 */     GlStateManager.func_179147_l();
/* 169 */     GL11.glEnable(3042);
/* 170 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 171 */     renderer.func_110577_a(particleTexture);
/* 172 */     GlStateManager.func_179132_a(false);
/*     */     
/* 174 */     for (int layer = 3; layer >= 0; layer--) {
/* 175 */       if (particles[layer].containsKey(Integer.valueOf(dim))) {
/* 176 */         ArrayList<Particle> parts = (ArrayList)particles[layer].get(Integer.valueOf(dim));
/* 177 */         if (parts.size() != 0) {
/*     */           
/* 179 */           switch (layer) {
/*     */             case 0:
/* 181 */               GlStateManager.func_179112_b(770, 1);
/*     */               break;
/*     */             case 1:
/* 184 */               GlStateManager.func_179112_b(770, 771);
/*     */               break;
/*     */             case 2:
/* 187 */               GlStateManager.func_179112_b(770, 1);
/* 188 */               GlStateManager.func_179097_i();
/*     */               break;
/*     */             case 3:
/* 191 */               GlStateManager.func_179112_b(770, 771);
/* 192 */               GlStateManager.func_179097_i();
/*     */               break;
/*     */           } 
/*     */           
/* 196 */           float f1 = ActiveRenderInfo.func_178808_b();
/* 197 */           float f2 = ActiveRenderInfo.func_178803_d();
/* 198 */           float f3 = ActiveRenderInfo.func_178805_e();
/* 199 */           float f4 = ActiveRenderInfo.func_178807_f();
/* 200 */           float f5 = ActiveRenderInfo.func_178809_c();
/* 201 */           Particle.field_70556_an = entityPlayerSP.field_70142_S + (entityPlayerSP.field_70165_t - entityPlayerSP.field_70142_S) * frame;
/* 202 */           Particle.field_70554_ao = entityPlayerSP.field_70137_T + (entityPlayerSP.field_70163_u - entityPlayerSP.field_70137_T) * frame;
/* 203 */           Particle.field_70555_ap = entityPlayerSP.field_70136_U + (entityPlayerSP.field_70161_v - entityPlayerSP.field_70136_U) * frame;
/*     */           
/* 205 */           Tessellator tessellator = Tessellator.func_178181_a();
/* 206 */           BufferBuilder buffer = tessellator.func_178180_c();
/* 207 */           buffer.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */           
/* 209 */           for (int j = 0; j < parts.size(); j++) {
/* 210 */             final Particle Particle = (Particle)parts.get(j);
/* 211 */             if (Particle != null) {
/*     */               
/*     */               try {
/* 214 */                 Particle.func_180434_a(buffer, entityPlayerSP, frame, f1, f5, f2, f3, f4);
/* 215 */               } catch (Throwable throwable) {
/* 216 */                 CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering Particle");
/* 217 */                 CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
/* 218 */                 crashreportcategory.func_189529_a("Particle", new ICrashReportDetail<String>()
/*     */                     {
/*     */                       public String call() {
/* 221 */                         return Particle.toString();
/*     */                       }
/*     */                     });
/* 224 */                 crashreportcategory.func_189529_a("Particle Type", new ICrashReportDetail<String>()
/*     */                     {
/*     */                       public String call() {
/* 227 */                         return "ENTITY_PARTICLE_TEXTURE";
/*     */                       }
/*     */                     });
/* 230 */                 throw new ReportedException(crashreport);
/*     */               } 
/*     */             }
/*     */           } 
/* 234 */           tessellator.func_78381_a();
/*     */           
/* 236 */           switch (layer) { case 2:
/*     */             case 3:
/* 238 */               GlStateManager.func_179126_j(); break; }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/* 243 */     GlStateManager.func_179132_a(true);
/* 244 */     GlStateManager.func_179112_b(770, 771);
/* 245 */     GlStateManager.func_179084_k();
/* 246 */     GlStateManager.func_179092_a(516, 0.1F);
/* 247 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/* 251 */   public static void addEffect(World world, Particle fx) { addEffect(world.field_73011_w.getDimension(), fx); }
/*     */ 
/*     */   
/*     */   private static int getParticleLimit() {
/* 255 */     return ((FMLClientHandler.instance().getClient()).field_71474_y.field_74362_aa == 2) ? 500 : (
/* 256 */       ((FMLClientHandler.instance().getClient()).field_71474_y.field_74362_aa == 1) ? 1000 : 2000);
/*     */   }
/*     */   
/*     */   public static void addEffect(int dim, Particle fx) {
/* 260 */     if ((Minecraft.func_71410_x()).field_71441_e == null)
/* 261 */       return;  int ps = (FMLClientHandler.instance().getClient()).field_71474_y.field_74362_aa;
/* 262 */     if (Minecraft.func_71410_x().func_175610_ah() < 30) ps++; 
/* 263 */     if ((Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextInt(3) < ps)
/* 264 */       return;  if (!particles[fx.func_70537_b()].containsKey(Integer.valueOf(dim))) {
/* 265 */       particles[fx.func_70537_b()].put(Integer.valueOf(dim), new ArrayList());
/*     */     }
/* 267 */     ArrayList<Particle> parts = (ArrayList)particles[fx.func_70537_b()].get(Integer.valueOf(dim));
/* 268 */     if (parts.size() >= getParticleLimit()) {
/* 269 */       parts.remove(0);
/*     */     }
/* 271 */     parts.add(fx);
/* 272 */     particles[fx.func_70537_b()].put(Integer.valueOf(dim), parts);
/*     */   }
/*     */ 
/*     */   
/* 276 */   public static void addEffectWithDelay(World world, Particle fx, int delay) { particlesDelayed.add(new ParticleDelay(fx, world.field_73011_w.getDimension(), delay)); }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void updateParticles(TickEvent.ClientTickEvent event) {
/* 282 */     if (event.side == Side.SERVER)
/* 283 */       return;  Minecraft mc = FMLClientHandler.instance().getClient();
/* 284 */     WorldClient worldClient = mc.field_71441_e;
/* 285 */     if (mc.field_71441_e == null)
/* 286 */       return;  int dim = worldClient.field_73011_w.getDimension();
/* 287 */     if (event.phase == TickEvent.Phase.START) {
/*     */       
/*     */       try {
/* 290 */         Iterator<ParticleDelay> i = particlesDelayed.iterator();
/* 291 */         while (i.hasNext()) {
/* 292 */           ParticleDelay pd = (ParticleDelay)i.next();
/* 293 */           pd.delay--;
/* 294 */           if (pd.delay <= 0) {
/* 295 */             if (pd.dim == dim) addEffect(pd.dim, pd.particle); 
/* 296 */             i.remove();
/*     */           } 
/*     */         } 
/* 299 */       } catch (Exception exception) {}
/*     */       
/* 301 */       for (int layer = 0; layer < 6; layer++) {
/* 302 */         if (particles[layer].containsKey(Integer.valueOf(dim))) {
/* 303 */           ArrayList<Particle> parts = (ArrayList)particles[layer].get(Integer.valueOf(dim));
/*     */           
/* 305 */           for (int j = 0; j < parts.size(); j++) {
/* 306 */             final Particle Particle = (Particle)parts.get(j);
/*     */             
/*     */             try {
/* 309 */               if (Particle != null) {
/* 310 */                 Particle.func_189213_a();
/*     */               }
/* 312 */             } catch (Exception e) {
/*     */               try {
/* 314 */                 CrashReport crashreport = CrashReport.func_85055_a(e, "Ticking Particle");
/* 315 */                 CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being ticked");
/* 316 */                 crashreportcategory.func_71507_a("Particle", new Callable() { public String call() { return Particle.toString(); } });
/* 317 */                 crashreportcategory.func_71507_a("Particle Type", new Callable()
/*     */                     {
/*     */                       public String call() {
/* 320 */                         return "ENTITY_PARTICLE_TEXTURE";
/*     */                       }
/*     */                     });
/*     */                 
/* 324 */                 Particle.func_187112_i();
/* 325 */               } catch (Exception exception) {}
/*     */             } 
/*     */             
/* 328 */             if (Particle == null || !Particle.func_187113_k()) {
/* 329 */               parts.remove(j--);
/* 330 */               particles[layer].put(Integer.valueOf(dim), parts);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\ParticleEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */