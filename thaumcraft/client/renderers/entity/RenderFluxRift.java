/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import org.lwjgl.opengl.ARBShaderObjects;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.ender.ShaderCallback;
/*     */ import thaumcraft.client.lib.ender.ShaderHelper;
/*     */ import thaumcraft.common.entities.EntityFluxRift;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderFluxRift
/*     */   extends Render
/*     */ {
/*     */   private final ShaderCallback shaderCallback;
/*  29 */   private static final ResourceLocation starsTexture = new ResourceLocation("textures/entity/end_portal.png");
/*  30 */   CoreGLE gle = new CoreGLE();
/*     */ 
/*     */   
/*     */   public RenderFluxRift(RenderManager rm) {
/*  34 */     super(rm);
/*  35 */     this.field_76989_e = 0.0F;
/*  36 */     this.shaderCallback = new ShaderCallback()
/*     */       {
/*     */         public void call(int shader) {
/*  39 */           Minecraft mc = Minecraft.func_71410_x();
/*     */           
/*  41 */           int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
/*  42 */           ARBShaderObjects.glUniform1fARB(x, (float)((mc.field_71439_g.field_70177_z * 2.0F) * Math.PI / 360.0D));
/*     */           
/*  44 */           int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
/*  45 */           ARBShaderObjects.glUniform1fARB(z, -((float)((mc.field_71439_g.field_70125_A * 2.0F) * Math.PI / 360.0D)));
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pt) {
/*  55 */     EntityFluxRift rift = (EntityFluxRift)entity;
/*     */     
/*  57 */     boolean goggles = EntityUtils.hasGoggles((Minecraft.func_71410_x()).field_71439_g);
/*     */     
/*  59 */     GL11.glPushMatrix();
/*  60 */     func_110776_a(starsTexture);
/*  61 */     ShaderHelper.useShader(ShaderHelper.endShader, this.shaderCallback);
/*     */     
/*  63 */     float amp = 1.0F;
/*  64 */     float stab = MathHelper.func_76131_a(1.0F - rift.getRiftStability() / 50.0F, 0.0F, 1.5F);
/*     */     
/*  66 */     GL11.glEnable(3042);
/*  67 */     for (int q = 0; q <= 3; q++) {
/*  68 */       if (q < 3) {
/*  69 */         GlStateManager.func_179132_a(false);
/*  70 */         if (q == 0 && goggles) GL11.glDisable(2929); 
/*     */       } 
/*  72 */       GL11.glBlendFunc(770, (q < 3) ? 1 : 771);
/*  73 */       if (rift.points.size() > 2) {
/*  74 */         GL11.glPushMatrix();
/*  75 */         double[][] pp = new double[rift.points.size()][3];
/*  76 */         float[][] colours = new float[rift.points.size()][4];
/*  77 */         double[] radii = new double[rift.points.size()];
/*  78 */         for (int a = 0; a < rift.points.size(); a++) {
/*  79 */           float var = rift.field_70173_aa + pt;
/*  80 */           if (a > rift.points.size() / 2) { var -= (a * 10); }
/*  81 */           else if (a < rift.points.size() / 2) { var += (a * 10); }
/*  82 */            pp[a][0] = ((Vec3d)rift.points.get(a)).field_72450_a + x + Math.sin((var / 50.0F * amp)) * 0.10000000149011612D * stab;
/*  83 */           pp[a][1] = ((Vec3d)rift.points.get(a)).field_72448_b + y + Math.sin((var / 60.0F * amp)) * 0.10000000149011612D * stab;
/*  84 */           pp[a][2] = ((Vec3d)rift.points.get(a)).field_72449_c + z + Math.sin((var / 70.0F * amp)) * 0.10000000149011612D * stab;
/*  85 */           colours[a][0] = 1.0F;
/*  86 */           colours[a][1] = 1.0F;
/*  87 */           colours[a][2] = 1.0F;
/*  88 */           colours[a][3] = 1.0F;
/*  89 */           double w = 1.0D - Math.sin((var / 8.0F * amp)) * 0.10000000149011612D * stab;
/*  90 */           radii[a] = ((Float)rift.pointsWidth.get(a)).floatValue() * w * ((q < 3) ? (1.25F + 0.5F * q) : 1.0F);
/*     */         } 
/*  92 */         this.gle.set_POLYCYL_TESS(6);
/*  93 */         this.gle.gleSetJoinStyle(1026);
/*  94 */         this.gle.glePolyCone(pp.length, pp, colours, radii, 1.0F, 0.0F);
/*     */         
/*  96 */         GL11.glPopMatrix();
/*     */       } 
/*  98 */       if (q < 3) {
/*  99 */         GlStateManager.func_179132_a(true);
/* 100 */         if (q == 0 && goggles) GL11.glEnable(2929);
/*     */       
/*     */       } 
/*     */     } 
/* 104 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 105 */     GL11.glBlendFunc(770, 771);
/* 106 */     GL11.glDisable(3042);
/*     */ 
/*     */     
/* 109 */     ShaderHelper.releaseShader();
/* 110 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\RenderFluxRift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */