/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.items.RechargeHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileRechargePedestal
/*     */   extends TileThaumcraftInventory
/*     */   implements IAspectContainer
/*     */ {
/*     */   public TileRechargePedestal() {
/*  25 */     super(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     this.counter = 0;
/*     */     this.syncedSlots = new int[] { 0 };
/*     */   } private static final int[] slots = { 0 };
/*     */   public void func_73660_a() {
/*  44 */     super.func_73660_a();
/*  45 */     if (!(func_145831_w()).field_72995_K && this.counter++ % 10 == 0 && func_70301_a(false) != null && 
/*  46 */       RechargeHelper.rechargeItem(func_145831_w(), func_70301_a(0), this.field_174879_c, null, 5) > 0.0F) {
/*  47 */       syncTile(false);
/*  48 */       func_70296_d();
/*  49 */       ArrayList<Aspect> al = Aspect.getPrimalAspects();
/*  50 */       this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 5, ((Aspect)al.get((func_145831_w()).field_73012_v.nextInt(al.size()))).getColor());
/*     */     } 
/*     */   } int counter;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() { return (new AxisAlignedBB(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1))).func_72314_b(2.0D, 2.0D, 2.0D); }
/*     */   public void setInventorySlotContentsFromInfusion(int par1, ItemStack stack2) {
/*  56 */     func_70299_a(par1, stack2);
/*  57 */     func_70296_d();
/*  58 */     if (!this.field_145850_b.field_72995_K) {
/*  59 */       syncTile(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public boolean func_94041_b(int par1, ItemStack stack) { return stack.func_77973_b() instanceof thaumcraft.api.items.IRechargable; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public int[] func_180463_a(EnumFacing side) { return slots; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public boolean func_180462_a(int par1, ItemStack stack, EnumFacing par3) { return stack.func_77973_b() instanceof thaumcraft.api.items.IRechargable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public boolean func_180461_b(int par1, ItemStack stack2, EnumFacing par3) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList getAspects() {
/*  88 */     ItemStack s = (this.field_145850_b == null || this.field_145850_b.field_72995_K) ? getSyncedStackInSlot(0) : func_70301_a(0);
/*  89 */     if (s != null && s.func_77973_b() instanceof thaumcraft.api.items.IRechargable) {
/*  90 */       float c = RechargeHelper.getCharge(s);
/*  91 */       return (new AspectList()).add(Aspect.ENERGY, Math.round(c));
/*     */     } 
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public int addToContainer(Aspect tag, int amount) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public boolean takeFromContainer(Aspect tag, int amount) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public boolean doesContainerContainAmount(Aspect tag, int amount) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public int containerContains(Aspect tag) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 146 */     if (i == 5) {
/*     */       
/* 148 */       if (this.field_145850_b.field_72995_K) {
/* 149 */         FXDispatcher.INSTANCE.visSparkle(this.field_174879_c
/* 150 */             .func_177958_n() + (func_145831_w()).field_73012_v.nextInt(3) - (func_145831_w()).field_73012_v.nextInt(3), this.field_174879_c
/* 151 */             .func_177984_a().func_177956_o() + (func_145831_w()).field_73012_v.nextInt(3), this.field_174879_c
/* 152 */             .func_177952_p() + (func_145831_w()).field_73012_v.nextInt(3) - (func_145831_w()).field_73012_v.nextInt(3), this.field_174879_c
/* 153 */             .func_177958_n(), this.field_174879_c.func_177984_a().func_177956_o(), this.field_174879_c.func_177952_p(), j);
/*     */       }
/* 155 */       return true;
/*     */     } 
/*     */     
/* 158 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileRechargePedestal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */