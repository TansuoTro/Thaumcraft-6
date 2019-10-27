/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXVent2
/*     */   extends Particle
/*     */ {
/*     */   float grav;
/*     */   float psm;
/*     */   
/*     */   public FXVent2(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int color) {
/*  19 */     super(par1World, par2, par4, par6, par8, par10, par12);
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
/*  43 */     this.grav = 0.0F;
/*  44 */     this.psm = 1.0F; func_187115_a(0.02F, 0.02F); this.field_70544_f = this.field_187136_p.nextFloat() * 0.1F + 0.05F; this.field_187129_i = par8; this.field_187130_j = par10; this.field_187131_k = par12; Color c = new Color(color); this.field_70552_h = (float)MathHelper.func_151237_a((c.getRed() / 255.0F) + this.field_187136_p.nextGaussian() * 0.05D, 0.0D, 1.0D); this.field_70551_j = (float)MathHelper.func_151237_a((c.getBlue() / 255.0F) + this.field_187136_p.nextGaussian() * 0.05D, 0.0D, 1.0D); this.field_70553_i = (float)MathHelper.func_151237_a((c.getGreen() / 255.0F) + this.field_187136_p.nextGaussian() * 0.05D, 0.0D, 1.0D); Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa(); int visibleDistance = 50; if (!(FMLClientHandler.instance().getClient()).field_71474_y.field_74347_j)
/*     */       visibleDistance = 25;  if (renderentity.func_70011_f(this.field_187126_f, this.field_187127_g, this.field_187128_h) > visibleDistance)
/*  46 */       this.field_70547_e = 0;  this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g; this.field_187125_e = this.field_187128_h; this.grav = (float)(this.field_187136_p.nextGaussian() * 0.0075D); } public void setScale(float f) { this.field_70544_f *= f;
/*  47 */     this.psm *= f; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8) {
/*  52 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*  53 */     par1 /= f2;
/*  54 */     par3 /= f2;
/*  55 */     par5 /= f2;
/*  56 */     par1 += this.field_187136_p.nextGaussian() * (this.field_187136_p.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*  57 */     par3 += this.field_187136_p.nextGaussian() * (this.field_187136_p.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*  58 */     par5 += this.field_187136_p.nextGaussian() * (this.field_187136_p.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*  59 */     par1 *= par7;
/*  60 */     par3 *= par7;
/*  61 */     par5 *= par7;
/*  62 */     this.field_187129_i = par1;
/*  63 */     this.field_187130_j = par3;
/*  64 */     this.field_187131_k = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/*  72 */     this.field_187123_c = this.field_187126_f;
/*  73 */     this.field_187124_d = this.field_187127_g;
/*  74 */     this.field_187125_e = this.field_187128_h;
/*  75 */     this.field_70546_d++;
/*  76 */     if (this.field_70544_f >= this.psm)
/*     */     {
/*  78 */       func_187112_i();
/*     */     }
/*     */     
/*  81 */     this.field_187130_j += this.grav;
/*  82 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*  83 */     this.field_187129_i *= 0.8500000190734863D;
/*  84 */     this.field_187130_j *= 0.8500000190734863D;
/*  85 */     this.field_187131_k *= 0.8500000190734863D;
/*  86 */     if (this.field_70544_f < this.psm) this.field_70544_f = (float)(this.field_70544_f * 1.2D); 
/*  87 */     if (this.field_70544_f > this.psm) this.field_70544_f = this.psm; 
/*  88 */     if (this.field_187132_l) {
/*     */       
/*  90 */       this.field_187129_i *= 0.699999988079071D;
/*  91 */       this.field_187131_k *= 0.699999988079071D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*  96 */     this.field_70552_h = r;
/*  97 */     this.field_70553_i = g;
/*  98 */     this.field_70551_j = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 105 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
/* 106 */     int part = (int)(1.0F + this.field_70544_f / this.psm * 4.0F);
/* 107 */     float var8 = (part % 16) / 64.0F;
/* 108 */     float var9 = var8 + 0.015625F;
/* 109 */     float var10 = (part / 64) / 64.0F;
/* 110 */     float var11 = var10 + 0.015625F;
/* 111 */     float var12 = 0.3F * this.field_70544_f;
/*     */     
/* 113 */     float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/* 114 */     float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/* 115 */     float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/* 116 */     float var16 = 1.0F;
/*     */     
/* 118 */     int i = func_189214_a(f);
/* 119 */     int j = i >> 16 & 0xFFFF;
/* 120 */     int k = i & 0xFFFF;
/*     */     
/* 122 */     float alpha = this.field_82339_as * (this.psm - this.field_70544_f) / this.psm;
/* 123 */     wr.func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11)
/* 124 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_187314_a(j, k).func_181675_d();
/* 125 */     wr.func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10)
/* 126 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_187314_a(j, k).func_181675_d();
/* 127 */     wr.func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10)
/* 128 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_187314_a(j, k).func_181675_d();
/* 129 */     wr.func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11)
/* 130 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_187314_a(j, k).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public int func_70537_b() { return 1; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXVent2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */