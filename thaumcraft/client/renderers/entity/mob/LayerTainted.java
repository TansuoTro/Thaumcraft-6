/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class LayerTainted
/*    */   extends Object implements LayerRenderer<EntityLiving> {
/* 16 */   private static final ResourceLocation TAINT_TEXTURE = new ResourceLocation("thaumcraft:textures/models/taint_fibres.png");
/*    */   
/*    */   private final RenderLivingBase renderer;
/*    */   private final ModelBase model;
/* 20 */   public static ArrayList<Integer> taintLayers = new ArrayList();
/*    */ 
/*    */   
/*    */   public LayerTainted(int i, RenderLivingBase witherRendererIn, ModelBase model) {
/* 24 */     this.renderer = witherRendererIn;
/* 25 */     this.model = model;
/* 26 */     taintLayers.add(Integer.valueOf(i));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLiving entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
/* 32 */     if (!taintLayers.contains(Integer.valueOf(entitylivingbaseIn.func_145782_y())))
/*    */       return; 
/* 34 */     boolean flag = entitylivingbaseIn.func_82150_aj();
/* 35 */     GlStateManager.func_179132_a(!flag);
/*    */     
/* 37 */     this.renderer.func_110776_a(TAINT_TEXTURE);
/* 38 */     GlStateManager.func_179128_n(5890);
/* 39 */     GlStateManager.func_179096_D();
/* 40 */     float f = entitylivingbaseIn.func_145782_y();
/* 41 */     float f1 = MathHelper.func_76134_b(f * 2.5E-4F);
/* 42 */     float f2 = f * 0.001F;
/* 43 */     GlStateManager.func_179152_a(8.0F, 4.0F, 4.0F);
/* 44 */     GlStateManager.func_179109_b(f1, f2, 0.0F);
/* 45 */     GlStateManager.func_179128_n(5888);
/* 46 */     GlStateManager.func_179147_l();
/* 47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/*    */     
/* 49 */     GL11.glBlendFunc(770, 771);
/* 50 */     this.model.func_78086_a(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
/* 51 */     this.model.func_178686_a(this.renderer.func_177087_b());
/* 52 */     this.model.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
/* 53 */     GlStateManager.func_179128_n(5890);
/* 54 */     GlStateManager.func_179096_D();
/* 55 */     GlStateManager.func_179128_n(5888);
/* 56 */     GlStateManager.func_179084_k();
/* 57 */     GlStateManager.func_179132_a(flag);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public boolean func_177142_b() { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\LayerTainted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */