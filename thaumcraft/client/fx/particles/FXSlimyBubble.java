/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class FXSlimyBubble extends Particle {
/*    */   int particle;
/*    */   
/*    */   public FXSlimyBubble(World world, double d, double d1, double d2, float f) {
/* 13 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
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
/* 26 */     this.particle = 144; this.field_70552_h = 1.0F; this.field_70553_i = 1.0F;
/*    */     this.field_70551_j = 1.0F;
/*    */     this.field_70545_g = 0.0F;
/*    */     this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D;
/*    */     this.field_70544_f = f;
/*    */     this.field_70547_e = 15 + world.field_73012_v.nextInt(5);
/* 32 */     func_187115_a(0.01F, 0.01F); } public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/*    */     
/* 34 */     float var8 = (this.particle % 16) / 64.0F;
/* 35 */     float var9 = var8 + 0.015625F;
/* 36 */     float var10 = (this.particle / 16) / 64.0F;
/* 37 */     float var11 = var10 + 0.015625F;
/* 38 */     float var12 = this.field_70544_f;
/* 39 */     float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/* 40 */     float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/* 41 */     float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*    */     
/* 43 */     int i = func_189214_a(f);
/* 44 */     int j = i >> 16 & 0xFFFF;
/* 45 */     int k = i & 0xFFFF;
/*    */     
/* 47 */     wr.func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11)
/* 48 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 49 */     wr.func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10)
/* 50 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 51 */     wr.func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10)
/* 52 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 53 */     wr.func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11)
/* 54 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public int func_70537_b() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_189213_a() {
/* 66 */     this.field_187123_c = this.field_187126_f;
/* 67 */     this.field_187124_d = this.field_187127_g;
/* 68 */     this.field_187125_e = this.field_187128_h;
/*    */     
/* 70 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 71 */       func_187112_i();
/*    */     }
/* 73 */     if (this.field_70546_d - 1 < 6) {
/* 74 */       this.particle = 144 + this.field_70546_d / 2;
/* 75 */       if (this.field_70546_d == 5) {
/* 76 */         this.field_187127_g += 0.1D;
/*    */       }
/*    */     }
/* 79 */     else if (this.field_70546_d < this.field_70547_e - 4) {
/* 80 */       this.field_187130_j += 0.005D;
/* 81 */       this.particle = 147 + this.field_70546_d % 4 / 2;
/*    */     } else {
/* 83 */       this.field_187130_j /= 2.0D;
/* 84 */       this.particle = 150 - (this.field_70547_e - this.field_70546_d) / 2;
/*    */     } 
/*    */     
/* 87 */     this.field_187127_g += this.field_187130_j;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXSlimyBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */