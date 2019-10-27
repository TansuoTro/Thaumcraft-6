/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.lib.RenderCubes;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.block.ModelBrain;
/*     */ import thaumcraft.client.renderers.models.block.ModelJar;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*     */ import thaumcraft.common.tiles.essentia.TileJar;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileJarRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  35 */   private ModelJar model = new ModelJar();
/*  36 */   private ModelBrain brain = new ModelBrain();
/*     */   
/*  38 */   private static final ResourceLocation TEX_LABEL = new ResourceLocation("thaumcraft", "textures/models/label.png");
/*  39 */   private static final ResourceLocation TEX_BRAIN = new ResourceLocation("thaumcraft", "textures/models/brain2.png");
/*  40 */   private static final ResourceLocation TEX_BRINE = new ResourceLocation("thaumcraft", "textures/models/jarbrine.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileJar tile, double x, double y, double z, float f) {
/*  47 */     GL11.glPushMatrix();
/*  48 */     GL11.glDisable(2884);
/*     */     
/*  50 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.01F, (float)z + 0.5F);
/*  51 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/*  53 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  55 */     if (tile instanceof TileJarBrain) {
/*  56 */       renderBrain((TileJarBrain)tile, x, y, z, f);
/*     */     }
/*  58 */     else if (tile instanceof TileJarFillable) {
/*  59 */       GL11.glDisable(2896);
/*     */       
/*  61 */       if (((TileJarFillable)tile).blocked) {
/*  62 */         GL11.glPushMatrix();
/*  63 */         func_147499_a(TEX_BRINE);
/*  64 */         GL11.glScaled(1.001D, 1.001D, 1.001D);
/*  65 */         this.model.renderLidBrace();
/*  66 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/*  69 */       if (ThaumcraftApiHelper.getConnectableTile(tile.func_145831_w(), tile.func_174877_v(), EnumFacing.UP) != null) {
/*  70 */         GL11.glPushMatrix();
/*  71 */         func_147499_a(TEX_BRINE);
/*  72 */         GL11.glScaled(0.9D, 1.0D, 0.9D);
/*  73 */         this.model.renderLidExtension();
/*  74 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/*  77 */       if (((TileJarFillable)tile).aspectFilter != null) {
/*  78 */         GL11.glPushMatrix();
/*  79 */         GL11.glBlendFunc(770, 771);
/*  80 */         switch (((TileJarFillable)tile).facing) { case 3:
/*  81 */             GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/*  82 */           case 5: GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  83 */           case 4: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*     */             break; }
/*     */         
/*  86 */         float rot = ((((TileJarFillable)tile).aspectFilter.getTag().hashCode() + tile.func_174877_v().func_177958_n() + ((TileJarFillable)tile).facing) % 4 - 2);
/*     */         
/*  88 */         GL11.glPushMatrix();
/*  89 */         GL11.glTranslatef(0.0F, -0.4F, 0.315F);
/*  90 */         if (ModConfig.CONFIG_GRAPHICS.crooked) GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F); 
/*  91 */         UtilsFX.renderQuadCentered(TEX_LABEL, 0.5F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
/*  92 */         GL11.glPopMatrix();
/*     */         
/*  94 */         GL11.glPushMatrix();
/*  95 */         GL11.glTranslatef(0.0F, -0.4F, 0.316F);
/*  96 */         if (ModConfig.CONFIG_GRAPHICS.crooked) GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F); 
/*  97 */         GL11.glScaled(0.021D, 0.021D, 0.021D);
/*  98 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */         
/* 100 */         UtilsFX.drawTag(-8, -8, ((TileJarFillable)tile).aspectFilter);
/* 101 */         GL11.glPopMatrix();
/*     */         
/* 103 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/* 106 */       if (((TileJarFillable)tile).amount > 0) {
/* 107 */         renderLiquid((TileJarFillable)tile, x, y, z, f);
/*     */       }
/* 109 */       GL11.glEnable(2896);
/*     */     } 
/*     */     
/* 112 */     GL11.glEnable(2884);
/* 113 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderLiquid(TileJarFillable te, double x, double y, double z, float f) {
/* 119 */     GL11.glPushMatrix();
/* 120 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/* 122 */     World world = te.func_145831_w();
/* 123 */     RenderCubes renderBlocks = new RenderCubes();
/*     */     
/* 125 */     GL11.glDisable(2896);
/*     */     
/* 127 */     te; float level = te.amount / 250.0F * 0.625F;
/*     */     
/* 129 */     Tessellator t = Tessellator.func_178181_a();
/*     */     
/* 131 */     renderBlocks.setRenderBounds(0.25D, 0.0625D, 0.25D, 0.75D, 0.0625D + level, 0.75D);
/*     */     
/* 133 */     t.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/* 134 */     Color co = new Color(0);
/* 135 */     if (te.aspect != null) {
/* 136 */       co = new Color(te.aspect.getColor());
/*     */     }
/*     */     
/* 139 */     TextureAtlasSprite icon = Minecraft.func_71410_x().func_147117_R().func_110572_b("thaumcraft:blocks/animatedglow");
/*     */     
/* 141 */     func_147499_a(TextureMap.field_110575_b);
/*     */     
/* 143 */     renderBlocks.renderFaceYNeg(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 144 */     renderBlocks.renderFaceYPos(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 145 */     renderBlocks.renderFaceZNeg(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 146 */     renderBlocks.renderFaceZPos(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 147 */     renderBlocks.renderFaceXNeg(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 148 */     renderBlocks.renderFaceXPos(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/*     */     
/* 150 */     t.func_78381_a();
/* 151 */     GL11.glEnable(2896);
/*     */     
/* 153 */     GL11.glPopMatrix();
/*     */     
/* 155 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderBrain(TileJarBrain te, double x, double y, double z, float f) {
/* 161 */     float bob = MathHelper.func_76126_a((Minecraft.func_71410_x()).field_71439_g.field_70173_aa / 14.0F) * 0.03F + 0.03F;
/* 162 */     GL11.glPushMatrix();
/* 163 */     GL11.glTranslatef(0.0F, -0.8F + bob, 0.0F);
/*     */     float f2;
/* 165 */     for (f2 = te.rota - te.rotb; f2 >= 3.141593F; f2 -= 6.283185F);
/* 166 */     for (; f2 < -3.141593F; f2 += 6.283185F);
/* 167 */     float f3 = te.rotb + f2 * f;
/* 168 */     GL11.glRotatef(f3 * 180.0F / 3.141593F, 0.0F, 1.0F, 0.0F);
/* 169 */     GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 170 */     func_147499_a(TEX_BRAIN);
/* 171 */     GL11.glScalef(0.4F, 0.4F, 0.4F);
/* 172 */     this.brain.render();
/* 173 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 174 */     GL11.glPopMatrix();
/* 175 */     GL11.glPushMatrix();
/* 176 */     func_147499_a(TEX_BRINE);
/* 177 */     this.model.renderBrine();
/* 178 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 183 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 184 */     renderTileEntityAt((TileJar)te, x, y, z, partialTicks);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileJarRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */