/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.container.slot.SlotPotion;
/*    */ import thaumcraft.common.tiles.devices.TilePotionSprayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerPotionSprayer
/*    */   extends Container
/*    */ {
/*    */   private TilePotionSprayer sprayer;
/*    */   private int lastBreakTime;
/*    */   
/*    */   public ContainerPotionSprayer(InventoryPlayer par1InventoryPlayer, TilePotionSprayer tilePotionSprayer) {
/* 20 */     this.sprayer = tilePotionSprayer;
/* 21 */     func_75146_a(new SlotPotion(tilePotionSprayer, 0, 56, 64));
/*    */     
/*    */     int i;
/* 24 */     for (i = 0; i < 3; i++) {
/*    */       
/* 26 */       for (int j = 0; j < 9; j++)
/*    */       {
/* 28 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 16 + j * 18, 151 + i * 18));
/*    */       }
/*    */     } 
/*    */     
/* 32 */     for (i = 0; i < 9; i++)
/*    */     {
/* 34 */       func_75146_a(new Slot(par1InventoryPlayer, i, 16 + i * 18, 209));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.sprayer.func_70300_a(par1EntityPlayer); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/* 47 */     ItemStack stack = ItemStack.field_190927_a;
/* 48 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */ 
/*    */     
/* 51 */     if (slotObject != null && slotObject.func_75216_d()) {
/* 52 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 53 */       stack = stackInSlot.func_77946_l();
/*    */       
/* 55 */       if (slot == 0) {
/* 56 */         if (!this.sprayer.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)) {
/* 57 */           return ItemStack.field_190927_a;
/*    */         }
/* 59 */       } else if (!this.sprayer.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, 0, 1, false)) {
/* 60 */         return ItemStack.field_190927_a;
/*    */       } 
/*    */       
/* 63 */       if (stackInSlot.func_190916_E() == 0) {
/* 64 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*    */       } else {
/* 66 */         slotObject.func_75218_e();
/*    */       } 
/*    */     } 
/*    */     
/* 70 */     return stack;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerPotionSprayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */