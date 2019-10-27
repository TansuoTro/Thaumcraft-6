/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.obj.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.obj.IModelCustom;
/*     */ 
/*     */ public class FXSonic extends Particle {
/*     */   Entity target;
/*     */   float yaw;
/*     */   float pitch;
/*     */   private IModelCustom model;
/*     */   
/*  23 */   public FXSonic(World world, double d, double d1, double d2, Entity target, int age) { super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
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
/*  51 */     this.target = null;
/*  52 */     this.yaw = 0.0F;
/*  53 */     this.pitch = 0.0F; this.field_70552_h = 1.0F; this.field_70553_i = 1.0F; this.field_70551_j = 1.0F; this.field_70545_g = 0.0F; this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D; this.field_70547_e = age + this.field_187136_p.nextInt(age / 2); func_187115_a(0.01F, 0.01F); this.field_70544_f = 1.0F; this.target = target; this.yaw = target.func_70079_am(); this.pitch = target.field_70125_A;
/*     */     this.field_187123_c = this.field_187126_f = target.field_70165_t;
/*     */     this.field_187124_d = this.field_187127_g = target.field_70163_u + target.func_70047_e();
/*  56 */     this.field_187125_e = this.field_187128_h = target.field_70161_v; } private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "models/obj/hemis.obj");
/*     */ 
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) {
/*  60 */     Tessellator.func_178181_a().func_78381_a();
/*  61 */     GL11.glPushMatrix();
/*  62 */     GL11.glDepthMask(false);
/*  63 */     GL11.glEnable(3042);
/*  64 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  66 */     if (this.model == null) {
/*  67 */       this.model = AdvancedModelLoader.loadModel(MODEL);
/*     */     }
/*     */     
/*  70 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*     */     
/*  72 */     float xx = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*  73 */     float yy = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*  74 */     float zz = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*  75 */     GL11.glTranslated(xx, yy, zz);
/*  76 */     float b = 1.0F;
/*  77 */     int frame = Math.min(15, (int)(14.0F * fade) + 1);
/*  78 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(new ResourceLocation("thaumcraft", "textures/models/ripple" + frame + ".png"));
/*  79 */     b = 0.5F;
/*  80 */     int i = 220;
/*  81 */     int j = i % 65536;
/*  82 */     int k = i / 65536;
/*  83 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  84 */     GL11.glRotatef(-this.yaw, 0.0F, 1.0F, 0.0F);
/*  85 */     GL11.glRotatef(this.pitch, 1.0F, 0.0F, 0.0F);
/*  86 */     GL11.glTranslated(0.0D, 0.0D, (2.0F * this.target.field_70131_O + this.target.field_70130_N / 2.0F));
/*  87 */     GL11.glScaled(0.25D * this.target.field_70131_O, 0.25D * this.target.field_70131_O, (-1.0F * this.target.field_70131_O));
/*  88 */     GL11.glColor4f(b, b, b, 1.0F);
/*  89 */     this.model.renderAll();
/*     */     
/*  91 */     GL11.glDisable(3042);
/*  92 */     GL11.glDepthMask(true);
/*  93 */     GL11.glPopMatrix();
/*     */     
/*  95 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleManager.field_110737_b);
/*  96 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/* 102 */     this.field_187123_c = this.field_187126_f;
/* 103 */     this.field_187124_d = this.field_187127_g;
/* 104 */     this.field_187125_e = this.field_187128_h;
/*     */     
/* 106 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 107 */       func_187112_i();
/*     */     }
/*     */     
/* 110 */     this.field_187126_f = this.target.field_70165_t;
/* 111 */     this.field_187127_g = this.target.field_70163_u + this.target.func_70047_e();
/* 112 */     this.field_187128_h = this.target.field_70161_v;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\other\FXSonic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */