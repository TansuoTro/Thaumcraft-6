/*     */ package thaumcraft.common.container;
/*     */ public class ContainerLogistics extends Container implements IInventoryChangedListener { private World worldObj; EntityPlayer player; public IInventory input; TreeMap<String, ItemStack> items; int lastTotal; public int start; public int end; public String searchText; int lastStart; int lastEnd; public boolean updated; public void refreshItemList(boolean full) { int newTotal = this.lastTotal; TreeMap<String, ItemStack> ti = new TreeMap<String, ItemStack>(); if (full) {
/*     */       newTotal = 0; CopyOnWriteArrayList<SealEntity> seals = SealHandler.getSealsInRange(this.worldObj, this.player.func_180425_c(), 32); for (SealEntity seal : seals) {
/*     */         if (seal.getSeal() instanceof SealProvide && seal.getOwner().equals(this.player.func_110124_au().toString())) {
/*     */           IItemHandler handler = ThaumcraftInvHelper.getItemHandlerAt(this.worldObj, (seal.getSealPos()).pos, (seal.getSealPos()).face); for (int slot = 0; slot < handler.getSlots(); slot++) {
/*     */             ItemStack stack = handler.getStackInSlot(slot).func_77946_l(); if (((SealProvide)seal.getSeal()).matchesFilters(stack) && (this.searchText.isEmpty() || stack.func_82833_r().toLowerCase().contains(this.searchText.toLowerCase()))) {
/*     */               String key = stack.func_82833_r() + stack.func_77952_i() + stack.func_77978_p(); if (ti.containsKey(key))
/*     */                 stack.func_190917_f(((ItemStack)ti.get(key)).func_190916_E());  ti.put(key, stack); newTotal += stack.func_190916_E();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     if (this.lastTotal != newTotal || this.start != this.lastStart) {
/*     */       this.lastTotal = newTotal;
/*     */       if (full)
/*     */         this.items = ti; 
/*     */       this.input.func_174888_l();
/*     */       int j = 0;
/*     */       int q = 0;
/*     */       for (String key : this.items.keySet()) {
/*     */         j++;
/*     */         if (j <= this.start * 9)
/*     */           continue; 
/*     */         this.input.func_70299_a(q, (ItemStack)this.items.get(key));
/*     */         q++;
/*     */         if (q >= this.input.func_70302_i_())
/*     */           break; 
/*     */       } 
/*     */       this.end = this.items.size() / 9 - 8;
/*  31 */     }  } public ContainerLogistics(InventoryPlayer iinventory, World par2World) { this.player = null;
/*  32 */     this.input = new InventoryLogistics(this);
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
/*  50 */     this.items = new TreeMap();
/*  51 */     this.lastTotal = 0;
/*  52 */     this.start = 0;
/*  53 */     this.end = 0;
/*  54 */     this.searchText = "";
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
/*     */     
/* 118 */     this.lastStart = 0;
/* 119 */     this.lastEnd = 0;
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
/* 177 */     this.updated = false; this.worldObj = par2World; this.player = iinventory.field_70458_d; for (int a = 0; a < this.input.func_70302_i_(); a++)
/*     */       func_75146_a(new SlotGhostFull(this.input, a, 19 + a % 9 * 19, 19 + a / 9 * 19));  refreshItemList(true); }
/*     */   public void func_75132_a(IContainerListener listener) { super.func_75132_a(listener);
/*     */     listener.func_175173_a(this, this.input);
/* 181 */     listener.func_71112_a(this, 0, this.start); } public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2) { if (par2 == 22) {
/* 182 */       refreshItemList(true);
/* 183 */       return true;
/*     */     } 
/* 185 */     if (par2 == 0) {
/* 186 */       if (this.start < this.items.size() / 9 - 8) {
/* 187 */         this.start++;
/* 188 */         refreshItemList(false);
/*     */       } 
/* 190 */       return true;
/*     */     } 
/* 192 */     if (par2 == 1) {
/* 193 */       if (this.start > 0) {
/* 194 */         this.start--;
/* 195 */         refreshItemList(false);
/*     */       } 
/* 197 */       return true;
/*     */     } 
/* 199 */     if (par2 >= 100) {
/* 200 */       int s = par2 - 100;
/* 201 */       if (s >= 0 && s <= this.items.size() / 9 - 8) {
/* 202 */         this.start = s;
/* 203 */         refreshItemList(false);
/*     */       } 
/* 205 */       return true;
/*     */     } 
/* 207 */     return super.func_75140_a(par1EntityPlayer, par2); } public void func_75142_b() { sendLargeSlotsToClient(); super.func_75142_b(); for (int i = 0; i < this.field_75149_d.size(); i++) { IContainerListener icrafting = (IContainerListener)this.field_75149_d.get(i); if (this.lastStart != this.start)
/*     */         icrafting.func_71112_a(this, 0, this.start);  if (this.lastEnd != this.end)
/*     */         icrafting.func_71112_a(this, 1, this.end);  }  this.lastStart = this.start; this.lastEnd = this.end; }
/*     */   private void sendLargeSlotsToClient() { for (int i = 0; i < this.field_75151_b.size(); i++) { if (func_75139_a(i) instanceof SlotGhostFull) { ItemStack itemstack = ((Slot)this.field_75151_b.get(i)).func_75211_c(); ItemStack itemstack1 = (ItemStack)this.field_75153_a.get(i); if (itemstack.func_190916_E() > itemstack.func_77976_d())
/*     */           for (int j = 0; j < this.field_75149_d.size(); j++) { if (this.field_75149_d.get(j) instanceof EntityPlayerMP) { EntityPlayerMP p = (EntityPlayerMP)this.field_75149_d.get(j); PacketHandler.INSTANCE.sendTo(new PacketItemToClientContainer(this.field_75152_c, i, itemstack), p); }  }   }  }  }
/*     */   @SideOnly(Side.CLIENT) public void func_75137_b(int par1, int par2) { if (par1 == 0) { this.start = par2; this.updated = true; }
/*     */      if (par1 == 1) { this.end = par2; this.updated = true; }
/*     */      }
/* 215 */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) { ItemStack stack = ItemStack.field_190927_a;
/* 216 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */ 
/*     */     
/* 219 */     if (slotObject != null && slotObject.func_75216_d()) {
/* 220 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 221 */       stack = stackInSlot.func_77946_l();
/*     */       
/* 223 */       if (slot < this.input.func_70302_i_()) {
/* 224 */         if (!this.input.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, this.input.func_70302_i_(), this.field_75151_b.size(), true)) {
/* 225 */           return ItemStack.field_190927_a;
/*     */         
/*     */         }
/*     */       }
/* 229 */       else if (!this.input.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, 0, this.input.func_70302_i_(), false)) {
/* 230 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 233 */       if (stackInSlot.func_190916_E() == 0) {
/* 234 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 236 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 240 */     return stack; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public boolean func_75145_c(EntityPlayer var1) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public void func_76316_a(IInventory invBasic) { func_75142_b(); } }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerLogistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */