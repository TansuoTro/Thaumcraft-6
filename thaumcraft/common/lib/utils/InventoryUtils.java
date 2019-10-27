/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.Tuple;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryUtils
/*     */ {
/*  36 */   public static ItemStack copyMaxedStack(ItemStack stack) { return copyLimitedStack(stack, stack.func_77976_d()); }
/*     */ 
/*     */   
/*     */   public static ItemStack copyLimitedStack(ItemStack stack, int limit) {
/*  40 */     if (stack == null) return ItemStack.field_190927_a; 
/*  41 */     ItemStack s = stack.func_77946_l();
/*  42 */     if (s.func_190916_E() > limit) {
/*  43 */       s.func_190920_e(limit);
/*     */     }
/*  45 */     return s;
/*     */   }
/*     */   
/*     */   public static boolean consumeItemsFromAdjacentInventoryOrPlayer(World world, BlockPos pos, EntityPlayer player, boolean sim, ItemStack... items) {
/*  49 */     for (ItemStack stack : items) {
/*  50 */       boolean b = checkAdjacentChests(world, pos, stack);
/*  51 */       if (!b) b = isPlayerCarryingAmount(player, stack, true); 
/*  52 */       if (!b) return false; 
/*     */     } 
/*  54 */     if (!sim) {
/*  55 */       for (ItemStack stack : items) {
/*  56 */         if (!consumeFromAdjacentChests(world, pos, stack.func_77946_l())) {
/*  57 */           consumePlayerItem(player, stack, true, true);
/*     */         }
/*     */       } 
/*     */     }
/*  61 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean checkAdjacentChests(World world, BlockPos pos, ItemStack itemStack) {
/*  65 */     int c = itemStack.func_190916_E();
/*  66 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/*  67 */       if (face != EnumFacing.UP) {
/*  68 */         c -= ThaumcraftInvHelper.countTotalItemsIn(world, pos.func_177972_a(face), face.func_176734_d(), itemStack.func_77946_l(), ThaumcraftInvHelper.InvFilter.BASEORE);
/*  69 */         if (c <= 0) return true; 
/*     */       } 
/*  71 */     }  return false;
/*     */   }
/*     */   
/*     */   public static boolean consumeFromAdjacentChests(World world, BlockPos pos, ItemStack itemStack) {
/*  75 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/*  76 */       if (face != EnumFacing.UP && !itemStack.func_190926_b())
/*  77 */       { ItemStack os = removeStackFrom(world, pos.func_177972_a(face), face.func_176734_d(), itemStack, ThaumcraftInvHelper.InvFilter.BASEORE, false);
/*  78 */         itemStack.func_190920_e(itemStack.func_190916_E() - os.func_190916_E());
/*  79 */         if (itemStack.func_190926_b())
/*     */           break;  } 
/*  81 */     }  return itemStack.func_190926_b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static ItemStack insertStackAt(World world, BlockPos pos, EnumFacing side, ItemStack stack, boolean simulate) { return ThaumcraftInvHelper.insertStackAt(world, pos, side, stack, simulate); }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static void ejectStackAt(World world, BlockPos pos, EnumFacing side, ItemStack out) { ejectStackAt(world, pos, side, out, false); }
/*     */ 
/*     */   
/*     */   public static ItemStack ejectStackAt(World world, BlockPos pos, EnumFacing side, ItemStack out, boolean smart) {
/* 100 */     out = ThaumcraftInvHelper.insertStackAt(world, pos.func_177972_a(side), side.func_176734_d(), out, false);
/* 101 */     if (smart && ThaumcraftInvHelper.getItemHandlerAt(world, pos.func_177972_a(side), side.func_176734_d()) != null) {
/* 102 */       return out;
/*     */     }
/* 104 */     if (!out.func_190926_b()) {
/* 105 */       if (world.func_175665_u(pos.func_177972_a(side))) pos = pos.func_177972_a(side.func_176734_d());
/*     */ 
/*     */ 
/*     */       
/* 109 */       EntityItem entityitem2 = new EntityItem(world, pos.func_177958_n() + 0.5D + (1 * side.func_82601_c()), (pos.func_177956_o() + (1 * side.func_96559_d())), pos.func_177952_p() + 0.5D + (1 * side.func_82599_e()), out);
/* 110 */       entityitem2.field_70159_w = 0.3D * side.func_82601_c();
/* 111 */       entityitem2.field_70181_x = 0.3D * side.func_96559_d();
/* 112 */       entityitem2.field_70179_y = 0.3D * side.func_82599_e();
/* 113 */       world.func_72838_d(entityitem2);
/*     */     } 
/* 115 */     return ItemStack.field_190927_a;
/*     */   }
/*     */ 
/*     */   
/* 119 */   public static ItemStack removeStackFrom(World world, BlockPos pos, EnumFacing side, ItemStack stack, ThaumcraftInvHelper.InvFilter filter, boolean simulate) { return removeStackFrom(ThaumcraftInvHelper.getItemHandlerAt(world, pos, side), stack, filter, simulate); }
/*     */ 
/*     */   
/*     */   public static ItemStack removeStackFrom(IItemHandler inventory, ItemStack stack, ThaumcraftInvHelper.InvFilter filter, boolean simulate) {
/* 123 */     int amount = stack.func_190916_E();
/* 124 */     int removed = 0;
/*     */     
/* 126 */     if (inventory != null)
/* 127 */       for (int a = 0; a < inventory.getSlots(); a++) {
/* 128 */         if (areItemStacksEqual(stack, inventory.getStackInSlot(a), filter)) {
/* 129 */           int s = Math.min(amount - removed, inventory.getStackInSlot(a).func_190916_E());
/* 130 */           ItemStack es = inventory.extractItem(a, s, simulate);
/* 131 */           if (es != null && !es.func_190926_b()) {
/* 132 */             removed += es.func_190916_E();
/*     */           }
/*     */         } 
/* 135 */         if (removed >= amount) {
/*     */           break;
/*     */         }
/*     */       }  
/* 139 */     if (removed == 0) return ItemStack.field_190927_a; 
/* 140 */     ItemStack s = stack.func_77946_l();
/* 141 */     s.func_190920_e(removed);
/* 142 */     return s;
/*     */   }
/*     */   
/*     */   public static int countStackInWorld(World world, BlockPos pos, ItemStack stack, double range, ThaumcraftInvHelper.InvFilter filter) {
/* 146 */     int count = 0;
/* 147 */     List<EntityItem> l = EntityUtils.getEntitiesInRange(world, pos, null, EntityItem.class, range);
/* 148 */     for (EntityItem ei : l) {
/* 149 */       if (ei.func_92059_d() != null && ei.func_92059_d().func_190926_b() && areItemStacksEqual(stack, ei.func_92059_d(), filter)) {
/* 150 */         count += ei.func_92059_d().func_190916_E();
/*     */       }
/*     */     } 
/* 153 */     return count;
/*     */   }
/*     */   
/*     */   public static void dropItems(World world, BlockPos pos) {
/* 157 */     TileEntity tileEntity = world.func_175625_s(pos);
/* 158 */     if (!(tileEntity instanceof IInventory)) {
/*     */       return;
/*     */     }
/* 161 */     IInventory inventory = (IInventory)tileEntity;
/* 162 */     InventoryHelper.func_180175_a(world, pos, inventory);
/*     */   }
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
/*     */   public static boolean consumePlayerItem(EntityPlayer player, ItemStack item, boolean nocheck, boolean ore) { // Byte code:
/*     */     //   0: iload_2
/*     */     //   1: ifne -> 15
/*     */     //   4: aload_0
/*     */     //   5: aload_1
/*     */     //   6: iload_3
/*     */     //   7: invokestatic isPlayerCarryingAmount : (Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;Z)Z
/*     */     //   10: ifne -> 15
/*     */     //   13: iconst_0
/*     */     //   14: ireturn
/*     */     //   15: aload_1
/*     */     //   16: invokevirtual func_190916_E : ()I
/*     */     //   19: istore #4
/*     */     //   21: iconst_0
/*     */     //   22: istore #5
/*     */     //   24: iload #5
/*     */     //   26: aload_0
/*     */     //   27: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   30: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   33: invokevirtual size : ()I
/*     */     //   36: if_icmpge -> 209
/*     */     //   39: aload_1
/*     */     //   40: aload_0
/*     */     //   41: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   44: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   47: iload #5
/*     */     //   49: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   52: checkcast net/minecraft/item/ItemStack
/*     */     //   55: invokestatic checkEnchantedPlaceholder : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   58: ifne -> 108
/*     */     //   61: aload_0
/*     */     //   62: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   65: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   68: iload #5
/*     */     //   70: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   73: checkcast net/minecraft/item/ItemStack
/*     */     //   76: aload_1
/*     */     //   77: new thaumcraft/api/ThaumcraftInvHelper$InvFilter
/*     */     //   80: dup
/*     */     //   81: iconst_0
/*     */     //   82: aload_1
/*     */     //   83: invokevirtual func_77942_o : ()Z
/*     */     //   86: ifne -> 93
/*     */     //   89: iconst_1
/*     */     //   90: goto -> 94
/*     */     //   93: iconst_0
/*     */     //   94: iload_3
/*     */     //   95: iconst_0
/*     */     //   96: invokespecial <init> : (ZZZZ)V
/*     */     //   99: invokevirtual setRelaxedNBT : ()Lthaumcraft/api/ThaumcraftInvHelper$InvFilter;
/*     */     //   102: invokestatic areItemStacksEqual : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Lthaumcraft/api/ThaumcraftInvHelper$InvFilter;)Z
/*     */     //   105: ifeq -> 203
/*     */     //   108: aload_0
/*     */     //   109: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   112: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   115: iload #5
/*     */     //   117: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   120: checkcast net/minecraft/item/ItemStack
/*     */     //   123: invokevirtual func_190916_E : ()I
/*     */     //   126: iload #4
/*     */     //   128: if_icmple -> 157
/*     */     //   131: aload_0
/*     */     //   132: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   135: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   138: iload #5
/*     */     //   140: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   143: checkcast net/minecraft/item/ItemStack
/*     */     //   146: iload #4
/*     */     //   148: invokevirtual func_190918_g : (I)V
/*     */     //   151: iconst_0
/*     */     //   152: istore #4
/*     */     //   154: goto -> 196
/*     */     //   157: iload #4
/*     */     //   159: aload_0
/*     */     //   160: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   163: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   166: iload #5
/*     */     //   168: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   171: checkcast net/minecraft/item/ItemStack
/*     */     //   174: invokevirtual func_190916_E : ()I
/*     */     //   177: isub
/*     */     //   178: istore #4
/*     */     //   180: aload_0
/*     */     //   181: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   184: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   187: iload #5
/*     */     //   189: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
/*     */     //   192: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
/*     */     //   195: pop
/*     */     //   196: iload #4
/*     */     //   198: ifgt -> 203
/*     */     //   201: iconst_1
/*     */     //   202: ireturn
/*     */     //   203: iinc #5, 1
/*     */     //   206: goto -> 24
/*     */     //   209: iconst_0
/*     */     //   210: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #171	-> 0
/*     */     //   #173	-> 15
/*     */     //   #174	-> 21
/*     */     //   #176	-> 39
/*     */     //   #177	-> 70
/*     */     //   #179	-> 108
/*     */     //   #180	-> 131
/*     */     //   #181	-> 151
/*     */     //   #183	-> 157
/*     */     //   #184	-> 180
/*     */     //   #187	-> 196
/*     */     //   #174	-> 203
/*     */     //   #191	-> 209
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   24	185	5	var2	I
/*     */     //   0	211	0	player	Lnet/minecraft/entity/player/EntityPlayer;
/*     */     //   0	211	1	item	Lnet/minecraft/item/ItemStack;
/*     */     //   0	211	2	nocheck	Z
/*     */     //   0	211	3	ore	Z
/*     */     //   21	190	4	count	I }
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
/*     */   public static boolean consumePlayerItem(EntityPlayer player, Item item, int md, int amt) {
/* 196 */     if (!isPlayerCarryingAmount(player, new ItemStack(item, amt, md), false)) return false;
/*     */     
/* 198 */     int count = amt;
/* 199 */     for (int var2 = 0; var2 < player.field_71071_by.field_70462_a.size(); var2++) {
/*     */       
/* 201 */       if (((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_77973_b() == item && ((ItemStack)player.field_71071_by.field_70462_a
/* 202 */         .get(var2)).func_77952_i() == md) {
/*     */         
/* 204 */         if (((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_190916_E() > count) {
/* 205 */           ((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_190918_g(count);
/* 206 */           count = 0;
/*     */         } else {
/* 208 */           count -= ((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_190916_E();
/* 209 */           player.field_71071_by.field_70462_a.set(var2, ItemStack.field_190927_a);
/*     */         } 
/*     */         
/* 212 */         if (count <= 0) return true;
/*     */       
/*     */       } 
/*     */     } 
/* 216 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean consumePlayerItem(EntityPlayer player, Item item, int md) {
/* 221 */     for (int var2 = 0; var2 < player.field_71071_by.field_70462_a.size(); var2++) {
/*     */       
/* 223 */       if (((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_77973_b() == item && ((ItemStack)player.field_71071_by.field_70462_a
/* 224 */         .get(var2)).func_77952_i() == md) {
/* 225 */         ((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_190918_g(1);
/* 226 */         if (((ItemStack)player.field_71071_by.field_70462_a.get(var2)).func_190916_E() <= 0)
/*     */         {
/* 228 */           player.field_71071_by.field_70462_a.set(var2, ItemStack.field_190927_a);
/*     */         }
/* 230 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPlayerCarryingAmount(EntityPlayer player, ItemStack stack, boolean ore) { // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ifnull -> 11
/*     */     //   4: aload_1
/*     */     //   5: invokevirtual func_190926_b : ()Z
/*     */     //   8: ifeq -> 13
/*     */     //   11: iconst_0
/*     */     //   12: ireturn
/*     */     //   13: aload_1
/*     */     //   14: invokevirtual func_190916_E : ()I
/*     */     //   17: istore_3
/*     */     //   18: iconst_0
/*     */     //   19: istore #4
/*     */     //   21: iload #4
/*     */     //   23: aload_0
/*     */     //   24: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   27: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   30: invokevirtual size : ()I
/*     */     //   33: if_icmpge -> 138
/*     */     //   36: aload_1
/*     */     //   37: aload_0
/*     */     //   38: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   41: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   44: iload #4
/*     */     //   46: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   49: checkcast net/minecraft/item/ItemStack
/*     */     //   52: invokestatic checkEnchantedPlaceholder : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   55: ifne -> 105
/*     */     //   58: aload_0
/*     */     //   59: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   62: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   65: iload #4
/*     */     //   67: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   70: checkcast net/minecraft/item/ItemStack
/*     */     //   73: aload_1
/*     */     //   74: new thaumcraft/api/ThaumcraftInvHelper$InvFilter
/*     */     //   77: dup
/*     */     //   78: iconst_0
/*     */     //   79: aload_1
/*     */     //   80: invokevirtual func_77942_o : ()Z
/*     */     //   83: ifne -> 90
/*     */     //   86: iconst_1
/*     */     //   87: goto -> 91
/*     */     //   90: iconst_0
/*     */     //   91: iload_2
/*     */     //   92: iconst_0
/*     */     //   93: invokespecial <init> : (ZZZZ)V
/*     */     //   96: invokevirtual setRelaxedNBT : ()Lthaumcraft/api/ThaumcraftInvHelper$InvFilter;
/*     */     //   99: invokestatic areItemStacksEqual : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Lthaumcraft/api/ThaumcraftInvHelper$InvFilter;)Z
/*     */     //   102: ifeq -> 132
/*     */     //   105: iload_3
/*     */     //   106: aload_0
/*     */     //   107: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   110: getfield field_70462_a : Lnet/minecraft/util/NonNullList;
/*     */     //   113: iload #4
/*     */     //   115: invokevirtual get : (I)Ljava/lang/Object;
/*     */     //   118: checkcast net/minecraft/item/ItemStack
/*     */     //   121: invokevirtual func_190916_E : ()I
/*     */     //   124: isub
/*     */     //   125: istore_3
/*     */     //   126: iload_3
/*     */     //   127: ifgt -> 132
/*     */     //   130: iconst_1
/*     */     //   131: ireturn
/*     */     //   132: iinc #4, 1
/*     */     //   135: goto -> 21
/*     */     //   138: iconst_0
/*     */     //   139: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #239	-> 0
/*     */     //   #240	-> 13
/*     */     //   #241	-> 18
/*     */     //   #243	-> 36
/*     */     //   #244	-> 67
/*     */     //   #246	-> 105
/*     */     //   #247	-> 126
/*     */     //   #241	-> 132
/*     */     //   #251	-> 138
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   21	117	4	var2	I
/*     */     //   0	140	0	player	Lnet/minecraft/entity/player/EntityPlayer;
/*     */     //   0	140	1	stack	Lnet/minecraft/item/ItemStack;
/*     */     //   0	140	2	ore	Z
/*     */     //   18	122	3	count	I }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkEnchantedPlaceholder(ItemStack stack, ItemStack stack2) {
/* 255 */     if (stack.func_77973_b() != ItemsTC.enchantedPlaceholder) return false; 
/* 256 */     Map<Enchantment, Integer> en = EnchantmentHelper.func_82781_a(stack);
/* 257 */     boolean b = !en.isEmpty();
/* 258 */     label26: for (Enchantment e : en.keySet()) {
/* 259 */       Map<Enchantment, Integer> en2 = EnchantmentHelper.func_82781_a(stack2);
/* 260 */       if (en2.isEmpty()) return false; 
/* 261 */       b = false;
/* 262 */       for (Enchantment e2 : en2.keySet()) {
/* 263 */         if (!e2.equals(e))
/* 264 */           continue;  b = true;
/* 265 */         if (((Integer)en2.get(e2)).intValue() < ((Integer)en.get(e)).intValue()) {
/* 266 */           b = false;
/*     */           break label26;
/*     */         } 
/*     */       } 
/*     */     } 
/* 271 */     return b;
/*     */   }
/*     */   
/*     */   public static EntityEquipmentSlot isHoldingItem(EntityPlayer player, Item item) {
/* 275 */     if (player == null || item == null) return null; 
/* 276 */     if (player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() == item) return EntityEquipmentSlot.MAINHAND; 
/* 277 */     if (player.func_184592_cb() != null && player.func_184592_cb().func_77973_b() == item) return EntityEquipmentSlot.OFFHAND; 
/* 278 */     return null;
/*     */   }
/*     */   
/*     */   public static EntityEquipmentSlot isHoldingItem(EntityPlayer player, Class item) {
/* 282 */     if (player == null || item == null) return null; 
/* 283 */     if (player.func_184614_ca() != null && item.isAssignableFrom(player.func_184614_ca().func_77973_b().getClass())) return EntityEquipmentSlot.MAINHAND; 
/* 284 */     if (player.func_184592_cb() != null && item.isAssignableFrom(player.func_184592_cb().func_77973_b().getClass())) return EntityEquipmentSlot.OFFHAND; 
/* 285 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPlayerSlotFor(EntityPlayer player, ItemStack stack) {
/* 290 */     for (int i = 0; i < player.field_71071_by.field_70462_a.size(); i++) {
/*     */       
/* 292 */       if (!((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_190926_b() && stackEqualExact(stack, (ItemStack)player.field_71071_by.field_70462_a.get(i)))
/*     */       {
/* 294 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 298 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 303 */   public static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) { return (stack1.func_77973_b() == stack2.func_77973_b() && (!stack1.func_77981_g() || stack1.func_77960_j() == stack2.func_77960_j()) && ItemStack.func_77970_a(stack1, stack2)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public static boolean areItemStacksEqualStrict(ItemStack stack0, ItemStack stack1) { return areItemStacksEqual(stack0, stack1, ThaumcraftInvHelper.InvFilter.STRICT); }
/*     */ 
/*     */ 
/*     */   
/* 313 */   public static ItemStack findFirstMatchFromFilter(NonNullList<ItemStack> filterStacks, boolean blacklist, IItemHandler inv, EnumFacing face, ThaumcraftInvHelper.InvFilter filter) { return findFirstMatchFromFilter(filterStacks, blacklist, inv, face, filter, false); }
/*     */ 
/*     */   
/*     */   public static ItemStack findFirstMatchFromFilter(NonNullList<ItemStack> filterStacks, boolean blacklist, IItemHandler inv, EnumFacing face, ThaumcraftInvHelper.InvFilter filter, boolean leaveOne) {
/*     */     int a;
/* 318 */     label34: for (a = 0; a < inv.getSlots(); a++) {
/* 319 */       ItemStack is = inv.getStackInSlot(a);
/* 320 */       if (is != null && !is.func_190926_b() && is.func_190916_E() > 0)
/*     */       {
/* 322 */         if (!leaveOne || ThaumcraftInvHelper.countTotalItemsIn(inv, is, filter) >= 2) {
/*     */           
/* 324 */           boolean allow = false;
/* 325 */           boolean allEmpty = true;
/*     */           
/* 327 */           for (ItemStack fs : filterStacks) {
/* 328 */             if (fs == null || fs.func_190926_b())
/* 329 */               continue;  allEmpty = false;
/*     */             
/* 331 */             boolean r = areItemStacksEqual(fs.func_77946_l(), is.func_77946_l(), filter);
/* 332 */             if (blacklist) {
/* 333 */               if (!r) {
/* 334 */                 allow = true;
/*     */                 continue;
/*     */               } 
/*     */               continue label34;
/*     */             } 
/* 339 */             if (r) {
/* 340 */               return is;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 345 */           if (blacklist && (allow || allEmpty)) return is; 
/*     */         }  } 
/*     */     } 
/* 348 */     return ItemStack.field_190927_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean matchesFilters(NonNullList<ItemStack> nonNullList, boolean blacklist, ItemStack is, ThaumcraftInvHelper.InvFilter filter) {
/* 353 */     if (is == null || is.func_190926_b() || is.func_190916_E() <= 0) return false; 
/* 354 */     boolean allow = false;
/* 355 */     boolean allEmpty = true;
/* 356 */     for (ItemStack fs : nonNullList) {
/* 357 */       if (fs == null || fs.func_190926_b())
/* 358 */         continue;  allEmpty = false;
/*     */       
/* 360 */       boolean r = areItemStacksEqual(fs.func_77946_l(), is.func_77946_l(), filter);
/* 361 */       if (blacklist) {
/* 362 */         if (!r) { allow = true; continue; }  return false;
/*     */       } 
/* 364 */       if (r) {
/* 365 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 370 */     if (blacklist && (allow || allEmpty)) {
/* 371 */       return true;
/*     */     }
/*     */     
/* 374 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 378 */   public static ItemStack findFirstMatchFromFilter(NonNullList<ItemStack> filterStacks, NonNullList<Integer> filterStacksSizes, boolean blacklist, NonNullList<ItemStack> itemStacks, ThaumcraftInvHelper.InvFilter filter) { return (ItemStack)findFirstMatchFromFilterTuple(filterStacks, filterStacksSizes, blacklist, itemStacks, filter).func_76341_a(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Tuple<ItemStack, Integer> findFirstMatchFromFilterTuple(NonNullList<ItemStack> filterStacks, NonNullList<Integer> filterStacksSizes, boolean blacklist, NonNullList<ItemStack> stacks, ThaumcraftInvHelper.InvFilter filter) {
/* 384 */     label31: for (ItemStack is : stacks) {
/* 385 */       if (is == null || is.func_190926_b() || is.func_190916_E() <= 0)
/* 386 */         continue;  boolean allow = false;
/* 387 */       boolean allEmpty = true;
/* 388 */       for (int idx = 0; idx < filterStacks.size(); idx++) {
/* 389 */         ItemStack fs = (ItemStack)filterStacks.get(idx);
/* 390 */         if (fs != null && !fs.func_190926_b()) {
/* 391 */           allEmpty = false;
/*     */           
/* 393 */           boolean r = areItemStacksEqual(fs.func_77946_l(), is.func_77946_l(), filter);
/* 394 */           if (blacklist) {
/* 395 */             if (!r) { allow = true; } else { continue label31; }
/*     */           
/* 397 */           } else if (r) {
/* 398 */             return new Tuple(is, filterStacksSizes.get(idx));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 403 */       if (blacklist && (allow || allEmpty)) {
/* 404 */         return new Tuple(is, Integer.valueOf(0));
/*     */       }
/*     */     } 
/*     */     
/* 408 */     return new Tuple(ItemStack.field_190927_a, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, ThaumcraftInvHelper.InvFilter filter) {
/* 413 */     if (stack0 == null && stack1 != null) return false; 
/* 414 */     if (stack0 != null && stack1 == null) return false; 
/* 415 */     if (stack0 == null && stack1 == null) return true;
/*     */     
/* 417 */     if (stack0.func_190926_b() && !stack1.func_190926_b()) return false; 
/* 418 */     if (!stack0.func_190926_b() && stack1.func_190926_b()) return false; 
/* 419 */     if (stack0.func_190926_b() && stack1.func_190926_b()) return true;
/*     */     
/* 421 */     if (filter.useMod) {
/* 422 */       String m1 = "A";
/* 423 */       String m2 = "B";
/*     */       
/* 425 */       String a = stack0.func_77973_b().getRegistryName().func_110624_b();
/* 426 */       if (a != null) m1 = a; 
/* 427 */       String b = stack1.func_77973_b().getRegistryName().func_110624_b();
/* 428 */       if (b != null) m2 = b;
/*     */       
/* 430 */       return m1.equals(m2);
/*     */     } 
/*     */     
/* 433 */     if (filter.useOre && !stack0.func_190926_b()) {
/* 434 */       int[] od = OreDictionary.getOreIDs(stack0);
/* 435 */       for (int i : od) {
/* 436 */         if (ThaumcraftInvHelper.containsMatch(false, new ItemStack[] { stack1
/* 437 */             }, OreDictionary.getOres(OreDictionary.getOreName(i), false))) {
/* 438 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 442 */     boolean t1 = true;
/* 443 */     if (!filter.igNBT) {
/* 444 */       t1 = filter.relaxedNBT ? ThaumcraftInvHelper.areItemStackTagsEqualRelaxed(stack0, stack1) : ItemStack.func_77970_a(stack0, stack1);
/*     */     }
/* 446 */     if (stack0.func_77952_i() == 32767 || stack1
/* 447 */       .func_77952_i() == 32767)
/* 448 */       filter.igDmg = true; 
/* 449 */     boolean t2 = (!filter.igDmg && stack0.func_77952_i() != stack1.func_77952_i());
/*     */     
/* 451 */     return (stack0.func_77973_b() != stack1.func_77973_b()) ? false : (t2 ? false : t1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public static void dropHarvestsAtPos(World worldIn, BlockPos pos, List<ItemStack> list) { dropHarvestsAtPos(worldIn, pos, list, false, 0, null); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropHarvestsAtPos(World worldIn, BlockPos pos, List<ItemStack> list, boolean followItem, int color, Entity target) {
/* 462 */     for (ItemStack item : list) {
/*     */       
/* 464 */       if (!worldIn.field_72995_K && worldIn.func_82736_K().func_82766_b("doTileDrops") && !worldIn.restoringBlockSnapshots) {
/*     */ 
/*     */         
/* 467 */         float f = 0.5F;
/* 468 */         double d0 = (worldIn.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 469 */         double d1 = (worldIn.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 470 */         double d2 = (worldIn.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 471 */         EntityItem entityitem = null;
/* 472 */         if (followItem) {
/* 473 */           EntityFollowingItem entityFollowingItem = new EntityFollowingItem(worldIn, pos.func_177958_n() + d0, pos.func_177956_o() + d1, pos.func_177952_p() + d2, item, target, color);
/*     */         } else {
/* 475 */           entityitem = new EntityItem(worldIn, pos.func_177958_n() + d0, pos.func_177956_o() + d1, pos.func_177952_p() + d2, item);
/*     */         } 
/* 477 */         entityitem.func_174869_p();
/* 478 */         worldIn.func_72838_d(entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void dropItemAtPos(World world, ItemStack item, BlockPos pos) {
/* 484 */     if (!world.field_72995_K && item != null && !item.func_190926_b() && item.func_190916_E() > 0) {
/*     */ 
/*     */       
/* 487 */       EntityItem entityItem = new EntityItem(world, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, item.func_77946_l());
/* 488 */       world.func_72838_d(entityItem);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void dropItemAtEntity(World world, ItemStack item, Entity entity) {
/* 493 */     if (!world.field_72995_K && item != null && !item.func_190926_b() && item.func_190916_E() > 0) {
/*     */ 
/*     */       
/* 496 */       EntityItem entityItem = new EntityItem(world, entity.field_70165_t, entity.field_70163_u + (entity.func_70047_e() / 2.0F), entity.field_70161_v, item.func_77946_l());
/* 497 */       world.func_72838_d(entityItem);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void dropItemsAtEntity(World world, BlockPos pos, Entity entity) {
/* 502 */     TileEntity tileEntity = world.func_175625_s(pos);
/* 503 */     if (!(tileEntity instanceof IInventory) || world.field_72995_K) {
/*     */       return;
/*     */     }
/* 506 */     IInventory inventory = (IInventory)tileEntity;
/*     */     
/* 508 */     for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 509 */       ItemStack item = inventory.func_70301_a(i);
/*     */       
/* 511 */       if (!item.func_190926_b() && item.func_190916_E() > 0) {
/*     */ 
/*     */ 
/*     */         
/* 515 */         EntityItem entityItem = new EntityItem(world, entity.field_70165_t, entity.field_70163_u + (entity.func_70047_e() / 2.0F), entity.field_70161_v, item.func_77946_l());
/*     */         
/* 517 */         world.func_72838_d(entityItem);
/* 518 */         inventory.func_70299_a(i, ItemStack.field_190927_a);
/*     */       } 
/*     */     } 
/*     */   }
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
/* 584 */   public static ItemStack cycleItemStack(Object input) { return cycleItemStack(input, 0); }
/*     */ 
/*     */   
/*     */   public static ItemStack cycleItemStack(Object input, int counter) {
/* 588 */     ItemStack arrayOfItemStack[], it = ItemStack.field_190927_a;
/*     */     
/* 590 */     if (input instanceof Ingredient) {
/*     */       
/* 592 */       boolean b = (!((Ingredient)input).isSimple() && !(input instanceof thaumcraft.api.crafting.IngredientNBTTC) && !(input instanceof net.minecraftforge.common.crafting.IngredientNBT));
/* 593 */       arrayOfItemStack = ((Ingredient)input).func_193365_a();
/* 594 */       if (b) {
/* 595 */         ItemStack[] q = (ItemStack[])arrayOfItemStack;
/* 596 */         ItemStack[] r = new ItemStack[q.length];
/* 597 */         for (int a = 0; a < q.length; a++) {
/* 598 */           r[a] = q[a].func_77946_l();
/* 599 */           r[a].func_77964_b(32767);
/*     */         } 
/* 601 */         arrayOfItemStack = r;
/*     */       } 
/*     */     } 
/*     */     
/* 605 */     if (arrayOfItemStack instanceof ItemStack[]) {
/* 606 */       ItemStack[] q = (ItemStack[])arrayOfItemStack;
/* 607 */       if (q != null && q.length > 0) {
/* 608 */         int idx = (int)((counter + System.currentTimeMillis() / 1000L) % q.length);
/* 609 */         it = cycleItemStack(q[idx], counter++);
/*     */       }
/*     */     
/* 612 */     } else if (arrayOfItemStack instanceof ItemStack) {
/* 613 */       it = (ItemStack)arrayOfItemStack;
/* 614 */       if (it != null && !it.func_190926_b() && it.func_77973_b() != null && it.func_77984_f() && it.func_77952_i() == 32767) {
/* 615 */         int q = 5000 / it.func_77958_k();
/* 616 */         int md = (int)((counter + System.currentTimeMillis() / q) % it.func_77958_k());
/* 617 */         ItemStack it2 = new ItemStack(it.func_77973_b(), 1, md);
/* 618 */         it2.func_77982_d(it.func_77978_p());
/* 619 */         it = it2;
/*     */       }
/*     */     
/* 622 */     } else if (arrayOfItemStack instanceof List) {
/* 623 */       List<ItemStack> q = (List)arrayOfItemStack;
/* 624 */       if (q != null && q.size() > 0) {
/* 625 */         int idx = (int)((counter + System.currentTimeMillis() / 1000L) % q.size());
/* 626 */         it = cycleItemStack(q.get(idx), counter++);
/*     */       }
/*     */     
/* 629 */     } else if (arrayOfItemStack instanceof String) {
/* 630 */       NonNullList nonNullList = OreDictionary.getOres((String)arrayOfItemStack, false);
/* 631 */       if (nonNullList != null && nonNullList.size() > 0) {
/* 632 */         int idx = (int)((counter + System.currentTimeMillis() / 1000L) % nonNullList.size());
/* 633 */         it = cycleItemStack(nonNullList.get(idx), counter++);
/*     */       } 
/*     */     } 
/*     */     
/* 637 */     return it;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\InventoryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */