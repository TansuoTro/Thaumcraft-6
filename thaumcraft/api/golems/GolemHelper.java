/*     */ package thaumcraft.api.golems;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GolemHelper
/*     */ {
/*  25 */   public static void registerSeal(ISeal seal) { ThaumcraftApi.internalMethods.registerSeal(seal); }
/*     */ 
/*     */ 
/*     */   
/*  29 */   public static ISeal getSeal(String key) { return ThaumcraftApi.internalMethods.getSeal(key); }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static ItemStack getSealStack(String key) { return ThaumcraftApi.internalMethods.getSealStack(key); }
/*     */ 
/*     */ 
/*     */   
/*  37 */   public static ISealEntity getSealEntity(int dim, SealPos pos) { return ThaumcraftApi.internalMethods.getSealEntity(dim, pos); }
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static void addGolemTask(int dim, Task task) { ThaumcraftApi.internalMethods.addGolemTask(dim, task); }
/*     */ 
/*     */   
/*  44 */   public static HashMap<Integer, ArrayList<ProvisionRequest>> provisionRequests = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   static final int LISTLIMIT = 1000;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void requestProvisioning(World world, ISealEntity seal, ItemStack stack) {
/*  54 */     if (!provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension())))
/*  55 */       provisionRequests.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList()); 
/*  56 */     ArrayList<ProvisionRequest> list = (ArrayList)provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  57 */     ProvisionRequest pr = new ProvisionRequest(seal, stack.func_77946_l());
/*  58 */     if (!list.contains(pr)) {
/*  59 */       list.add(pr);
/*     */     }
/*  61 */     if (list.size() > 1000) list.remove(0);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void requestProvisioning(World world, BlockPos pos, EnumFacing side, ItemStack stack) {
/*  72 */     if (!provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension())))
/*  73 */       provisionRequests.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList()); 
/*  74 */     ArrayList<ProvisionRequest> list = (ArrayList)provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  75 */     ProvisionRequest pr = new ProvisionRequest(pos, side, stack.func_77946_l());
/*  76 */     if (!list.contains(pr)) {
/*  77 */       list.add(pr);
/*     */     }
/*  79 */     if (list.size() > 1000) list.remove(0);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void requestProvisioning(World world, Entity entity, ItemStack stack) {
/*  89 */     if (!provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension())))
/*  90 */       provisionRequests.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList()); 
/*  91 */     ArrayList<ProvisionRequest> list = (ArrayList)provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  92 */     ProvisionRequest pr = new ProvisionRequest(entity, stack.func_77946_l());
/*  93 */     if (!list.contains(pr)) {
/*  94 */       list.add(pr);
/*     */     }
/*  96 */     if (list.size() > 1000) list.remove(0);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void requestProvisioning(World world, BlockPos pos, EnumFacing side, ItemStack stack, int ui) {
/* 108 */     if (!provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension())))
/* 109 */       provisionRequests.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList()); 
/* 110 */     ArrayList<ProvisionRequest> list = (ArrayList)provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 111 */     ProvisionRequest pr = new ProvisionRequest(pos, side, stack.func_77946_l());
/* 112 */     pr.setUI(ui);
/* 113 */     if (!list.contains(pr)) {
/* 114 */       list.add(pr);
/*     */     }
/* 116 */     if (list.size() > 1000) list.remove(0);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void requestProvisioning(World world, Entity entity, ItemStack stack, int ui) {
/* 127 */     if (!provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension())))
/* 128 */       provisionRequests.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList()); 
/* 129 */     ArrayList<ProvisionRequest> list = (ArrayList)provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 130 */     ProvisionRequest pr = new ProvisionRequest(entity, stack.func_77946_l());
/* 131 */     pr.setUI(ui);
/* 132 */     if (!list.contains(pr)) {
/* 133 */       list.add(pr);
/*     */     }
/* 135 */     if (list.size() > 1000) list.remove(0);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos getPosInArea(ISealEntity seal, int count) {
/* 146 */     int xx = 1 + (seal.getArea().func_177958_n() - 1) * (((seal.getSealPos()).face.func_82601_c() == 0) ? 2 : 1);
/* 147 */     int yy = 1 + (seal.getArea().func_177956_o() - 1) * (((seal.getSealPos()).face.func_96559_d() == 0) ? 2 : 1);
/* 148 */     int zz = 1 + (seal.getArea().func_177952_p() - 1) * (((seal.getSealPos()).face.func_82599_e() == 0) ? 2 : 1);
/*     */     
/* 150 */     int qx = ((seal.getSealPos()).face.func_82601_c() != 0) ? (seal.getSealPos()).face.func_82601_c() : 1;
/* 151 */     int qy = ((seal.getSealPos()).face.func_96559_d() != 0) ? (seal.getSealPos()).face.func_96559_d() : 1;
/* 152 */     int qz = ((seal.getSealPos()).face.func_82599_e() != 0) ? (seal.getSealPos()).face.func_82599_e() : 1;
/*     */     
/* 154 */     int y = qy * count / zz / xx % yy + (seal.getSealPos()).face.func_96559_d();
/* 155 */     int x = qx * count / zz % xx + (seal.getSealPos()).face.func_82601_c();
/* 156 */     int z = qz * count % zz + (seal.getSealPos()).face.func_82599_e();
/*     */     
/* 158 */     return (seal.getSealPos()).pos.func_177982_a(x - (
/* 159 */         ((seal.getSealPos()).face.func_82601_c() == 0) ? (xx / 2) : 0), y - (
/* 160 */         ((seal.getSealPos()).face.func_96559_d() == 0) ? (yy / 2) : 0), z - (
/* 161 */         ((seal.getSealPos()).face.func_82599_e() == 0) ? (zz / 2) : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB getBoundsForArea(ISealEntity seal) {
/* 173 */     return (new AxisAlignedBB(
/* 174 */         (seal.getSealPos()).pos.func_177958_n(), (seal.getSealPos()).pos.func_177956_o(), (seal.getSealPos()).pos.func_177952_p(), (
/* 175 */         (seal.getSealPos()).pos.func_177958_n() + 1), ((seal.getSealPos()).pos.func_177956_o() + 1), ((seal.getSealPos()).pos.func_177952_p() + 1)))
/* 176 */       .func_72317_d(
/* 177 */         (seal.getSealPos()).face.func_82601_c(), 
/* 178 */         (seal.getSealPos()).face.func_96559_d(), 
/* 179 */         (seal.getSealPos()).face.func_82599_e())
/* 180 */       .func_72321_a(
/* 181 */         ((seal.getSealPos()).face.func_82601_c() != 0) ? ((seal.getArea().func_177958_n() - 1) * (seal.getSealPos()).face.func_82601_c()) : 0.0D, 
/* 182 */         ((seal.getSealPos()).face.func_96559_d() != 0) ? ((seal.getArea().func_177956_o() - 1) * (seal.getSealPos()).face.func_96559_d()) : 0.0D, 
/* 183 */         ((seal.getSealPos()).face.func_82599_e() != 0) ? ((seal.getArea().func_177952_p() - 1) * (seal.getSealPos()).face.func_82599_e()) : 0.0D)
/* 184 */       .func_72314_b(
/* 185 */         ((seal.getSealPos()).face.func_82601_c() == 0) ? (seal.getArea().func_177958_n() - 1) : 0.0D, 
/* 186 */         ((seal.getSealPos()).face.func_96559_d() == 0) ? (seal.getArea().func_177956_o() - 1) : 0.0D, 
/* 187 */         ((seal.getSealPos()).face.func_82599_e() == 0) ? (seal.getArea().func_177952_p() - 1) : 0.0D);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\GolemHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */