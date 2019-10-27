/*    */ package thaumcraft.common.lib.crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.items.resources.ItemCrystalEssence;
/*    */ 
/*    */ public class RecipeMagicDust
/*    */   extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
/*    */   public boolean func_77569_a(InventoryCrafting inv, World worldIn) {
/* 18 */     boolean bowl = false;
/* 19 */     boolean flint = false;
/* 20 */     boolean redstone = false;
/* 21 */     ArrayList<String> crystals = new ArrayList<String>();
/* 22 */     for (int a = 0; a < 3; a++) {
/* 23 */       for (int b = 0; b < 3; b++) {
/* 24 */         if (inv.func_70463_b(a, b) != null && !inv.func_70463_b(a, b).func_190926_b()) {
/* 25 */           ItemStack stack = inv.func_70463_b(a, b).func_77946_l();
/* 26 */           if (stack.func_77973_b() == Items.field_151054_z && bowl) return false; 
/* 27 */           if (stack.func_77973_b() == Items.field_151054_z && !bowl)
/* 28 */           { bowl = true; }
/*    */           else
/*    */           
/* 31 */           { if (stack.func_77973_b() == Items.field_151145_ak && flint) return false; 
/* 32 */             if (stack.func_77973_b() == Items.field_151145_ak && !flint)
/* 33 */             { flint = true; }
/*    */             else
/*    */             
/* 36 */             { if (stack.func_77973_b() == Items.field_151137_ax && redstone) return false; 
/* 37 */               if (stack.func_77973_b() == Items.field_151137_ax && !redstone)
/* 38 */               { redstone = true;
/*    */                  }
/*    */               
/* 41 */               else if (stack.func_77973_b() == ItemsTC.crystalEssence)
/* 42 */               { ItemCrystalEssence ice = (ItemCrystalEssence)stack.func_77973_b();
/* 43 */                 if (crystals.contains(ice.getAspects(stack).getAspects()[0].getTag()) || crystals.size() >= 3) return false; 
/* 44 */                 crystals.add(ice.getAspects(stack).getAspects()[0].getTag()); }
/*    */               else
/*    */               
/* 47 */               { return false; }  }  } 
/*    */         } 
/*    */       } 
/* 50 */     }  return (bowl && redstone && flint && crystals.size() == 3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public ItemStack func_77572_b(InventoryCrafting inv) { return new ItemStack(ItemsTC.salisMundus); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public boolean func_194133_a(int width, int height) { return (width * height >= 6); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public ItemStack func_77571_b() { return new ItemStack(ItemsTC.salisMundus); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NonNullList<ItemStack> func_179532_b(InventoryCrafting inv) {
/* 71 */     NonNullList<ItemStack> ret = NonNullList.func_191197_a(inv.func_70302_i_(), ItemStack.field_190927_a);
/* 72 */     for (int i = 0; i < ret.size(); i++) {
/*    */       
/* 74 */       ItemStack itemstack = inv.func_70301_a(i);
/* 75 */       ItemStack itemstack2 = ForgeHooks.getContainerItem(itemstack);
/* 76 */       if (itemstack != null && !itemstack.func_190926_b() && (itemstack.func_77973_b() == Items.field_151145_ak || itemstack.func_77973_b() == Items.field_151054_z)) {
/* 77 */         ItemStack is = itemstack.func_77946_l();
/* 78 */         is.func_190920_e(1);
/* 79 */         itemstack2 = is;
/*    */       } 
/* 81 */       ret.set(i, itemstack2);
/*    */     } 
/* 83 */     return ret;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\RecipeMagicDust.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */