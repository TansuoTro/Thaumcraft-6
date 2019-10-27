/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ public class TilePatternCrafter
/*     */   extends TileThaumcraft
/*     */   implements ITickable
/*     */ {
/*  34 */   public byte type = 0;
/*  35 */   public int count = (new Random(System.currentTimeMillis())).nextInt(20);
/*     */   
/*  37 */   private final InventoryCrafting craftMatrix = new InventoryCrafting(new Container(this)
/*     */       {
/*  39 */         public boolean func_75145_c(EntityPlayer playerIn) { return false; }
/*     */       },  3, 3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public void readSyncNBT(NBTTagCompound nbt) { this.type = nbt.func_74771_c("type"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
/*  54 */     nbt.func_74774_a("type", this.type);
/*  55 */     return nbt;
/*     */   }
/*     */   
/*  58 */   float power = 0.0F; public float rot;
/*     */   public float rp;
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  62 */     this.power = nbt.func_74760_g("power");
/*  63 */     super.func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
/*  68 */     nbt.func_74776_a("power", this.power);
/*  69 */     return super.func_189515_b(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public int rotTicks = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  78 */     if (this.field_145850_b.field_72995_K) {
/*  79 */       if (this.rotTicks > 0) {
/*  80 */         this.rotTicks--;
/*  81 */         if (this.rotTicks % Math.floor(Math.max(1.0F, this.rp)) == 0.0D) {
/*  82 */           this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, SoundsTC.clack, SoundCategory.BLOCKS, 0.2F, 1.7F, false);
/*     */         }
/*  84 */         this.rp++;
/*     */       } else {
/*  86 */         this.rp *= 0.8F;
/*     */       } 
/*  88 */       this.rot += this.rp;
/*     */     } 
/*  90 */     if (!this.field_145850_b.field_72995_K && this.count++ % 20 == 0 && BlockStateUtils.isEnabled(func_145832_p())) {
/*     */       
/*  92 */       if (this.power <= 0.0F) {
/*  93 */         this.power += AuraHelper.drainVis(func_145831_w(), func_174877_v(), 5.0F, false);
/*     */       }
/*     */       
/*  96 */       int amt = 9;
/*  97 */       switch (this.type) {
/*     */         case 0:
/*  99 */           amt = 9;
/*     */           break;
/*     */         case 1:
/* 102 */           amt = 1;
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/* 106 */           amt = 2;
/*     */           break;
/*     */         case 4:
/* 109 */           amt = 4;
/*     */           break;
/*     */         case 5:
/*     */         case 6:
/* 113 */           amt = 3;
/*     */           break;
/*     */         case 7:
/*     */         case 8:
/* 117 */           amt = 6;
/*     */           break;
/*     */         case 9:
/* 120 */           amt = 8;
/*     */           break;
/*     */       } 
/*     */       
/* 124 */       IItemHandler above = ThaumcraftInvHelper.getItemHandlerAt(func_145831_w(), func_174877_v().func_177984_a(), EnumFacing.DOWN);
/* 125 */       IItemHandler below = ThaumcraftInvHelper.getItemHandlerAt(func_145831_w(), func_174877_v().func_177977_b(), EnumFacing.UP);
/*     */       
/* 127 */       if (above != null && below != null)
/*     */       {
/*     */         
/* 130 */         for (int a = 0; a < above.getSlots(); a++) {
/* 131 */           ItemStack testStack = above.getStackInSlot(a).func_77946_l();
/* 132 */           if (!testStack.func_190926_b()) {
/*     */             
/* 134 */             testStack.func_190920_e(amt);
/*     */             
/* 136 */             if (InventoryUtils.removeStackFrom(func_145831_w(), func_174877_v().func_177984_a(), EnumFacing.DOWN, testStack.func_77946_l(), ThaumcraftInvHelper.InvFilter.BASEORE, true).func_190916_E() == amt)
/*     */             {
/* 138 */               if (craft(testStack) && this.power >= 1.0F)
/*     */               {
/* 140 */                 if (ItemHandlerHelper.insertItem(below, this.outStack.func_77946_l(), true).func_190926_b()) {
/* 141 */                   boolean b = true;
/* 142 */                   for (int i = 0; i < 9; i++) {
/* 143 */                     if (this.craftMatrix.func_70301_a(i) != null && 
/* 144 */                       !ItemHandlerHelper.insertItem(below, this.craftMatrix.func_70301_a(i).func_77946_l(), true).func_190926_b()) {
/* 145 */                       b = false;
/*     */                       break;
/*     */                     } 
/*     */                   } 
/* 149 */                   if (b) {
/* 150 */                     ItemHandlerHelper.insertItem(below, this.outStack.func_77946_l(), false);
/* 151 */                     for (int i = 0; i < 9; i++) {
/* 152 */                       if (this.craftMatrix.func_70301_a(i) != null)
/* 153 */                         ItemHandlerHelper.insertItem(below, this.craftMatrix.func_70301_a(i).func_77946_l(), false); 
/* 154 */                     }  InventoryUtils.removeStackFrom(func_145831_w(), func_174877_v().func_177984_a(), EnumFacing.DOWN, testStack, ThaumcraftInvHelper.InvFilter.BASEORE, false);
/* 155 */                     this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, 0);
/* 156 */                     this.power--;
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/* 168 */   ItemStack outStack = null;
/*     */   private boolean craft(ItemStack inStack) {
/*     */     int a, a, a, a, a, a, a, a, a;
/* 171 */     this.outStack = ItemStack.field_190927_a;
/* 172 */     this.craftMatrix.func_174888_l();
/* 173 */     switch (this.type) {
/*     */       case 0:
/* 175 */         for (a = 0; a < 9; a++)
/* 176 */           this.craftMatrix.func_70299_a(a, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         break;
/*     */       case 1:
/* 179 */         this.craftMatrix.func_70299_a(0, ItemHandlerHelper.copyStackWithSize(inStack, 1));
/*     */         break;
/*     */       case 2:
/* 182 */         for (a = 0; a < 2; a++)
/* 183 */           this.craftMatrix.func_70299_a(a, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         break;
/*     */       case 3:
/* 186 */         for (a = 0; a < 2; a++)
/* 187 */           this.craftMatrix.func_70299_a(a * 3, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         break;
/*     */       case 4:
/* 190 */         for (a = 0; a < 2; a++) {
/* 191 */           for (int b = 0; b < 2; b++)
/* 192 */             this.craftMatrix.func_70299_a(a + b * 3, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         }  break;
/*     */       case 5:
/* 195 */         for (a = 0; a < 3; a++)
/* 196 */           this.craftMatrix.func_70299_a(a, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         break;
/*     */       case 6:
/* 199 */         for (a = 0; a < 3; a++)
/* 200 */           this.craftMatrix.func_70299_a(a * 3, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         break;
/*     */       case 7:
/* 203 */         for (a = 0; a < 6; a++)
/* 204 */           this.craftMatrix.func_70299_a(a, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         break;
/*     */       case 8:
/* 207 */         for (a = 0; a < 2; a++) {
/* 208 */           for (int b = 0; b < 3; b++)
/* 209 */             this.craftMatrix.func_70299_a(a + b * 3, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         }  break;
/*     */       case 9:
/* 212 */         for (a = 0; a < 9; a++) {
/* 213 */           if (a != 4)
/* 214 */             this.craftMatrix.func_70299_a(a, ItemHandlerHelper.copyStackWithSize(inStack, 1)); 
/*     */         } 
/*     */         break;
/*     */     } 
/* 218 */     IRecipe ir = CraftingManager.func_192413_b(this.craftMatrix, this.field_145850_b);
/*     */ 
/*     */ 
/*     */     
/* 222 */     if (ir == null) {
/* 223 */       return false;
/*     */     }
/* 225 */     this.outStack = ir.func_77572_b(this.craftMatrix);
/*     */     
/* 227 */     NonNullList<ItemStack> aitemstack = CraftingManager.func_180303_b(this.craftMatrix, this.field_145850_b);
/* 228 */     for (int i = 0; i < aitemstack.size(); i++) {
/* 229 */       ItemStack itemstack1 = this.craftMatrix.func_70301_a(i);
/* 230 */       ItemStack itemstack2 = (ItemStack)aitemstack.get(i);
/*     */       
/* 232 */       if (!itemstack1.func_190926_b()) {
/* 233 */         this.craftMatrix.func_70299_a(i, ItemStack.field_190927_a);
/*     */       }
/*     */       
/* 236 */       if (!itemstack1.func_190926_b() && this.craftMatrix.func_70301_a(i).func_190926_b()) {
/* 237 */         this.craftMatrix.func_70299_a(i, itemstack2);
/*     */       }
/*     */     } 
/*     */     
/* 241 */     return !this.outStack.func_190926_b();
/*     */   }
/*     */   
/*     */   public void cycle() {
/* 245 */     this.type = (byte)(this.type + 1);
/* 246 */     if (this.type > 9)
/* 247 */       this.type = 0; 
/* 248 */     syncTile(false);
/* 249 */     func_70296_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 254 */     if (i == 1) {
/* 255 */       if (this.field_145850_b.field_72995_K) {
/* 256 */         this.rotTicks = 10;
/*     */       }
/*     */       
/* 259 */       return true;
/*     */     } 
/* 261 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ 
/*     */   
/* 265 */   public RayTraceResult rayTrace(World world, Vec3d vec3d, Vec3d vec3d1, RayTraceResult fullblock) { return fullblock; }
/*     */ 
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids) {
/* 269 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 270 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(0), getCuboidByFacing(facing)));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboidByFacing(EnumFacing facing) {
/* 274 */     switch (facing)
/*     */     { default:
/* 276 */         return new Cuboid6(func_174877_v().func_177958_n() + 0.75D, func_174877_v().func_177956_o() + 0.125D, func_174877_v().func_177952_p() + 0.375D, 
/* 277 */             func_174877_v().func_177958_n() + 0.875D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.625D);
/*     */       case EAST:
/* 279 */         return new Cuboid6(func_174877_v().func_177958_n() + 0.125D, func_174877_v().func_177956_o() + 0.125D, func_174877_v().func_177952_p() + 0.375D, 
/* 280 */             func_174877_v().func_177958_n() + 0.25D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.625D);
/*     */       case NORTH:
/* 282 */         return new Cuboid6(func_174877_v().func_177958_n() + 0.375D, func_174877_v().func_177956_o() + 0.125D, func_174877_v().func_177952_p() + 0.75D, 
/* 283 */             func_174877_v().func_177958_n() + 0.625D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.875D);
/*     */       case SOUTH:
/* 285 */         break; }  return new Cuboid6(func_174877_v().func_177958_n() + 0.375D, func_174877_v().func_177956_o() + 0.125D, func_174877_v().func_177952_p() + 0.125D, 
/* 286 */         func_174877_v().func_177958_n() + 0.625D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.25D);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TilePatternCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */