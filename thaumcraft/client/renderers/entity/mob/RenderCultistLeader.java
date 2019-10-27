/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ 
/*     */ import java.awt.Color;
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
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultistLeader
/*     */   extends RenderBiped<EntityCultistLeader>
/*     */ {
/*  27 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/entity/cultist.png");
/*  28 */   private static final ResourceLocation fl = new ResourceLocation("thaumcraft", "textures/misc/wispy.png");
/*     */ 
/*     */   
/*     */   public RenderCultistLeader(RenderManager p_i46127_1_) {
/*  32 */     super(p_i46127_1_, new ModelBiped(), 0.5F);
/*  33 */     func_177094_a(new LayerHeldItem(this));
/*  34 */     LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
/*     */       {
/*     */         protected void func_177177_a()
/*     */         {
/*  38 */           this.field_177189_c = new ModelBiped();
/*  39 */           this.field_177186_d = new ModelBiped();
/*     */         }
/*     */       };
/*  42 */     func_177094_a(layerbipedarmor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected ResourceLocation getEntityTexture(EntityCultistLeader p_110775_1_) { return skin; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityCultistLeader entitylivingbaseIn, float partialTickTime) {
/*  53 */     super.func_77041_b(entitylivingbaseIn, partialTickTime);
/*  54 */     GL11.glScalef(1.15F, 1.15F, 1.15F);
/*     */   }
/*     */   
/*     */   private void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, float speed, float distance, float width) {
/*  58 */     Entity player = Minecraft.func_71410_x().func_175606_aa();
/*  59 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  60 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  61 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/*  63 */     double ePX = x2;
/*  64 */     double ePY = y2;
/*  65 */     double ePZ = z2;
/*     */     
/*  67 */     GL11.glTranslated(-iPX + ePX, -iPY + ePY, -iPZ + ePZ);
/*     */     
/*  69 */     float time = (float)(System.nanoTime() / 30000000L);
/*     */     
/*  71 */     Color co = new Color(color);
/*  72 */     float r = co.getRed() / 255.0F;
/*  73 */     float g = co.getGreen() / 255.0F;
/*  74 */     float b = co.getBlue() / 255.0F;
/*     */ 
/*     */ 
/*     */     
/*  78 */     GL11.glEnable(3042);
/*  79 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  81 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  83 */     double ds1x = ePX;
/*  84 */     double ds1y = ePY;
/*  85 */     double ds1z = ePZ;
/*  86 */     double dd1x = x;
/*  87 */     double dd1y = y;
/*  88 */     double dd1z = z;
/*  89 */     double dc1x = (float)(dd1x - ds1x);
/*  90 */     double dc1y = (float)(dd1y - ds1y);
/*  91 */     double dc1z = (float)(dd1z - ds1z);
/*     */     
/*  93 */     func_110776_a(fl);
/*     */ 
/*     */     
/*  96 */     tessellator.func_178180_c().func_181668_a(5, DefaultVertexFormats.field_181709_i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     double dx2 = 0.0D;
/* 106 */     double dy2 = 0.0D;
/* 107 */     double dz2 = 0.0D;
/* 108 */     double d3 = x - ePX;
/* 109 */     double d4 = y - ePY;
/* 110 */     double d5 = z - ePZ;
/*     */     
/* 112 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/* 113 */     float blocks = Math.round(dist);
/* 114 */     float length = blocks * 6.0F;
/*     */     
/* 116 */     float f9 = 0.0F;
/* 117 */     float f10 = 1.0F;
/*     */     int i;
/* 119 */     for (i = 0; i <= length * distance; i++) {
/*     */       
/* 121 */       float f2 = i / length;
/* 122 */       float f2a = i * 1.5F / length;
/* 123 */       f2a = Math.min(0.75F, f2a);
/* 124 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / length / 2.0F;
/*     */       
/* 126 */       double dx = dc1x + (MathHelper.func_76126_a((float)((z % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 4.0D)) * 0.5F * f3);
/* 127 */       double dy = dc1y + (MathHelper.func_76126_a((float)((x % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 3.0D)) * 0.5F * f3);
/* 128 */       double dz = dc1z + (MathHelper.func_76126_a((float)((y % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 2.0D)) * 0.5F * f3);
/*     */       
/* 130 */       float f13 = (1.0F - f2) * dist - time * speed;
/* 131 */       tessellator.func_178180_c().func_181662_b(dx * f2, dy * f2 - width, dz * f2).func_187315_a(f13, f10).func_181666_a(r, g, b, 0.8F).func_181675_d();
/* 132 */       tessellator.func_178180_c().func_181662_b(dx * f2, dy * f2 + width, dz * f2).func_187315_a(f13, f9).func_181666_a(r, g, b, 0.8F).func_181675_d();
/*     */     } 
/*     */ 
/*     */     
/* 136 */     tessellator.func_78381_a();
/*     */     
/* 138 */     tessellator.func_178180_c().func_181668_a(5, DefaultVertexFormats.field_181709_i);
/* 139 */     for (i = 0; i <= length * distance; i++) {
/*     */       
/* 141 */       float f2 = i / length;
/* 142 */       float f2a = i * 1.5F / length;
/* 143 */       f2a = Math.min(0.75F, f2a);
/* 144 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / length / 2.0F;
/*     */       
/* 146 */       double dx = dc1x + (MathHelper.func_76126_a((float)((z % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 4.0D)) * 0.5F * f3);
/* 147 */       double dy = dc1y + (MathHelper.func_76126_a((float)((x % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 3.0D)) * 0.5F * f3);
/* 148 */       double dz = dc1z + (MathHelper.func_76126_a((float)((y % 16.0D + (dist * (1.0F - f2) * 6.0F) - (time % 32767.0F / 5.0F)) / 2.0D)) * 0.5F * f3);
/*     */ 
/*     */       
/* 151 */       float f13 = (1.0F - f2) * dist - time * speed;
/*     */       
/* 153 */       tessellator.func_178180_c().func_181662_b(dx * f2 - width, dy * f2, dz * f2).func_187315_a(f13, f10).func_181666_a(r, g, b, 0.8F).func_181675_d();
/* 154 */       tessellator.func_178180_c().func_181662_b(dx * f2 + width, dy * f2, dz * f2).func_187315_a(f13, f9).func_181666_a(r, g, b, 0.8F).func_181675_d();
/*     */     } 
/*     */     
/* 157 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/* 160 */     GL11.glDisable(3042);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderCultistLeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */