/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.IInventoryChangedListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.container.slot.SlotOutput;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerPech
/*     */   extends Container
/*     */   implements IInventoryChangedListener
/*     */ {
/*     */   private EntityPech pech;
/*     */   private InventoryPech inventory;
/*     */   private EntityPlayer player;
/*     */   private final World theWorld;
/*     */   
/*     */   public ContainerPech(InventoryPlayer par1InventoryPlayer, World par3World, EntityPech par2IMerchant) {
/*  35 */     this.pech = par2IMerchant;
/*  36 */     this.theWorld = par3World;
/*  37 */     this.player = par1InventoryPlayer.field_70458_d;
/*  38 */     this.inventory = new InventoryPech(this, par1InventoryPlayer.field_70458_d, par2IMerchant);
/*  39 */     this.pech.trading = true;
/*  40 */     func_75146_a(new Slot(this.inventory, 0, 36, 29));
/*     */     
/*     */     int i;
/*     */     
/*  44 */     for (i = 0; i < 2; i++) {
/*  45 */       for (int j = 0; j < 2; j++) {
/*  46 */         func_75146_a(new SlotOutput(this.inventory, 1 + j + i * 2, 106 + 18 * j, 20 + 18 * i));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  51 */     for (i = 0; i < 3; i++) {
/*     */       
/*  53 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  55 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  59 */     for (i = 0; i < 9; i++)
/*     */     {
/*  61 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public InventoryPech getMerchantInventory() { return this.inventory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76316_a(IInventory invBasic) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2) {
/*  79 */     if (par2 == 0) {
/*  80 */       generateContents();
/*  81 */       return true;
/*     */     } 
/*  83 */     return super.func_75140_a(par1EntityPlayer, par2);
/*     */   }
/*     */   
/*     */   private boolean hasStuffInPack() {
/*  87 */     for (ItemStack stack : this.pech.loot) {
/*  88 */       if (stack != null && !stack.func_190926_b() && stack.func_190916_E() > 0)
/*  89 */         return true; 
/*     */     } 
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void generateContents() {
/*  96 */     if (!this.theWorld.field_72995_K && !this.inventory.func_70301_a(0).func_190926_b() && this.inventory
/*  97 */       .func_70301_a(1).func_190926_b() && this.inventory.func_70301_a(2).func_190926_b() && this.inventory
/*  98 */       .func_70301_a(3).func_190926_b() && this.inventory.func_70301_a(4).func_190926_b())
/*     */     {
/*     */       
/* 101 */       if (this.pech.isValued(this.inventory.func_70301_a(0))) {
/* 102 */         int value = this.pech.getValue(this.inventory.func_70301_a(0));
/* 103 */         if (this.theWorld.field_73012_v.nextInt(100) <= value / 2) {
/* 104 */           this.pech.setTamed(false);
/*     */           
/* 106 */           this.pech.func_184185_a(SoundsTC.pech_trade, 0.4F, 1.0F);
/*     */         } 
/*     */         
/* 109 */         if (this.theWorld.field_73012_v.nextInt(5) == 0) {
/* 110 */           value += this.theWorld.field_73012_v.nextInt(3);
/*     */         }
/* 112 */         else if (this.theWorld.field_73012_v.nextBoolean()) {
/* 113 */           value -= this.theWorld.field_73012_v.nextInt(3);
/*     */         } 
/*     */         
/* 116 */         this.pech; ArrayList<List> pos = (ArrayList)EntityPech.tradeInventory.get(Integer.valueOf(this.pech.getPechType()));
/* 117 */         while (value > 0) {
/* 118 */           int am = Math.min(5, Math.max((value + 1) / 2, this.theWorld.field_73012_v.nextInt(value) + 1));
/* 119 */           value -= am;
/*     */           
/* 121 */           if (am == 1 && this.theWorld.field_73012_v.nextBoolean() && hasStuffInPack()) {
/* 122 */             ArrayList<Integer> loot = new ArrayList<Integer>();
/* 123 */             for (int a = 0; a < this.pech.loot.size(); a++) {
/* 124 */               if (this.pech.loot.get(a) != null && !((ItemStack)this.pech.loot.get(a)).func_190926_b() && ((ItemStack)this.pech.loot.get(a)).func_190916_E() > 0)
/* 125 */                 loot.add(Integer.valueOf(a)); 
/*     */             } 
/* 127 */             int r = ((Integer)loot.get(this.theWorld.field_73012_v.nextInt(loot.size()))).intValue();
/* 128 */             ItemStack is = ((ItemStack)this.pech.loot.get(r)).func_77946_l();
/* 129 */             is.func_190920_e(1);
/*     */             
/* 131 */             addStack(is);
/* 132 */             ((ItemStack)this.pech.loot.get(r)).func_190918_g(1);
/* 133 */             if (((ItemStack)this.pech.loot.get(r)).func_190916_E() <= 0) this.pech.loot.set(r, ItemStack.field_190927_a);  continue;
/*     */           } 
/* 135 */           if (am >= 4 && this.theWorld.field_73012_v.nextBoolean()) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 153 */           List it = null;
/*     */           do {
/* 155 */             it = (List)pos.get(this.theWorld.field_73012_v.nextInt(pos.size()));
/* 156 */           } while (((Integer)it.get(0)).intValue() != am);
/* 157 */           ItemStack is = ((ItemStack)it.get(1)).func_77946_l();
/* 158 */           is.func_77980_a(this.theWorld, this.player, 0);
/* 159 */           addStack(is);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 164 */         this.inventory.func_70298_a(0, 1);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addStack(ItemStack s) {
/* 172 */     for (int a = 1; a < 5; a++) {
/* 173 */       if (this.inventory.func_70301_a(a).func_190926_b()) {
/* 174 */         this.inventory.func_70299_a(a, s); break;
/*     */       } 
/* 176 */       if (this.inventory.func_70301_a(a).func_77969_a(s) && this.inventory.func_70301_a(a).func_190916_E() + s.func_190916_E() < this.inventory.func_70301_a(a).func_77976_d()) {
/* 177 */         this.inventory.func_70301_a(a).func_190917_f(s.func_190916_E());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {}
/*     */ 
/*     */ 
/*     */   
/* 189 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.pech.isTamed(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
/* 198 */     ItemStack itemstack = ItemStack.field_190927_a;
/* 199 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 201 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/* 203 */       ItemStack itemstack1 = slot.func_75211_c();
/* 204 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 206 */       if (par2 == 0) {
/*     */         
/* 208 */         if (!func_75135_a(itemstack1, 5, 41, true))
/*     */         {
/* 210 */           return ItemStack.field_190927_a;
/*     */         
/*     */         }
/*     */       }
/* 214 */       else if (par2 >= 1 && par2 < 5) {
/*     */         
/* 216 */         if (!func_75135_a(itemstack1, 5, 41, true))
/*     */         {
/* 218 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/* 221 */       else if (par2 != 0) {
/*     */         
/* 223 */         if (par2 >= 5 && par2 < 41)
/*     */         {
/*     */           
/* 226 */           if (!func_75135_a(itemstack1, 0, 1, true))
/*     */           {
/* 228 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/* 233 */       if (itemstack1.func_190916_E() == 0) {
/*     */         
/* 235 */         slot.func_75215_d(ItemStack.field_190927_a);
/*     */       }
/*     */       else {
/*     */         
/* 239 */         slot.func_75218_e();
/*     */       } 
/*     */       
/* 242 */       if (itemstack1.func_190916_E() == itemstack.func_190916_E())
/*     */       {
/* 244 */         return ItemStack.field_190927_a;
/*     */       }
/* 246 */       slot.func_190901_a(par1EntityPlayer, itemstack1);
/*     */     } 
/*     */     
/* 249 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/* 258 */     super.func_75134_a(par1EntityPlayer);
/* 259 */     this.pech.trading = false;
/* 260 */     if (!this.theWorld.field_72995_K)
/*     */     {
/* 262 */       for (int a = 0; a < 5; a++) {
/* 263 */         ItemStack itemstack = this.inventory.func_70304_b(a);
/* 264 */         if (itemstack != null) {
/*     */           
/* 266 */           EntityItem ei = par1EntityPlayer.func_71019_a(itemstack, false);
/* 267 */           if (ei != null)
/* 268 */             ei.func_145799_b("PechDrop"); 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */