/*     */ package thaumcraft.common.tiles;
/*     */ public class TileThaumcraftInventory extends TileThaumcraft implements ISidedInventory, ITickable { private NonNullList<ItemStack> stacks; protected int[] syncedSlots; private NonNullList<ItemStack> syncedStacks; protected String customName; private int[] faceSlots; boolean initial; IItemHandler handlerTop; IItemHandler handlerBottom; IItemHandler handlerWest; IItemHandler handlerEast; IItemHandler handlerNorth; IItemHandler handlerSouth; public int func_70302_i_() { return this.stacks.size(); } protected NonNullList<ItemStack> getItems() { return this.stacks; } public ItemStack getSyncedStackInSlot(int index) { return (ItemStack)this.syncedStacks.get(index); } public ItemStack func_70301_a(int index) { return (ItemStack)getItems().get(index); } public ItemStack func_70298_a(int index, int count) {
/*     */     ItemStack itemstack = ItemStackHelper.func_188382_a(getItems(), index, count);
/*     */     if (!itemstack.func_190926_b() && isSyncedSlot(index))
/*     */       syncSlots(null); 
/*     */     func_70296_d();
/*     */     return itemstack;
/*     */   }
/*     */   public ItemStack func_70304_b(int index) {
/*     */     ItemStack s = ItemStackHelper.func_188383_a(getItems(), index);
/*     */     if (isSyncedSlot(index))
/*     */       syncSlots(null); 
/*     */     func_70296_d();
/*     */     return s;
/*     */   }
/*     */   public void func_70299_a(int index, @Nullable ItemStack stack) {
/*     */     getItems().set(index, stack);
/*     */     if (stack.func_190916_E() > func_70297_j_())
/*     */       stack.func_190920_e(func_70297_j_()); 
/*     */     func_70296_d();
/*     */     if (isSyncedSlot(index))
/*     */       syncSlots(null); 
/*     */   }
/*     */   public String func_70005_c_() { return func_145818_k_() ? this.customName : "container.thaumcraft"; }
/*  25 */   public TileThaumcraftInventory(int size) { this.stacks = NonNullList.func_191197_a(1, ItemStack.field_190927_a);
/*  26 */     this.syncedSlots = new int[0];
/*  27 */     this.syncedStacks = NonNullList.func_191197_a(1, ItemStack.field_190927_a);
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
/* 305 */     this.initial = true;
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
/* 321 */     this.handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
/* 322 */     this.handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
/* 323 */     this.handlerWest = new SidedInvWrapper(this, EnumFacing.WEST);
/* 324 */     this.handlerEast = new SidedInvWrapper(this, EnumFacing.EAST);
/* 325 */     this.handlerNorth = new SidedInvWrapper(this, EnumFacing.NORTH);
/* 326 */     this.handlerSouth = new SidedInvWrapper(this, EnumFacing.SOUTH); this.stacks = NonNullList.func_191197_a(size, ItemStack.field_190927_a); this.syncedStacks = NonNullList.func_191197_a(size, ItemStack.field_190927_a); this.faceSlots = new int[size]; for (int a = 0; a < size; ) { this.faceSlots[a] = a; a++; }  }
/*     */   public boolean func_145818_k_() { return (this.customName != null && this.customName.length() > 0); }
/*     */   public ITextComponent func_145748_c_() { return null; }
/*     */   private boolean isSyncedSlot(int slot) { for (int s : this.syncedSlots) { if (s == slot) return true;  }  return false; }
/*     */   protected void syncSlots(EntityPlayerMP player) { if (this.syncedSlots.length > 0) { NBTTagCompound nbt = new NBTTagCompound(); NBTTagList nbttaglist = new NBTTagList(); for (int i = 0; i < this.stacks.size(); i++) { if (!((ItemStack)this.stacks.get(i)).func_190926_b() && isSyncedSlot(i)) { NBTTagCompound nbttagcompound1 = new NBTTagCompound(); nbttagcompound1.func_74774_a("Slot", (byte)i); ((ItemStack)this.stacks.get(i)).func_77955_b(nbttagcompound1); nbttaglist.func_74742_a(nbttagcompound1); }  }  nbt.func_74782_a("ItemsSynced", nbttaglist); sendMessageToClient(nbt, player); }  }
/*     */   public void syncTile(boolean rerender) { super.syncTile(rerender); syncSlots(null); } public void messageFromClient(NBTTagCompound nbt, EntityPlayerMP player) { super.messageFromClient(nbt, player); if (nbt.func_74764_b("requestSync"))
/*     */       syncSlots(player);  } public void messageFromServer(NBTTagCompound nbt) { super.messageFromServer(nbt); if (nbt.func_74764_b("ItemsSynced")) { this.syncedStacks = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a); NBTTagList nbttaglist = nbt.func_150295_c("ItemsSynced", 10); for (int i = 0; i < nbttaglist.func_74745_c(); i++) { NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i); byte b0 = nbttagcompound1.func_74771_c("Slot"); if (isSyncedSlot(b0))
/* 333 */           this.syncedStacks.set(b0, new ItemStack(nbttagcompound1));  }  }  } @Nullable public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) { if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
/* 334 */       if (facing == EnumFacing.DOWN)
/* 335 */         return (T)this.handlerBottom; 
/* 336 */       if (facing == EnumFacing.UP)
/* 337 */         return (T)this.handlerTop; 
/* 338 */       if (facing == EnumFacing.WEST)
/* 339 */         return (T)this.handlerWest; 
/* 340 */       if (facing == EnumFacing.EAST)
/* 341 */         return (T)this.handlerEast; 
/* 342 */       if (facing == EnumFacing.NORTH)
/* 343 */         return (T)this.handlerNorth; 
/* 344 */       return (T)this.handlerSouth;
/* 345 */     }  return (T)super.getCapability(capability, facing); } public void func_145839_a(NBTTagCompound nbtCompound) { super.func_145839_a(nbtCompound); if (nbtCompound.func_74764_b("CustomName")) this.customName = nbtCompound.func_74779_i("CustomName");  this.stacks = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a); ItemStackHelper.func_191283_b(nbtCompound, this.stacks); } public NBTTagCompound func_189515_b(NBTTagCompound nbtCompound) { super.func_189515_b(nbtCompound); if (func_145818_k_()) nbtCompound.func_74778_a("CustomName", this.customName);  ItemStackHelper.func_191282_a(nbtCompound, this.stacks); return nbtCompound; } public int func_70297_j_() { return 64; } public boolean func_70300_a(EntityPlayer par1EntityPlayer) { return (this.field_145850_b.func_175625_s(func_174877_v()) != this) ? false : ((par1EntityPlayer.func_174831_c(func_174877_v()) <= 64.0D)); } public boolean func_94041_b(int par1, ItemStack stack2) { return true; } public int[] func_180463_a(EnumFacing par1) { return this.faceSlots; } public void func_174889_b(EntityPlayer player) {} public void func_174886_c(EntityPlayer player) {} public int func_174887_a_(int id) { return 0; } public void func_174885_b(int id, int value) {} public int func_174890_g() { return 0; } public void func_174888_l() {}
/*     */   public boolean func_180462_a(int par1, ItemStack stack2, EnumFacing par3) { return func_94041_b(par1, stack2); }
/*     */   public boolean func_180461_b(int par1, ItemStack stack2, EnumFacing par3) { return true; }
/*     */   public boolean func_191420_l() { for (ItemStack itemstack : this.stacks) { if (!itemstack.func_190926_b()) return false;  }  return true; }
/*     */   public void func_73660_a() { if (this.initial) { this.initial = false; if (!this.field_145850_b.field_72995_K) { syncSlots(null); } else { NBTTagCompound nbt = new NBTTagCompound(); nbt.func_74757_a("requestSync", true); sendMessageToServer(nbt); }  }  }
/* 350 */   public boolean hasCapability(Capability<?> capability, EnumFacing facing) { return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)); } }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\TileThaumcraftInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */