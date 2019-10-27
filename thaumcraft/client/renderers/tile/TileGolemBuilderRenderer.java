/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.lib.obj.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.obj.IModelCustom;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileGolemBuilderRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  27 */   private static final ResourceLocation TM = new ResourceLocation("thaumcraft", "models/block/golembuilder.obj");
/*  28 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/blocks/golembuilder.png");
/*     */   
/*  30 */   private IModelCustom model = AdvancedModelLoader.loadModel(TM);
/*     */ 
/*     */   
/*  33 */   EntityItem entityitem = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileGolemBuilder tile, double par2, double par4, double par6, float pt, int destroyStage) {
/*  41 */     GL11.glPushMatrix();
/*  42 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/*  43 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  45 */     func_147499_a(TEX);
/*     */     
/*  47 */     if (destroyStage >= 0) {
/*     */       
/*  49 */       func_147499_a(field_178460_a[destroyStage]);
/*  50 */       GlStateManager.func_179128_n(5890);
/*  51 */       GlStateManager.func_179094_E();
/*  52 */       GlStateManager.func_179152_a(5.0F, 5.0F, 2.0F);
/*  53 */       GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
/*  54 */       GlStateManager.func_179128_n(5888);
/*     */     } else {
/*  56 */       GL11.glMatrixMode(5890);
/*  57 */       GL11.glLoadIdentity();
/*  58 */       GL11.glScalef(1.0F, -1.0F, 1.0F);
/*  59 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  64 */     EnumFacing facing = BlockStateUtils.getFacing(tile.func_145832_p());
/*  65 */     if (tile.func_145831_w() != null) {
/*  66 */       switch (facing.ordinal()) { case 5:
/*  67 */           GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/*  68 */         case 4: GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  69 */         case 3: GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */           break; }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  75 */     this.model.renderAllExcept(new String[] { "press" });
/*     */     
/*  77 */     GL11.glPushMatrix();
/*  78 */     float h = tile.press;
/*  79 */     double s = Math.sin(Math.toRadians(h)) * 0.625D;
/*  80 */     GL11.glTranslated(0.0D, -s, 0.0D);
/*  81 */     this.model.renderPart("press");
/*  82 */     GL11.glPopMatrix();
/*     */     
/*  84 */     if (destroyStage >= 0) {
/*     */       
/*  86 */       GlStateManager.func_179128_n(5890);
/*  87 */       GlStateManager.func_179121_F();
/*  88 */       GlStateManager.func_179128_n(5888);
/*     */     } else {
/*  90 */       GL11.glMatrixMode(5890);
/*  91 */       GL11.glLoadIdentity();
/*  92 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  93 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  98 */     GL11.glTranslatef(-0.3125F, 0.625F, 1.3125F);
/*  99 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 100 */     TextureAtlasSprite icon = Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(Blocks.field_150353_l.func_176223_P());
/* 101 */     UtilsFX.renderQuadFromIcon(icon, 0.625F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*     */     
/* 103 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 109 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 110 */     renderTileEntityAt((TileGolemBuilder)te, x, y, z, partialTicks, destroyStage);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileGolemBuilderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */