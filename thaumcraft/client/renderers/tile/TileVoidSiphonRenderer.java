/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.ARBShaderObjects;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.lib.ender.ShaderCallback;
/*    */ import thaumcraft.client.lib.ender.ShaderHelper;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileVoidSiphonRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 24 */   private final ShaderCallback shaderCallback = new ShaderCallback()
/*    */     {
/*    */       public void call(int shader) {
/* 27 */         Minecraft mc = Minecraft.func_71410_x();
/*    */         
/* 29 */         int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
/* 30 */         ARBShaderObjects.glUniform1fARB(x, (float)((mc.field_71439_g.field_70177_z * 2.0F) * Math.PI / 360.0D));
/*    */         
/* 32 */         int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
/* 33 */         ARBShaderObjects.glUniform1fARB(z, -((float)((mc.field_71439_g.field_70125_A * 2.0F) * Math.PI / 360.0D)));
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/* 39 */   private static final ResourceLocation starsTexture = new ResourceLocation("textures/entity/end_portal.png");
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 43 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 44 */     if (BlockStateUtils.isEnabled(te.func_145832_p())) {
/* 45 */       GL11.glPushMatrix();
/* 46 */       func_147499_a(starsTexture);
/* 47 */       ShaderHelper.useShader(ShaderHelper.endShader, this.shaderCallback);
/* 48 */       GL11.glPushMatrix();
/* 49 */       GL11.glTranslated(x + 0.5D, y + 0.875D, z + 0.5D);
/* 50 */       GlStateManager.func_179132_a(false);
/* 51 */       for (EnumFacing face : EnumFacing.values()) {
/* 52 */         GL11.glPushMatrix();
/* 53 */         GL11.glRotatef(90.0F, -face.func_96559_d(), face.func_82601_c(), -face.func_82599_e());
/* 54 */         if (face.func_82599_e() < 0) {
/* 55 */           GL11.glTranslated(0.0D, 0.0D, 0.126D);
/* 56 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */         } else {
/* 58 */           GL11.glTranslated(0.0D, 0.0D, -0.126D);
/*    */         } 
/* 60 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/* 61 */         GL11.glScaled(0.2D, 0.2D, 0.2D);
/* 62 */         UtilsFX.renderQuadCentered(1, 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, 200, 1, 1.0F);
/* 63 */         GL11.glPopMatrix();
/*    */       } 
/* 65 */       GL11.glPopMatrix();
/*    */       
/* 67 */       GL11.glPushMatrix();
/* 68 */       GL11.glTranslated(x + 0.5D, y + 0.3125D, z + 0.5D);
/* 69 */       GlStateManager.func_179132_a(false);
/* 70 */       for (EnumFacing face : EnumFacing.values()) {
/* 71 */         GL11.glPushMatrix();
/* 72 */         GL11.glRotatef(90.0F, -face.func_96559_d(), face.func_82601_c(), -face.func_82599_e());
/* 73 */         if (face.func_82599_e() < 0) {
/* 74 */           GL11.glTranslated(0.0D, 0.0D, 0.26D);
/* 75 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */         } else {
/* 77 */           GL11.glTranslated(0.0D, 0.0D, -0.26D);
/*    */         } 
/* 79 */         if (face.func_82599_e() != 0) {
/* 80 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*    */         }
/*    */         
/* 83 */         GL11.glScaled(0.25D, 0.5D, 0.25D);
/* 84 */         UtilsFX.renderQuadCentered(1, 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/* 85 */         GL11.glPopMatrix();
/*    */       } 
/* 87 */       GL11.glPopMatrix();
/*    */       
/* 89 */       GlStateManager.func_179132_a(true);
/* 90 */       ShaderHelper.releaseShader();
/* 91 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileVoidSiphonRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */