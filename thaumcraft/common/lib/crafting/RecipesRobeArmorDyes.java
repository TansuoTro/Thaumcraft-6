/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.RecipesArmorDyes;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.DyeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesRobeArmorDyes
/*     */   extends RecipesArmorDyes
/*     */ {
/*     */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World) {
/*  20 */     ItemStack itemstack = ItemStack.field_190927_a;
/*  21 */     ArrayList arraylist = new ArrayList();
/*     */     
/*  23 */     for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); i++) {
/*     */       
/*  25 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
/*     */       
/*  27 */       if (itemstack1 != null && !itemstack1.func_190926_b())
/*     */       {
/*  29 */         if (itemstack1.func_77973_b() instanceof ItemArmor) {
/*     */           
/*  31 */           ItemArmor itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  33 */           if (!(itemarmor instanceof thaumcraft.common.items.armor.ItemRobeArmor) || !itemstack.func_190926_b())
/*     */           {
/*  35 */             return false;
/*     */           }
/*     */           
/*  38 */           itemstack = itemstack1;
/*     */         }
/*     */         else {
/*     */           
/*  42 */           if (!DyeUtils.isDye(itemstack1))
/*     */           {
/*  44 */             return false;
/*     */           }
/*     */           
/*  47 */           arraylist.add(itemstack1);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  52 */     return (!itemstack.func_190926_b() && !arraylist.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting) {
/*  60 */     ItemStack itemstack = ItemStack.field_190927_a;
/*  61 */     int[] aint = new int[3];
/*  62 */     int i = 0;
/*  63 */     int j = 0;
/*  64 */     ItemArmor itemarmor = null;
/*     */ 
/*     */ 
/*     */     
/*     */     int k;
/*     */ 
/*     */     
/*  71 */     for (k = 0; k < par1InventoryCrafting.func_70302_i_(); k++) {
/*     */       
/*  73 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(k);
/*     */       
/*  75 */       if (itemstack1 != null && !itemstack1.func_190926_b())
/*     */       {
/*  77 */         if (itemstack1.func_77973_b() instanceof ItemArmor) {
/*     */           
/*  79 */           itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  81 */           if (!(itemarmor instanceof thaumcraft.common.items.armor.ItemRobeArmor) || !itemstack.func_190926_b())
/*     */           {
/*  83 */             return ItemStack.field_190927_a;
/*     */           }
/*     */           
/*  86 */           itemstack = itemstack1.func_77946_l();
/*  87 */           itemstack.func_190920_e(1);
/*     */           
/*  89 */           if (itemarmor.func_82816_b_(itemstack1))
/*     */           {
/*  91 */             int l = itemarmor.func_82814_b(itemstack);
/*  92 */             float f = (l >> 16 & 0xFF) / 255.0F;
/*  93 */             float f1 = (l >> 8 & 0xFF) / 255.0F;
/*  94 */             float f2 = (l & 0xFF) / 255.0F;
/*  95 */             i = (int)(i + Math.max(f, Math.max(f1, f2)) * 255.0F);
/*  96 */             aint[0] = (int)(aint[0] + f * 255.0F);
/*  97 */             aint[1] = (int)(aint[1] + f1 * 255.0F);
/*  98 */             aint[2] = (int)(aint[2] + f2 * 255.0F);
/*  99 */             j++;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 104 */           if (!DyeUtils.isDye(itemstack1))
/*     */           {
/* 106 */             return ItemStack.field_190927_a;
/*     */           }
/*     */           
/* 109 */           float[] afloat = ((EnumDyeColor)DyeUtils.colorFromStack(itemstack1).get()).func_193349_f();
/* 110 */           int j1 = (int)(afloat[0] * 255.0F);
/* 111 */           int k1 = (int)(afloat[1] * 255.0F);
/* 112 */           int i1 = (int)(afloat[2] * 255.0F);
/* 113 */           i += Math.max(j1, Math.max(k1, i1));
/* 114 */           aint[0] = aint[0] + j1;
/* 115 */           aint[1] = aint[1] + k1;
/* 116 */           aint[2] = aint[2] + i1;
/* 117 */           j++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 122 */     if (itemarmor == null)
/*     */     {
/* 124 */       return ItemStack.field_190927_a;
/*     */     }
/*     */ 
/*     */     
/* 128 */     k = aint[0] / j;
/* 129 */     int l1 = aint[1] / j;
/* 130 */     int l = aint[2] / j;
/* 131 */     float f = i / j;
/* 132 */     float f1 = Math.max(k, Math.max(l1, l));
/* 133 */     k = (int)(k * f / f1);
/* 134 */     l1 = (int)(l1 * f / f1);
/* 135 */     l = (int)(l * f / f1);
/* 136 */     int i1 = (k << 8) + l1;
/* 137 */     i1 = (i1 << 8) + l;
/* 138 */     itemarmor.func_82813_b(itemstack, i1);
/* 139 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\RecipesRobeArmorDyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */