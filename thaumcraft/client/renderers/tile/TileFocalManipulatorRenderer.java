/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileFocalManipulatorRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  31 */   EntityItem entityitem = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileFocalManipulator table, double par2, double par4, double par6, float par8) {
/*  36 */     if (table.func_145831_w() != null) {
/*  37 */       float ticks = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa + par8;
/*  38 */       if (table.getSyncedStackInSlot(false) != null) {
/*  39 */         GL11.glPushMatrix();
/*  40 */         GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.8F, (float)par6 + 0.5F);
/*  41 */         GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/*  42 */         ItemStack is = table.getSyncedStackInSlot(0).func_77946_l();
/*  43 */         this.entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/*  44 */         this.entityitem.field_70290_d = MathHelper.func_76126_a(ticks / 14.0F) * 0.2F + 0.2F;
/*  45 */         RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/*  46 */         rendermanager.func_188391_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
/*  47 */         GL11.glPopMatrix();
/*     */       } 
/*  49 */       if (table.crystalsSync.getAspects().length > 0) {
/*  50 */         int q = table.crystalsSync.getAspects().length;
/*  51 */         float ang = (360 / q);
/*  52 */         for (int a = 0; a < q; a++) {
/*  53 */           float angle = ticks % 720.0F / 2.0F + ang * a;
/*  54 */           float bob = MathHelper.func_76126_a((ticks + (a * 10)) / 12.0F) * 0.02F + 0.02F;
/*     */           
/*  56 */           GL11.glPushMatrix();
/*  57 */           GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.3F, (float)par6 + 0.5F);
/*  58 */           GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
/*  59 */           GL11.glTranslatef(0.0F, bob, 0.4F);
/*  60 */           GL11.glRotatef(-angle, 0.0F, 1.0F, 0.0F);
/*  61 */           func_147499_a(ParticleEngine.particleTexture);
/*  62 */           GL11.glEnable(3042);
/*  63 */           GL11.glBlendFunc(770, 1);
/*  64 */           GL11.glAlphaFunc(516, 0.003921569F);
/*  65 */           GL11.glDepthMask(false);
/*  66 */           Color c = new Color(table.crystalsSync.getAspects()[a].getColor());
/*  67 */           float r = c.getRed() / 255.0F;
/*  68 */           float g = c.getGreen() / 255.0F;
/*  69 */           float b = c.getBlue() / 255.0F;
/*  70 */           GL11.glColor4f(r, g, b, 0.66F);
/*  71 */           UtilsFX.renderBillboardQuad(0.17499999701976776D, 64, 64, 320 + (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa % 16);
/*  72 */           GL11.glDepthMask(true);
/*  73 */           GL11.glBlendFunc(770, 771);
/*  74 */           GL11.glDisable(3042);
/*  75 */           GlStateManager.func_179092_a(516, 0.1F);
/*  76 */           GL11.glPopMatrix();
/*     */           
/*  78 */           GL11.glPushMatrix();
/*  79 */           GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.05F, (float)par6 + 0.5F);
/*  80 */           GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
/*  81 */           GL11.glTranslatef(0.0F, bob, 0.4F);
/*  82 */           GL11.glScaled(0.5D, 0.5D, 0.5D);
/*  83 */           ItemStack is = ThaumcraftApiHelper.makeCrystal(table.crystalsSync.getAspects()[a]);
/*  84 */           this.entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/*  85 */           this.entityitem.field_70290_d = 0.0F;
/*     */           
/*  87 */           renderRay(angle, a, bob, r, g, b, ticks);
/*  88 */           renderRay(angle, (a + 1) * 5, bob, r, g, b, ticks);
/*     */           
/*  90 */           RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/*  91 */           rendermanager.func_188391_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
/*     */ 
/*     */           
/*  94 */           GL11.glPopMatrix();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderRay(float angle, int num, float lift, float r, float g, float b, float ticks) {
/* 101 */     Random random = new Random(187L + (num * num));
/* 102 */     GL11.glPushMatrix();
/* 103 */     float pan = MathHelper.func_76126_a((ticks + (num * 10)) / 15.0F) * 15.0F;
/* 104 */     float aparture = MathHelper.func_76126_a((ticks + (num * 10)) / 14.0F) * 2.0F;
/*     */     
/* 106 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 107 */     BufferBuilder wr = tessellator.func_178180_c();
/* 108 */     RenderHelper.func_74518_a();
/*     */     
/* 110 */     GL11.glTranslatef(0.0F, 0.475F + lift, 0.0F);
/*     */     
/* 112 */     GL11.glDisable(3553);
/* 113 */     GL11.glShadeModel(7425);
/* 114 */     GL11.glEnable(3042);
/* 115 */     GL11.glBlendFunc(770, 1);
/* 116 */     GL11.glDisable(3008);
/* 117 */     GL11.glEnable(2884);
/* 118 */     GL11.glDepthMask(false);
/* 119 */     GL11.glPushMatrix();
/* 120 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 121 */     GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
/* 122 */     GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/* 123 */     GL11.glRotated(pan, 1.0D, 0.0D, 0.0D);
/* 124 */     wr.func_181668_a(6, DefaultVertexFormats.field_181706_f);
/* 125 */     float fa = random.nextFloat() * 20.0F + 10.0F;
/* 126 */     float f4 = random.nextFloat() * 4.0F + 6.0F + aparture;
/* 127 */     fa /= 30.0F / Math.min(ticks, 10.0F) / 10.0F;
/* 128 */     f4 /= 30.0F / Math.min(ticks, 10.0F) / 10.0F;
/* 129 */     wr.func_181662_b(0.0D, 0.0D, 0.0D).func_181666_a(r, g, b, 0.66F).func_181675_d();
/* 130 */     wr.func_181662_b(-0.8D * f4, fa, (-0.5F * f4)).func_181666_a(r, g, b, 0.0F).func_181675_d();
/* 131 */     wr.func_181662_b(0.8D * f4, fa, (-0.5F * f4)).func_181666_a(r, g, b, 0.0F).func_181675_d();
/* 132 */     wr.func_181662_b(0.0D, fa, (1.0F * f4)).func_181666_a(r, g, b, 0.0F).func_181675_d();
/* 133 */     wr.func_181662_b(-0.8D * f4, fa, (-0.5F * f4)).func_181666_a(r, g, b, 0.0F).func_181675_d();
/* 134 */     tessellator.func_78381_a();
/*     */     
/* 136 */     GL11.glPopMatrix();
/* 137 */     GL11.glDepthMask(true);
/* 138 */     GL11.glDisable(2884);
/* 139 */     GL11.glBlendFunc(770, 771);
/* 140 */     GL11.glDisable(3042);
/* 141 */     GL11.glShadeModel(7424);
/* 142 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 143 */     GL11.glEnable(3553);
/* 144 */     GL11.glEnable(3008);
/* 145 */     RenderHelper.func_74519_b();
/*     */     
/* 147 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 152 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 153 */     renderTileEntityAt((TileFocalManipulator)te, x, y, z, partialTicks);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileFocalManipulatorRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */