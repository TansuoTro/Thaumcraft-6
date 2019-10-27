/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.crafting.CraftingHelper;
/*    */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ 
/*    */ public class ShapedArcaneRecipe
/*    */   extends ShapedOreRecipe
/*    */   implements IArcaneRecipe
/*    */ {
/*    */   private String research;
/*    */   private int vis;
/*    */   private AspectList crystals;
/*    */   
/* 26 */   public ShapedArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, Block result, Object... recipe) { this(group, res, vis, crystals, new ItemStack(result), recipe); }
/* 27 */   public ShapedArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, Item result, Object... recipe) { this(group, res, vis, crystals, new ItemStack(result), recipe); }
/* 28 */   public ShapedArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, @Nonnull ItemStack result, Object... recipe) { this(group, res, vis, crystals, result, CraftingHelper.parseShaped(recipe)); }
/*    */   public ShapedArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, @Nonnull ItemStack result, CraftingHelper.ShapedPrimer primer) {
/* 30 */     super(group, result, primer);
/* 31 */     this.research = res;
/* 32 */     this.vis = vis;
/* 33 */     this.crystals = crystals;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting var1) {
/* 38 */     if (!(var1 instanceof IArcaneWorkbench)) return ItemStack.field_190927_a; 
/* 39 */     return super.func_77572_b(var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77569_a(InventoryCrafting inv, World world) {
/* 45 */     if (inv.func_70302_i_() < 15) return false;
/*    */     
/* 47 */     InventoryCrafting dummy = new InventoryCrafting(new ContainerDummy(), 3, 3);
/* 48 */     for (int a = 0; a < 9; ) { dummy.func_70299_a(a, inv.func_70301_a(a)); a++; }
/*    */     
/* 50 */     if (this.crystals != null && inv.func_70302_i_() >= 15)
/* 51 */       for (Aspect aspect : this.crystals.getAspects()) {
/* 52 */         ItemStack cs = ThaumcraftApiHelper.makeCrystal(aspect, this.crystals.getAmount(aspect));
/* 53 */         boolean b = false;
/* 54 */         for (int i = 0; i < 6; i++) {
/*    */           
/* 56 */           ItemStack itemstack1 = inv.func_70301_a(9 + i);
/* 57 */           if (itemstack1 != null && itemstack1.func_77973_b() == ItemsTC.crystalEssence && itemstack1.func_190916_E() >= cs.func_190916_E() && ItemStack.func_77970_a(cs, itemstack1))
/*    */           {
/* 59 */             b = true;
/*    */           }
/*    */         } 
/* 62 */         if (!b) return false;
/*    */       
/*    */       }  
/* 65 */     return (inv instanceof IArcaneWorkbench && super.func_77569_a(dummy, world));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public int getVis() { return this.vis; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public String getResearch() { return this.research; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   public AspectList getCrystals() { return this.crystals; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\ShapedArcaneRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */