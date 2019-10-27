/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.IInventoryChangedListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.container.slot.SlotLimitedByClass;
/*     */ import thaumcraft.common.items.casters.ItemFocusPouch;
/*     */ 
/*     */ public class ContainerFocusPouch
/*     */   extends Container implements IInventoryChangedListener {
/*     */   private World worldObj;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*     */   
/*     */   public ContainerFocusPouch(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5) {
/*  24 */     this.input = new InventoryFocusPouch(this);
/*  25 */     this.pouch = null;
/*  26 */     this.player = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.worldObj = par2World;
/*  32 */     this.posX = par3;
/*  33 */     this.posY = par4;
/*  34 */     this.posZ = par5;
/*  35 */     this.player = iinventory.field_70458_d;
/*  36 */     this.pouch = iinventory.func_70448_g();
/*  37 */     this.blockSlot = iinventory.field_70461_c + 45;
/*     */     
/*  39 */     for (a = 0; a < 18; a++) {
/*  40 */       func_75146_a(new SlotLimitedByClass(thaumcraft.common.items.casters.ItemFocus.class, this.input, a, 37 + a % 6 * 18, 51 + a / 6 * 18));
/*     */     }
/*  42 */     bindPlayerInventory(iinventory);
/*     */     
/*  44 */     if (!par2World.field_72995_K)
/*     */       try {
/*  46 */         NonNullList<ItemStack> list = ((ItemFocusPouch)this.pouch.func_77973_b()).getInventory(this.pouch);
/*  47 */         for (int a = 0; a < list.size(); a++)
/*  48 */           this.input.func_70299_a(a, (ItemStack)list.get(a)); 
/*  49 */       } catch (Exception a) {
/*     */         Exception exception;
/*     */       }  
/*  52 */     func_75130_a(this.input);
/*     */   }
/*     */   private int blockSlot; public IInventory input; ItemStack pouch;
/*     */   EntityPlayer player;
/*     */   
/*  57 */   public void func_76316_a(IInventory invBasic) { func_75142_b(); }
/*     */ 
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  61 */     for (int i = 0; i < 3; i++) {
/*  62 */       for (int j = 0; j < 9; j++) {
/*  63 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  68 */     for (int i = 0; i < 9; i++) {
/*  69 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 209));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/*  78 */     if (slot == this.blockSlot) return ItemStack.field_190927_a; 
/*  79 */     ItemStack stack = ItemStack.field_190927_a;
/*  80 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */ 
/*     */     
/*  83 */     if (slotObject != null && slotObject.func_75216_d()) {
/*  84 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  85 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  87 */       if (slot < 18) {
/*  88 */         if (!this.input.func_94041_b(slot, stackInSlot) || 
/*  89 */           !func_75135_a(stackInSlot, 18, this.field_75151_b.size(), true)) {
/*  90 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/*  93 */       else if (!this.input.func_94041_b(slot, stackInSlot) || !func_75135_a(stackInSlot, 0, 18, false)) {
/*  94 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/*  97 */       if (stackInSlot.func_190916_E() == 0) {
/*  98 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 100 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public boolean func_75145_c(EntityPlayer var1) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
/* 114 */     if (slotId == this.blockSlot) return ItemStack.field_190927_a; 
/* 115 */     return super.func_184996_a(slotId, dragType, clickTypeIn, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/* 121 */     super.func_75134_a(par1EntityPlayer);
/* 122 */     if (!this.worldObj.field_72995_K) {
/*     */       
/* 124 */       NonNullList<ItemStack> list = NonNullList.func_191197_a(18, ItemStack.field_190927_a);
/* 125 */       for (int a = 0; a < list.size(); a++)
/* 126 */         list.set(a, this.input.func_70301_a(a)); 
/* 127 */       if (this.pouch.func_77973_b() instanceof ItemFocusPouch)
/* 128 */         ((ItemFocusPouch)this.pouch.func_77973_b()).setInventory(this.pouch, list); 
/* 129 */       if (this.player == null)
/* 130 */         return;  if (this.player.func_184586_b(this.player.func_184600_cs()).func_77969_a(this.pouch))
/* 131 */         this.player.func_184611_a(this.player.func_184600_cs(), this.pouch); 
/* 132 */       this.player.field_71071_by.func_70296_d();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerFocusPouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */