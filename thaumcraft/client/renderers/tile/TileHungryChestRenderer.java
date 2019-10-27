/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.model.ModelChest;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.tiles.devices.TileHungryChest;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileHungryChestRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  19 */   private ModelChest chestModel = new ModelChest();
/*  20 */   private static final ResourceLocation textureNormal = new ResourceLocation("thaumcraft", "textures/models/chesthungry.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityChestAt(TileHungryChest chest, double par2, double par4, double par6, float par8, int bp) {
/*  27 */     int var9 = 0;
/*     */     
/*  29 */     if (!chest.func_145830_o()) {
/*     */       
/*  31 */       var9 = 0;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  36 */       var9 = chest.func_145832_p();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  41 */     ModelChest var14 = this.chestModel;
/*     */     
/*  43 */     if (bp >= 0) {
/*     */       
/*  45 */       func_147499_a(field_178460_a[bp]);
/*  46 */       GlStateManager.func_179128_n(5890);
/*  47 */       GlStateManager.func_179094_E();
/*  48 */       GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
/*  49 */       GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
/*  50 */       GlStateManager.func_179128_n(5888);
/*     */     } else {
/*  52 */       func_147499_a(textureNormal);
/*     */     } 
/*  54 */     GL11.glPushMatrix();
/*  55 */     GL11.glEnable(32826);
/*  56 */     if (bp < 0)
/*     */     {
/*  58 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     
/*  61 */     GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
/*  62 */     GL11.glScalef(1.0F, -1.0F, -1.0F);
/*  63 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*  64 */     short var11 = 0;
/*     */     
/*  66 */     if (var9 == 2)
/*     */     {
/*  68 */       var11 = 180;
/*     */     }
/*     */     
/*  71 */     if (var9 == 3)
/*     */     {
/*  73 */       var11 = 0;
/*     */     }
/*     */     
/*  76 */     if (var9 == 4)
/*     */     {
/*  78 */       var11 = 90;
/*     */     }
/*     */     
/*  81 */     if (var9 == 5)
/*     */     {
/*  83 */       var11 = -90;
/*     */     }
/*     */     
/*  86 */     GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
/*  87 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  88 */     float var12 = chest.field_145986_n + (chest.field_145989_m - chest.field_145986_n) * par8;
/*     */ 
/*     */     
/*  91 */     var12 = 1.0F - var12;
/*  92 */     var12 = 1.0F - var12 * var12 * var12;
/*  93 */     var14.field_78234_a.field_78795_f = -(var12 * 3.1415927F / 2.0F);
/*  94 */     var14.func_78231_a();
/*  95 */     GL11.glDisable(32826);
/*  96 */     GL11.glPopMatrix();
/*  97 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  99 */     if (bp >= 0) {
/*     */       
/* 101 */       GlStateManager.func_179128_n(5890);
/* 102 */       GlStateManager.func_179121_F();
/* 103 */       GlStateManager.func_179128_n(5888);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) { renderTileEntityChestAt((TileHungryChest)te, x, y, z, partialTicks, destroyStage); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileHungryChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */