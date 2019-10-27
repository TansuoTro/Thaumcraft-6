/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import thaumcraft.common.container.slot.SlotFocus;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerFocalManipulator
/*     */   extends Container
/*     */ {
/*     */   private TileFocalManipulator table;
/*     */   private int lastBreakTime;
/*     */   
/*     */   public ContainerFocalManipulator(InventoryPlayer inventoryPlayer, TileFocalManipulator tileEntity) {
/*  22 */     this.table = tileEntity;
/*  23 */     func_75146_a(new SlotFocus(tileEntity, 0, 31, 191));
/*     */     
/*  25 */     for (int i = 0; i < 3; i++) {
/*     */       
/*  27 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  29 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, i * 18 - 62, 64 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  33 */     for (int i = 0; i < 3; i++) {
/*  34 */       for (int j = 0; j < 3; j++) {
/*  35 */         func_75146_a(new Slot(inventoryPlayer, i + j * 3, i * 18 - 62, j * 18 + 7));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer p, int button) {
/*  43 */     if (button == 0 && 
/*  44 */       !this.table.startCraft(button, p)) {
/*  45 */       this.table.func_145831_w().func_184133_a(p, this.table.func_174877_v(), SoundsTC.craftfail, SoundCategory.BLOCKS, 0.33F, 1.0F);
/*     */     }
/*     */     
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.table.func_70300_a(par1EntityPlayer); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
/*  61 */     ItemStack itemstack = ItemStack.field_190927_a;
/*  62 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/*  64 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  66 */       ItemStack itemstack1 = slot.func_75211_c();
/*  67 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  69 */       if (par2 != 0) {
/*     */         
/*  71 */         if (itemstack1.func_77973_b() instanceof thaumcraft.common.items.casters.ItemFocus)
/*     */         {
/*  73 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/*  75 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/*  78 */         else if (par2 >= 1 && par2 < 28)
/*     */         {
/*  80 */           if (!func_75135_a(itemstack1, 28, 37, false))
/*     */           {
/*  82 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/*  85 */         else if (par2 >= 28 && par2 < 37 && !func_75135_a(itemstack1, 1, 28, false))
/*     */         {
/*  87 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       
/*  90 */       } else if (!func_75135_a(itemstack1, 1, 37, false)) {
/*     */         
/*  92 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/*  95 */       if (itemstack1.func_190916_E() == 0) {
/*     */         
/*  97 */         slot.func_75215_d(ItemStack.field_190927_a);
/*     */       }
/*     */       else {
/*     */         
/* 101 */         slot.func_75218_e();
/*     */       } 
/*     */       
/* 104 */       if (itemstack1.func_190916_E() == itemstack.func_190916_E())
/*     */       {
/* 106 */         return ItemStack.field_190927_a;
/*     */       }
/*     */       
/* 109 */       slot.func_190901_a(par1EntityPlayer, itemstack1);
/*     */     } 
/*     */     
/* 112 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerFocalManipulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */