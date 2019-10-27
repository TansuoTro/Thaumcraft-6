/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.entities.EntityFluxRift;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileVoidSiphon
/*     */   extends TileThaumcraftInventory
/*     */ {
/*     */   public TileVoidSiphon() {
/*  20 */     super(1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  25 */     this.counter = 0;
/*  26 */     this.progress = 0;
/*     */     
/*  28 */     this.PROGREQ = 2000;
/*     */   }
/*     */   private static final int[] slots = { 0 }; int counter;
/*     */   public void func_73660_a() {
/*  32 */     super.func_73660_a();
/*  33 */     this.counter++;
/*  34 */     if (!(func_145831_w()).field_72995_K || !BlockStateUtils.isEnabled(func_145832_p()))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  49 */       if (!(func_145831_w()).field_72995_K && BlockStateUtils.isEnabled(func_145832_p()) && this.counter % 20 == 0 && this.progress < 2000 && (
/*     */         
/*  51 */         func_70301_a(0).func_190926_b() || (func_70301_a(false).func_77973_b() == ItemsTC.voidSeed && 
/*  52 */         func_70301_a(0).func_190916_E() < func_70301_a(0).func_77976_d()))) {
/*  53 */         List<EntityFluxRift> frl = getValidRifts();
/*  54 */         boolean b = false;
/*  55 */         for (EntityFluxRift fr : frl) {
/*  56 */           double d = Math.sqrt(fr.getRiftSize());
/*  57 */           this.progress = (int)(this.progress + d);
/*  58 */           fr.setRiftStability((float)(fr.getRiftStability() - d / 15.0D));
/*  59 */           if (this.field_145850_b.field_73012_v.nextInt(33) == 0) fr.setRiftSize(fr.getRiftSize() - 1); 
/*  60 */           b = (d >= 1.0D);
/*     */         } 
/*  62 */         if (b && this.counter % 40 == 0) this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 5, this.counter); 
/*  63 */         b = false;
/*  64 */         while (this.progress >= 2000 && (
/*  65 */           func_70301_a(0).func_190926_b() || (func_70301_a(false).func_77973_b() == ItemsTC.voidSeed && 
/*  66 */           func_70301_a(0).func_190916_E() < func_70301_a(0).func_77976_d()))) {
/*  67 */           this.progress -= 2000;
/*  68 */           if (func_70301_a(0).func_190926_b()) {
/*  69 */             func_70299_a(0, new ItemStack(ItemsTC.voidSeed));
/*     */           } else {
/*  71 */             func_70301_a(0).func_190920_e(func_70301_a(0).func_190916_E() + 1);
/*     */           } 
/*  73 */           b = true;
/*     */         } 
/*  75 */         if (b) {
/*  76 */           syncTile(false);
/*  77 */           func_70296_d();
/*     */         } 
/*     */       }  } 
/*     */   }
/*     */   public int progress; public final int PROGREQ = 2000;
/*     */   private List<EntityFluxRift> getValidRifts() {
/*  83 */     ArrayList<EntityFluxRift> ret = new ArrayList<EntityFluxRift>();
/*  84 */     List<EntityFluxRift> frl = EntityUtils.getEntitiesInRange(func_145831_w(), func_174877_v(), null, EntityFluxRift.class, 8.0D);
/*  85 */     for (EntityFluxRift fr : frl) {
/*  86 */       if (fr.field_70128_L || fr.getRiftSize() < 2)
/*  87 */         continue;  double xx = func_174877_v().func_177958_n() + 0.5D;
/*  88 */       double yy = (func_174877_v().func_177956_o() + 1);
/*  89 */       double zz = func_174877_v().func_177952_p() + 0.5D;
/*  90 */       Vec3d v1 = new Vec3d(xx, yy, zz);
/*  91 */       Vec3d v2 = new Vec3d(fr.field_70165_t, fr.field_70163_u, fr.field_70161_v);
/*  92 */       v1 = v1.func_178787_e(v2.func_178788_d(v1).func_72432_b());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (EntityUtils.canEntityBeSeen(fr, v1.field_72450_a, v1.field_72448_b, v1.field_72449_c)) {
/*  99 */         ret.add(fr);
/*     */       }
/*     */     } 
/* 102 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 107 */     super.func_145839_a(nbt);
/* 108 */     this.progress = nbt.func_74765_d("progress");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
/* 113 */     super.func_189515_b(nbt);
/* 114 */     nbt.func_74777_a("progress", (short)this.progress);
/* 115 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public boolean func_94041_b(int par1, ItemStack stack) { return (stack.func_77973_b() == ItemsTC.voidSeed); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public int[] func_180463_a(EnumFacing side) { return slots; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean func_180462_a(int par1, ItemStack stack, EnumFacing par3) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public boolean func_180461_b(int par1, ItemStack stack2, EnumFacing par3) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 144 */     if (i == 5) {
/*     */       
/* 146 */       if (this.field_145850_b.field_72995_K) {
/* 147 */         List<EntityFluxRift> frl = getValidRifts();
/* 148 */         for (EntityFluxRift fr : frl) {
/* 149 */           FXDispatcher.INSTANCE.voidStreak(fr.field_70165_t, fr.field_70163_u, fr.field_70161_v, 
/*     */               
/* 151 */               func_174877_v().func_177958_n() + 0.5D, (func_174877_v().func_177956_o() + 0.5625F), func_174877_v().func_177952_p() + 0.5D, j, 0.04F);
/*     */         }
/*     */       } 
/*     */       
/* 155 */       return true;
/*     */     } 
/*     */     
/* 158 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileVoidSiphon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */