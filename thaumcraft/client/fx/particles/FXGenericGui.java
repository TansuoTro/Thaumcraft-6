/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FXGenericGui
/*    */   extends FXGeneric
/*    */ {
/*    */   boolean inGame = false;
/*    */   
/*    */   public FXGenericGui(World world, double x, double y, double z) {
/* 20 */     super(world, x, y, z);
/* 21 */     this.inGame = (Minecraft.func_71410_x()).field_71415_G;
/*    */   }
/*    */   
/*    */   public FXGenericGui(World world, double x, double y, double z, double xx, double yy, double zz) {
/* 25 */     super(world, x, y, z, xx, yy, zz);
/* 26 */     this.inGame = (Minecraft.func_71410_x()).field_71415_G;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_189213_a() {
/* 32 */     super.func_189213_a();
/* 33 */     if (!this.inGame && (Minecraft.func_71410_x()).field_71415_G) func_187112_i();
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void draw(BufferBuilder wr, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
/* 40 */     float tx1 = this.field_94054_b / this.gridSize;
/* 41 */     float tx2 = tx1 + 1.0F / this.gridSize;
/* 42 */     float ty1 = this.field_94055_c / this.gridSize;
/* 43 */     float ty2 = ty1 + 1.0F / this.gridSize;
/* 44 */     float ts = 0.1F * this.field_70544_f;
/*    */     
/* 46 */     if (this.field_187119_C != null) {
/*    */       
/* 48 */       tx1 = this.field_187119_C.func_94209_e();
/* 49 */       tx2 = this.field_187119_C.func_94212_f();
/* 50 */       ty1 = this.field_187119_C.func_94206_g();
/* 51 */       ty2 = this.field_187119_C.func_94210_h();
/*    */     } 
/*    */     
/* 54 */     if (this.flipped) {
/* 55 */       float t = tx1;
/* 56 */       tx1 = tx2;
/* 57 */       tx2 = t;
/*    */     } 
/*    */     
/* 60 */     float fs = MathHelper.func_76131_a((this.field_70546_d + partialTicks) / this.field_70547_e, 0.0F, 1.0F);
/* 61 */     float pr = this.field_70552_h + (this.dr - this.field_70552_h) * fs;
/* 62 */     float pg = this.field_70553_i + (this.dg - this.field_70553_i) * fs;
/* 63 */     float pb = this.field_70551_j + (this.db - this.field_70551_j) * fs;
/* 64 */     int i = func_189214_a(partialTicks);
/* 65 */     int j = i >> 16 & 0xFFFF;
/* 66 */     int k = i & 0xFFFF;
/*    */     
/* 68 */     float f5 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * partialTicks);
/* 69 */     float f6 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * partialTicks);
/* 70 */     float f7 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * partialTicks);
/*    */     
/* 72 */     GL11.glPushMatrix();
/* 73 */     GL11.glTranslatef(f5, f6, -90.0F + f7);
/* 74 */     if (this.angled) {
/* 75 */       GL11.glRotatef(-this.angleYaw + 90.0F, 0.0F, 1.0F, 0.0F);
/* 76 */       GL11.glRotatef(this.anglePitch + 90.0F, 1.0F, 0.0F, 0.0F);
/*    */     } 
/* 78 */     if (this.field_190014_F != 0.0F) {
/*    */       
/* 80 */       float f8 = this.field_190014_F + (this.field_190014_F - this.field_190015_G) * partialTicks;
/* 81 */       GL11.glRotated(f8 * 57.29577951308232D, 0.0D, 0.0D, 1.0D);
/*    */     } 
/* 83 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 84 */     wr.func_181662_b(-ts, -ts, 0.0D).func_187315_a(tx2, ty2).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 85 */     wr.func_181662_b(-ts, ts, 0.0D).func_187315_a(tx2, ty1).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 86 */     wr.func_181662_b(ts, ts, 0.0D).func_187315_a(tx1, ty1).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 87 */     wr.func_181662_b(ts, -ts, 0.0D).func_187315_a(tx1, ty2).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 88 */     Tessellator.func_178181_a().func_78381_a();
/* 89 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXGenericGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */