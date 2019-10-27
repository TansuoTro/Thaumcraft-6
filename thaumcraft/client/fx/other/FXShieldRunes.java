/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.obj.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.obj.IModelCustom;
/*     */ 
/*     */ public class FXShieldRunes extends Particle {
/*     */   ResourceLocation[] tex1;
/*     */   ResourceLocation[] tex2;
/*     */   Entity target;
/*     */   float yaw;
/*     */   float pitch;
/*     */   private IModelCustom model;
/*     */   
/*  24 */   public FXShieldRunes(World world, double d, double d1, double d2, Entity target, int age, float yaw, float pitch) { super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
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
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.tex1 = new ResourceLocation[15];
/*  59 */     this.tex2 = new ResourceLocation[15];
/*     */     
/*  61 */     this.target = null;
/*  62 */     this.yaw = 0.0F;
/*  63 */     this.pitch = 0.0F; this.field_70552_h = 1.0F; this.field_70553_i = 1.0F; this.field_70551_j = 1.0F; this.field_70545_g = 0.0F; this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D; this.field_70547_e = age + this.field_187136_p.nextInt(age / 2); func_187115_a(0.01F, 0.01F); this.field_70544_f = 1.0F; this.target = target; this.yaw = yaw; this.pitch = pitch; if (target != null) { this.field_187123_c = this.field_187126_f = target.field_70165_t; this.field_187124_d = this.field_187127_g = ((target.func_174813_aQ()).field_72338_b + (target.func_174813_aQ()).field_72337_e) / 2.0D; this.field_187125_e = this.field_187128_h = target.field_70161_v; }
/*     */      for (int a = 0; a < 15; a++) {
/*     */       this.tex1[a] = new ResourceLocation("thaumcraft", "textures/models/ripple" + (a + 1) + ".png"); this.tex2[a] = new ResourceLocation("thaumcraft", "textures/models/hemis" + (a + 1) + ".png");
/*  66 */     }  } private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "models/obj/hemis.obj");
/*     */ 
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) {
/*  70 */     Tessellator.func_178181_a().func_78381_a();
/*  71 */     GL11.glPushMatrix();
/*  72 */     GL11.glDisable(2884);
/*  73 */     GL11.glDepthMask(false);
/*  74 */     GL11.glEnable(3042);
/*  75 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  77 */     if (this.model == null) {
/*  78 */       this.model = AdvancedModelLoader.loadModel(MODEL);
/*     */     }
/*     */     
/*  81 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*     */     
/*  83 */     float xx = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*  84 */     float yy = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*  85 */     float zz = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*  86 */     GL11.glTranslated(xx, yy, zz);
/*  87 */     float b = 1.0F;
/*  88 */     int frame = Math.min(15, (int)(14.0F * fade) + 1);
/*  89 */     if (this.target != null && this.target instanceof net.minecraft.entity.monster.EntityMob && !(this.target instanceof thaumcraft.common.entities.monster.cult.EntityCultist)) {
/*  90 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.tex1[frame - 1]);
/*  91 */       b = 0.5F;
/*     */     } else {
/*  93 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.tex2[frame - 1]);
/*     */     } 
/*  95 */     int i = 220;
/*  96 */     int j = i % 65536;
/*  97 */     int k = i / 65536;
/*  98 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  99 */     GL11.glRotatef(180.0F - this.yaw, 0.0F, 1.0F, 0.0F);
/* 100 */     GL11.glRotatef(-this.pitch, 1.0F, 0.0F, 0.0F);
/* 101 */     float th = (this.target == null) ? 2.0F : this.target.field_70131_O;
/* 102 */     GL11.glScaled(0.2D * th, 0.2D * th, 0.2D * th);
/* 103 */     if (this.target == null) {
/* 104 */       GL11.glColor4f(0.65F, 0.1F, 0.5F, Math.min(1.0F, (1.0F - fade) * 3.0F));
/*     */     } else {
/* 106 */       GL11.glColor4f(b, b, b, Math.min(1.0F, (1.0F - fade) * 3.0F));
/* 107 */     }  this.model.renderAll();
/*     */     
/* 109 */     GL11.glBlendFunc(770, 771);
/* 110 */     GL11.glDisable(3042);
/* 111 */     GL11.glDepthMask(true);
/* 112 */     GL11.glEnable(2884);
/* 113 */     GL11.glPopMatrix();
/*     */     
/* 115 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleManager.field_110737_b);
/* 116 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/* 122 */     this.field_187123_c = this.field_187126_f;
/* 123 */     this.field_187124_d = this.field_187127_g;
/* 124 */     this.field_187125_e = this.field_187128_h;
/*     */     
/* 126 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 127 */       func_187112_i();
/*     */     }
/*     */     
/* 130 */     if (this.target != null) {
/* 131 */       this.field_187126_f = this.target.field_70165_t;
/* 132 */       this.field_187127_g = ((this.target.func_174813_aQ()).field_72338_b + (this.target.func_174813_aQ()).field_72337_e) / 2.0D;
/* 133 */       this.field_187128_h = this.target.field_70161_v;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\other\FXShieldRunes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */