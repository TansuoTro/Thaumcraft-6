/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.container.ContainerThaumatorium;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketSelectThaumotoriumRecipeToServer;
/*     */ import thaumcraft.common.tiles.crafting.TileThaumatorium;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiThaumatorium
/*     */   extends GuiContainer
/*     */ {
/*     */   private TileThaumatorium inventory;
/*  33 */   private ContainerThaumatorium container = null;
/*  34 */   private int index = 0;
/*  35 */   private int lastSize = 0;
/*  36 */   private EntityPlayer player = null; ResourceLocation tex;
/*     */   ArrayList<Integer> hashList;
/*     */   long lastHLUpdate;
/*     */   
/*  40 */   public GuiThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium par2TileEntityFurnace) { super(new ContainerThaumatorium(par1InventoryPlayer, par2TileEntityFurnace));
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
/*  58 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_thaumatorium.png");
/*     */     
/*  60 */     this.hashList = new ArrayList();
/*  61 */     this.lastHLUpdate = 0L; this.field_146999_f = 175; this.field_147000_g = 216;
/*     */     this.inventory = par2TileEntityFurnace;
/*     */     this.container = (ContainerThaumatorium)this.field_147002_h;
/*     */     this.player = par1InventoryPlayer.field_70458_d;
/*     */     this.inventory.updateRecipes(this.player);
/*  66 */     this.lastSize = this.hashList.size(); } protected void func_146976_a(float par1, int mx, int my) { GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  67 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*  68 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  69 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*  71 */     GL11.glEnable(3042);
/*  72 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/*  74 */     long t = System.currentTimeMillis();
/*  75 */     if (t > this.lastHLUpdate) {
/*  76 */       this.hashList.clear();
/*  77 */       this.hashList = this.inventory.generateRecipeHashlist();
/*  78 */       this.lastHLUpdate = t + 500L;
/*     */     } 
/*     */     
/*  81 */     if (this.hashList.size() > 0) {
/*  82 */       if (this.index > this.hashList.size() / 2) {
/*  83 */         this.index = this.hashList.size() / 2;
/*     */       }
/*  85 */       if (this.index < 0 || this.hashList.size() <= 6) this.index = 0;
/*     */       
/*  87 */       if (this.hashList.size() > 6) {
/*     */         
/*  89 */         if (this.index > 0) {
/*  90 */           func_73729_b(k + 82, l + 56, 176, 56, 8, 11);
/*     */         }
/*     */         
/*  93 */         if (this.index < this.hashList.size() / 2.0F - 3.0F) {
/*  94 */           func_73729_b(k + 82, l + 93, 176, 93, 8, 11);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     drawAspects(k, l, t);
/*     */     
/* 101 */     if (this.inventory.maxRecipes > 1) {
/* 102 */       GL11.glPushMatrix();
/* 103 */       GL11.glTranslatef((k + 64), (l + 48), 0.0F);
/* 104 */       GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 105 */       String text = this.inventory.recipeHash.size() + "/" + this.inventory.maxRecipes;
/*     */       
/* 107 */       int ll = this.field_146289_q.func_78256_a(text) / 2;
/* 108 */       this.field_146289_q.func_78276_b(text, -ll, 0, 16777215);
/* 109 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 110 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 113 */     drawOutput(k, l, mx, my, t); }
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) { func_146276_q_();
/*     */     super.func_73863_a(mouseX, mouseY, partialTicks);
/* 116 */     func_191948_b(mouseX, mouseY); } static HashMap<Integer, CrucibleRecipe> recipeCache = new HashMap();
/*     */   
/*     */   private static CrucibleRecipe getRecipeCached(int hash) {
/* 119 */     if (recipeCache.containsKey(Integer.valueOf(hash))) return (CrucibleRecipe)recipeCache.get(Integer.valueOf(hash)); 
/* 120 */     CrucibleRecipe cr = ThaumcraftApi.getCrucibleRecipeFromHash(hash);
/* 121 */     if (cr != null) recipeCache.put(Integer.valueOf(hash), cr); 
/* 122 */     return cr;
/*     */   }
/*     */   
/*     */   private void drawAspects(int k, int l, long time) {
/* 126 */     if (this.inventory.recipeHash.size() <= 0)
/* 127 */       return;  int count = 0;
/* 128 */     int px = 0;
/* 129 */     int py = 0;
/* 130 */     GL11.glEnable(3042);
/*     */     
/* 132 */     int hash = ((Integer)this.inventory.recipeHash.get((int)(time / 1000L % this.inventory.recipeHash.size()))).intValue();
/*     */     
/* 134 */     if (this.inventory.recipeHash.contains(Integer.valueOf(hash))) {
/* 135 */       CrucibleRecipe cr = getRecipeCached(hash);
/* 136 */       if (cr == null)
/* 137 */         return;  for (Aspect aspect : cr.getAspects().getAspectsSortedByName()) {
/* 138 */         GL11.glPushMatrix();
/* 139 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/* 141 */         func_73729_b(k + 98 + 16 * px, l + 40 + 20 * py, 176, 4, 12, 3);
/*     */ 
/*     */         
/* 144 */         int i1 = (int)(this.inventory.essentia.getAmount(aspect) / cr.getAspects().getAmount(aspect) * 12.0F);
/*     */         
/* 146 */         Color c = new Color(aspect.getColor());
/* 147 */         GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 148 */         func_73729_b(k + 98 + 16 * px, l + 40 + 20 * py, 176, 0, i1, 3);
/* 149 */         GL11.glPopMatrix();
/*     */         
/* 151 */         px++;
/* 152 */         if (px > 1) {
/* 153 */           px = 0;
/* 154 */           py++;
/*     */         } 
/* 156 */         count++;
/* 157 */         if (count >= 8)
/*     */           break; 
/* 159 */       }  count = 0;
/* 160 */       px = 0;
/* 161 */       py = 0;
/* 162 */       for (Aspect aspect : cr.getAspects().getAspectsSortedByName()) {
/* 163 */         UtilsFX.drawTag(k + 96 + 16 * px, l + 24 + 20 * py, aspect, cr.getAspects().getAmount(aspect), 0, this.field_73735_i);
/* 164 */         px++;
/* 165 */         if (px > 1) {
/* 166 */           px = 0;
/* 167 */           py++;
/*     */         } 
/* 169 */         count++;
/* 170 */         if (count >= 8)
/*     */           break; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawOutput(int x, int y, int mx, int my, long time) {
/* 177 */     GL11.glPushMatrix();
/*     */     
/* 179 */     int px = 0;
/* 180 */     int py = 0;
/* 181 */     int q = 0;
/* 182 */     int idx = 0;
/*     */     Iterator iterator;
/* 184 */     for (iterator = this.hashList.iterator(); iterator.hasNext(); ) { int hash = ((Integer)iterator.next()).intValue();
/* 185 */       if (q++ < this.index * 2)
/* 186 */         continue;  CrucibleRecipe cr = getRecipeCached(hash);
/* 187 */       if (cr == null)
/* 188 */         continue;  drawOutputIcon(x + 48 + px * 16, y + 56 + py * 16, getRecipeCached(hash), time);
/* 189 */       px++;
/* 190 */       if (px > 1) {
/* 191 */         px = 0;
/* 192 */         py++;
/*     */       } 
/* 194 */       idx++;
/* 195 */       if (idx >= 6)
/*     */         break;  }
/*     */     
/* 198 */     px = 0;
/* 199 */     py = 0;
/* 200 */     q = 0;
/* 201 */     idx = 0;
/* 202 */     for (iterator = this.hashList.iterator(); iterator.hasNext(); ) { int hash = ((Integer)iterator.next()).intValue();
/* 203 */       if (q++ < this.index * 2)
/* 204 */         continue;  CrucibleRecipe cr = getRecipeCached(hash);
/* 205 */       if (cr == null)
/* 206 */         continue;  int xx = mx - x + 48 + px * 16;
/* 207 */       int yy = my - y + 56 + py * 16;
/* 208 */       if (xx >= 0 && yy >= 0 && xx < 16 && yy < 16) {
/* 209 */         func_146285_a(cr.getRecipeOutput(), mx, my);
/*     */         break;
/*     */       } 
/* 212 */       px++;
/* 213 */       if (px > 1) {
/* 214 */         px = 0;
/* 215 */         py++;
/*     */       } 
/* 217 */       idx++;
/* 218 */       if (idx >= 6)
/*     */         break;  }
/*     */     
/* 221 */     GL11.glDisable(3042);
/* 222 */     GL11.glDisable(2896);
/* 223 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawOutputIcon(int x, int y, CrucibleRecipe cr, long time) {
/* 227 */     if (this.inventory.recipeHash.contains(Integer.valueOf(cr.hash))) {
/* 228 */       int hash = ((Integer)this.inventory.recipeHash.get((int)(time / 1000L % this.inventory.recipeHash.size()))).intValue();
/* 229 */       this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 230 */       GL11.glPushMatrix();
/* 231 */       GL11.glEnable(3042);
/* 232 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
/* 233 */       func_73729_b(x, y, 176, 8, 16, 16);
/* 234 */       if (this.inventory.recipeHash.size() > 1 && hash == cr.hash)
/* 235 */         func_73729_b(x, y, 176, 8, 16, 16); 
/* 236 */       GL11.glDisable(3042);
/* 237 */       GL11.glPopMatrix();
/*     */     } 
/* 239 */     GlStateManager.func_179094_E();
/* 240 */     RenderHelper.func_74520_c();
/* 241 */     GlStateManager.func_179140_f();
/* 242 */     GlStateManager.func_179091_B();
/* 243 */     GlStateManager.func_179142_g();
/* 244 */     GlStateManager.func_179145_e();
/* 245 */     this.field_146296_j.field_77023_b = 100.0F;
/* 246 */     this.field_146296_j.func_180450_b(cr.getRecipeOutput(), x, y);
/* 247 */     this.field_146296_j.func_175030_a(this.field_146289_q, cr.getRecipeOutput(), x, y);
/* 248 */     this.field_146296_j.field_77023_b = 0.0F;
/* 249 */     GlStateManager.func_179140_f();
/* 250 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int mx, int my, int par3) throws IOException {
/* 255 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 257 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 258 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */ 
/*     */ 
/*     */     
/* 262 */     int px = 0;
/* 263 */     int py = 0;
/* 264 */     int q = 0;
/* 265 */     int idx = 0;
/* 266 */     for (Iterator iterator = this.hashList.iterator(); iterator.hasNext(); ) { int hash = ((Integer)iterator.next()).intValue();
/* 267 */       if (q++ < this.index * 2)
/* 268 */         continue;  CrucibleRecipe cr = getRecipeCached(hash);
/* 269 */       if (cr == null)
/*     */         continue; 
/* 271 */       int x = mx - gx + 48 + px * 16;
/* 272 */       int y = my - gy + 56 + py * 16;
/*     */       
/* 274 */       if (x >= 0 && y >= 0 && x < 16 && y < 16) {
/*     */ 
/*     */         
/* 277 */         PacketHandler.INSTANCE.sendToServer(new PacketSelectThaumotoriumRecipeToServer(this.player, this.inventory
/* 278 */               .func_174877_v(), hash));
/* 279 */         playButtonSelect();
/* 280 */         this.lastHLUpdate = 0L;
/*     */         
/*     */         break;
/*     */       } 
/* 284 */       px++;
/* 285 */       if (px > 1) {
/* 286 */         px = 0;
/* 287 */         py++;
/*     */       } 
/* 289 */       idx++;
/* 290 */       if (idx >= 6)
/*     */         break;  }
/*     */     
/* 293 */     if (this.hashList.size() > 6) {
/*     */       
/* 295 */       if (this.index > 0) {
/* 296 */         int x = mx - gx + 82;
/* 297 */         int y = my - gy + 56;
/* 298 */         if (x >= 0 && y >= 0 && x < 8 && y < 11) {
/* 299 */           this.index--;
/* 300 */           playButtonClick();
/* 301 */           this.lastHLUpdate = 0L;
/*     */         } 
/*     */       } 
/*     */       
/* 305 */       if (this.index < this.hashList.size() / 2.0F - 3.0F) {
/* 306 */         int x = mx - gx + 82;
/* 307 */         int y = my - gy + 93;
/* 308 */         if (x >= 0 && y >= 0 && x < 8 && y < 11) {
/* 309 */           this.index++;
/* 310 */           playButtonClick();
/* 311 */           this.lastHLUpdate = 0L;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   private void playButtonSelect() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.hhon, 0.3F, 1.0F); }
/*     */ 
/*     */ 
/*     */   
/* 324 */   private void playButtonClick() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.clack, 0.4F, 1.0F); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiThaumatorium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */