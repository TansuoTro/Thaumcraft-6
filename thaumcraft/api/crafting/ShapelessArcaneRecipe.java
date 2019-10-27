/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class ShapelessArcaneRecipe
/*    */   extends ShapelessOreRecipe
/*    */   implements IArcaneRecipe {
/*    */   private String research;
/*    */   private int vis;
/*    */   private AspectList crystals;
/*    */   
/* 25 */   public ShapelessArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, Block result, Object... recipe) { this(group, res, vis, crystals, new ItemStack(result), recipe); }
/* 26 */   public ShapelessArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, Item result, Object... recipe) { this(group, res, vis, crystals, new ItemStack(result), recipe); }
/*    */   
/*    */   public ShapelessArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, NonNullList<Ingredient> input, @Nonnull ItemStack result) {
/* 29 */     super(group, input, result);
/* 30 */     this.research = res;
/* 31 */     this.vis = vis;
/* 32 */     this.crystals = crystals;
/*    */   }
/*    */   public ShapelessArcaneRecipe(ResourceLocation group, String res, int vis, AspectList crystals, ItemStack result, Object[] recipe) {
/* 35 */     super(group, result, recipe);
/* 36 */     this.research = res;
/* 37 */     this.vis = vis;
/* 38 */     this.crystals = crystals;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting var1) {
/* 43 */     if (!(var1 instanceof IArcaneWorkbench)) return ItemStack.field_190927_a; 
/* 44 */     return super.func_77572_b(var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77569_a(InventoryCrafting inv, World world) {
/* 50 */     InventoryCrafting dummy = new InventoryCrafting(new ContainerDummy(), 3, 3);
/* 51 */     for (int a = 0; a < 9; ) { dummy.func_70299_a(a, inv.func_70301_a(a)); a++; }
/*    */     
/* 53 */     if (this.crystals != null && inv.func_70302_i_() >= 15)
/* 54 */       for (Aspect aspect : this.crystals.getAspects()) {
/* 55 */         ItemStack cs = ThaumcraftApiHelper.makeCrystal(aspect, this.crystals.getAmount(aspect));
/* 56 */         boolean b = false;
/* 57 */         for (int i = 0; i < 6; i++) {
/*    */           
/* 59 */           ItemStack itemstack1 = inv.func_70301_a(9 + i);
/* 60 */           if (itemstack1 != null && itemstack1.func_77973_b() == ItemsTC.crystalEssence && itemstack1.func_190916_E() >= cs.func_190916_E() && ItemStack.func_77970_a(cs, itemstack1))
/*    */           {
/* 62 */             b = true;
/*    */           }
/*    */         } 
/* 65 */         if (!b) return false;
/*    */       
/*    */       }  
/* 68 */     return (inv instanceof IArcaneWorkbench && super.func_77569_a(dummy, world));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public int getVis() { return this.vis; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   public String getResearch() { return this.research; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 83 */   public AspectList getCrystals() { return this.crystals; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\ShapelessArcaneRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */