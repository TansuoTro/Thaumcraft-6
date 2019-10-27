/*     */ package thaumcraft.common.container.slot;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCraftResult;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.ContainerDummy;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.items.casters.CasterManager;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ 
/*     */ public class SlotCraftingArcaneWorkbench
/*     */   extends Slot {
/*     */   private final InventoryCrafting craftMatrix;
/*     */   private EntityPlayer player;
/*     */   private int amountCrafted;
/*     */   private TileArcaneWorkbench tile;
/*     */   
/*     */   public SlotCraftingArcaneWorkbench(TileArcaneWorkbench te, EntityPlayer par1EntityPlayer, InventoryCrafting inventory, IInventory par3IInventory, int par4, int par5, int par6) {
/*  33 */     super(par3IInventory, par4, par5, par6);
/*  34 */     this.player = par1EntityPlayer;
/*  35 */     this.craftMatrix = inventory;
/*     */     
/*  37 */     this.tile = te;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public boolean func_75214_a(ItemStack stack) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_75209_a(int amount) {
/*  49 */     if (func_75216_d())
/*     */     {
/*  51 */       this.amountCrafted += Math.min(amount, func_75211_c().func_190916_E());
/*     */     }
/*     */     
/*  54 */     return super.func_75209_a(amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_75210_a(ItemStack stack, int amount) {
/*  60 */     this.amountCrafted += amount;
/*  61 */     func_75208_c(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   protected void func_190900_b(int p_190900_1_) { this.amountCrafted += p_190900_1_; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_75208_c(ItemStack stack) {
/*  73 */     if (this.amountCrafted > 0) {
/*     */       
/*  75 */       stack.func_77980_a(this.player.field_70170_p, this.player, this.amountCrafted);
/*  76 */       FMLCommonHandler.instance().firePlayerCraftingEvent(this.player, stack, this.craftMatrix);
/*     */     } 
/*     */     
/*  79 */     this.amountCrafted = 0;
/*  80 */     InventoryCraftResult inventorycraftresult = (InventoryCraftResult)this.field_75224_c;
/*  81 */     IRecipe irecipe = inventorycraftresult.func_193055_i();
/*     */     
/*  83 */     if (irecipe != null && !irecipe.func_192399_d()) {
/*     */       
/*  85 */       this.player.func_192021_a(Lists.newArrayList(new IRecipe[] { irecipe }));
/*  86 */       inventorycraftresult.func_193056_a((IRecipe)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_190901_a(EntityPlayer thePlayer, ItemStack stack) {
/*     */     NonNullList<ItemStack> nonnulllist;
/*  93 */     func_75208_c(stack);
/*     */     
/*  95 */     IArcaneRecipe recipe = ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.craftMatrix, thePlayer);
/*  96 */     InventoryCrafting ic = this.craftMatrix;
/*     */     
/*  98 */     ForgeHooks.setCraftingPlayer(thePlayer);
/*     */     
/* 100 */     if (recipe != null) {
/* 101 */       nonnulllist = CraftingManager.func_180303_b(this.craftMatrix, thePlayer.field_70170_p);
/*     */     } else {
/* 103 */       ic = new InventoryCrafting(new ContainerDummy(), 3, 3);
/* 104 */       for (int a = 0; a < 9; a++) {
/* 105 */         ic.func_70299_a(a, this.craftMatrix.func_70301_a(a));
/*     */       }
/* 107 */       ic.field_70465_c = this.craftMatrix.field_70465_c;
/* 108 */       nonnulllist = CraftingManager.func_180303_b(ic, thePlayer.field_70170_p);
/*     */     } 
/*     */     
/* 111 */     ForgeHooks.setCraftingPlayer(null);
/*     */ 
/*     */     
/* 114 */     int vis = 0;
/* 115 */     AspectList crystals = null;
/*     */     
/* 117 */     if (recipe != null) {
/* 118 */       vis = recipe.getVis();
/* 119 */       vis = (int)(vis * (1.0F - CasterManager.getTotalVisDiscount(thePlayer)));
/* 120 */       crystals = recipe.getCrystals();
/* 121 */       if (vis > 0) {
/* 122 */         this.tile.getAura();
/* 123 */         this.tile.spendAura(vis);
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     for (int i = 0; i < Math.min(9, nonnulllist.size()); i++) {
/*     */       
/* 129 */       ItemStack itemstack = ic.func_70301_a(i);
/* 130 */       ItemStack itemstack1 = (ItemStack)nonnulllist.get(i);
/*     */       
/* 132 */       if (!itemstack.func_190926_b()) {
/*     */         
/* 134 */         this.craftMatrix.func_70298_a(i, 1);
/* 135 */         itemstack = ic.func_70301_a(i);
/*     */       } 
/*     */       
/* 138 */       if (!itemstack1.func_190926_b())
/*     */       {
/* 140 */         if (itemstack.func_190926_b()) {
/*     */           
/* 142 */           this.craftMatrix.func_70299_a(i, itemstack1);
/*     */         }
/* 144 */         else if (ItemStack.func_179545_c(itemstack, itemstack1) && ItemStack.func_77970_a(itemstack, itemstack1)) {
/*     */           
/* 146 */           itemstack1.func_190917_f(itemstack.func_190916_E());
/* 147 */           this.craftMatrix.func_70299_a(i, itemstack1);
/*     */         }
/* 149 */         else if (!this.player.field_71071_by.func_70441_a(itemstack1)) {
/*     */           
/* 151 */           this.player.func_71019_a(itemstack1, false);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 156 */     if (crystals != null) {
/* 157 */       for (Aspect aspect : crystals.getAspects()) {
/* 158 */         ItemStack cs = ThaumcraftApiHelper.makeCrystal(aspect, crystals.getAmount(aspect));
/* 159 */         for (int i = 0; i < 6; i++) {
/*     */           
/* 161 */           ItemStack itemstack1 = this.craftMatrix.func_70301_a(9 + i);
/* 162 */           if (itemstack1.func_77973_b() == ItemsTC.crystalEssence && ItemStack.func_77970_a(cs, itemstack1))
/*     */           {
/* 164 */             this.craftMatrix.func_70298_a(9 + i, cs.func_190916_E());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 169 */     return stack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotCraftingArcaneWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */