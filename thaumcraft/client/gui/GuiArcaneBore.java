/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.container.ContainerArcaneBore;
/*     */ import thaumcraft.common.entities.construct.EntityArcaneBore;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiArcaneBore
/*     */   extends GuiContainer
/*     */ {
/*     */   EntityArcaneBore turret;
/*     */   
/*     */   public GuiArcaneBore(InventoryPlayer par1InventoryPlayer, World world, EntityArcaneBore t) {
/*  23 */     super(new ContainerArcaneBore(par1InventoryPlayer, world, t));
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
/*  41 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_arcanebore.png");
/*     */     this.field_146999_f = 175;
/*     */     this.field_147000_g = 232;
/*     */     this.turret = t;
/*     */   }
/*     */   ResourceLocation tex;
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/*  49 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*  50 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  51 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  52 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*  53 */     GL11.glEnable(3042);
/*  54 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/*  56 */     int h = (int)(39.0F * this.turret.func_110143_aJ() / this.turret.func_110138_aP());
/*  57 */     func_73729_b(k + 68, l + 59, 192, 48, h, 6);
/*     */     
/*  59 */     if (this.turret.func_184614_ca() != null && !this.turret.func_184614_ca().func_190926_b() && this.turret.func_184614_ca().func_77952_i() + 1 >= this.turret.func_184614_ca().func_77958_k()) {
/*  60 */       func_73729_b(k + 80, l + 29, 240, 0, 16, 16);
/*     */     }
/*     */     
/*  63 */     GL11.glPushMatrix();
/*  64 */     GL11.glTranslatef((k + 124), (l + 18), 505.0F);
/*  65 */     GL11.glScalef(0.5F, 0.5F, 0.0F);
/*     */     
/*  67 */     String text = "Width: " + (1 + this.turret.getDigRadius() * 2);
/*  68 */     this.field_146289_q.func_175063_a(text, 0.0F, 0.0F, 16777215);
/*     */     
/*  70 */     text = "Depth: " + this.turret.getDigDepth();
/*  71 */     this.field_146289_q.func_175063_a(text, 64.0F, 0.0F, 16777215);
/*     */     
/*  73 */     text = "Speed: +" + this.turret.getDigSpeed(Blocks.field_150348_b.func_176223_P());
/*  74 */     this.field_146289_q.func_175063_a(text, 0.0F, 10.0F, 16777215);
/*     */     
/*  76 */     int base = 0;
/*  77 */     int refining = this.turret.getRefining();
/*  78 */     int fortune = this.turret.getFortune();
/*     */     
/*  80 */     if (this.turret.hasSilkTouch() || refining > 0 || fortune > 0) {
/*  81 */       text = "Other properties:";
/*  82 */       this.field_146289_q.func_175063_a(text, 0.0F, 24.0F, 16777215);
/*     */     } 
/*     */     
/*  85 */     if (refining > 0) {
/*  86 */       text = "Refining " + refining;
/*  87 */       this.field_146289_q.func_175063_a(text, 4.0F, (34 + base), 12632256);
/*  88 */       base += 9;
/*     */     } 
/*     */     
/*  91 */     if (fortune > 0) {
/*  92 */       text = "Fortune " + fortune;
/*  93 */       this.field_146289_q.func_175063_a(text, 4.0F, (34 + base), 15648330);
/*  94 */       base += 9;
/*     */     } 
/*     */     
/*  97 */     if (this.turret.hasSilkTouch()) {
/*  98 */       text = "Silk Touch";
/*  99 */       this.field_146289_q.func_175063_a(text, 4.0F, (34 + base), 8421631);
/* 100 */       base += 9;
/*     */     } 
/*     */     
/* 103 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 104 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*     */     func_146276_q_();
/*     */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */     func_191948_b(mouseX, mouseY);
/*     */   }
/*     */   
/*     */   protected void func_146979_b(int par1, int par2) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiArcaneBore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */