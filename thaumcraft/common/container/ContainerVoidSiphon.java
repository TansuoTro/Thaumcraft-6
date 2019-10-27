/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IContainerListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.container.slot.SlotOutput;
/*     */ import thaumcraft.common.tiles.crafting.TileVoidSiphon;
/*     */ 
/*     */ public class ContainerVoidSiphon
/*     */   extends Container
/*     */ {
/*     */   private TileVoidSiphon siphon;
/*     */   private int lastProgress;
/*     */   
/*     */   public ContainerVoidSiphon(InventoryPlayer par1InventoryPlayer, TileVoidSiphon tileEntity) {
/*  21 */     this.siphon = tileEntity;
/*  22 */     func_75146_a(new SlotOutput(tileEntity, 0, 80, 32));
/*     */     
/*     */     int i;
/*  25 */     for (i = 0; i < 3; i++) {
/*     */       
/*  27 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  29 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  33 */     for (i = 0; i < 9; i++)
/*     */     {
/*  35 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75132_a(IContainerListener par1ICrafting) {
/*  42 */     super.func_75132_a(par1ICrafting);
/*  43 */     par1ICrafting.func_71112_a(this, 0, this.siphon.progress);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  51 */     super.func_75142_b();
/*     */     
/*  53 */     for (int i = 0; i < this.field_75149_d.size(); i++) {
/*     */       
/*  55 */       IContainerListener icrafting = (IContainerListener)this.field_75149_d.get(i);
/*     */       
/*  57 */       if (this.lastProgress != this.siphon.progress)
/*     */       {
/*  59 */         icrafting.func_71112_a(this, 0, this.siphon.progress);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  64 */     this.lastProgress = this.siphon.progress;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {
/*  71 */     if (par1 == 0)
/*     */     {
/*  73 */       this.siphon.progress = par2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.siphon.func_70300_a(par1EntityPlayer); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/*  89 */     ItemStack stack = ItemStack.field_190927_a;
/*  90 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */ 
/*     */     
/*  93 */     if (slotObject != null && slotObject.func_75216_d()) {
/*  94 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  95 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  97 */       if (slot == 0) {
/*  98 */         if (!this.siphon.func_94041_b(slot, stackInSlot) || 
/*  99 */           !func_75135_a(stackInSlot, 1, this.field_75151_b
/* 100 */             .size(), true)) {
/* 101 */           return ItemStack.field_190927_a;
/*     */         
/*     */         }
/*     */       }
/* 105 */       else if (!this.siphon.func_94041_b(slot, stackInSlot) || 
/* 106 */         !func_75135_a(stackInSlot, 0, 1, false)) {
/* 107 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 110 */       if (stackInSlot.func_190916_E() == 0) {
/* 111 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 113 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     return stack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerVoidSiphon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */