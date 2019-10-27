/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import net.minecraftforge.items.VanillaInventoryCodeHooks;
/*     */ import net.minecraftforge.items.wrapper.InvWrapper;
/*     */ import net.minecraftforge.items.wrapper.SidedInvWrapper;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class ThaumcraftInvHelper
/*     */ {
/*     */   public static class InvFilter
/*     */   {
/*     */     public boolean igDmg;
/*     */     public boolean igNBT;
/*     */     
/*     */     public InvFilter(boolean ignoreDamage, boolean ignoreNBT, boolean useOre, boolean useMod) {
/*  31 */       this.relaxedNBT = false;
/*     */ 
/*     */       
/*  34 */       this.igDmg = ignoreDamage;
/*  35 */       this.igNBT = ignoreNBT;
/*  36 */       this.useOre = useOre;
/*  37 */       this.useMod = useMod;
/*     */     }
/*     */     public boolean useOre; public boolean useMod; public boolean relaxedNBT;
/*     */     public InvFilter setRelaxedNBT() {
/*  41 */       this.relaxedNBT = true;
/*  42 */       return this;
/*     */     }
/*     */     
/*  45 */     public static final InvFilter STRICT = new InvFilter(false, false, false, false);
/*  46 */     public static final InvFilter BASEORE = new InvFilter(false, false, true, false);
/*     */   }
/*     */   
/*     */   public static IItemHandler getItemHandlerAt(World world, BlockPos pos, EnumFacing side) {
/*  50 */     Pair<IItemHandler, Object> dest = VanillaInventoryCodeHooks.getItemHandler(world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), side);
/*  51 */     if (dest != null && dest.getLeft() != null) {
/*  52 */       return (IItemHandler)dest.getLeft();
/*     */     }
/*  54 */     TileEntity tileentity = world.func_175625_s(pos);
/*  55 */     if (tileentity != null && tileentity instanceof IInventory) {
/*  56 */       return wrapInventory((IInventory)tileentity, side);
/*     */     }
/*     */     
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   
/*  63 */   public static IItemHandler wrapInventory(IInventory inventory, EnumFacing side) { return (inventory instanceof ISidedInventory) ? new SidedInvWrapper((ISidedInventory)inventory, side) : new InvWrapper(inventory); }
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
/*     */   public static boolean areItemStackTagsEqualRelaxed(ItemStack prime, ItemStack other) {
/*  75 */     if (prime.func_190926_b() && other.func_190926_b())
/*     */     {
/*  77 */       return true;
/*     */     }
/*  79 */     if (!prime.func_190926_b() && !other.func_190926_b())
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       return (prime.func_77978_p() == null || compareTagsRelaxed(prime.func_77978_p(), other.func_77978_p()));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean compareTagsRelaxed(NBTTagCompound prime, NBTTagCompound other) {
/*  97 */     for (String key : prime.func_150296_c()) {
/*  98 */       if (!other.func_74764_b(key) || !prime.func_74781_a(key).equals(other.func_74781_a(key))) {
/*  99 */         return false;
/*     */       }
/*     */     } 
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areItemStacksEqualForCrafting(ItemStack stack0, Object in) {
/* 107 */     if (stack0 == null && in != null) return false; 
/* 108 */     if (stack0 != null && in == null) return false; 
/* 109 */     if (stack0 == null && in == null) return true;
/*     */     
/* 111 */     if (in instanceof Object[]) return true;
/*     */     
/* 113 */     if (in instanceof String) {
/* 114 */       NonNullList nonNullList = OreDictionary.getOres((String)in, false);
/* 115 */       return containsMatch(false, new ItemStack[] { stack0 }, nonNullList);
/*     */     } 
/*     */     
/* 118 */     if (in instanceof ItemStack) {
/*     */       
/* 120 */       boolean t1 = (!stack0.func_77942_o() || areItemStackTagsEqualForCrafting(stack0, (ItemStack)in));
/* 121 */       if (!t1) return false; 
/* 122 */       return OreDictionary.itemMatches((ItemStack)in, stack0, false);
/*     */     } 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean containsMatch(boolean strict, ItemStack[] inputs, List<ItemStack> targets) {
/* 130 */     for (ItemStack input : inputs) {
/*     */       
/* 132 */       for (ItemStack target : targets) {
/*     */         
/* 134 */         if (OreDictionary.itemMatches(target, input, strict) && ItemStack.func_77970_a(target, input))
/*     */         {
/* 136 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areItemsEqual(ItemStack s1, ItemStack s2) {
/* 145 */     if (s1.func_77984_f() && s2.func_77984_f())
/*     */     {
/* 147 */       return (s1.func_77973_b() == s2.func_77973_b());
/*     */     }
/* 149 */     return (s1.func_77973_b() == s2.func_77973_b() && s1.func_77952_i() == s2.func_77952_i());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areItemStackTagsEqualForCrafting(ItemStack slotItem, ItemStack recipeItem) {
/* 154 */     if (recipeItem == null || slotItem == null) return false; 
/* 155 */     if (recipeItem.func_77978_p() != null && slotItem.func_77978_p() == null) return false; 
/* 156 */     if (recipeItem.func_77978_p() == null) return true;
/*     */     
/* 158 */     Iterator iterator = recipeItem.func_77978_p().func_150296_c().iterator();
/* 159 */     while (iterator.hasNext()) {
/*     */       
/* 161 */       String s = (String)iterator.next();
/* 162 */       if (slotItem.func_77978_p().func_74764_b(s)) {
/* 163 */         if (!slotItem.func_77978_p().func_74781_a(s).toString().equals(recipeItem
/* 164 */             .func_77978_p().func_74781_a(s).toString()))
/* 165 */           return false; 
/*     */         continue;
/*     */       } 
/* 168 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack insertStackAt(World world, BlockPos pos, EnumFacing side, ItemStack stack, boolean simulate) {
/* 177 */     IItemHandler inventory = getItemHandlerAt(world, pos, side);
/* 178 */     if (inventory != null) {
/* 179 */       return ItemHandlerHelper.insertItemStacked(inventory, stack, simulate);
/*     */     }
/* 181 */     return stack;
/*     */   }
/*     */   
/*     */   public static ItemStack hasRoomFor(World world, BlockPos pos, EnumFacing side, ItemStack stack) {
/* 185 */     ItemStack testStack = insertStackAt(world, pos, side, stack.func_77946_l(), true);
/* 186 */     if (testStack.func_190926_b()) {
/* 187 */       return stack.func_77946_l();
/*     */     }
/* 189 */     testStack.func_190920_e(stack.func_190916_E() - testStack.func_190916_E());
/* 190 */     return testStack;
/*     */   }
/*     */   
/*     */   public static boolean hasRoomForSome(World world, BlockPos pos, EnumFacing side, ItemStack stack) {
/* 194 */     ItemStack testStack = insertStackAt(world, pos, side, stack.func_77946_l(), true);
/* 195 */     return (stack.func_190916_E() == 0 || testStack.func_190916_E() != stack.func_190916_E());
/*     */   }
/*     */ 
/*     */   
/* 199 */   public static boolean hasRoomForAll(World world, BlockPos pos, EnumFacing side, ItemStack stack) { return insertStackAt(world, pos, side, stack.func_77946_l(), true).func_190926_b(); }
/*     */ 
/*     */   
/*     */   public static int countTotalItemsIn(IItemHandler inventory, ItemStack stack, InvFilter filter) {
/* 203 */     int count = 0;
/* 204 */     if (inventory != null) {
/* 205 */       for (int a = 0; a < inventory.getSlots(); a++) {
/* 206 */         if (InventoryUtils.areItemStacksEqual(stack, inventory.getStackInSlot(a), filter)) {
/* 207 */           count += inventory.getStackInSlot(a).func_190916_E();
/*     */         }
/*     */       } 
/*     */     }
/* 211 */     return count;
/*     */   }
/*     */ 
/*     */   
/* 215 */   public static int countTotalItemsIn(World world, BlockPos pos, EnumFacing side, ItemStack stack, InvFilter filter) { return countTotalItemsIn(getItemHandlerAt(world, pos, side), stack, filter); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\ThaumcraftInvHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */