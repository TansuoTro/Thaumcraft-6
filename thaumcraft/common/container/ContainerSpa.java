/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.container.slot.SlotLimitedByClass;
/*    */ import thaumcraft.common.tiles.devices.TileSpa;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerSpa
/*    */   extends Container
/*    */ {
/*    */   private TileSpa spa;
/*    */   private int lastBreakTime;
/*    */   
/*    */   public ContainerSpa(InventoryPlayer par1InventoryPlayer, TileSpa tileEntity) {
/* 20 */     this.spa = tileEntity;
/* 21 */     func_75146_a(new SlotLimitedByClass(thaumcraft.common.items.consumables.ItemBathSalts.class, tileEntity, 0, 65, 31));
/*    */     
/*    */     int i;
/* 24 */     for (i = 0; i < 3; i++) {
/*    */       
/* 26 */       for (int j = 0; j < 9; j++)
/*    */       {
/* 28 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*    */       }
/*    */     } 
/*    */     
/* 32 */     for (i = 0; i < 9; i++)
/*    */     {
/* 34 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75140_a(EntityPlayer p, int button) {
/* 43 */     if (button == 1) {
/* 44 */       this.spa.toggleMix();
/*    */     }
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.spa.func_70300_a(par1EntityPlayer); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/* 61 */     ItemStack stack = ItemStack.field_190927_a;
/* 62 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */ 
/*    */     
/* 65 */     if (slotObject != null && slotObject.func_75216_d()) {
/* 66 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 67 */       stack = stackInSlot.func_77946_l();
/*    */       
/* 69 */       if (slot == 0) {
/* 70 */         if (!this.spa.func_94041_b(slot, stackInSlot) || 
/* 71 */           !func_75135_a(stackInSlot, 1, this.field_75151_b
/* 72 */             .size(), true)) {
/* 73 */           return ItemStack.field_190927_a;
/*    */         
/*    */         }
/*    */       }
/* 77 */       else if (!this.spa.func_94041_b(slot, stackInSlot) || 
/* 78 */         !func_75135_a(stackInSlot, 0, 1, false)) {
/* 79 */         return ItemStack.field_190927_a;
/*    */       } 
/*    */       
/* 82 */       if (stackInSlot.func_190916_E() == 0) {
/* 83 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*    */       } else {
/* 85 */         slotObject.func_75218_e();
/*    */       } 
/*    */     } 
/*    */     
/* 89 */     return stack;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerSpa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */