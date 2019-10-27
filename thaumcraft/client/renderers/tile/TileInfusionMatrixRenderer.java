/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileInfusionMatrixRenderer
/*     */   extends TileEntitySpecialRenderer<TileInfusionMatrix>
/*     */ {
/*  29 */   private ModelCube model = new ModelCube(0);
/*  30 */   private ModelCube model_over = new ModelCube(32);
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHalo(TileEntity is, double x, double y, double z, float par8, int count) {
/*  35 */     GL11.glPushMatrix();
/*  36 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */     
/*  38 */     int q = !(FMLClientHandler.instance().getClient()).field_71474_y.field_74347_j ? 10 : 20;
/*     */     
/*  40 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  42 */     RenderHelper.func_74518_a();
/*  43 */     float f1 = count / 500.0F;
/*  44 */     float f3 = 0.9F;
/*  45 */     float f2 = 0.0F;
/*     */     
/*  47 */     Random random = new Random(245L);
/*  48 */     GL11.glDisable(3553);
/*  49 */     GL11.glShadeModel(7425);
/*  50 */     GL11.glEnable(3042);
/*  51 */     GL11.glBlendFunc(770, 1);
/*  52 */     GL11.glDisable(3008);
/*  53 */     GL11.glEnable(2884);
/*  54 */     GL11.glDepthMask(false);
/*  55 */     GL11.glPushMatrix();
/*  56 */     for (int i = 0; i < q; i++) {
/*     */       
/*  58 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  59 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  60 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  61 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  62 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  63 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*  64 */       tessellator.func_178180_c().func_181668_a(6, DefaultVertexFormats.field_181706_f);
/*  65 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  66 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  67 */       fa /= 20.0F / Math.min(count, 50) / 50.0F;
/*  68 */       f4 /= 20.0F / Math.min(count, 50) / 50.0F;
/*  69 */       tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, 0.0D).func_181669_b(255, 255, 255, (int)(255.0F * (1.0F - f1))).func_181675_d();
/*  70 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, (-0.5F * f4)).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  71 */       tessellator.func_178180_c().func_181662_b(0.866D * f4, fa, (-0.5F * f4)).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  72 */       tessellator.func_178180_c().func_181662_b(0.0D, fa, (1.0F * f4)).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  73 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, (-0.5F * f4)).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  74 */       tessellator.func_78381_a();
/*     */     } 
/*     */     
/*  77 */     GL11.glPopMatrix();
/*  78 */     GL11.glDepthMask(true);
/*  79 */     GL11.glDisable(2884);
/*  80 */     GL11.glDisable(3042);
/*  81 */     GL11.glShadeModel(7424);
/*  82 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  83 */     GL11.glEnable(3553);
/*  84 */     GL11.glEnable(3008);
/*  85 */     RenderHelper.func_74519_b();
/*  86 */     GL11.glBlendFunc(770, 771);
/*  87 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*  90 */   private static final ResourceLocation tex1 = new ResourceLocation("thaumcraft", "textures/blocks/infuser_normal.png");
/*  91 */   private static final ResourceLocation tex2 = new ResourceLocation("thaumcraft", "textures/blocks/infuser_ancient.png");
/*  92 */   private static final ResourceLocation tex3 = new ResourceLocation("thaumcraft", "textures/blocks/infuser_eldritch.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInfusionMatrix(TileInfusionMatrix is, double par2, double par4, double par6, float par8, int destroyStage) {
/* 100 */     GL11.glPushMatrix();
/*     */     
/* 102 */     ResourceLocation t = tex1;
/*     */     
/* 104 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.5F, (float)par6 + 0.5F);
/* 105 */     float ticks = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa + par8;
/*     */ 
/*     */ 
/*     */     
/* 109 */     float inst = 0.0F;
/* 110 */     int craftcount = 0;
/* 111 */     float startup = 0.0F;
/* 112 */     boolean active = false;
/* 113 */     boolean crafting = false;
/*     */     
/* 115 */     if (is != null && is.func_145831_w() != null) {
/* 116 */       GL11.glRotatef(ticks % 360.0F * is.startUp, 0.0F, 1.0F, 0.0F);
/* 117 */       GL11.glRotatef(35.0F * is.startUp, 1.0F, 0.0F, 0.0F);
/* 118 */       GL11.glRotatef(45.0F * is.startUp, 0.0F, 0.0F, 1.0F);
/* 119 */       IBlockState bs = is.func_145831_w().func_180495_p(is.func_174877_v().func_177982_a(-1, -2, -1));
/* 120 */       if (bs.func_177230_c() == BlocksTC.pillarAncient) t = tex2; 
/* 121 */       if (bs.func_177230_c() == BlocksTC.pillarEldritch) t = tex3; 
/* 122 */       inst = is.stability;
/* 123 */       craftcount = is.craftCount;
/* 124 */       startup = is.startUp;
/* 125 */       active = is.active;
/* 126 */       crafting = is.crafting;
/*     */     } 
/*     */     
/* 129 */     func_147499_a(t);
/*     */     
/* 131 */     if (destroyStage >= 0) {
/*     */       
/* 133 */       func_147499_a(field_178460_a[destroyStage]);
/* 134 */       GlStateManager.func_179128_n(5890);
/* 135 */       GlStateManager.func_179094_E();
/* 136 */       GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
/* 137 */       GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
/* 138 */       GlStateManager.func_179128_n(5888);
/*     */     } 
/*     */     
/* 141 */     float instability = Math.min(6.0F, 1.0F + ((inst < 0.0F) ? (-inst * 0.66F) : 1.0F) * Math.min(craftcount, 50) / 50.0F);
/*     */     
/* 143 */     float b1 = 0.0F;
/* 144 */     float b2 = 0.0F;
/* 145 */     float b3 = 0.0F;
/* 146 */     int aa = 0;
/* 147 */     int bb = 0;
/* 148 */     int cc = 0;
/* 149 */     for (int a = 0; a < 2; a++) {
/* 150 */       for (int b = 0; b < 2; b++) {
/* 151 */         for (int c = 0; c < 2; c++) {
/* 152 */           if (active) {
/* 153 */             b1 = MathHelper.func_76126_a((ticks + (a * 10)) / 15.0F) * 0.01F * startup * instability;
/* 154 */             b2 = MathHelper.func_76126_a((ticks + (b * 10)) / 14.0F) * 0.01F * startup * instability;
/* 155 */             b3 = MathHelper.func_76126_a((ticks + (c * 10)) / 13.0F) * 0.01F * startup * instability;
/*     */           } 
/*     */           
/* 158 */           aa = (a == 0) ? -1 : 1;
/* 159 */           bb = (b == 0) ? -1 : 1;
/* 160 */           cc = (c == 0) ? -1 : 1;
/*     */           
/* 162 */           GL11.glPushMatrix();
/* 163 */           GL11.glTranslatef(b1 + aa * 0.25F, b2 + bb * 0.25F, b3 + cc * 0.25F);
/* 164 */           if (a > 0) GL11.glRotatef(90.0F, a, 0.0F, 0.0F); 
/* 165 */           if (b > 0) GL11.glRotatef(90.0F, 0.0F, b, 0.0F); 
/* 166 */           if (c > 0) GL11.glRotatef(90.0F, 0.0F, 0.0F, c);
/*     */           
/* 168 */           GL11.glScaled(0.45D, 0.45D, 0.45D);
/*     */           
/* 170 */           this.model.render();
/*     */           
/* 172 */           GL11.glPopMatrix();
/*     */         } 
/*     */       } 
/*     */     } 
/* 176 */     if (active) {
/* 177 */       GL11.glPushMatrix();
/* 178 */       GL11.glAlphaFunc(516, 0.003921569F);
/* 179 */       GL11.glEnable(3042);
/* 180 */       GL11.glBlendFunc(770, 1);
/*     */       
/* 182 */       for (int a = 0; a < 2; a++) {
/* 183 */         for (int b = 0; b < 2; b++) {
/* 184 */           for (int c = 0; c < 2; c++) {
/*     */             
/* 186 */             b1 = MathHelper.func_76126_a((ticks + (a * 10)) / 15.0F) * 0.01F * startup * instability;
/* 187 */             b2 = MathHelper.func_76126_a((ticks + (b * 10)) / 14.0F) * 0.01F * startup * instability;
/* 188 */             b3 = MathHelper.func_76126_a((ticks + (c * 10)) / 13.0F) * 0.01F * startup * instability;
/*     */             
/* 190 */             aa = (a == 0) ? -1 : 1;
/* 191 */             bb = (b == 0) ? -1 : 1;
/* 192 */             cc = (c == 0) ? -1 : 1;
/*     */             
/* 194 */             GL11.glPushMatrix();
/*     */             
/* 196 */             GL11.glTranslatef(b1 + aa * 0.25F, b2 + bb * 0.25F, b3 + cc * 0.25F);
/* 197 */             if (a > 0) GL11.glRotatef(90.0F, a, 0.0F, 0.0F); 
/* 198 */             if (b > 0) GL11.glRotatef(90.0F, 0.0F, b, 0.0F); 
/* 199 */             if (c > 0) GL11.glRotatef(90.0F, 0.0F, 0.0F, c);
/*     */             
/* 201 */             GL11.glScaled(0.45D, 0.45D, 0.45D);
/*     */             
/* 203 */             int j = 15728880;
/* 204 */             int k = j % 65536;
/* 205 */             int l = j / 65536;
/* 206 */             OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 207 */             GL11.glColor4f(0.8F, 0.1F, 1.0F, (MathHelper.func_76126_a((ticks + (a * 2) + (b * 3) + (c * 4)) / 4.0F) * 0.1F + 0.2F) * startup);
/* 208 */             this.model_over.render();
/* 209 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */             
/* 211 */             GL11.glPopMatrix();
/*     */           } 
/*     */         } 
/*     */       } 
/* 215 */       GL11.glBlendFunc(770, 771);
/* 216 */       GL11.glDisable(3042);
/* 217 */       GL11.glAlphaFunc(516, 0.1F);
/* 218 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 221 */     if (destroyStage >= 0) {
/*     */       
/* 223 */       GlStateManager.func_179128_n(5890);
/* 224 */       GlStateManager.func_179121_F();
/* 225 */       GlStateManager.func_179128_n(5888);
/*     */     } 
/*     */     
/* 228 */     GL11.glPopMatrix();
/*     */ 
/*     */ 
/*     */     
/* 232 */     if (crafting) {
/* 233 */       drawHalo(is, par2, par4, par6, par8, craftcount);
/*     */     }
/* 235 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(TileInfusionMatrix te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 240 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 241 */     renderInfusionMatrix(te, x, y, z, partialTicks, destroyStage);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileInfusionMatrixRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */