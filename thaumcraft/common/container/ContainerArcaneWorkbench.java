/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IContainerListener;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCraftResult;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.network.play.server.SPacketSetSlot;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.crafting.ContainerDummy;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.common.blocks.world.ore.ShardType;
/*     */ import thaumcraft.common.container.slot.SlotCraftingArcaneWorkbench;
/*     */ import thaumcraft.common.container.slot.SlotCrystal;
/*     */ import thaumcraft.common.items.casters.CasterManager;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerArcaneWorkbench
/*     */   extends Container
/*     */ {
/*     */   private TileArcaneWorkbench tileEntity;
/*     */   private InventoryPlayer ip;
/*     */   public InventoryCraftResult craftResult;
/*  42 */   public static int[] xx = { 64, 17, 112, 17, 112, 64 };
/*  43 */   public static int[] yy = { 13, 35, 35, 93, 93, 115 };
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
/*     */   private int lastVis;
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
/*     */   private long lastCheck;
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
/*     */   public ContainerArcaneWorkbench(InventoryPlayer par1InventoryPlayer, TileArcaneWorkbench e) {
/*     */     this.craftResult = new InventoryCraftResult();
/*  98 */     this.lastVis = -1;
/*  99 */     this.lastCheck = 0L; this.tileEntity = e; this.tileEntity.inventoryCraft.field_70465_c = this; this.ip = par1InventoryPlayer; e.getAura(); func_75146_a(new SlotCraftingArcaneWorkbench(this.tileEntity, par1InventoryPlayer.field_70458_d, this.tileEntity.inventoryCraft, this.craftResult, 15, 160, 64)); int var6; for (var6 = 0; var6 < 3; var6++) { for (int var7 = 0; var7 < 3; var7++)
/*     */         func_75146_a(new Slot(this.tileEntity.inventoryCraft, var7 + var6 * 3, 40 + var7 * 24, 40 + var6 * 24));  }  for (ShardType st : ShardType.values()) { if (st.getMetadata() < 6)
/*     */         func_75146_a(new SlotCrystal(st.getAspect(), this.tileEntity.inventoryCraft, st.getMetadata() + 9, xx[st.getMetadata()], yy[st.getMetadata()]));  }  for (var6 = 0; var6 < 3; var6++) { for (int var7 = 0; var7 < 9; var7++)
/*     */         func_75146_a(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 16 + var7 * 18, 151 + var6 * 18));  }
/*     */      for (var6 = 0; var6 < 9; var6++)
/* 104 */       func_75146_a(new Slot(par1InventoryPlayer, var6, 16 + var6 * 18, 209));  func_75130_a(this.tileEntity.inventoryCraft); } public void func_75142_b() { super.func_75142_b();
/* 105 */     long t = System.currentTimeMillis();
/* 106 */     if (t > this.lastCheck) {
/* 107 */       this.lastCheck = t + 500L;
/* 108 */       this.tileEntity.getAura();
/*     */     } 
/* 110 */     if (this.lastVis != this.tileEntity.auraVisServer)
/*     */     {
/* 112 */       func_75130_a(this.tileEntity.inventoryCraft);
/*     */     }
/* 114 */     for (int i = 0; i < this.field_75149_d.size(); i++) {
/*     */       
/* 116 */       IContainerListener icrafting = (IContainerListener)this.field_75149_d.get(i);
/* 117 */       if (this.lastVis != this.tileEntity.auraVisServer)
/*     */       {
/* 119 */         icrafting.func_71112_a(this, 0, this.tileEntity.auraVisServer);
/*     */       }
/*     */     } 
/* 122 */     this.lastVis = this.tileEntity.auraVisServer; }
/*     */    public void func_75132_a(IContainerListener par1ICrafting) {
/*     */     super.func_75132_a(par1ICrafting);
/*     */     this.tileEntity.getAura();
/*     */     par1ICrafting.func_71112_a(this, 0, this.tileEntity.auraVisServer);
/*     */   } @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {
/* 129 */     if (par1 == 0)
/*     */     {
/* 131 */       this.tileEntity.auraVisClient = par2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75130_a(IInventory par1IInventory) {
/* 138 */     IArcaneRecipe recipe = ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity.inventoryCraft, this.ip.field_70458_d);
/*     */     
/* 140 */     boolean hasVis = true;
/* 141 */     boolean hasCrystals = true;
/*     */     
/* 143 */     if (recipe != null) {
/* 144 */       int vis = 0;
/* 145 */       AspectList crystals = null;
/* 146 */       vis = recipe.getVis();
/* 147 */       vis = (int)(vis * (1.0F - CasterManager.getTotalVisDiscount(this.ip.field_70458_d)));
/* 148 */       crystals = recipe.getCrystals();
/* 149 */       this.tileEntity.getAura();
/* 150 */       hasVis = (this.tileEntity.func_145831_w()).field_72995_K ? ((this.tileEntity.auraVisClient >= vis)) : ((this.tileEntity.auraVisServer >= vis));
/* 151 */       if (crystals != null && crystals.size() > 0) {
/* 152 */         for (Aspect aspect : crystals.getAspects()) {
/* 153 */           if (ThaumcraftInvHelper.countTotalItemsIn(ThaumcraftInvHelper.wrapInventory(this.tileEntity.inventoryCraft, EnumFacing.UP), 
/* 154 */               ThaumcraftApiHelper.makeCrystal(aspect, crystals.getAmount(aspect)), ThaumcraftInvHelper.InvFilter.STRICT) < crystals.getAmount(aspect)) {
/* 155 */             hasCrystals = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 162 */     if (hasVis && hasCrystals) {
/* 163 */       func_192389_a(this.tileEntity.func_145831_w(), this.ip.field_70458_d, this.tileEntity.inventoryCraft, this.craftResult);
/*     */     }
/*     */     
/* 166 */     super.func_75142_b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_192389_a(World world, EntityPlayer player, InventoryCrafting craftMat, InventoryCraftResult craftRes) {
/* 172 */     if (!world.field_72995_K) {
/* 173 */       EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
/* 174 */       ItemStack itemstack = ItemStack.field_190927_a;
/* 175 */       IArcaneRecipe arecipe = ThaumcraftCraftingManager.findMatchingArcaneRecipe(craftMat, entityplayermp);
/* 176 */       if (arecipe != null && (arecipe.func_192399_d() || !world.func_82736_K().func_82766_b("doLimitedCrafting") || entityplayermp
/* 177 */         .func_192037_E().func_193830_f(arecipe)) && 
/* 178 */         ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(arecipe.getResearch())) {
/* 179 */         craftRes.func_193056_a(arecipe);
/* 180 */         itemstack = arecipe.func_77572_b(craftMat);
/*     */       } else {
/* 182 */         InventoryCrafting craftInv = new InventoryCrafting(new ContainerDummy(), 3, 3);
/* 183 */         for (int a = 0; a < 9; a++)
/* 184 */           craftInv.func_70299_a(a, craftMat.func_70301_a(a)); 
/* 185 */         IRecipe irecipe = CraftingManager.func_192413_b(craftInv, world);
/* 186 */         if (irecipe != null && (irecipe.func_192399_d() || !world.func_82736_K().func_82766_b("doLimitedCrafting") || entityplayermp
/* 187 */           .func_192037_E().func_193830_f(irecipe))) {
/* 188 */           craftRes.func_193056_a(irecipe);
/* 189 */           itemstack = irecipe.func_77572_b(craftMat);
/*     */         } 
/*     */       } 
/* 192 */       craftRes.func_70299_a(0, itemstack);
/* 193 */       entityplayermp.field_71135_a.func_147359_a(new SPacketSetSlot(this.field_75152_c, 0, itemstack));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/* 202 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/* 204 */     if (!(this.tileEntity.func_145831_w()).field_72995_K)
/*     */     {
/* 206 */       this.tileEntity.inventoryCraft.field_70465_c = new ContainerDummy();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
/* 213 */     return (this.tileEntity.func_145831_w().func_175625_s(this.tileEntity.func_174877_v()) != this.tileEntity) ? false : (
/* 214 */       (par1EntityPlayer.func_174831_c(this.tileEntity.func_174877_v()) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par1) {
/* 223 */     ItemStack var2 = ItemStack.field_190927_a;
/* 224 */     Slot var3 = (Slot)this.field_75151_b.get(par1);
/*     */     
/* 226 */     if (var3 != null && var3.func_75216_d()) {
/*     */       
/* 228 */       ItemStack var4 = var3.func_75211_c();
/* 229 */       var2 = var4.func_77946_l();
/*     */       
/* 231 */       if (par1 == 0) {
/*     */         
/* 233 */         if (!func_75135_a(var4, 16, 52, true))
/*     */         {
/* 235 */           return ItemStack.field_190927_a;
/*     */         }
/*     */         
/* 238 */         var3.func_75220_a(var4, var2);
/*     */       }
/* 240 */       else if (par1 >= 16 && par1 < 52) {
/*     */         
/* 242 */         for (ShardType st : ShardType.values()) {
/* 243 */           if (st.getMetadata() < 6 && 
/* 244 */             SlotCrystal.isValidCrystal(var4, st.getAspect())) {
/* 245 */             if (!func_75135_a(var4, 10 + st.getMetadata(), 11 + st.getMetadata(), false)) {
/* 246 */               return ItemStack.field_190927_a;
/*     */             }
/* 248 */             if (var4.func_190916_E() == 0)
/*     */               break; 
/*     */           } 
/* 251 */         }  if (var4.func_190916_E() != 0) {
/* 252 */           if (par1 >= 16 && par1 < 43)
/*     */           {
/* 254 */             if (!func_75135_a(var4, 43, 52, false))
/*     */             {
/* 256 */               return ItemStack.field_190927_a;
/*     */             }
/*     */           }
/* 259 */           else if (par1 >= 43 && par1 < 52)
/*     */           {
/* 261 */             if (!func_75135_a(var4, 16, 43, false))
/*     */             {
/* 263 */               return ItemStack.field_190927_a;
/*     */             }
/*     */           }
/*     */         
/*     */         }
/* 268 */       } else if (!func_75135_a(var4, 16, 52, false)) {
/*     */         
/* 270 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 273 */       if (var4.func_190916_E() == 0) {
/*     */         
/* 275 */         var3.func_75215_d(ItemStack.field_190927_a);
/*     */       }
/*     */       else {
/*     */         
/* 279 */         var3.func_75218_e();
/*     */       } 
/*     */       
/* 282 */       if (var4.func_190916_E() == var2.func_190916_E())
/*     */       {
/* 284 */         return ItemStack.field_190927_a;
/*     */       }
/*     */       
/* 287 */       var3.func_190901_a(this.ip.field_70458_d, var4);
/*     */     } 
/*     */     
/* 290 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public boolean func_94530_a(ItemStack stack, Slot slot) { return (slot.field_75224_c != this.craftResult && super.func_94530_a(stack, slot)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerArcaneWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */