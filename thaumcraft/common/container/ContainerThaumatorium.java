/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.common.tiles.crafting.TileThaumatorium;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerThaumatorium
/*     */   extends Container
/*     */ {
/*     */   private TileThaumatorium thaumatorium;
/*     */   private EntityPlayer player;
/*     */   
/*     */   public ContainerThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium tileEntity) {
/*  19 */     this.player = null;
/*     */ 
/*     */ 
/*     */     
/*  23 */     this.player = par1InventoryPlayer.field_70458_d;
/*  24 */     this.thaumatorium = tileEntity;
/*  25 */     this.thaumatorium.eventHandler = this;
/*  26 */     func_75146_a(new Slot(tileEntity, 0, 55, 24));
/*     */     
/*     */     int i;
/*  29 */     for (i = 0; i < 3; i++) {
/*     */       
/*  31 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  33 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 135 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  37 */     for (i = 0; i < 9; i++)
/*     */     {
/*  39 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 193));
/*     */     }
/*     */     
/*  42 */     this.thaumatorium.updateRecipes(this.player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  49 */     super.func_75142_b();
/*  50 */     this.thaumatorium.updateRecipes(this.player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/*  56 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/*  58 */     if (!(this.thaumatorium.func_145831_w()).field_72995_K)
/*     */     {
/*  60 */       this.thaumatorium.eventHandler = null;
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
/*  75 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.thaumatorium.func_70300_a(par1EntityPlayer); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
/*  84 */     ItemStack itemstack = ItemStack.field_190927_a;
/*  85 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/*  87 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  89 */       ItemStack itemstack1 = slot.func_75211_c();
/*  90 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  92 */       if (par2 != 0) {
/*     */         
/*  94 */         if (!func_75135_a(itemstack1, 0, 1, false))
/*     */         {
/*  96 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/*  99 */       else if (par2 >= 1 && par2 < 28) {
/*     */         
/* 101 */         if (!func_75135_a(itemstack1, 28, 37, false))
/*     */         {
/* 103 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       } else {
/* 106 */         if (par2 >= 28 && par2 < 37 && !func_75135_a(itemstack1, 1, 28, false))
/*     */         {
/* 108 */           return ItemStack.field_190927_a;
/*     */         }
/* 110 */         if (!func_75135_a(itemstack1, 1, 37, false))
/*     */         {
/* 112 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       } 
/* 115 */       if (itemstack1.func_190916_E() == 0) {
/*     */         
/* 117 */         slot.func_75215_d(ItemStack.field_190927_a);
/*     */       }
/*     */       else {
/*     */         
/* 121 */         slot.func_75218_e();
/*     */       } 
/*     */       
/* 124 */       if (itemstack1.func_190916_E() == itemstack.func_190916_E())
/*     */       {
/* 126 */         return ItemStack.field_190927_a;
/*     */       }
/*     */       
/* 129 */       slot.func_190901_a(par1EntityPlayer, itemstack1);
/*     */     } 
/*     */     
/* 132 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerThaumatorium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */