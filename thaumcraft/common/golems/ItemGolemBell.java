/*     */ package thaumcraft.common.golems;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.golems.ISealDisplayer;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class ItemGolemBell
/*     */   extends ItemTCBase
/*     */   implements ISealDisplayer
/*     */ {
/*     */   public ItemGolemBell() {
/*  31 */     super("golem_bell", new String[0]);
/*  32 */     func_77627_a(false);
/*  33 */     func_77625_d(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand hand) {
/*  38 */     playerIn.func_184609_a(hand);
/*  39 */     if (!worldIn.field_72995_K) {
/*  40 */       RayTraceResult mop = RayTracer.retrace(playerIn);
/*  41 */       if (mop == null || (mop.field_72313_a != RayTraceResult.Type.BLOCK && mop.field_72313_a != RayTraceResult.Type.ENTITY)) {
/*  42 */         if (playerIn.func_70093_af() && ThaumcraftCapabilities.knowsResearch(playerIn, new String[] { "GOLEMLOGISTICS" })) {
/*  43 */           playerIn.openGui(Thaumcraft.instance, 20, playerIn.field_70170_p, (int)playerIn.field_70165_t, (int)playerIn.field_70163_u, (int)playerIn.field_70161_v);
/*  44 */           return new ActionResult(EnumActionResult.FAIL, playerIn.func_184586_b(hand));
/*     */         } 
/*     */       } else {
/*  47 */         ISealEntity se = getSeal(playerIn);
/*  48 */         if (se != null) {
/*  49 */           if (playerIn.func_70093_af()) {
/*  50 */             SealHandler.removeSealEntity(playerIn.field_70170_p, se.getSealPos(), false);
/*  51 */             worldIn.func_184133_a(null, (se.getSealPos()).pos, SoundsTC.zap, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*     */           } else {
/*  53 */             playerIn.openGui(Thaumcraft.instance, 18, playerIn.field_70170_p, 
/*  54 */                 (se.getSealPos()).pos.func_177958_n(), (se.getSealPos()).pos.func_177956_o(), (se.getSealPos()).pos.func_177952_p());
/*     */           } 
/*     */         }
/*  57 */         return new ActionResult(EnumActionResult.FAIL, playerIn.func_184586_b(hand));
/*     */       } 
/*     */     } else {
/*  60 */       playerIn.func_184185_a(SoundEvents.field_187604_bf, 0.6F, 1.0F + worldIn.field_73012_v.nextFloat() * 0.1F);
/*     */     } 
/*  62 */     return super.func_77659_a(worldIn, playerIn, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/*  70 */     player.func_184609_a(hand);
/*  71 */     if (!world.field_72995_K) {
/*  72 */       ISealEntity se = SealHandler.getSealEntity(world.field_73011_w.getDimension(), new SealPos(pos, side));
/*  73 */       if (se != null) {
/*  74 */         if (player.func_70093_af()) {
/*  75 */           SealHandler.removeSealEntity(world, se.getSealPos(), false);
/*  76 */           world.func_184133_a(null, pos, SoundsTC.zap, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*     */         } else {
/*  78 */           player.openGui(Thaumcraft.instance, 18, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */         } 
/*  80 */         return EnumActionResult.SUCCESS;
/*     */       } 
/*  82 */       if (player.func_70093_af() && ThaumcraftCapabilities.knowsResearch(player, new String[] { "GOLEMLOGISTICS" })) {
/*  83 */         player.openGui(Thaumcraft.instance, 20, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*  84 */         return EnumActionResult.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     return EnumActionResult.PASS;
/*     */   }
/*     */   
/*     */   public static ISealEntity getSeal(EntityPlayer playerIn) {
/*  92 */     float f = playerIn.field_70125_A;
/*  93 */     float f1 = playerIn.field_70177_z;
/*  94 */     double d0 = playerIn.field_70165_t;
/*  95 */     double d1 = playerIn.field_70163_u + playerIn.func_70047_e();
/*  96 */     double d2 = playerIn.field_70161_v;
/*  97 */     Vec3d vec0 = new Vec3d(d0, d1, d2);
/*  98 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/*  99 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 100 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 101 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 102 */     float f6 = f3 * f4;
/* 103 */     float f7 = f2 * f4;
/* 104 */     double d3 = 5.0D;
/* 105 */     Vec3d vec1 = vec0.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/* 106 */     Vec3d vec2 = new Vec3d(f6 * d3, f5 * d3, f7 * d3);
/*     */     
/* 108 */     Vec3d vec3 = vec0.func_72441_c(vec2.field_72450_a / 10.0D, vec2.field_72448_b / 10.0D, vec2.field_72449_c / 10.0D);
/*     */     
/* 110 */     for (int a = 0; a < vec2.func_72433_c() * 10.0D; a++) {
/* 111 */       BlockPos pos = new BlockPos(vec3);
/* 112 */       RayTraceResult mop = collisionRayTrace(playerIn.field_70170_p, pos, vec0, vec1);
/* 113 */       if (mop != null) {
/* 114 */         ISealEntity se = SealHandler.getSealEntity(playerIn.field_70170_p.field_73011_w.getDimension(), new SealPos(pos, mop.field_178784_b));
/* 115 */         if (se != null) {
/* 116 */           return se;
/*     */         }
/*     */       } 
/* 119 */       vec3 = vec3.func_72441_c(vec2.field_72450_a / 10.0D, vec2.field_72448_b / 10.0D, vec2.field_72449_c / 10.0D);
/*     */     } 
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   private static boolean isVecInsideYZBounds(Vec3d point, BlockPos pos) { return (point == null) ? false : ((point.field_72448_b >= pos.func_177956_o() && point.field_72448_b <= (pos.func_177956_o() + 1) && point.field_72449_c >= pos.func_177952_p() && point.field_72449_c <= (pos.func_177952_p() + 1))); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   private static boolean isVecInsideXZBounds(Vec3d point, BlockPos pos) { return (point == null) ? false : ((point.field_72450_a >= pos.func_177958_n() && point.field_72450_a <= (pos.func_177958_n() + 1) && point.field_72449_c >= pos.func_177952_p() && point.field_72449_c <= (pos.func_177952_p() + 1))); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   private static boolean isVecInsideXYBounds(Vec3d point, BlockPos pos) { return (point == null) ? false : ((point.field_72450_a >= pos.func_177958_n() && point.field_72450_a <= (pos.func_177958_n() + 1) && point.field_72448_b >= pos.func_177956_o() && point.field_72448_b <= (pos.func_177956_o() + 1))); }
/*     */ 
/*     */ 
/*     */   
/*     */   private static RayTraceResult collisionRayTrace(World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
/* 142 */     Vec3d vec3 = start.func_72429_b(end, pos.func_177958_n());
/* 143 */     Vec3d vec31 = start.func_72429_b(end, (pos.func_177958_n() + 1));
/* 144 */     Vec3d vec32 = start.func_72435_c(end, pos.func_177956_o());
/* 145 */     Vec3d vec33 = start.func_72435_c(end, (pos.func_177956_o() + 1));
/* 146 */     Vec3d vec34 = start.func_72434_d(end, pos.func_177952_p());
/* 147 */     Vec3d vec35 = start.func_72434_d(end, (pos.func_177952_p() + 1));
/*     */     
/* 149 */     if (!isVecInsideYZBounds(vec3, pos))
/*     */     {
/* 151 */       vec3 = null;
/*     */     }
/*     */     
/* 154 */     if (!isVecInsideYZBounds(vec31, pos))
/*     */     {
/* 156 */       vec31 = null;
/*     */     }
/*     */     
/* 159 */     if (!isVecInsideXZBounds(vec32, pos))
/*     */     {
/* 161 */       vec32 = null;
/*     */     }
/*     */     
/* 164 */     if (!isVecInsideXZBounds(vec33, pos))
/*     */     {
/* 166 */       vec33 = null;
/*     */     }
/*     */     
/* 169 */     if (!isVecInsideXYBounds(vec34, pos))
/*     */     {
/* 171 */       vec34 = null;
/*     */     }
/*     */     
/* 174 */     if (!isVecInsideXYBounds(vec35, pos))
/*     */     {
/* 176 */       vec35 = null;
/*     */     }
/*     */     
/* 179 */     Vec3d vec36 = null;
/*     */     
/* 181 */     if (vec3 != null && (vec36 == null || start.func_72436_e(vec3) < start.func_72436_e(vec36)))
/*     */     {
/* 183 */       vec36 = vec3;
/*     */     }
/*     */     
/* 186 */     if (vec31 != null && (vec36 == null || start.func_72436_e(vec31) < start.func_72436_e(vec36)))
/*     */     {
/* 188 */       vec36 = vec31;
/*     */     }
/*     */     
/* 191 */     if (vec32 != null && (vec36 == null || start.func_72436_e(vec32) < start.func_72436_e(vec36)))
/*     */     {
/* 193 */       vec36 = vec32;
/*     */     }
/*     */     
/* 196 */     if (vec33 != null && (vec36 == null || start.func_72436_e(vec33) < start.func_72436_e(vec36)))
/*     */     {
/* 198 */       vec36 = vec33;
/*     */     }
/*     */     
/* 201 */     if (vec34 != null && (vec36 == null || start.func_72436_e(vec34) < start.func_72436_e(vec36)))
/*     */     {
/* 203 */       vec36 = vec34;
/*     */     }
/*     */     
/* 206 */     if (vec35 != null && (vec36 == null || start.func_72436_e(vec35) < start.func_72436_e(vec36)))
/*     */     {
/* 208 */       vec36 = vec35;
/*     */     }
/*     */     
/* 211 */     if (vec36 == null)
/*     */     {
/* 213 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 217 */     EnumFacing enumfacing = null;
/*     */     
/* 219 */     if (vec36 == vec3)
/*     */     {
/* 221 */       enumfacing = EnumFacing.WEST;
/*     */     }
/*     */     
/* 224 */     if (vec36 == vec31)
/*     */     {
/* 226 */       enumfacing = EnumFacing.EAST;
/*     */     }
/*     */     
/* 229 */     if (vec36 == vec32)
/*     */     {
/* 231 */       enumfacing = EnumFacing.DOWN;
/*     */     }
/*     */     
/* 234 */     if (vec36 == vec33)
/*     */     {
/* 236 */       enumfacing = EnumFacing.UP;
/*     */     }
/*     */     
/* 239 */     if (vec36 == vec34)
/*     */     {
/* 241 */       enumfacing = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 244 */     if (vec36 == vec35)
/*     */     {
/* 246 */       enumfacing = EnumFacing.SOUTH;
/*     */     }
/*     */     
/* 249 */     return new RayTraceResult(vec36.func_72441_c(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), enumfacing, pos);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ItemGolemBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */