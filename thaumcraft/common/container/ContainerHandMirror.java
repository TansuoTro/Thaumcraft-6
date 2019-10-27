/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IContainerListener;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.IInventoryChangedListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.items.tools.ItemHandMirror;
/*     */ 
/*     */ public class ContainerHandMirror extends Container implements IInventoryChangedListener {
/*     */   private World worldObj;
/*     */   private int posX;
/*     */   private int posY;
/*     */   
/*     */   public ContainerHandMirror(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5) {
/*  21 */     this.input = new InventoryHandMirror(this);
/*  22 */     this.mirror = null;
/*  23 */     this.player = null;
/*     */ 
/*     */ 
/*     */     
/*  27 */     this.worldObj = par2World;
/*  28 */     this.posX = par3;
/*  29 */     this.posY = par4;
/*  30 */     this.posZ = par5;
/*  31 */     this.player = iinventory.field_70458_d;
/*  32 */     this.mirror = iinventory.func_70448_g();
/*     */     
/*  34 */     func_75146_a(new Slot(this.input, 0, 80, 24));
/*  35 */     bindPlayerInventory(iinventory);
/*  36 */     func_75130_a(this.input);
/*     */   }
/*     */   private int posZ; public IInventory input; ItemStack mirror; EntityPlayer player;
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  40 */     for (int i = 0; i < 3; i++) {
/*  41 */       for (int j = 0; j < 9; j++) {
/*  42 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  47 */     for (int i = 0; i < 9; i++) {
/*  48 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75130_a(IInventory par1IInventory) {
/*  56 */     if (!this.input.func_70301_a(0).func_190926_b() && ItemStack.func_77989_b(this.input.func_70301_a(0), this.mirror)) {
/*  57 */       this.player.field_71070_bA = this.player.field_71069_bz;
/*     */     }
/*  59 */     else if (!this.worldObj.field_72995_K && !this.input.func_70301_a(0).func_190926_b() && this.player != null) {
/*  60 */       ItemStack is = this.input.func_70301_a(0).func_77946_l();
/*  61 */       this.input.func_70299_a(0, ItemStack.field_190927_a);
/*  62 */       this.input.func_70296_d();
/*  63 */       if (ItemHandMirror.transport(this.mirror, is, this.player, this.worldObj)) {
/*  64 */         for (int var4 = 0; var4 < this.field_75149_d.size(); var4++)
/*     */         {
/*  66 */           ((IContainerListener)this.field_75149_d.get(var4)).func_71111_a(this, 0, ItemStack.field_190927_a);
/*     */         }
/*     */       } else {
/*  69 */         this.input.func_70299_a(0, is);
/*  70 */         this.input.func_70296_d();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
/*     */     try {
/*  78 */       ItemStack s = func_75139_a(slotId).func_75211_c();
/*  79 */       if (s.func_77973_b() instanceof ItemHandMirror) return ItemStack.field_190927_a; 
/*  80 */     } catch (Exception exception) {}
/*  81 */     return super.func_184996_a(slotId, dragType, clickTypeIn, player);
/*     */   }
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
/*  93 */     if (slotObject != null && slotObject.func_75216_d() && !(slotObject.func_75211_c().func_77973_b() instanceof ItemHandMirror)) {
/*  94 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  95 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  97 */       if (slot == 0) {
/*  98 */         if (!func_75135_a(stackInSlot, 1, this.field_75151_b
/*  99 */             .size(), true)) {
/* 100 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/* 103 */       else if (!func_75135_a(stackInSlot, 0, 1, false)) {
/* 104 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 107 */       if (stackInSlot.func_190916_E() == 0) {
/* 108 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 110 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public boolean func_75145_c(EntityPlayer var1) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/* 125 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/* 127 */     if (!this.worldObj.field_72995_K) {
/*     */       
/* 129 */       ItemStack var3 = this.input.func_70304_b(0);
/* 130 */       par1EntityPlayer.func_71019_a(var3, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack stackin, int par2, int par3, boolean par4, int limit) {
/* 136 */     boolean var5 = false;
/* 137 */     int var6 = par2;
/*     */     
/* 139 */     if (par4)
/*     */     {
/* 141 */       var6 = par3 - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     if (stackin.func_77985_e())
/*     */     {
/* 149 */       while (stackin.func_190916_E() > 0 && ((!par4 && var6 < par3) || (par4 && var6 >= par2))) {
/*     */         
/* 151 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 152 */         ItemStack stack8 = var7.func_75211_c();
/*     */         
/* 154 */         if (stack8 != null && !stack8.func_190926_b() && stack8.func_77973_b() == stackin.func_77973_b() && (
/* 155 */           !stackin.func_77981_g() || stackin.func_77952_i() == stack8.func_77952_i()) && 
/* 156 */           ItemStack.func_77970_a(stackin, stack8)) {
/*     */           
/* 158 */           int var9 = stack8.func_190916_E() + stackin.func_190916_E();
/*     */           
/* 160 */           if (var9 <= Math.min(stackin.func_77976_d(), limit)) {
/*     */             
/* 162 */             stackin.func_190920_e(0);
/* 163 */             stack8.func_190920_e(var9);
/* 164 */             var7.func_75218_e();
/* 165 */             var5 = true;
/*     */           }
/* 167 */           else if (stack8.func_190916_E() < Math.min(stackin.func_77976_d(), limit)) {
/*     */             
/* 169 */             stackin.func_190918_g(Math.min(stackin.func_77976_d(), limit) - stack8.func_190916_E());
/* 170 */             stack8.func_190920_e(Math.min(stackin.func_77976_d(), limit));
/* 171 */             var7.func_75218_e();
/* 172 */             var5 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 176 */         if (par4) {
/*     */           
/* 178 */           var6--;
/*     */           
/*     */           continue;
/*     */         } 
/* 182 */         var6++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 187 */     if (stackin.func_190916_E() > 0) {
/*     */       
/* 189 */       if (par4) {
/*     */         
/* 191 */         var6 = par3 - 1;
/*     */       }
/*     */       else {
/*     */         
/* 195 */         var6 = par2;
/*     */       } 
/*     */       
/* 198 */       while ((!par4 && var6 < par3) || (par4 && var6 >= par2)) {
/*     */         
/* 200 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 201 */         ItemStack stack8 = var7.func_75211_c();
/*     */         
/* 203 */         if (stack8 == null || stack8.func_190926_b()) {
/*     */           
/* 205 */           ItemStack res = stackin.func_77946_l();
/* 206 */           res.func_190920_e(Math.min(res.func_190916_E(), limit));
/* 207 */           var7.func_75215_d(res);
/* 208 */           var7.func_75218_e();
/* 209 */           stackin.func_190918_g(res.func_190916_E());
/* 210 */           var5 = true;
/*     */           
/*     */           break;
/*     */         } 
/* 214 */         if (par4) {
/*     */           
/* 216 */           var6--;
/*     */           
/*     */           continue;
/*     */         } 
/* 220 */         var6++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 225 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 230 */   public void func_76316_a(IInventory invBasic) { func_75142_b(); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerHandMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */