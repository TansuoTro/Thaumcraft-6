/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ public class TileThaumatoriumTop
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer, IEssentiaTransport, ISidedInventory, ITickable
/*     */ {
/*  20 */   public TileThaumatorium thaumatorium = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  25 */     if (this.thaumatorium == null) {
/*  26 */       TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177977_b());
/*  27 */       if (tile != null && tile instanceof TileThaumatorium) {
/*  28 */         this.thaumatorium = (TileThaumatorium)tile;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int addToContainer(Aspect tt, int am) {
/*  35 */     if (this.thaumatorium == null) return am; 
/*  36 */     return this.thaumatorium.addToContainer(tt, am);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am) {
/*  41 */     if (this.thaumatorium == null) return false; 
/*  42 */     return this.thaumatorium.takeFromContainer(tt, am);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am) {
/*  57 */     if (this.thaumatorium == null) return false; 
/*  58 */     return this.thaumatorium.doesContainerContainAmount(tt, am);
/*     */   }
/*     */ 
/*     */   
/*     */   public int containerContains(Aspect tt) {
/*  63 */     if (this.thaumatorium == null) return 0; 
/*  64 */     return this.thaumatorium.containerContains(tt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConnectable(EnumFacing face) {
/*  76 */     if (this.thaumatorium == null) return false; 
/*  77 */     return this.thaumatorium.isConnectable(face);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face) {
/*  82 */     if (this.thaumatorium == null) return false; 
/*  83 */     return this.thaumatorium.canInputFrom(face);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public boolean canOutputTo(EnumFacing face) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {
/*  93 */     if (this.thaumatorium == null)
/*  94 */       return;  this.thaumatorium.setSuction(aspect, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc) {
/*  99 */     if (this.thaumatorium == null) return null; 
/* 100 */     return this.thaumatorium.getSuctionType(loc);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc) {
/* 105 */     if (this.thaumatorium == null) return 0; 
/* 106 */     return this.thaumatorium.getSuctionAmount(loc);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 121 */     if (this.thaumatorium == null) return 0; 
/* 122 */     return this.thaumatorium.takeEssentia(aspect, amount, face);
/*     */   }
/*     */ 
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 127 */     if (this.thaumatorium == null) return 0; 
/* 128 */     return this.thaumatorium.addEssentia(aspect, amount, face);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList getAspects() {
/* 138 */     if (this.thaumatorium == null) return null; 
/* 139 */     return this.thaumatorium.essentia;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {
/* 144 */     if (this.thaumatorium == null)
/* 145 */       return;  this.thaumatorium.setAspects(aspects);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public int func_70302_i_() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int par1) {
/* 159 */     if (this.thaumatorium == null) return ItemStack.field_190927_a; 
/* 160 */     return this.thaumatorium.func_70301_a(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2) {
/* 166 */     if (this.thaumatorium == null) return ItemStack.field_190927_a; 
/* 167 */     return this.thaumatorium.func_70298_a(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int par1) {
/* 173 */     if (this.thaumatorium == null) return ItemStack.field_190927_a; 
/* 174 */     return this.thaumatorium.func_70304_b(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack stack2) {
/* 180 */     if (this.thaumatorium == null)
/* 181 */       return;  this.thaumatorium.func_70299_a(par1, stack2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public int func_70297_j_() { return 64; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public boolean func_70300_a(EntityPlayer par1EntityPlayer) { return (this.field_145850_b.func_175625_s(this.field_174879_c) != this) ? false : ((par1EntityPlayer.func_174831_c(this.field_174879_c) <= 64.0D)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public boolean func_94041_b(int par1, ItemStack stack2) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public int[] func_180463_a(EnumFacing side) { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing direction) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public boolean func_180461_b(int index, ItemStack stack, EnumFacing direction) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */ 
/*     */   
/* 229 */   public int func_174887_a_(int id) { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174885_b(int id, int value) {}
/*     */ 
/*     */ 
/*     */   
/* 237 */   public int func_174890_g() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public void func_174888_l() { this.thaumatorium.func_174888_l(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public String func_70005_c_() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public boolean func_145818_k_() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public ITextComponent func_145748_c_() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public boolean func_191420_l() { return this.thaumatorium.func_191420_l(); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileThaumatoriumTop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */