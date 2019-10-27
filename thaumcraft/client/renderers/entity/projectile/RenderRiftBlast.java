/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.ARBShaderObjects;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.ender.ShaderCallback;
/*     */ import thaumcraft.client.lib.ender.ShaderHelper;
/*     */ import thaumcraft.common.entities.projectile.EntityRiftBlast;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderRiftBlast
/*     */   extends Render
/*     */ {
/*     */   private final ShaderCallback shaderCallback;
/*     */   
/*     */   public RenderRiftBlast(RenderManager rm) {
/*  28 */     super(rm);
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
/*  48 */     this.gle = new CoreGLE(); this.field_76989_e = 0.0F; this.shaderCallback = new ShaderCallback() { public void call(int shader) { Minecraft mc = Minecraft.func_71410_x(); int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw"); ARBShaderObjects.glUniform1fARB(x, (float)((mc.field_71439_g.field_70177_z * 2.0F) * Math.PI / 360.0D));
/*     */           int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
/*     */           ARBShaderObjects.glUniform1fARB(z, -((float)((mc.field_71439_g.field_70125_A * 2.0F) * Math.PI / 360.0D))); } };
/*  51 */   } public void renderEntityAt(EntityRiftBlast entity, double x, double y, double z, float fq, float pticks) { Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  53 */     GL11.glPushMatrix();
/*  54 */     GL11.glTranslated(x, y, z);
/*  55 */     float xx = (float)(entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * pticks);
/*  56 */     float yy = (float)(entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * pticks);
/*  57 */     float zz = (float)(entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * pticks);
/*  58 */     GL11.glTranslated(-xx, -yy, -zz);
/*  59 */     GL11.glEnable(3042);
/*     */     
/*  61 */     for (int q = 0; q <= 1; q++) {
/*     */       
/*  63 */       if (q < 1) {
/*  64 */         GlStateManager.func_179132_a(false);
/*     */       }
/*     */       
/*  67 */       GL11.glBlendFunc(770, (q < 1) ? 1 : 771);
/*     */       
/*  69 */       if (entity.points != null && entity.points.length > 2) {
/*     */         
/*  71 */         (Minecraft.func_71410_x()).field_71446_o.func_110577_a(starsTexture);
/*  72 */         ShaderHelper.useShader(ShaderHelper.endShader, this.shaderCallback);
/*     */         
/*  74 */         double[] r2 = new double[entity.radii.length];
/*  75 */         int ri = 0;
/*  76 */         float m = (1.5F - q) / 1.0F;
/*  77 */         for (double d : entity.radii) {
/*  78 */           r2[ri] = entity.radii[ri] * m;
/*  79 */           ri++;
/*     */         } 
/*  81 */         this.gle.set_POLYCYL_TESS(3);
/*  82 */         this.gle.set__ROUND_TESS_PIECES(1);
/*  83 */         this.gle.gleSetJoinStyle(1042);
/*  84 */         this.gle.glePolyCone(entity.points.length, entity.points, entity.colours, r2, 1.0F / entity.points.length, 0.0F);
/*  85 */         ShaderHelper.releaseShader();
/*     */       } 
/*     */       
/*  88 */       if (q < 1) {
/*  89 */         GlStateManager.func_179132_a(true);
/*     */       }
/*     */     } 
/*     */     
/*  93 */     GL11.glDisable(3042);
/*  94 */     GL11.glDisable(32826);
/*  95 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */     
/*  98 */     GL11.glPopMatrix(); }
/*     */ 
/*     */   
/*     */   private static final ResourceLocation starsTexture = new ResourceLocation("textures/entity/end_portal.png");
/*     */   CoreGLE gle;
/*     */   
/* 104 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt((EntityRiftBlast)entity, d, d1, d2, f, f1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderRiftBlast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */