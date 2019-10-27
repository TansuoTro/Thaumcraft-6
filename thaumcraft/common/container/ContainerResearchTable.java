/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*     */ import thaumcraft.common.container.slot.SlotLimitedByClass;
/*     */ import thaumcraft.common.container.slot.SlotLimitedByItemstack;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ 
/*     */ 
/*     */ public class ContainerResearchTable
/*     */   extends Container
/*     */ {
/*     */   public TileResearchTable tileEntity;
/*     */   String[] aspects;
/*     */   EntityPlayer player;
/*     */   
/*     */   public ContainerResearchTable(InventoryPlayer iinventory, TileResearchTable iinventory1) {
/*  27 */     this.player = iinventory.field_70458_d;
/*  28 */     this.tileEntity = iinventory1;
/*  29 */     this.aspects = (String[])Aspect.aspects.keySet().toArray(new String[0]);
/*     */     
/*  31 */     func_75146_a(new SlotLimitedByClass(thaumcraft.api.items.IScribeTools.class, iinventory1, 0, 16, 15));
/*  32 */     func_75146_a(new SlotLimitedByItemstack(new ItemStack(Items.field_151121_aF), iinventory1, 1, 224, 16));
/*     */     
/*  34 */     bindPlayerInventory(iinventory);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  38 */     for (int i = 0; i < 3; i++) {
/*  39 */       for (int j = 0; j < 9; j++) {
/*  40 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 77 + j * 18, 190 + i * 18));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  45 */     for (int i = 0; i < 3; i++) {
/*  46 */       for (int j = 0; j < 3; j++)
/*  47 */         func_75146_a(new Slot(inventoryPlayer, i + j * 3, 20 + i * 18, 190 + j * 18)); 
/*     */     } 
/*     */   }
/*     */   
/*  51 */   static HashMap<Integer, Long> antiSpam = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer playerIn, int button) {
/*  56 */     if (button == 1) {
/*  57 */       if (this.tileEntity.data.lastDraw != null) this.tileEntity.data.savedCards.add(Long.valueOf(this.tileEntity.data.lastDraw.card.getSeed())); 
/*  58 */       for (ResearchTableData.CardChoice cc : this.tileEntity.data.cardChoices) {
/*  59 */         if (cc.selected) {
/*  60 */           this.tileEntity.data.lastDraw = cc;
/*     */           break;
/*     */         } 
/*     */       } 
/*  64 */       this.tileEntity.data.cardChoices.clear();
/*  65 */       this.tileEntity.syncTile(false);
/*  66 */       return true;
/*     */     } 
/*     */     
/*  69 */     if (button == 4 || button == 5 || button == 6) {
/*     */       
/*  71 */       long tn = System.currentTimeMillis();
/*  72 */       long to = 0L;
/*  73 */       if (antiSpam.containsKey(Integer.valueOf(playerIn.func_145782_y()))) {
/*  74 */         to = ((Long)antiSpam.get(Integer.valueOf(playerIn.func_145782_y()))).longValue();
/*     */       }
/*  76 */       if (tn - to < 333L) return false; 
/*  77 */       antiSpam.put(Integer.valueOf(playerIn.func_145782_y()), Long.valueOf(tn));
/*     */       
/*     */       try {
/*  80 */         TheorycraftCard card = ((ResearchTableData.CardChoice)this.tileEntity.data.cardChoices.get(button - 4)).card;
/*  81 */         if (card.getRequiredItems() != null) {
/*  82 */           for (ItemStack stack : card.getRequiredItems()) {
/*  83 */             if (stack != null && !stack.func_190926_b() && !InventoryUtils.isPlayerCarryingAmount(this.player, stack, true)) {
/*  84 */               return false;
/*     */             }
/*     */           } 
/*  87 */           if (card.getRequiredItemsConsumed() != null && card.getRequiredItemsConsumed().length == card.getRequiredItems().length) {
/*  88 */             for (int a = 0; a < card.getRequiredItems().length; a++) {
/*  89 */               if (card.getRequiredItemsConsumed()[a] && card.getRequiredItems()[a] != null && !card.getRequiredItems()[a].func_190926_b()) {
/*  90 */                 InventoryUtils.consumePlayerItem(this.player, card.getRequiredItems()[a], true, true);
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*  95 */         if (card.activate(playerIn, this.tileEntity.data)) {
/*  96 */           this.tileEntity.consumeInkFromTable();
/*  97 */           ((ResearchTableData.CardChoice)this.tileEntity.data.cardChoices.get(button - 4)).selected = true;
/*  98 */           this.tileEntity.data.addInspiration(-card.getInspirationCost());
/*  99 */           this.tileEntity.syncTile(false);
/* 100 */           return true;
/*     */         } 
/* 102 */       } catch (Exception e) {
/* 103 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     if (button == 7 && 
/* 108 */       this.tileEntity.data.isComplete()) {
/* 109 */       this.tileEntity.finishTheory(playerIn);
/* 110 */       this.tileEntity.syncTile(false);
/* 111 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     if (button == 9 && 
/* 116 */       !this.tileEntity.data.isComplete()) {
/* 117 */       this.tileEntity.data = null;
/* 118 */       this.tileEntity.syncTile(false);
/* 119 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 123 */     if (button == 2 || button == 3) {
/* 124 */       if (this.tileEntity.data != null && !this.tileEntity.data.isComplete() && this.tileEntity
/* 125 */         .consumepaperFromTable()) {
/* 126 */         this.tileEntity.data.drawCards(button, playerIn);
/* 127 */         this.tileEntity.syncTile(false);
/*     */       } 
/* 129 */       return true;
/*     */     } 
/*     */     
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/* 137 */     ItemStack stack = ItemStack.field_190927_a;
/* 138 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */ 
/*     */     
/* 141 */     if (slotObject != null && slotObject.func_75216_d()) {
/* 142 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 143 */       stack = stackInSlot.func_77946_l();
/*     */ 
/*     */       
/* 146 */       if (slot < 2) {
/* 147 */         if (!this.tileEntity.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true)) {
/* 148 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/* 151 */       else if (!this.tileEntity.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, 0, 2, false)) {
/* 152 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 155 */       if (stackInSlot.func_190916_E() == 0) {
/* 156 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 158 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 162 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public boolean func_75145_c(EntityPlayer player) { return this.tileEntity.func_70300_a(player); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerResearchTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */