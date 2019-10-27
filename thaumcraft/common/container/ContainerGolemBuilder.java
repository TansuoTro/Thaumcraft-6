/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IContainerListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.container.slot.SlotOutput;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ 
/*     */ 
/*     */ public class ContainerGolemBuilder
/*     */   extends Container
/*     */ {
/*     */   private TileGolemBuilder builder;
/*     */   
/*     */   public ContainerGolemBuilder(InventoryPlayer par1InventoryPlayer, TileGolemBuilder tileEntity) {
/*  22 */     this.builder = tileEntity;
/*  23 */     func_75146_a(new SlotOutput(tileEntity, 0, 160, 104));
/*     */     
/*     */     int i;
/*  26 */     for (i = 0; i < 3; i++) {
/*     */       
/*  28 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  30 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 24 + j * 18, 142 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  34 */     for (i = 0; i < 9; i++)
/*     */     {
/*  36 */       func_75146_a(new Slot(par1InventoryPlayer, i, 24 + i * 18, 200)); } 
/*     */   }
/*     */   
/*     */   public static boolean redo = false;
/*     */   private int lastCost;
/*     */   private int lastMaxCost;
/*     */   
/*     */   public ItemStack func_184996_a(int slotId, int clickedButton, ClickType mode, EntityPlayer playerIn) {
/*  44 */     redo = true;
/*  45 */     return super.func_184996_a(slotId, clickedButton, mode, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75141_a(int p_75141_1_, ItemStack p_75141_2_) {
/*  50 */     redo = true;
/*  51 */     super.func_75141_a(p_75141_1_, p_75141_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer p, int button) {
/*  57 */     if (button == 99) redo = true; 
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75132_a(IContainerListener par1ICrafting) {
/*  64 */     super.func_75132_a(par1ICrafting);
/*  65 */     par1ICrafting.func_71112_a(this, 0, this.builder.cost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  74 */     super.func_75142_b();
/*     */     
/*  76 */     for (int i = 0; i < this.field_75149_d.size(); i++) {
/*     */       
/*  78 */       IContainerListener icrafting = (IContainerListener)this.field_75149_d.get(i);
/*     */       
/*  80 */       if (this.lastCost != this.builder.cost)
/*     */       {
/*  82 */         icrafting.func_71112_a(this, 0, this.builder.cost);
/*     */       }
/*     */       
/*  85 */       if (this.lastMaxCost != this.builder.maxCost)
/*     */       {
/*  87 */         icrafting.func_71112_a(this, 1, this.builder.maxCost);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  92 */     this.lastCost = this.builder.cost;
/*  93 */     this.lastMaxCost = this.builder.maxCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {
/* 100 */     if (par1 == 0)
/*     */     {
/* 102 */       this.builder.cost = par2;
/*     */     }
/* 104 */     if (par1 == 1)
/*     */     {
/* 106 */       this.builder.maxCost = par2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.builder.func_70300_a(par1EntityPlayer); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/* 122 */     ItemStack stack = ItemStack.field_190927_a;
/* 123 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */ 
/*     */     
/* 126 */     if (slotObject != null && slotObject.func_75216_d()) {
/* 127 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 128 */       stack = stackInSlot.func_77946_l();
/*     */       
/* 130 */       if (slot == 0) {
/* 131 */         if (!this.builder.func_94041_b(slot, stackInSlot) || 
/* 132 */           !func_75135_a(stackInSlot, 1, this.field_75151_b
/* 133 */             .size(), true)) {
/* 134 */           return ItemStack.field_190927_a;
/*     */         
/*     */         }
/*     */       }
/* 138 */       else if (!this.builder.func_94041_b(slot, stackInSlot) || 
/* 139 */         !func_75135_a(stackInSlot, 0, 1, false)) {
/* 140 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 143 */       if (stackInSlot.func_190916_E() == 0) {
/* 144 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 146 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     return stack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerGolemBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */