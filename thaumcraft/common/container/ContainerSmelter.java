/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IContainerListener;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.container.slot.SlotLimitedHasAspects;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.essentia.TileSmelter;
/*     */ 
/*     */ 
/*     */ public class ContainerSmelter
/*     */   extends Container
/*     */ {
/*     */   private TileSmelter furnace;
/*     */   private int lastCookTime;
/*     */   private int lastBurnTime;
/*     */   private int lastItemBurnTime;
/*     */   private int lastVis;
/*     */   private int lastSmelt;
/*     */   private int lastFlux;
/*     */   
/*     */   public ContainerSmelter(InventoryPlayer par1InventoryPlayer, TileSmelter tileEntity) {
/*  29 */     this.furnace = tileEntity;
/*  30 */     func_75146_a(new SlotLimitedHasAspects(tileEntity, 0, 80, 8));
/*  31 */     func_75146_a(new Slot(tileEntity, 1, 80, 48));
/*     */     
/*     */     int i;
/*  34 */     for (i = 0; i < 3; i++) {
/*     */       
/*  36 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  38 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  42 */     for (i = 0; i < 9; i++)
/*     */     {
/*  44 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75132_a(IContainerListener listener) {
/*  52 */     super.func_75132_a(listener);
/*  53 */     listener.func_175173_a(this, this.furnace);
/*  54 */     listener.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*  55 */     listener.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*  56 */     listener.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*  57 */     listener.func_71112_a(this, 3, this.furnace.vis);
/*  58 */     listener.func_71112_a(this, 4, this.furnace.smeltTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  65 */     super.func_75142_b();
/*     */     
/*  67 */     for (int i = 0; i < this.field_75149_d.size(); i++) {
/*     */       
/*  69 */       IContainerListener icrafting = (IContainerListener)this.field_75149_d.get(i);
/*     */       
/*  71 */       if (this.lastCookTime != this.furnace.furnaceCookTime)
/*     */       {
/*  73 */         icrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*     */       }
/*     */       
/*  76 */       if (this.lastBurnTime != this.furnace.furnaceBurnTime)
/*     */       {
/*  78 */         icrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*     */       }
/*     */       
/*  81 */       if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
/*     */       {
/*  83 */         icrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*     */       }
/*     */       
/*  86 */       if (this.lastVis != this.furnace.vis)
/*     */       {
/*  88 */         icrafting.func_71112_a(this, 3, this.furnace.vis);
/*     */       }
/*     */       
/*  91 */       if (this.lastSmelt != this.furnace.smeltTime)
/*     */       {
/*  93 */         icrafting.func_71112_a(this, 4, this.furnace.smeltTime);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  99 */     this.lastCookTime = this.furnace.furnaceCookTime;
/* 100 */     this.lastBurnTime = this.furnace.furnaceBurnTime;
/* 101 */     this.lastItemBurnTime = this.furnace.currentItemBurnTime;
/* 102 */     this.lastVis = this.furnace.vis;
/* 103 */     this.lastSmelt = this.furnace.smeltTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {
/* 110 */     if (par1 == 0)
/*     */     {
/* 112 */       this.furnace.furnaceCookTime = par2;
/*     */     }
/*     */     
/* 115 */     if (par1 == 1)
/*     */     {
/* 117 */       this.furnace.furnaceBurnTime = par2;
/*     */     }
/*     */     
/* 120 */     if (par1 == 2)
/*     */     {
/* 122 */       this.furnace.currentItemBurnTime = par2;
/*     */     }
/*     */     
/* 125 */     if (par1 == 3)
/*     */     {
/* 127 */       this.furnace.vis = par2;
/*     */     }
/*     */     
/* 130 */     if (par1 == 4)
/*     */     {
/* 132 */       this.furnace.smeltTime = par2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return this.furnace.func_70300_a(par1EntityPlayer); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
/* 146 */     ItemStack itemstack = ItemStack.field_190927_a;
/* 147 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 149 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/* 151 */       ItemStack itemstack1 = slot.func_75211_c();
/* 152 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 154 */       if (par2 != 1 && par2 != 0) {
/*     */         
/* 156 */         AspectList al = ThaumcraftCraftingManager.getObjectTags(itemstack1);
/*     */         
/* 158 */         if (TileSmelter.isItemFuel(itemstack1))
/*     */         {
/* 160 */           if (!func_75135_a(itemstack1, 1, 2, false))
/*     */           {
/* 162 */             if (!func_75135_a(itemstack1, 0, 1, false))
/*     */             {
/* 164 */               return ItemStack.field_190927_a;
/*     */             }
/*     */           }
/*     */         }
/* 168 */         else if (al != null && al.size() > 0)
/*     */         {
/* 170 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/* 172 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/* 175 */         else if (par2 >= 2 && par2 < 29)
/*     */         {
/* 177 */           if (!func_75135_a(itemstack1, 29, 38, false))
/*     */           {
/* 179 */             return ItemStack.field_190927_a;
/*     */           }
/*     */         }
/* 182 */         else if (par2 >= 29 && par2 < 38 && !func_75135_a(itemstack1, 2, 29, false))
/*     */         {
/* 184 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       
/* 187 */       } else if (!func_75135_a(itemstack1, 2, 38, false)) {
/*     */         
/* 189 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/* 192 */       if (itemstack1.func_190916_E() == 0) {
/*     */         
/* 194 */         slot.func_75215_d(ItemStack.field_190927_a);
/*     */       }
/*     */       else {
/*     */         
/* 198 */         slot.func_75218_e();
/*     */       } 
/*     */       
/* 201 */       if (itemstack1.func_190916_E() == itemstack.func_190916_E())
/*     */       {
/* 203 */         return ItemStack.field_190927_a;
/*     */       }
/*     */       
/* 206 */       slot.func_190901_a(par1EntityPlayer, itemstack1);
/*     */     } 
/*     */     
/* 209 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerSmelter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */