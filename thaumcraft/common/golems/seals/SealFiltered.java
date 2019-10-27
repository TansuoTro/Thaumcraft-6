/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ItemStackHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseContainer;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseGUI;
/*     */ 
/*     */ public abstract class SealFiltered
/*     */   implements ISeal, ISealGui, ISealConfigFilter
/*     */ {
/*     */   public void readCustomNBT(NBTTagCompound nbt) {
/*  25 */     this.filter = NonNullList.func_191197_a(getFilterSize(), ItemStack.field_190927_a);
/*  26 */     ItemStackHelper.func_191283_b(nbt, this.filter);
/*  27 */     for (ItemStack s : this.filter) {
/*  28 */       if (s.func_190916_E() > 1) s.func_190920_e(1);
/*     */     
/*     */     } 
/*  31 */     this.blacklist = nbt.func_74767_n("bl");
/*     */     
/*  33 */     this.filterSize = NonNullList.func_191197_a(getFilterSize(), Integer.valueOf(0));
/*  34 */     NBTTagList nbttaglist = nbt.func_150295_c("Sizes", 10);
/*  35 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/*  37 */       NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
/*  38 */       int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
/*  39 */       if (j >= 0 && j < this.filterSize.size())
/*     */       {
/*  41 */         this.filterSize.set(j, Integer.valueOf(nbttagcompound.func_74762_e("Size")));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt) {
/*  48 */     ItemStackHelper.func_191282_a(nbt, this.filter);
/*     */     
/*  50 */     nbt.func_74757_a("bl", this.blacklist);
/*     */     
/*  52 */     NBTTagList nbttaglist = new NBTTagList();
/*  53 */     for (int i = 0; i < this.filterSize.size(); i++) {
/*     */       
/*  55 */       int size = ((Integer)this.filterSize.get(i)).intValue();
/*     */       
/*  57 */       if (size != 0) {
/*     */         
/*  59 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*  60 */         nbttagcompound.func_74774_a("Slot", (byte)i);
/*  61 */         nbttagcompound.func_74768_a("Size", size);
/*  62 */         nbttaglist.func_74742_a(nbttagcompound);
/*     */       } 
/*     */     } 
/*     */     
/*  66 */     nbt.func_74782_a("Sizes", nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseContainer(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  77 */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseGUI(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public int[] getGuiCategories() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public int getFilterSize() { return 1; }
/*     */ 
/*     */   
/*  88 */   NonNullList<ItemStack> filter = NonNullList.func_191197_a(getFilterSize(), ItemStack.field_190927_a);
/*  89 */   NonNullList<Integer> filterSize = NonNullList.func_191197_a(getFilterSize(), Integer.valueOf(0));
/*     */ 
/*     */ 
/*     */   
/*  93 */   public NonNullList<ItemStack> getInv() { return this.filter; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public NonNullList<Integer> getSizes() { return this.filterSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public ItemStack getFilterSlot(int i) { return (ItemStack)this.filter.get(i); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public int getFilterSlotSize(int i) { return ((Integer)this.filterSize.get(i)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setFilterSlot(int i, ItemStack stack) { this.filter.set(i, stack.func_77946_l()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void setFilterSlotSize(int i, int size) { this.filterSize.set(i, Integer.valueOf(size)); }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean blacklist = true;
/*     */ 
/*     */   
/* 125 */   public boolean isBlacklist() { return this.blacklist; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void setBlacklist(boolean black) { this.blacklist = black; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public boolean hasStacksizeLimiters() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealFiltered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */