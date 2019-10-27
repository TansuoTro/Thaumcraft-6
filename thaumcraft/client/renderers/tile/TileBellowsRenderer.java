/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.block.ModelBellows;
/*     */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
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
/*     */ public class TileBellowsRenderer
/*     */   extends TileEntitySpecialRenderer<TileBellows>
/*     */ {
/*  29 */   private ModelBellows model = new ModelBellows();
/*  30 */   private ModelBoreBase model2 = new ModelBoreBase();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/blocks/bellows.png");
/*  36 */   private static final ResourceLocation TEX_BORE = new ResourceLocation("thaumcraft", "textures/models/bore.png");
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(TileBellows bellows, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/*  41 */     super.func_192841_a(bellows, x, y, z, partialTicks, destroyStage, alpha);
/*     */     
/*  43 */     float scale = 0.0F;
/*  44 */     EnumFacing dir = EnumFacing.WEST;
/*  45 */     boolean extension = false;
/*  46 */     if (bellows == null) {
/*  47 */       EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*  48 */       scale = MathHelper.func_76126_a(entityPlayerSP.field_70173_aa / 8.0F) * 0.3F + 0.7F;
/*     */     } else {
/*  50 */       scale = bellows.inflation;
/*  51 */       dir = BlockStateUtils.getFacing(bellows.func_145832_p());
/*  52 */       TileEntity te = bellows.func_145831_w().func_175625_s(bellows.func_174877_v().func_177972_a(BlockStateUtils.getFacing(bellows.func_145832_p())));
/*  53 */       if (te != null && te instanceof thaumcraft.common.tiles.essentia.TileTubeBuffer) {
/*  54 */         extension = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  60 */     float tscale = 0.125F + scale * 0.875F;
/*     */ 
/*     */     
/*  63 */     if (extension) {
/*  64 */       func_147499_a(TEX_BORE);
/*  65 */       GL11.glPushMatrix();
/*  66 */       GL11.glTranslatef((float)x + 0.5F + dir.func_82601_c(), (float)y + dir.func_96559_d(), (float)z + 0.5F + dir.func_82599_e());
/*  67 */       switch (dir.func_176734_d().ordinal()) { case 0:
/*  68 */           GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/*  69 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/*  70 */         case 1: GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  71 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/*  72 */         case 2: GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  73 */         case 3: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/*  74 */         case 4: GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/*  75 */         case 5: GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F); break; }
/*     */       
/*  77 */       this.model2.renderNozzle();
/*  78 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/*  81 */     func_147499_a(TEX);
/*  82 */     GL11.glPushMatrix();
/*  83 */     GL11.glEnable(2977);
/*  84 */     GL11.glEnable(32826);
/*  85 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  86 */     translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal());
/*     */     
/*  88 */     if (destroyStage >= 0) {
/*     */       
/*  90 */       func_147499_a(field_178460_a[destroyStage]);
/*  91 */       GlStateManager.func_179128_n(5890);
/*  92 */       GlStateManager.func_179094_E();
/*  93 */       GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
/*  94 */       GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
/*  95 */       GlStateManager.func_179128_n(5888);
/*     */     } 
/*     */     
/*  98 */     GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/*     */     
/* 100 */     GL11.glPushMatrix();
/* 101 */     GL11.glScalef(0.5F, (scale + 0.1F) / 2.0F, 0.5F);
/* 102 */     this.model.Bag.func_78793_a(0.0F, 0.5F, 0.0F);
/* 103 */     this.model.Bag.func_78785_a(0.0625F);
/* 104 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 105 */     GL11.glPopMatrix();
/*     */     
/* 107 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*     */     
/* 109 */     GL11.glPushMatrix();
/* 110 */     GL11.glTranslatef(0.0F, -tscale / 2.0F + 0.5F, 0.0F);
/* 111 */     this.model.TopPlank.func_78785_a(0.0625F);
/* 112 */     GL11.glTranslatef(0.0F, tscale / 2.0F - 0.5F, 0.0F);
/* 113 */     GL11.glPopMatrix();
/*     */     
/* 115 */     GL11.glPushMatrix();
/* 116 */     GL11.glTranslatef(0.0F, tscale / 2.0F - 0.5F, 0.0F);
/* 117 */     this.model.BottomPlank.func_78785_a(0.0625F);
/* 118 */     GL11.glTranslatef(0.0F, -tscale / 2.0F + 0.5F, 0.0F);
/* 119 */     GL11.glPopMatrix();
/*     */     
/* 121 */     this.model.render();
/* 122 */     GL11.glDisable(32826);
/* 123 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 124 */     GL11.glPopMatrix();
/*     */ 
/*     */     
/* 127 */     if (destroyStage >= 0) {
/*     */       
/* 129 */       GlStateManager.func_179128_n(5890);
/* 130 */       GlStateManager.func_179121_F();
/* 131 */       GlStateManager.func_179128_n(5888);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation) {
/* 138 */     GL11.glTranslatef((float)x + 0.5F, (float)y - 0.5F, (float)z + 0.5F);
/* 139 */     if (orientation == 0) {
/* 140 */       GL11.glTranslatef(0.0F, 1.0F, -1.0F);
/* 141 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 142 */     } else if (orientation == 1) {
/* 143 */       GL11.glTranslatef(0.0F, 1.0F, 1.0F);
/* 144 */       GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
/* 145 */     } else if (orientation == 2) {
/* 146 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 147 */     } else if (orientation == 4) {
/* 148 */       GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/* 149 */     } else if (orientation == 5) {
/* 150 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileBellowsRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */