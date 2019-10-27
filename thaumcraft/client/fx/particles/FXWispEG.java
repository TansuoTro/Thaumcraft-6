/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class FXWispEG
/*    */   extends Particle
/*    */ {
/* 14 */   Entity target = null;
/* 15 */   double rx = 0.0D;
/* 16 */   double ry = 0.0D;
/* 17 */   double rz = 0.0D;
/*    */   public int blendmode;
/*    */   
/*    */   public FXWispEG(World world, double posX, double posY, double posZ, Entity target2) {
/* 21 */     super(world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 89 */     this.blendmode = 1;
/*    */     this.target = target2;
/*    */     this.field_187129_i = this.field_187136_p.nextGaussian() * 0.03D;
/*    */     this.field_187130_j = -0.05D;
/*    */     this.field_187131_k = this.field_187136_p.nextGaussian() * 0.03D;
/*    */     this.field_70544_f *= 0.4F;
/*    */     this.field_70547_e = (int)(40.0D / (Math.random() * 0.3D + 0.7D));
/*    */     func_187115_a(0.01F, 0.01F);
/*    */     this.field_187123_c = posX;
/*    */     this.field_187124_d = posY;
/*    */     this.field_187125_e = posZ;
/*    */     this.blendmode = 771;
/*    */     this.field_70552_h = this.field_187136_p.nextFloat() * 0.05F;
/*    */     this.field_70553_i = this.field_187136_p.nextFloat() * 0.05F;
/*    */     this.field_70551_j = this.field_187136_p.nextFloat() * 0.05F;
/*    */   }
/*    */   
/*    */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*    */     Entity e = Minecraft.func_71410_x().func_175606_aa();
/*    */     float agescale = 1.0F - this.field_70546_d / this.field_70547_e;
/*    */     float d6 = 1024.0F;
/*    */     double dist = (new Vec3d(e.field_70165_t, e.field_70163_u, e.field_70161_v)).func_72436_e(new Vec3d(this.field_187126_f, this.field_187127_g, this.field_187128_h));
/*    */     float base = (float)(1.0D - Math.min(d6, dist) / d6);
/*    */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F * base);
/*    */     float f10 = 0.5F * this.field_70544_f;
/*    */     float f11 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*    */     float f12 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*    */     float f13 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*    */     float var8 = (this.field_70546_d % 13) / 64.0F;
/*    */     float var9 = var8 + 0.015625F;
/*    */     float var10 = 0.046875F;
/*    */     float var11 = var10 + 0.015625F;
/*    */     int i = 240;
/*    */     int j = i >> 16 & 0xFFFF;
/*    */     int k = i & 0xFFFF;
/*    */     wr.func_181662_b((f11 - f1 * f10 - f4 * f10), (f12 - f2 * f10), (f13 - f3 * f10 - f5 * f10)).func_187315_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_187314_a(j, k).func_181675_d();
/*    */     wr.func_181662_b((f11 - f1 * f10 + f4 * f10), (f12 + f2 * f10), (f13 - f3 * f10 + f5 * f10)).func_187315_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_187314_a(j, k).func_181675_d();
/*    */     wr.func_181662_b((f11 + f1 * f10 + f4 * f10), (f12 + f2 * f10), (f13 + f3 * f10 + f5 * f10)).func_187315_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_187314_a(j, k).func_181675_d();
/*    */     wr.func_181662_b((f11 + f1 * f10 - f4 * f10), (f12 - f2 * f10), (f13 + f3 * f10 - f5 * f10)).func_187315_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_187314_a(j, k).func_181675_d();
/*    */   }
/*    */   
/*    */   public int func_70537_b() { return (this.blendmode == 1) ? 0 : 1; }
/*    */   
/*    */   public void func_189213_a() {
/*    */     super.func_189213_a();
/*    */     if (this.target != null && !this.field_187132_l) {
/*    */       this.field_187126_f += this.target.field_70159_w;
/*    */       this.field_187128_h += this.target.field_70179_y;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXWispEG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */