/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.common.blocks.world.ore.ShardType;
/*     */ import thaumcraft.common.container.ContainerArcaneWorkbench;
/*     */ import thaumcraft.common.items.casters.CasterManager;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiArcaneWorkbench
/*     */   extends GuiContainer
/*     */ {
/*     */   private TileArcaneWorkbench tileEntity;
/*     */   private InventoryPlayer ip;
/*  30 */   private int[][] aspectLocs = { { 72, 21 }, { 24, 43 }, { 24, 102 }, { 72, 124 }, { 120, 102 }, { 120, 43 } };
/*     */   ResourceLocation tex;
/*     */   
/*     */   public GuiArcaneWorkbench(InventoryPlayer par1InventoryPlayer, TileArcaneWorkbench e) {
/*  34 */     super(new ContainerArcaneWorkbench(par1InventoryPlayer, e));
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
/*  49 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/arcaneworkbench.png");
/*     */     this.tileEntity = e;
/*     */     this.ip = par1InventoryPlayer;
/*     */     this.field_147000_g = 234;
/*     */     this.field_146999_f = 190; } protected void func_146976_a(float par1, int par2, int par3) {
/*  54 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*  55 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  56 */     GL11.glEnable(3042);
/*  57 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/*  58 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/*  59 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/*  61 */     int cost = 0;
/*  62 */     int discount = 0;
/*  63 */     IArcaneRecipe result = ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity.inventoryCraft, this.ip.field_70458_d);
/*  64 */     AspectList crystals = null;
/*  65 */     float df = CasterManager.getTotalVisDiscount(this.ip.field_70458_d);
/*  66 */     if (result != null) {
/*  67 */       cost = result.getVis();
/*  68 */       cost = (int)(cost * (1.0F - df));
/*  69 */       discount = (int)(df * 100.0F);
/*  70 */       crystals = result.getCrystals();
/*     */     } 
/*     */     
/*  73 */     if (crystals != null) {
/*     */       
/*  75 */       GlStateManager.func_179112_b(770, 1);
/*  76 */       for (Aspect a : crystals.getAspects()) {
/*  77 */         int id = ShardType.getMetaByAspect(a);
/*  78 */         Color col = new Color(a.getColor());
/*  79 */         GL11.glColor4f(col.getRed() / 255.0F, col.getGreen() / 255.0F, col.getBlue() / 255.0F, 0.33F);
/*  80 */         GL11.glPushMatrix();
/*  81 */         GL11.glTranslatef((var5 + ContainerArcaneWorkbench.xx[id]) + 7.5F, (var6 + ContainerArcaneWorkbench.yy[id]) + 8.0F, 0.0F);
/*  82 */         GL11.glRotatef((id * 60 + (this.field_146297_k.func_175606_aa()).field_70173_aa % 360) + par1, 0.0F, 0.0F, 1.0F);
/*  83 */         GL11.glScalef(0.5F, 0.5F, 0.0F);
/*  84 */         func_73729_b(-32, -32, 192, 0, 64, 64);
/*  85 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  86 */         GL11.glPopMatrix();
/*     */       } 
/*  88 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  89 */       GlStateManager.func_179112_b(770, 771);
/*     */     } 
/*     */     
/*  92 */     GL11.glDisable(3042);
/*     */     
/*  94 */     GL11.glPushMatrix();
/*  95 */     GL11.glTranslatef((var5 + 168), (var6 + 46), 0.0F);
/*  96 */     GL11.glScalef(0.5F, 0.5F, 0.0F);
/*  97 */     String text = this.tileEntity.auraVisClient + " " + I18n.func_74838_a("workbench.available");
/*  98 */     int ll = this.field_146289_q.func_78256_a(text) / 2;
/*  99 */     this.field_146289_q.func_78276_b(text, -ll, 0, (this.tileEntity.auraVisClient < cost) ? 15625838 : 7237358);
/* 100 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 101 */     GL11.glPopMatrix();
/*     */     
/* 103 */     if (cost > 0) {
/* 104 */       if (this.tileEntity.auraVisClient < cost) {
/* 105 */         GL11.glPushMatrix();
/* 106 */         float var40 = 0.33F;
/* 107 */         GL11.glColor4f(var40, var40, var40, 0.66F);
/*     */         
/* 109 */         GL11.glEnable(2896);
/* 110 */         GL11.glEnable(2884);
/* 111 */         GL11.glEnable(3042);
/*     */         
/* 113 */         this.field_146296_j.func_180450_b(result
/* 114 */             .func_77572_b(this.tileEntity.inventoryCraft), var5 + 160, var6 + 64);
/* 115 */         this.field_146296_j.func_180453_a(this.field_146297_k.field_71466_p, result
/* 116 */             .func_77572_b(this.tileEntity.inventoryCraft), var5 + 160, var6 + 64, "");
/*     */         
/* 118 */         GlStateManager.func_179145_e();
/* 119 */         GlStateManager.func_179126_j();
/* 120 */         RenderHelper.func_74519_b();
/* 121 */         GL11.glPopMatrix();
/* 122 */         RenderHelper.func_74518_a();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       GL11.glPushMatrix();
/* 129 */       GL11.glTranslatef((var5 + 168), (var6 + 38), 0.0F);
/* 130 */       GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 131 */       text = cost + " " + I18n.func_74838_a("workbench.cost");
/* 132 */       if (discount > 0) text = text + " (" + discount + "% " + I18n.func_74838_a("workbench.discount") + ")"; 
/* 133 */       ll = this.field_146289_q.func_78256_a(text) / 2;
/* 134 */       this.field_146289_q.func_78276_b(text, -ll, 0, 12648447);
/* 135 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 136 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*     */     func_146276_q_();
/*     */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */     func_191948_b(mouseX, mouseY);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiArcaneWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */