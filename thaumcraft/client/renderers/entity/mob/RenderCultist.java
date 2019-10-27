/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultist
/*     */   extends RenderBiped<EntityCultist>
/*     */ {
/*  31 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/entity/cultist.png");
/*  32 */   private static final ResourceLocation fl = new ResourceLocation("thaumcraft", "textures/misc/wispy.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderCultist(RenderManager p_i46127_1_) {
/*  41 */     super(p_i46127_1_, new ModelBiped(), 0.5F);
/*  42 */     func_177094_a(new LayerHeldItem(this));
/*  43 */     LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
/*     */       {
/*     */         protected void func_177177_a()
/*     */         {
/*  47 */           this.field_177189_c = new ModelBiped();
/*  48 */           this.field_177186_d = new ModelBiped();
/*     */         }
/*     */       };
/*  51 */     func_177094_a(layerbipedarmor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected ResourceLocation getEntityTexture(EntityCultist p_110775_1_) { return skin; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityCultist entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  85 */     GL11.glPushMatrix();
/*     */     
/*  87 */     float bob = 0.0F;
/*  88 */     boolean rit = (entity instanceof EntityCultistCleric && ((EntityCultistCleric)entity).getIsRitualist());
/*  89 */     if (rit) {
/*  90 */       int val = (new Random(entity.func_145782_y())).nextInt(1000);
/*  91 */       float c = ((EntityCultistCleric)entity).field_70173_aa + p_76986_9_ + val;
/*  92 */       bob = MathHelper.func_76126_a(c / 9.0F) * 0.1F + 0.21F;
/*  93 */       GL11.glTranslated(0.0D, bob, 0.0D);
/*     */     } 
/*     */     
/*  96 */     super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     
/*  98 */     if (rit) {
/*  99 */       GL11.glPushMatrix();
/* 100 */       GL11.glDepthMask(false);
/* 101 */       drawFloatyLine(entity.field_70165_t, entity.field_70163_u + (entity.func_70047_e() * 1.2F), entity.field_70161_v, ((EntityCultistCleric)entity)
/* 102 */           .func_180486_cf().func_177958_n() + 0.5D, ((EntityCultistCleric)entity)
/* 103 */           .func_180486_cf().func_177956_o() + 1.5D - bob, ((EntityCultistCleric)entity)
/* 104 */           .func_180486_cf().func_177952_p() + 0.5D, p_76986_9_, 1114129, -0.03F, 
/*     */           
/* 106 */           Math.min(((EntityCultistCleric)entity).field_70173_aa, 10) / 10.0F, 0.25F);
/* 107 */       GL11.glDepthMask(true);
/* 108 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 111 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, float speed, float distance, float width) {
/* 115 */     Entity player = Minecraft.func_71410_x().func_175606_aa();
/* 116 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 117 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 118 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 120 */     double ePX = x2;
/* 121 */     double ePY = y2;
/* 122 */     double ePZ = z2;
/*     */     
/* 124 */     GL11.glTranslated(-iPX + ePX, -iPY + ePY, -iPZ + ePZ);
/*     */     
/* 126 */     float time = (float)(System.nanoTime() / 30000000L);
/*     */     
/* 128 */     Color co = new Color(color);
/* 129 */     float r = co.getRed() / 255.0F;
/* 130 */     float g = co.getGreen() / 255.0F;
/* 131 */     float b = co.getBlue() / 255.0F;
/*     */ 
/*     */ 
/*     */     
/* 135 */     GL11.glEnable(3042);
/* 136 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 138 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 140 */     double ds1x = ePX;
/* 141 */     double ds1y = ePY;
/* 142 */     double ds1z = ePZ;
/* 143 */     double dd1x = x;
/* 144 */     double dd1y = y;
/* 145 */     double dd1z = z;
/* 146 */     double dc1x = (float)(dd1x - ds1x);
/* 147 */     double dc1y = (float)(dd1y - ds1y);
/* 148 */     double dc1z = (float)(dd1z - ds1z);
/*     */     
/* 150 */     func_110776_a(fl);
/*     */ 
/*     */     
/* 153 */     tessellator.func_178180_c().func_181668_a(5, DefaultVertexFormats.field_181709_i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     double dx2 = 0.0D;
/* 163 */     double dy2 = 0.0D;
/* 164 */     double dz2 = 0.0D;
/* 165 */     double d3 = x - ePX;
/* 166 */     double d4 = y - ePY;
/* 167 */     double d5 = z - ePZ;
/*     */     
/* 169 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/* 170 */     float blocks = Math.round(dist);
/* 171 */     float length = blocks * 6.0F;
/*     */     
/* 173 */     float f9 = 0.0F;
/* 174 */     float f10 = 1.0F;
/*     */     int i;
/* 176 */     for (i = 0; i <= length * distance; i++) {
/*     */       
/* 178 */       float f2 = i / length;
/* 179 */       float f2a = i * 1.5F / length;
/* 180 */       f2a = Math.min(0.75F, f2a);
/* 181 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / length / 2.0F;
/*     */       
/* 183 */       double dx = dc1x + (MathHelper.func_76126_a((float)((z % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 4.0D)) * 0.5F * f3);
/* 184 */       double dy = dc1y + (MathHelper.func_76126_a((float)((x % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 3.0D)) * 0.5F * f3);
/* 185 */       double dz = dc1z + (MathHelper.func_76126_a((float)((y % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 2.0D)) * 0.5F * f3);
/*     */       
/* 187 */       float f13 = (1.0F - f2) * dist - time * speed;
/* 188 */       tessellator.func_178180_c().func_181662_b(dx * f2, dy * f2 - width, dz * f2).func_187315_a(f13, f10).func_181666_a(r, g, b, 0.8F).func_181675_d();
/* 189 */       tessellator.func_178180_c().func_181662_b(dx * f2, dy * f2 + width, dz * f2).func_187315_a(f13, f9).func_181666_a(r, g, b, 0.8F).func_181675_d();
/*     */     } 
/*     */ 
/*     */     
/* 193 */     tessellator.func_78381_a();
/*     */     
/* 195 */     tessellator.func_178180_c().func_181668_a(5, DefaultVertexFormats.field_181709_i);
/* 196 */     for (i = 0; i <= length * distance; i++) {
/*     */       
/* 198 */       float f2 = i / length;
/* 199 */       float f2a = i * 1.5F / length;
/* 200 */       f2a = Math.min(0.75F, f2a);
/* 201 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / length / 2.0F;
/*     */       
/* 203 */       double dx = dc1x + (MathHelper.func_76126_a((float)((z % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 4.0D)) * 0.5F * f3);
/* 204 */       double dy = dc1y + (MathHelper.func_76126_a((float)((x % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 3.0D)) * 0.5F * f3);
/* 205 */       double dz = dc1z + (MathHelper.func_76126_a((float)((y % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 2.0D)) * 0.5F * f3);
/*     */ 
/*     */       
/* 208 */       float f13 = (1.0F - f2) * dist - time * speed;
/*     */       
/* 210 */       tessellator.func_178180_c().func_181662_b(dx * f2 - width, dy * f2, dz * f2).func_187315_a(f13, f10).func_181666_a(r, g, b, 0.8F).func_181675_d();
/* 211 */       tessellator.func_178180_c().func_181662_b(dx * f2 + width, dy * f2, dz * f2).func_187315_a(f13, f9).func_181666_a(r, g, b, 0.8F).func_181675_d();
/*     */     } 
/*     */     
/* 214 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/* 217 */     GL11.glDisable(3042);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderCultist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */